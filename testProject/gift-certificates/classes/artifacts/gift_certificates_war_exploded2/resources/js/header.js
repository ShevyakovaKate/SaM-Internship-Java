var path = "resources/template/";
var self = this;
var getUrl = window.location;
var baseUrl = getUrl.protocol + "//" + getUrl.host + "/" + getUrl.pathname.split('/')[1];

$(document).ready(function(){

    $("#btn-main-page").click(function() {
        window.open(baseUrl, "_self");
    });

    $(document).on('keypress', "#searchInput", function(event) {
        if (event.keyCode !== 13) {
            return;
        }
        window.open(getUrl.origin +"/certificates?textpart=" + $('#searchInput').val(), "_self");
    });

    $(document).on('click', ".tag-link", function(event) {
        window.open(getUrl.origin +"/certificates?tagname=" +  $(this).text(), "_self");
    });

    $(document).on('click', ".btn-lang", function(event) {
        window.open(getUrl.origin +"/?lang=" +  $(this).val(), "_self");
    });

    $(document).on('click', "#navbarDropdownMenuLink", function() {
        $.ajax({
            dataType: 'json',
            url: getUrl.origin + '/tags',
            type: "GET",
            success: function (data) {
                $("#tag_item").load(path + "/dropdown_tag_item.html", function () {
                    var template = $(this).html();
                    data.forEach(function (item) {
                        var output = $(Mustache.render(template, item));
                        $("#tags_menu").append(output);
                    });
                });
            }
        });

    });

});

var openCertHtml = function () {
    window.open(baseUrl +"/certificatelist", "_self");
}

var init = function () {

};