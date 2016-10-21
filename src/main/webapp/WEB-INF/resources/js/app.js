$(document).ready(function() {


    $("#btn-add").click(function () {
        $("#myform").attr("action", "addGood");
        $("#myform").submit();
    });

    $("#btn-update").click(function () {
        
        $("#myform").attr("action", "updateGood");
        $("#myform").submit();
    });

    $("#btn-add-color").click(function () {
        $("#formColor").attr("action", "addColor");
        $("#formColor").submit();
    });

    $("#btn-update-color").click(function () {
        $("#formColor").attr("action", "updateColor");
        $("#formColor").submit();
    });

    $("#btn-add-category").click(function () {
        $("#formCategory").attr("action", "addCategory");
        $("#formCategory").submit();
    });

    $("#btn-update-category").click(function () {
        $("#formCategory").attr("action", "updateCategory");
        $("#formCategory").submit();
    });


   
}); 

$(document).ready(function() {

    /* This is basic - uses default settings */

    $("a.single_image").fancybox();

    /* Using custom settings */

    $("a#inline").fancybox({
        'hideOnContentClick': true
    });

    /* Apply fancybox to multiple items */

    $("a.group").fancybox({
        'transitionIn'	:	'elastic',
        'transitionOut'	:	'elastic',
        'speedIn'		:	600,
        'speedOut'		:	200,
        'overlayShow'	:	false
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
        },
        error: function (xhr, ajaxOptions, thrownError) {alert("ERROR:" + xhr.responseText+" - "+thrownError)},

        done : function(e) {
            console.log("DONE");
        }
    });


}