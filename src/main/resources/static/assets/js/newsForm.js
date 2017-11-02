function readURL(input) {

  if (input.files && input.files[0]) {
    var reader = new FileReader();

    reader.onload = function(e) {
      $('#preview').attr('style', "background-image: linear-gradient(rgba(0,0,0,0.6), rgba(0,0,0,0.2)), url("
      + e.target.result + ")");
    }

    reader.readAsDataURL(input.files[0]);
  }
}

$("#file").change(function() {
  readURL(this);
});

function closeFlash() {
    document.getElementById("container").style.display = "none";
}