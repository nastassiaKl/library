function checkForgetPassword() {
    if (document.form.number_ticket.value == "") {
        alert("Пожалуйста, введите Ваш номер билета!");
        return false;
    } else if (document.form.mail.value == "") {
        alert("Пожалуйста, введите Ваш почтовый ящик!");
        return false;
    }
}