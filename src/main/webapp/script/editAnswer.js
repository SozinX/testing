function deleteAnswer(el){
  const toHide = document.getElementById("up" + el);
  toHide.classList.remove("d-flex");
  toHide.style.display = 'none';
  var number = parseInt(document.getElementById("deleteCount").value, 10);
  const changeId = document.getElementById("up" + el + "i");
  changeId.setAttribute("name", "delete" + number);
  document.getElementById("select" + el).setAttribute("name", "delete");
  document.getElementById("input" + el).setAttribute("name", "delete");
  document.getElementById("deleteCount").value = number + 1;
   document.getElementById("answers").value = document.getElementById("answers").value - 1;
};
$("#answerAdder").click(function () {
    var number = parseInt(document.getElementById("answers").value, 10);
    var iterations = parseInt(document.getElementById("iterations").value, 10);
    if(number <= 10){
    newRowAdd =
                '<div id="row" class="py-3 d-flex align-items-center justify-content-center">'+
                '<select name="qt' + iterations + '" class="form-select form-select-md col-4" aria-label=".form-select-lg example">'+
                '<option value="0" selected>Not correct</option>'+
                '<option value="1">Correct</option>'+
                '</select>'+
                '<input name="a' + iterations + '" type="text" id="form1Example12" class="form-control form-control-lg" value="">'+
                '<button class="btn btn-danger" id="DeleteRow" type="button"><b>X</b></button>'+
                '</div>'
    document.getElementById("answers").value = number + 1;
    document.getElementById("iterations").value = iterations + 1;
    $('#newInput').append(newRowAdd);
    }
});
$("body").on("click", "#DeleteRow", function () {
    var number = parseInt(document.getElementById("answers").value, 10);
    document.getElementById("answers").value = number - 1;
    $(this).parents("#row").remove();
})