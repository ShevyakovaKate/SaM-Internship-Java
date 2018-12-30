
var href;
var url;
var id;

var getUrl = window.location;
var baseUrl = getUrl.protocol + "//" + getUrl.host + "/" + getUrl.pathname.split('/')[1];

$(document).ready(function () {
    var path = "resources/template/";

    href = window.location.href;
    url = new URL(href);
    id = url.searchParams.get("id");

    $("#btn-buy-anonymous").on('click', function () {
        event.preventDefault();
        $("#div-non-auth").toggle();
    });

    $("#btn-buy-authenticated").on('click', function () {
        var token = $("#_csrf").val();
        var header = $("#_csrf_header").val();
        var data = {
            "giftCertificate": {
                "id": $("#cert-id").val()
            },
            "user": {
                "login": $("#user-name-span").text()
            }
        };

        $.ajax({
            type: 'POST',
            url: getUrl.origin + '/orders',
            data: JSON.stringify(data),
            dataType : 'json',
            contentType: "application/json; charset=utf-8",
            beforeSend: function head(xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (data) {
                alert("Order added");
                window.open(getUrl.origin +"/certificates/" + $("#cert-id").val(), "_self");
            },
            error: function (ex) {
                alert("Error while buying certificate!");
            }
        });
    })
});

var applyTemplate = function (path,  containerId) {
    $.get(path, function (data) {
        var template = $($(data).html());
        $(containerId).append(template);
    });
};


function renderCertificateTemplate(certificateData) {
    $.get(path + "single_certificate_template.html", function (data) {
        var template = $(data).html();
        var output = $(Mustache.render(template, certificateData));
        $("#certificate-container").append(output);
        renderCertificateTagsTemplate(certificateData.tags);
    });
}

function renderCertificateTagsTemplate(tagsData) {

    $.get(path + "tag_span.html", function (data) {
        var template = $(data).html();
        tagsData.forEach(function (item) {
            var output = $(Mustache.render(template, item));
            $("#tags-container").append(output);
        });
    });

}