function checkAuthor() {
    if (document.form.surname.value == "") {
        alert("Пожалуйста, введите Вашу фамилию!");
        return false;
    } else if (document.form.name.value == "") {
        alert("Пожалуйста, введите Ваше имя!");
        return false;
    } else if (document.form.country.value == "") {
        alert("Пожалуйста, введите страну!");
        return false;
    }

    if (!(/^[A-ZА-Я][a-zа-я]+('[a-zа-я]+|-[A-ZА-Я][a-zа-я]+)?$/.test(document.form.surname.value))) {
        alert("Фамилия должна содержать только буквы и начинаться с заглавной буквы!");
        return false;
    }

    if (!(/^[A-ZА-Я][a-zа-я]+('[a-zа-я]+|-[A-ZА-Я][a-zа-я]+)?$/.test(document.form.name.value))) {
        alert("Имя должно содержать только буквы и начинаться с заглавной буквы!");
        return false;
    }

    if (document.form.middle_name.value != null) {
        if (!(/^\-|[A-ZА-Я][a-zа-я]+$/.test(document.form.middle_name.value))) {
            alert("Отчество должно содержать только буквы и начинаться с заглавной буквы!");
            return false;
        }
    }

    if (!(/^[A-ZА-Я][А-ЯA-Za-zа-я]+('[a-zа-я]+|-[A-ZА-Я][a-zа-я]+)?$/.test(document.form.country.value))) {
        alert("Страна должна содержать только буквы и начинаться с заглавной буквы!");
        return false;
    }
}