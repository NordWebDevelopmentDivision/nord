/* This function handles the countdown timer for events */
var myVar = setInterval(myTimer, 1000);
function myTimer() {
    var currDate = new Date();

    var countdown = document.getElementsByClassName("countdown");
    for (var i = 0; i < countdown.length; i++) {
        var countdownText = countdown[i].children[0];
        var regOpens = countdown[i].children[1];
        var regCloses = countdown[i].children[2];

        var regArr = regOpens.innerHTML.toString().split(":");
        var regDate = new Date(regArr[2], regArr[1]-1, regArr[0], regArr[3], regArr[4], 0, 0);

        var regClosesArr = regCloses.innerHTML.toString().split(":");
        var regClosesDate = new Date(regClosesArr[2], regClosesArr[1]-1, regClosesArr[0], regClosesArr[3], regClosesArr[4], 0, 0);

        var timerInts = countdown[i].getElementsByClassName("timerInt");

        if (currDate <= regDate) {
        /* Then the registration has not yet started */
            countdownText.innerHTML = "Skráning opnar eftir:";
            timerInts[0].innerHTML = regDate.getDate() - currDate.getDate();
            timerInts[1].innerHTML = regDate.getHours() - currDate.getHours();
            timerInts[2].innerHTML = regDate.getMinutes() - currDate.getMinutes() - 1;
            timerInts[3].innerHTML = 60 - currDate.getSeconds();
        } else if (currDate >= regDate && currDate <= regClosesDate) {
        /* Then the registration is open */
            countdownText.innerHTML = "Skráningu lýkur eftir:"
            timerInts[0].innerHTML = regClosesDate.getDate() - currDate.getDate();
            timerInts[1].innerHTML = regClosesDate.getHours() - currDate.getHours();
            timerInts[2].innerHTML = regClosesDate.getMinutes() - currDate.getMinutes() - 1;
            timerInts[3].innerHTML = 60 - currDate.getSeconds();
        } else {
        /* Then the registration is over */
            countdownText.innerHTML = "Skráningu lokið";
            countdown[i].className += "Off";
        }
    }
}