var inProgress = false;
var page = 0;
var categories = [];
var byCategory = false;
$(document).ready(function () {
    var url = window.location.href;
    var param = url.substr(url.lastIndexOf("/") + 1);
    if ($.isNumeric(param))
        loadContentWithCategory(param, 0);
    else
        loadContent(0);
    loadMenu();
    $('#goods').on('click', '#loader', function () {
        $(this).closest('div').remove();
        var param = url.substr(url.lastIndexOf("/") + 1);
        if ($.isNumeric(param))
            loadContentWithCategory(param, page);
        else if (byCategory) {
            loadCategories();
        }
        else {
            loadContent(page);
        }
    });
});

function loadContent(countPage) {
    $(this).closest('div').remove();
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
        done: function () {
            inProgress = false;
        }
    });
}

function loadContentWithCategory(category, countPage) {
    $(this).closest('div').remove();
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
        div = div + "<div class='price'>" + value.price + "грн <input type='button' class='btn btnCart' onclick='tobasket(" + value.id + ")' value='В корзину' id='btn-basket-add' class='btn btn-success'/></div>";
        div = div + " </form> </div></div>"
        div = div + "</div>";
        $('#goods').append(div);

    });
    page = 10 + page;

    var divLoad = "<div class='col-lg-2 col-md-3 col-sm-4 col-xs-6 marginImg miniGood' id='loader'>";
    divLoad = divLoad + "<img src='/images/images/load.png'/>";
    divLoad = divLoad + "</div>";
    if (data.length == 10) {
        $('#goods').append(divLoad);
    }

}

function loadMenu() {
    $.ajax({
        contentType: 'application/json; charset=utf-8',
        type: "GET",
        url: "/api/category/tree",
        dataType: 'json',
        success: function (data) {
            var obj = jQuery.parseJSON(JSON.stringify(data));
            var div = "";
            $.each(obj, function (key, value) {
                div = div + "<div class='panel panel-default'>" +
                    "<div class='panel-heading'>";
                div = div + "<h3 class='panel-title'>" +
                    "<a data-toggle='collapse' data-parent='#accordion' href='#heading" + value.id + "'>" + value.name + "</a></h3></div>";
                div = div + "<div id='heading" + value.id + "' class='panel-collapse collapse'>";
                div = div + "<div class='panel-body'><ul type='none'>";
                var obj1 = jQuery.parseJSON(JSON.stringify(value.child));
                $.each(obj1, function (key, value1) {
                    var cat = 'cat' + value1.id;
                    div = div + "<li><input type='checkbox' id='cat" + value1.id + "' onchange='loadByCategory(this)'/>" +
                        "<label for='cat" + value1.id + "'>" + value1.name + "</label></li>";

                });
                div = div + "</ul></div></div></div>";
            });
            $('#accordion').append(div);
        },
        done: function () {
            inProgress = false;
        }
    });

}

function loadByCategory(checkbox) {
    if (checkbox.checked) {
        categories.push(checkbox.getAttribute("id"));
        $('#goods').empty();
        page = 0;
        byCategory = true;
        loadCategories();
    }
    else {
        categories.pop(checkbox.getAttribute("id"));
        page = 0;
        $('#goods').empty();
        if (categories.length == 0) {
            byCategory = false;
            page = 0;
            loadContent(page);
        }
        else {
            byCategory = true;
            loadCategories();
        }
    }
}

function loadCategories() {
    $.ajax({
        contentType: 'application/json; charset=utf-8',
        type: "GET",
        url: "/api/good/category/list/" + page + "?categories=" + JSON.stringify(categories),
        dataType: 'json',
        beforeSend: function () {
            inProgress = true;
        },
        success: function (data) {
            setData(data);
        },
        done: function () {
            inProgress = false;
        }
    });
}

function getAll() {
    $.ajax({
        contentType: 'application/json; charset=utf-8',
        type: "GET",
        url: "/api/good/all",
        dataType: 'json',
        beforeSend: function () {
            inProgress = true;
        },
        success: function (data) {
            $('#goods').empty();
            setData(data);
        },
        done: function () {
            inProgress = false;
        }
    });
}
