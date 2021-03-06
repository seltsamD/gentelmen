$(document).ready(function() {


    $("#btn-add").click(function () {
        $("#myform").attr("action", "/admin/addGood");
        $("#myform").submit();
    });

    $("#btn-update").click(function () {
        
        $("#myform").attr("action", "/admin/updateGood");
        $("#myform").submit();
    });

    $("#btn-add-color").click(function () {
        $("#formColor").attr("action", "/admin/addColor");
        $("#formColor").submit();
    });

    $("#btn-update-color").click(function () {
        $("#formColor").attr("action", "/admin/updateColor");
        $("#formColor").submit();
    });

    $("#btn-add-category").click(function () {
        $("#formCategory").attr("action", "/admin/addCategory");
        $("#formCategory").submit();
    });

    $("#btn-update-category").click(function () {
        $("#formCategory").attr("action", "/admin/updateCategory");
        $("#formCategory").submit();
    });


   
});

$(document).ready(function() {
    $.ajax({
        contentType : 'application/json; charset=utf-8',
        type : "GET",
        url : "/order/api/getBasket",
        dataType : 'json',

        success : function(data) {
            var jsonData = jQuery.parseJSON(data); // $.parseJSON(data);
            $('#cartDiv').text(data);
            console.log(data);

        },
        error: function (xhr, ajaxOptions, thrownError) {alert("ERROR:" + xhr.responseText+" - "+thrownError)},

        done : function(e) {
            console.log("DONE");
        }
    });
});

function tobasket(id) {

    $.ajax({
        contentType : 'application/json; charset=utf-8',
        type : "GET",
        url : "/order/api/getCountBasket",
        data : { 'goodId':  id },
        dataType : 'json',

        success : function(data) {
           
            var jsonData = jQuery.parseJSON(data); // $.parseJSON(data);
            $('#cartDiv').text(data);
            
            var x = document.getElementById("snackbar")

            // Add the "show" class to DIV
            x.className = "show";

            // After 3 seconds, remove the show class from DIV
            setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
        },
        error: function (xhr, ajaxOptions, thrownError) {alert("ERROR:" + xhr.responseText+" - "+thrownError)},

        done : function(e) {
            console.log("DONE");
        }
    });


}

