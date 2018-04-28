function checkLibrarians() {
    if (document.form.surname.value == "") {
        alert("Пожалуйста, введите Вашу фамилию!");
        return false;
    } else if (document.form.name.value == "") {
        alert("Пожалуйста, введите Ваше имя!");
        return false;
    } else if (document.form.middle_name.value == "") {
        alert("Пожалуйста, введите Ваше отчество!");
        return false;
    } else if (document.form.login.value == "") {
        alert("Пожалуйста, введите логин!");
        return false;
    }

    if (!(/^[A-ZА-Я][a-zа-я]+('[a-zа-я]+|-[A-ZА-Я][a-zа-я]+)?$/.test(document.form.surname.value))) {
        alert("Фамилия должна содержать только буквы и начинаться с заглавной буквы!");
        return false;
    }

    if (!(/^[A-ZА-Я][a-zа-я]+('[a-zа-я]+|-[A-ZА-Я][a-zа-я]+)?$/.test(document.form.name.value))) {
        alert("Имя может содержать только буквы и символ "-" и начинаться с заглавной буквы!");
        return false;
    }

    if (!(/^[A-ZА-Я][a-zа-я]+$/.test(document.form.middle_name.value))) {
        alert("Отчество должно содержать только буквы и начинаться с заглавной буквы!");
        return false;
    }

    if (document.form.login.value.length < 5) {
        alert("Логин должнен содержать больше 5 символов!");
        return false;
    } else if (!(/^[A-Za-z][A-Za-z0-9_]+$/.test(document.form.login.value))) {
        alert("Логин должен содержать только латинские буквы и цифры!");
        return false;
    }
}