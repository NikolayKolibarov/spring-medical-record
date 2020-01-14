$(function () {

    $(".btn-details").click(infoOnClick);
    $(".btn-tasks").click(tasksOnClick);

    connect();
});

let button = null;

function startTimer(time) {
    let selector = $('#timer-body').empty();
    let obj = $("<div>").data('timer', time).appendTo(selector);

    obj.TimeCircles({
        animation: "smooth",
        bg_width: 0.4,
        fg_width: 0.043333333333333335,
        circle_bg_color: "#f0b00b",
        time: {
            Days: {show: false},
            Hours: {color: "#0275d8"},
            Minutes: {color: "#0275d8"},
            Seconds: {color: "#0275d8"}
        }
    }).addListener(function (unit, value, total) {
        if (total === 0) {
            button.prop('disabled', false);
            selector.fadeOut();
            selector.parent().empty();
        }
    });

}

function getInfo(id) {
    let url = "/characters/" + id;

    $.getJSON(url).done(function (data) {
        let modal = $('.modal-body');
        modal.empty();

        let level = data.level;
        let type = data.type;
        let task = data.activeTask;

        if (task) {
            let seconds = (task.endDate - Date.now()) / 1000;

            let missionDiv = $('<div>').attr("id", "active-mission").appendTo(modal);

            $('<h1>').addClass("text-center").text("On Mission").appendTo(missionDiv);
            $('<div>').attr("id", "timer-body").appendTo(missionDiv);

            setTimeout(function () {
                startTimer(seconds);
            }, 200);
        }

        $('<h1>').addClass("text-center").text(type).appendTo(modal);
        $('<h2>').addClass("text-center").text(level).appendTo(modal);

    });
}

function getTasks(id) {

    let div = $('.modal-body').load("/tasks/available", function () {
        $('.btn-choose').click(function (e) {
            e.preventDefault();
            let taskId = $(e.target).data('id');

            $.get("/characters/" + id + "/task/" + taskId).done(function () {
                $('#info-modal').modal('hide');
                button = $('#char-' + id).prop('disabled', true);
            })
        })
    });
}

function infoOnClick(event) {
    event.preventDefault();
    let element = $(event.target);
    let id = element.data('id');

    getInfo(id);
}

function tasksOnClick(event) {
    event.preventDefault();
    let element = $(event.target);
    let id = element.data('id');

    getTasks(id);
}

let stompClient = null;

function connect() {
    let socket = new SockJS('/game');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/character/money', function (data) {
            let obj = JSON.parse(data.body);

            updateCharMoney(obj);
        });

        stompClient.subscribe("/character/info", function (data) {
            let obj = JSON.parse(data.body);

            updateCharInfo(obj);
        });
    });
}

function updateCharMoney(data) {
    $('#money-' + data.id).text(data.money);
}

function updateCharInfo(data) {
    $('#money-' + data.id).text(data.money);
    $('#level-' + data.id).text(data.level);
}
