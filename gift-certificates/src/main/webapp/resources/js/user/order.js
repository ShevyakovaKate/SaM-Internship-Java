$(document).ready(function () {

    var token = $("#_csrf").val();
    var header = $("#_csrf_header").val();

    $(".btn-pay").on('click', function () {
        var paid_status = "PAID";

        var data = {
            'name': paid_status
        };

        var id = $(this).attr("data-order-id");

        $.ajax({
            type: 'PUT',
            url: getUrl.origin + '/orders/' + id,
            data: JSON.stringify(data),
            dataType : 'json',
            contentType: "application/json; charset=utf-8",
            beforeSend: function head(xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (data) {
                alert("Order paid!");
                window.open(getUrl.origin +"/user/orders", "_self");
            },
            error: function (ex) {
                alert("Error while paying certificate!");
            }
        });


    })

    $(".btn-delete").on('click', function (event) {

        var id = $(this).attr("data-order-id");

        $.ajax({
            type: 'DELETE',
            url: getUrl.origin + '/orders/' + id,
            beforeSend: function head(xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function () {
                alert("Order deleted!");
                window.open(getUrl.origin +"/user/orders", "_self");
            },
            error : function () {
                alert("Fail when delete.");
            }
        });
    });


});