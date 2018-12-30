var path = "resources/template/";

$(document).ready(function(){

    // applyTemplate(path + "header.html", "#header-container");
    // applyTemplate(path + "footer.html", "#footer-container");

});

var applyTemplate = function (path,  containerId) {
    $.get(path, function (data) {
        var template = $($(data).html());
        $(containerId).append(template);
    });
};