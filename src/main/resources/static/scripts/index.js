var nome = $("#inputName").val()
var email = $("#inputEmail").val()
var idade = $("#inputIdade").val()

document.getElementById("mainButton").onclick = (ev) => {
    $.ajax({
        type: "post",
        url: "register",
        data: {
            nome: nome, email: email, idade: idade,
        },
        dataType: "",
        success: function (response) {
            
        }
    });
}