$(document).ready(function () {

    $(".btn-delete").on('click', function (event) {
        var token = $("#_csrf").val();
        var header = $("#_csrf_header").val();

        var id = $(this).attr("data-tag-id");

        $.ajax({
            type: 'DELETE',
            url: getUrl.origin + '/tags/' + id,
            beforeSend: function head(xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function () {
                alert("Tag deleted!");
                window.open(getUrl.origin +"/admin/tags", "_self");
            },
            error : function () {
                alert("Fail when delete.");
            }
        });
    });

    $("#btn-add").on('click', function () {

        var token = $("#_csrf").val();
        var header = $("#_csrf_header").val();
        var data = {
            'name': $('#name').val()
        };

        $.ajax({
            type: 'POST',
            url: getUrl.origin + '/tags',
            data: JSON.stringify(data),
            dataType : 'json',
            contentType: "application/json; charset=utf-8",
            beforeSend: function head(xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (data) {
                alert("Tag added!");
                window.open(getUrl.origin +"/admin/tags", "_self");
            },
            error: function (ex) {
                alert("Error while adding tag!");
            }
        });
    });

    $("#btn-edit").on('click', function () {

        var token = $("#_csrf").val();
        var header = $("#_csrf_header").val();
        var data = {
            'name': $('#name').val()
        };

        $.ajax({
            type: 'PUT',
            url: getUrl.origin + '/tags/' + $("#tag-id").val(),
            data: JSON.stringify(data),
            dataType : 'json',
            contentType: "application/json; charset=utf-8",
            beforeSend: function head(xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (data) {
                alert("Tag changed!");
                window.open(getUrl.origin +"/admin/tags", "_self");
            },
            error: function (ex) {
                alert("Error while changing tag!");
            }
        });
    })


});