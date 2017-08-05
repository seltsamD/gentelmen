var inProgress = false;
var page = 0;
$(document).ready(function () {
    var url = window.location.href;
    var param = url.substr(url.lastIndexOf("/") + 1);
    if ($.isNumeric(param))
        loadContentWithCategory(param, 0);
    else
        loadContent(0);

    $('#goods').on('click', '#loader', function () {
        $(this).closest('div').remove();
        var param = url.substr(url.lastIndexOf("/") + 1);
        if ($.isNumeric(param))
            loadContentWithCategory(param, page);
        else
            loadContent(page);
    });

});

function loadContent(countPage) {
    $.ajax({
        contentType: 'application/json; charset=utf-8',
        type: "GET",
        url: "/api/good/" + countPage,
        dataType: 'json',
        beforeSend: function () {
            inProgress = true;
        },
        success: function (data) {
            setData(data);
        },
        error: function (xhr, ajaxOptions, thrownError) {
            console.log("ERROR:" + xhr.responseText + " - " + thrownError)
        },
        done: function () {
            inProgress = false;
        }
    });
}

function loadContentWithCategory(category, countPage) {
    $.ajax({
        contentType: 'application/json; charset=utf-8',
        type: "GET",
        url: "/api/good/category/" + category + "/" + countPage,
        dataType: 'json',
        beforeSend: function () {
            inProgress = true;
        },
        success: function (data) {
            setData(data);
        },
        error: function (xhr, ajaxOptions, thrownError) {
            console.log("ERROR:" + xhr.responseText + " - " + thrownError)
        },
        done: function () {
            inProgress = false;
        }
    });
}

function setData(data) {
    var obj = jQuery.parseJSON(JSON.stringify(data));
    $.each(obj, function (key, value) {
        var div = "<div class='col-lg-2 col-md-3 col-sm-4 col-xs-6 marginImg miniGood'>";
        div = div + "<img src='/images/" + value.id + "_0_mini.jpg'>";
        div = div + "<div class='miniText'><a href='" + value.href + "'>" + value.name + "</a>";
        div = div + "<div class='btnForm'><form id='baskForm" + value.id + "'  action='addToBasket' method='POST'>";
        div = div + "<input type='hidden' name='goodId' value=" + value.id + ">";
        div = div + "<input type='button' class='btn btnCart' onclick='tobasket(" + value.id + ")' value='В корзину' id='btn-basket-add' class='btn btn-success'>";
        div = div + " </form> </div></div>"
        div = div + "</div>";
        $('#goods').append(div);

    });
    page = 10 + page;
    var divLoad = "<div class='col-lg-2 col-md-3 col-sm-4 col-xs-6 marginImg miniGood' id='loader'>";
    divLoad = divLoad + "<img src='/images/images/load.png'/>";
    divLoad = divLoad + "</div>";
    $('#goods').append(divLoad);
}