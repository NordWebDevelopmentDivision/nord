/* This javascript handles the image preview for ads */
function readURL(input) {

  if (input.files && input.files[0]) {
    var reader = new FileReader();

    reader.onload = function(e) {
      $('#preview').attr('src', e.target.result);
    }

    reader.readAsDataURL(input.files[0]);
  }
}

$("#file").change(function() {
  readURL(this);
});

/* Close the flash message banner */
function closeFlash() {
    document.getElementById("container").style.display = "none";
}