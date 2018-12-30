$(document).ready(function () {

    $(".btn-delete").on('click', function (event) {
        var token = $("#_csrf").val();
        var header = $("#_csrf_header").val();

        var id = $(this).attr("data-certificate-id");

        $.ajax({
            type: 'DELETE',
            url: getUrl.origin + '/certificates/' + id,
            beforeSend: function head(xhr) {
                // xhr.setRequestHeader("Accept", "application/json");
                // xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader(header, token);
            },
            success: function () {
                alert("Certificate deleted!");
                window.open(getUrl.origin +"/admin/certificates", "_self");
            },
            error : function () {
                alert("Error while deleting certificate.");
            }
        });
    });
});