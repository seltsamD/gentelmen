function loadSecondLevel() {
    var parentCategoryId = $('#categorySelect option:selected').val();
    $.ajax({
        contentType: 'application/json; charset=utf-8',
        type: "GET",
        url: "/api/category?id=" + parentCategoryId,
        dataType: 'json',
        success: function (data) {
            $('#categoryChildren').empty();
            var obj = jQuery.parseJSON(JSON.stringify(data));
            var div = "";
            $.each(obj, function (key, value) {
                div = div + "<option value='" + value.id + "'>" + value.name + "</option>";
            });
            $('#categoryChildren').append(div);
        }
    });
}
