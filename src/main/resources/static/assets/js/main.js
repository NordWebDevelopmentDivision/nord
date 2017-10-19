/* Toggle between displaying and hiding the hamburger menu */
var toggleHamburgerMenu = function() {
  var menuList = document.getElementById("hamburgerMenu");

  /* Close the user menu */
  var userMenu = document.getElementById("userMenu");
  userMenu.style.display = "none";

  /* Close the info menu */
  var infoMenu = document.getElementById("infoMenu");
  infoMenu.style.display = "none";

  /* Close the about menu */
  var aboutMenu = document.getElementById("aboutMenu");
  aboutMenu.style.display = "none";

  if(menuList.style.display == "flex") { // if is menuList displayed, hide it
    menuList.style.display = "none";
	  }
  else { // if is menuList hidden, display it
    menuList.style.display = "flex";
  }
}

/* Toggle between displaying and hiding the user menu */
var toggleUserMenu = function() {
  var menuList = document.getElementById("userMenu");

  /* Close the hamburger menu */
  var hamburgerMenu = document.getElementById("hamburgerMenu");
  hamburgerMenu.style.display = "none";

  /* Close the info menu */
  var infoMenu = document.getElementById("infoMenu");
  infoMenu.style.display = "none";

  /* Close the about menu */
  var aboutMenu = document.getElementById("aboutMenu");
  aboutMenu.style.display = "none";

  if(menuList.style.display == "flex") { // if is menuList displayed, hide it
    menuList.style.display = "none";
    }
  else { // if is menuList hidden, display it
    menuList.style.display = "flex";
  }
}

/* Toggle between displaying and hiding the about menu */
var toggleAboutMenu = function() {
  var menuList = document.getElementById("aboutMenu");

  /* Close the hamburger menu */
  var hamburgerMenu = document.getElementById("hamburgerMenu");
  hamburgerMenu.style.display = "none";

  /* Close the user menu */
  var userMenu = document.getElementById("userMenu");
  userMenu.style.display = "none";

  /* Close the info menu */
  var infoMenu = document.getElementById("infoMenu");
  infoMenu.style.display = "none";

  if(menuList.style.display == "flex") { // if is menuList displayed, hide it
    menuList.style.display = "none";
    }
  else { // if is menuList hidden, display it
    menuList.style.display = "flex";
  }
}

/* Toggle between displaying and hiding the info menu */
var toggleInfoMenu = function() {
  var menuList = document.getElementById("infoMenu");

  /* Close the hamburger menu */
  var hamburgerMenu = document.getElementById("hamburgerMenu");
  hamburgerMenu.style.display = "none";

  /* Close the user menu */
  var userMenu = document.getElementById("userMenu");
  userMenu.style.display = "none";

  /* Close the about menu */
  var aboutMenu = document.getElementById("aboutMenu");
  aboutMenu.style.display = "none";

  if(menuList.style.display == "flex") { // if is menuList displayed, hide it
    menuList.style.display = "none";
    }
  else { // if is menuList hidden, display it
    menuList.style.display = "flex";
  }
}