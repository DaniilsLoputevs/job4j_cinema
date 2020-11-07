// save user choose in LocalStore by click on btn Submit
$(document).ready(function () {
    $("#payment-btn").click(function () {
        const radioValue = $("input[name='seat']:checked").val();
        if (radioValue) {
            localStorage.setItem("seat", radioValue);
        }
    });
});

// action set: btn Submit
$(() => {
    $('#payment-btn').click(() => {
        window.location.href = getContextPath() + "/payment.html";
    });
});