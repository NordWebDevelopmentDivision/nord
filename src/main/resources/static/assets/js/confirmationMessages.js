var deleteNews = document.getElementById('deleteNews');
var deleteEvent = document.getElementById('deleteEvent');

var eventBan = document.querySelectorAll("[id^='eventBan']")
var removeEventBan = document.querySelectorAll("[id^='removeEventBan']")
var deleteInfoNord = document.querySelectorAll("[id^='deleteInfoNord']")
var deleteInfoBoard = document.querySelectorAll("[id^='deleteInfoBoard']")
var deleteInfoMiddleBoard = document.querySelectorAll("[id^='deleteInfoMiddleBoard']")


if(deleteNews) {
    deleteNews.addEventListener('click', confirmDelete);
}

if(deleteEvent) {
    deleteEvent.addEventListener('click', confirmDelete);
}

for(var i=0; i<eventBan.length; i++) {
     eventBan[i].addEventListener('click', confirmEventBan);
 }

for(var i=0; i<removeEventBan.length; i++) {
     removeEventBan[i].addEventListener('click', confirmEventBanRemoval);
 }

for(var i=0; i<deleteInfoNord.length; i++) {
     deleteInfoNord[i].addEventListener('click', confirmDelete);
 }

for(var i=0; i<deleteInfoBoard.length; i++) {
     deleteInfoBoard[i].addEventListener('click', confirmDelete);
 }

 for(var i=0; i<deleteInfoMiddleBoard.length; i++) {
      deleteInfoMiddleBoard[i].addEventListener('click', confirmDelete);
  }

function confirmDelete(e) {
    var confirmDeletion = confirm('Viltu eyða?');
    if(!confirmDeletion) {
        e.preventDefault();
    }
};

function confirmEventBan(e) {
    var confirmBan = confirm('Viltu setja á bannlista?');
    if(!confirmBan) {
        e.preventDefault();
    }
};

function confirmEventBanRemoval(e) {
    var confirmRemoval = confirm('Viltu fjarlægja af bannlista?');
    if(!confirmRemoval) {
        e.preventDefault();
    }
};