// DOM set info about selected seat
$(document).ready(function () {
    const seat = localStorage.getItem("seat");
    const arr = seat.split('&');

    const hall = takeValue(arr[2]);
    const row = takeValue(arr[0]);
    const column = takeValue(arr[1]);

    const rsl = `Зал: ${hall} Ряд: ${row} Место: ${column}`;

    $('#head').html(rsl);
});

// parse info about selected seat
function takeValue(string) {
    return string.split("=")[1];
}

function submitPayment() {
    $.ajax({
        type: 'POST',
        crossdomain: true,
        url: getContextPath() + "/hall.do",
        data: {
            name: $('#username').val(),
            phone: $('#phone').val(),
            seat: localStorage.getItem("seat")
        },
        dataType: 'text/json'
    });
    alert("all nice!");
    window.location.href = getContextPath() + "/index.html";
}