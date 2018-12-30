var getUrl = window.location;
var baseUrl = getUrl.protocol + "//" + getUrl.host + "/" + getUrl.pathname.split('/')[1];

$(document).ready(function () {
    var tags;
    var tagData = [];
    $(".tag-name").each(function(){
        tagData.push($(this).val())
    });

    $.ajax({
        dataType: 'json',
        url: getUrl.origin + '/tags',
        type: "GET",
        success: function (data) {
            var allTags = [];
            data.forEach(function (item) {
                allTags.push(item.name);
            });

            tags = $('#tag-list').tags({
                suggestions:allTags,
                tagData: tagData,
                suggestOnClick:true,
                restrictTo:allTags
            });
        }
    });

    var token = $("#_csrf").val();
    var header = $("#_csrf_header").val();

    $( "#btn-submit" ).on('click', function( event ) {

        var tagsArray = [];
        tags.getTags().forEach(function (item) {
            var tag = {'name': item};
            tagsArray.push(tag);
        });

        var tag = {'name': tags.getTags()[0]};
        var data = {
            'name': $('#name').val(),
            'description': $('#description').val(),
            'price': $('#price').val(),
            'duration': $('#duration').val(),
            'tags': tagsArray
        };

        var json = JSON.stringify(data);

        $.ajax({
            type: 'PUT',
            url: getUrl.origin + '/certificates/' + $("#cert-id").val(),
            data: json,
            dataType : 'json',
            contentType: "application/json; charset=utf-8",
            beforeSend: function head(xhr) {
                // xhr.setRequestHeader("Accept", "application/json");
                // xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader(header, token);
            },
            success: function (data) {
                alert("Certificate changed!");
                window.open(getUrl.origin +"/admin/certificates", "_self");
            },
            error: function (ex) {
                alert("Error while changing!");
            }
        });
    });

});

var createTagOblectList = function (array,  containerId) {
    var listOfObjects = [];
    array.forEach(function(entry) {
        var singleObj = {};
        singleObj['type'] = 'name';
        singleObj['value'] = entry;
        listOfObjects.push(singleObj);
    });
    return listOfObjects;

};