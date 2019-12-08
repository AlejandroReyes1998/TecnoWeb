function eliminar(iden) {
    if (confirm("¿Eliminar usuario?")) {
        var urlServlet = "eliminar?nom=" + iden;
        $.ajax({url: urlServlet,
            success: function () {
                alert("eliminación exitosa");
                $("tr").remove("#id" + iden);
            },
            error: function () {
                alert("error");
            }
        });
    }
}
function eliminarG(iden) {
    if (confirm("¿Eliminar Grupo?")) {
        var urlServlet = "eliminarg?grupo=" + iden;
        $.ajax({url: urlServlet,
            success: function () {
                alert("eliminación exitosa");
                $("tr").remove("#id" + iden);
            },
            error: function () {
                alert("error");
            }
        });
    }
}

function alta() {
    var nombre = $('#username').val();
    var pass = $('#pass').val();
    var tipo = document.getElementById('tipo').value;
    var grupo = document.getElementById('grupo').value;
    var urlServlet = "registro?username=" + nombre + "?pass=" + pass + "?tipo=" + tipo + "?grupo=" + grupo;
    if(pass!==null&&nombre!==null&&tipo!==null&&grupo!==null){
         $.ajax({url: urlServlet,
        success: function () {
            alert("Alta exitosa");
        },
        error: function () {
            alert("error");
        }
    });   
    }else{
       alert("Llene todos los campos"); 
    }
};
function altagrupo(){
    $(document).ready(function () {   
    $('#submit').click(function (event) {
                var nombreVar = $('#grupo').val();
                $.get('registroGrupo', {
                    grupo: nombreVar
                }, function (responseText) {
                    alert(responseText);
                });
            });
    });
};
function crearGrupo(){
    var nombreVar = $('#grupo').val();
    if(nombreVar!==""){
       var urlServlet="registroGrupo?grupo="+nombreVar;
        $.ajax({ url: urlServlet,
            success: function(){
                alert('grupo registrado');
            },
            error: function(){
                alert("error");
            }
        }); 
    }else{
        alert('campo vacío');
    }
}
