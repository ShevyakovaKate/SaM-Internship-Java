var prefix = 'cert-id-';
var path = "resources/template/";
var pageUrl = window.location.href;
var getUrl = window.location;
var baseUrl = getUrl.protocol + "//" + getUrl.host + "/" + getUrl.pathname.split('/')[1];

$(document).ready(function () {
    //applyTemplate(path + "header.html", "#header-container");
    //applyTemplate(path + "footer.html", "#footer-container");

    var textField = 'textpart';
    var tagField = 'tagname';
    urlParameters = getAllUrlParams(pageUrl);

    /*if(textField in urlParameters) {
        $.ajax({
            dataType: "json",
            url: baseUrl + 'certificates?textpart=' + urlParameters[textField],
            type: "GET",
            success: function (data) {
                if($.isEmptyObject(data)) {
                    alert("The is no matches");
                }
                else {
                    renderCertificateTemplate(data);
                }
            }
    })}
    else if(tagField in urlParameters) {
        $.ajax({
            dataType: "json",
            url: baseUrl + 'certificates?tagname=' + urlParameters[tagField],
            type: "GET",
            success: function (data) {
                if($.isEmptyObject(data)) {
                    alert("The is no matches");
                }
                else {
                    renderCertificateTemplate(data);
                }
            }
    })}
    else {
        $.ajax({
            dataType: 'json',
            url:  baseUrl + 'certificates',
            type: "GET",
            success: function (data) {
                renderCertificateTemplate(data);
            }
        });
    }*/

    // $(".view-button").click(function() {
    //     window.open(baseUrl +"/certificates" + $(this).value(), "_self");
    // });


    // function renderCertificateTemplate(certificatesData) {
    //     $.get(path + "certificate-cart-template.html", function (data) {
    //         var template = $(data).html();
    //         certificatesData.forEach(function (item) {
    //             var output = $(Mustache.render(template, item));
    //             $("#certificates-container").append(output);
    //             /*var buttonId = prefix + item.id;
    //             var button = $(output).find('.view-button');
    //             button.attr('id', buttonId);
    //             button.on('click', function () {
    //                 var buttonId = $(this).attr('id');
    //                 var certId = buttonId.replace(prefix, '');
    //                 window.open(baseUrl + "/certificates/" + certId, "_self");
    //             });*/
    //         });
    //     });
    // }
});

var applyTemplate = function (path,  containerId) {
    $.get(path, function (data) {
        var template = $($(data).html());
        $(containerId).append(template);
    });
};
/*
var renderErrorTemplate = function (path, containerId, messageData) {
    $.get(path, function (data) {
        var template = $(data).html();
        var output = $($(Mustache.render(template, messageData)));
        $(containerId).append(template);
    });
}*/

function getAllUrlParams(url) {

    // get query string from url (optional) or window
    var queryString = url ? url.split('?')[1] : window.location.search.slice(1);

    // we'll store the parameters here
    var obj = {};

    // if query string exists
    if (queryString) {

        // stuff after # is not part of query string, so get rid of it
        queryString = queryString.split('#')[0];

        // split our query string into its component parts
        var arr = queryString.split('&');

        for (var i = 0; i < arr.length; i++) {
            // separate the keys and the values
            var a = arr[i].split('=');

            // set parameter name and value (use 'true' if empty)
            var paramName = a[0];
            var paramValue = typeof (a[1]) === 'undefined' ? true : a[1];

            // (optional) keep case consistent
            paramName = paramName.toLowerCase();
            if (typeof paramValue === 'string') paramValue = paramValue.toLowerCase();

            // if the paramName ends with square brackets, e.g. colors[] or colors[2]
            if (paramName.match(/\[(\d+)?\]$/)) {

                // create key if it doesn't exist
                var key = paramName.replace(/\[(\d+)?\]/, '');
                if (!obj[key]) obj[key] = [];

                // if it's an indexed array e.g. colors[2]
                if (paramName.match(/\[\d+\]$/)) {
                    // get the index value and add the entry at the appropriate position
                    var index = /\[(\d+)\]/.exec(paramName)[1];
                    obj[key][index] = paramValue;
                } else {
                    // otherwise add the value to the end of the array
                    obj[key].push(paramValue);
                }
            } else {
                // we're dealing with a string
                if (!obj[paramName]) {
                    // if it doesn't exist, create property
                    obj[paramName] = paramValue;
                } else if (obj[paramName] && typeof obj[paramName] === 'string'){
                    // if property does exist and it's a string, convert it to an array
                    obj[paramName] = [obj[paramName]];
                    obj[paramName].push(paramValue);
                } else {
                    // otherwise add the property
                    obj[paramName].push(paramValue);
                }
            }
        }
    }

    return obj;
}