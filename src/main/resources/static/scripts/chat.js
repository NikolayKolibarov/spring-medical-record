let id;
let stompClient;
let senderEmail;

$(function () {
    loadUsers();

    $('#send').click(sendMessage);

    $('#message').keyup(function (e) {
        if (e.keyCode === 13) {
            $('#send').click();
        }
    });
});

function loadUsers() {
    $.getJSON("/users/all").done(function (users) {
        let usersDiv = $('#users');
        $.each(users, function (key, value) {
            $('<p>')
                .addClass('dropdown-item')
                .text(value.fullName)
                .attr('user-id', value.id)
                .click(selectUser)
                .appendTo(usersDiv);
        })
    })
}

function getHistory() {
    $('#message-history').empty();

    $.getJSON("/chat/history/" + id).done(function (data) {
        $.each(data, function (key, value) {
            if (senderEmail === value.senderEmail) {
                orangeCard(value.message, moment(value.date, "x").format("hh:mm:ss / DD.MM.YYYY"));
            } else {
                blueCard(value.message, moment(value.date, "x").format("hh:mm:ss / DD.MM.YYYY"));
            }
        });

        let elem = document.getElementById('message-history');
        elem.scrollTop = elem.scrollHeight;
    });


}

function selectUser(e) {
    let element = $(this);

    disconnect();

    $('#receiver').text(element.text());
    id = element.attr('user-id');

    connect();
}

function setConnected(connected) {
    $('#send').prop("disabled", !connected);
    $('#message').prop("disabled", !connected);
}

function connect() {
    let socket = new SockJS("/game");
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (connection) {
        senderEmail = connection.headers['user-name'];
        let url = "/chat/" + senderEmail + "/msg/" + id;
        getHistory();
        setConnected(true);

        stompClient.subscribe(url, function (data) {
            let obj = JSON.parse(data.body);

            blueCard(obj.message, moment(obj.date, "x").format("hh:mm:ss / DD.MM.YYYY"));

            let elem = document.getElementById('message-history');
            elem.scrollTop = elem.scrollHeight;
        })
    })
}

function disconnect() {
    if(stompClient != null){
        stompClient.disconnect();
    }

    setConnected(false);
}

function sendMessage() {
    let messageBox = $('#message');
    let bindingModel = {
        message: messageBox.val(),
        receiverId: id
    };

    messageBox.val("");

    if(isBlank(bindingModel.message)){
        return;
    }

    orangeCard(bindingModel.message, moment().format("hh:mm:ss / DD.MM.YYYY"));

    let elem = document.getElementById('message-history');
    elem.scrollTop = elem.scrollHeight;

    stompClient.send("/app/chat",
        {},
        JSON.stringify(bindingModel))
}

function blueCard(text, date) {
    let quote = $('<blockquote>')
        .addClass('quote-card')
        .addClass('blue-card')
        .addClass('col-6')
        .appendTo($('#message-history'));

    let p = $('<p>')
        .text(text)
        .appendTo(quote);

    let cite = $('<cite>').text(date).appendTo(quote);
}

function orangeCard(text, date) {
    let quote = $('<blockquote>')
        .addClass('quote-card')
        .addClass('yellow-card')
        .addClass('offset-6')
        .addClass('col-6')
        .appendTo($('#message-history'));

    let p = $('<p>')
        .text(text)
        .appendTo(quote);

    let cite = $('<cite>').text(date).appendTo(quote);
}

function isBlank(str) {
    return /^\s*$/.test(str);
}