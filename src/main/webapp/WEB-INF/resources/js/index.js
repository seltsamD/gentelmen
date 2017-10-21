$(document).ready(function () {
    $.ajax({
        contentType: 'application/json; charset=utf-8',
        type: "GET",
        url: "/api/good/random",
        dataType: 'json',

        success: function (data) {
            var obj = jQuery.parseJSON(JSON.stringify(data));
            console.log(JSON.stringify(data));
            $.each(obj, function (key, value) {
                var div = "<div class='col-lg-2 col-md-3 col-sm-4 col-xs-6 marginImg miniGood'>";
                div = div + "<img src='/images/" + value.id + "_0_mini.jpg'>";
                div = div + "<div class='miniText'><a href='" + value.href + "'>" + value.name + "</a>";
                div = div + "<div class='btnForm'><form id='baskForm" + value.id + "'  action='addToBasket' method='POST'>";
                div = div + "<input type='hidden' name='goodId' value=" + value.id + ">";
                div = div + "<div class='price'>" + value.price + "грн <input type='button' class='btn btnCart' onclick='tobasket(" + value.id + ")' value='В корзину' id='btn-basket-add' class='btn btn-success'/></div>";
                div = div + " </form> </div></div>"
                div = div + "</div>";
                $('#indexGoods').append(div);
            });
        },
        error: function (xhr, ajaxOptions, thrownError) {
            console.log("ERROR:" + xhr.responseText + " - " + thrownError)
        },
    });
});