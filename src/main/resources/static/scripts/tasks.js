window.onload = function () {
    $(".btn-desc").click(infoOnClick);

    function infoOnClick(event)  {
        event.preventDefault();
        let element = $(event.target);
        let id = element.attr('data-id');

        getInfo(id);
    }
};

function getInfo(id) {
    let url = "/tasks/" + id;

    $.getJSON(url).done(function (info) {
        $("#title").text(info.title);
        $("#description").text(info.description);
    });
}

