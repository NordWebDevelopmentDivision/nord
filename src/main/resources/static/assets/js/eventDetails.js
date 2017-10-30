var myVar = setInterval(myTimer, 1000);
function myTimer() {
    var currDate = new Date();
    var countdown = document.getElementById("countdown");
    var countdownText = countdown.children[0];
    var regOpens = countdown.children[1];
    var regCloses = countdown.children[2];

    var regArr = regOpens.innerHTML.toString().split(":");
    var regDate = new Date(regArr[2], regArr[1]-1, regArr[0], regArr[3], regArr[4], 0, 0);

    var regClosesArr = regCloses.innerHTML.toString().split(":");
    var regClosesDate = new Date(regClosesArr[2], regClosesArr[1]-1, regClosesArr[0], regClosesArr[3], regClosesArr[4], 0, 0);

    var timerInts = countdown.getElementsByClassName("timerInt");

    var regBtn = document.getElementsByClassName("regBtn");

    if (currDate <= regDate) {
    /* Then the registration has not yet started */
        countdownText.innerHTML = "Skráning opnar eftir:";
        timerInts[0].innerHTML = regDate.getHours() - currDate.getHours();
        timerInts[1].innerHTML = regDate.getMinutes() - currDate.getMinutes() - 1;
        timerInts[2].innerHTML = 60 - currDate.getSeconds();
    } else if (currDate >= regDate && currDate <= regClosesDate) {
    /* Then the registration is open */
        for (var i = 0; i < regBtn.length; i++) {
            regBtn[i].style.display="initial";
        }
        countdownText.innerHTML = "Skráningu lýkur eftir:"
        countdownText.style.color="green";
        timerInts[0].innerHTML = regClosesDate.getHours() - currDate.getHours();
        timerInts[1].innerHTML = regClosesDate.getMinutes() - currDate.getMinutes() - 1;
        timerInts[2].innerHTML = 60 - currDate.getSeconds();

    } else {
    /* Then the registration is over */
        for (var i = 0; i < regBtn.length; i++) {
            regBtn[i].style.display="none";
        }
        countdownText.innerHTML = "Skráningu lokið";
        countdownText.style.color="red";
        /*timerInts[0].innerHTML = "00";
        timerInts[1].innerHTML = "00";
        timerInts[2].innerHTML = "00";*/

    }
}

   /* var x = document.getElementsByClassName("timer");
    var time = document.getElementsByClassName("registrationOpens");
    var button = document.getElementsByClassName("registerButton");
    for (var i = 0; i < x.length; i++) {

        var arr = time[i].innerHTML.toString().split(":");
        var date = new Date(arr[2], arr[1]-1, arr[0], arr[3], arr[4], 0, 0);
        x[i].innerHTML = (d.getDate() - date.getDate()) + " " + (d.getHours()-date.getHours()) + " " + (date.getMinutes()-d.getMinutes()-1) + " " + (60 - d.getSeconds());

        if (typeof button[i] != "undefined" && d >= date) {
            button[i].removeAttribute("disabled");
        } else {
            console.log("nayyyy");
        }
*/
       /* if (d.getTime() >= date.getTime()) {

        }
        else {
            button[i].style.disabled = true;
        }*/
