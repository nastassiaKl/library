function checkChangePassword() {
    if (document.form.old_password.value == "") {
        alert("Пожалуйста, введите Ваш старый пароль!");
        return false;
    } else if (document.form.new_password.value == "") {
        alert("Пожалуйста, введите Ваш новый пароль!");
        return false;
    } else if (document.form.repeat_new_password.value == "") {
        alert("Пожалуйста, повторите ваш новый пароль!");
        return false;
    }

    if (document.form.new_password.value.length < 6) {
        alert("Пароль должен содержать не менее 6 символов!");
        return false;
    } else if (!(/^(?=.*[a-z])(?=.*[A-Z]).{4,}$/.test(document.form.new_password.value))) {
        alert("Пароль должен содержать не менее одной буквы в каждом регистре и не менее одной цифры!");
        return false;
    }

    if (document.form.new_password.value != document.form.repeat_new_password.value) {
        alert("Пароли не совпадают!");
        return false;
    }
}