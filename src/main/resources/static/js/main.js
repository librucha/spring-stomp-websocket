var stompClient;

function connect() {
	var socket = new SockJS("/socket")
	stompClient = Stomp.over(socket);

	stompClient.connect({}, function () {
		$("#subscribe").prop("disabled", true);
		$("#unsubscribe").prop("disabled", false);
		stompClient.subscribe("/user/updates", function (message) {
			showMessage(JSON.parse(message.body))
		})
	})
}

function disconnect() {
	if (stompClient != null) {
		$("#subscribe").prop("disabled", false);
		$("#unsubscribe").prop("disabled", true);
		stompClient.disconnect();
	}
}

function showMessage(jsonMessage) {
	$("#messages").append("<tr><td>" + jsonMessage.receiver + "</td><td>" + jsonMessage.count + "</td><td>" + new Date(jsonMessage.time).toTimeString() + "</td></tr>");
}

$(function () {
	$("form").on('submit', function (e) {
		e.preventDefault();
	});
	$("#subscribe").click(function () {
		connect();
	});
	$("#unsubscribe").click(function () {
		disconnect();
	}).prop("disabled", true);
	$("#clear").click(function () {
		$("#messages").empty();
	})
});