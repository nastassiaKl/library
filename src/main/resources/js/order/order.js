function checkOrder() {
    if (document.form.date_borrow.value == "") {
        alert("Пожалуйста, введите дату взятия книги!");
        return false;
    } else if (document.form.date_return.value == "") {
        alert("Пожалуйста, введите дату возврата книги!");
        return false;
    } else if (document.form.method_borrow.value == "") {
        alert("Пожалуйста, выберите способ взятия книги!");
        return false;
    }

    if (!(/^(\d{4})\-(0\d|1[012])\-([0-2]\d|3[01])$/.test(document.form.date_borrow.value))) {
        alert("Дата взятия книги введена некорректно!");
        return false;
    }

    if (!(/^(\d{4})\-(0\d|1[012])\-([0-2]\d|3[01])$/.test(document.form.date_return.value))) {
        alert("Дата возврата книги введена некорректно!");
        return false;
    }
}