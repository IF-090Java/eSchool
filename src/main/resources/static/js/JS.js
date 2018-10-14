function button() {
        document.getElementById("button").innerHTML +="<br>Клас  <input type=\"text\" name=\"classes\" begraundV=\"Введіть клас\" >\n" +
            "                        <img src=\"../img/plus.png\" id=\"button\" onclick=\"button()\">Додати ще один клас</>\n";
        e.preventDefault();
}