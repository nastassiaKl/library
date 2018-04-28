function checkRegistration() {
    if (document.form.role.value == "") {
        alert("Пожалуйста, выберите роль!");
        return false;
    } else if (document.form.surname.value == "") {
        alert("Пожалуйста, введите Вашу фамилию!");
        return false;
    } else if (document.form.name.value == "") {
        alert("Пожалуйста, введите Ваше имя!");
        return false;
    } else if (document.form.middle_name.value == "") {
        alert("Пожалуйста, введите Ваше отчество!");
        return false;
    } else if (document.form.age.value == "") {
        alert("Пожалуйста, введите Ваш возраст!");
        return false;
    } else if (document.form.phone.value == "") {
        alert("Пожалуйста, введите Ваш номер телефона!");
        return false;
    } else if (document.form.mail.value == "") {
        alert("Пожалуйста, введите Вашу электронную почту!");
        return false;
    } else if (document.form.login.value == "") {
        alert("Пожалуйста, введите логин!");
        return false;
    } else if (document.form.password.value == "") {
        alert("Пожалуйста, введите пароль!");
        return false;
    } else if (document.form.password2.value == "") {
        alert("Пожалуйста, повторите введенный пароль!");
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

    if (!(/^[0-9]+$/.test(document.form.age.value))) {
        alert("Возраст должен содержать только цифры!");
        return false;
    } else if (document.form.age.value < 7 || document.form.age.value > 120) {
        alert("Возраст должен быть в дипазоне от 7 до 120 лет!");
        return false;
    }

    if (document.form.phone.value.length > 17 ) {
        alert("Превышение количества символов в номере телефона!");
        return false;
    } else if (!(/^(\s*)?(\+)?([- _():=+]?\d[- _():=+]?){10,14}(\s*)?$/.test(document.form.phone.value))) {
        alert("Неправильный формат номера телефона!");
        return false;
    }

    if (!(/^[\w-\.]+@[\w-]+\.[a-z]{2,3}$/.test(document.form.mail.value))) {
        alert("Некорректный адрес электронной почты!");
        return false;
    }

    if (document.form.login.value.length < 5) {
        alert("Имя пользователя должно содержать больше 5 символов!");
        return false;
    } else if (!(/^[A-Za-z][A-Za-z0-9_]+$/.test(document.form.login.value))) {
        alert("Имя пользователя может содержать только латинские буквы и цифры!");
        return false;
    }

    if (document.form.password.value.length < 6) {
        alert("Пароль должен содержать не менее 6 символов!");
        return false;
    } else if (!(/^(?=.*[a-z])(?=.*[A-Z]).{4,}$/.test(document.form.password.value))) {
        alert("Пароль должен содержать не менее одной буквы в каждом регистре и не менее одной цифры!");
        return false;
    }

    if (document.form.password.value != document.form.password2.value) {
        alert("Пароли не совпадают!");
        return false;
    }
}


