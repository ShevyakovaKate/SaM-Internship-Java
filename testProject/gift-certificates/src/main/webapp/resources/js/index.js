var path = "resources/template/";
var getUrl = window.location;
var baseUrl = getUrl.protocol + "//" + getUrl.host + "/" + getUrl.pathname.split('/')[1];

$(document).ready(function(){

    //applyTemplate(path + "header.html", "#header-container");
    //applyTemplate(path + "footer.html", "#footer-container");


    // $("#btn-search").click(function() {
    //     window.open(baseUrl +"/certificates", "_self");
    // });

});

var applyTemplate = function (path,  containerId) {
    $.get(path, function (data) {
        var template = $($(data).html());
        $(containerId).append(template);
    });
};
