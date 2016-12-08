/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function buscarIdNacionalidad() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarId.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_nacionalidad").val(json.id_nacionalidad);
            $("#nombre_nacionalidad").val(json.nombre_nacionalidad);
            if (json.nuevo === "true") {
                $("#botonAgregar"). prop('disabled', false);
                $("#botonModificar").prop('disabled', true); 
                $("#botonEliminar").prop('disabled', true);
                siguienteCampo("#nombre_nacionalidad", "#botonAgregar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                siguienteCampo("#nombre_nacionalidad", "#botonModificar", true);
            }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
                
            }
        }
    });
}

function buscarNombreNacionalidad() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombre.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function(objeto) {
            $("#mensajes").html("Enviando datos al servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function(json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("#tbody tr").on("click", function() {
                var id = $(this).find("td:first").html();
                alert("hola");
                $("#panelBuscar").html("");
                $("#id_nacionalidad").val(id);
                $("#nombre_nacionalidad").focus();
                buscarIdNacionalidad();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function(e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function(objeto, exito, error) {
            if(exito === "success") {
                
            }
        }
    });
}


function agregarNacionalidad() {
    
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax( {
        type: 'POST',
        url: 'jsp/agregar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            //limpiarFormulario();
            $("#id_nacionalidad").focus();
            $("#id_nacionalidad").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_nacionalidad").focus();
        }
    } );
}

function modificarNacionalidad() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/modificar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            //limpiarFormulario();
            $("#id_nacionalidad").focus();
            $("#id_nacionalidad").select();
        },
        
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
           
        }
    });
}

function eliminarNacionalidad() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_nacionalidad").focus();
            $("#id_nacionalidad").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo eliminar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
                
            }
        }
    });
}

function validarFormulario() {
    var valor = true;
    if ($("#nombre_nacionalidad").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Nombre no puede estar vacío.");
        $("#nombre_nacionalidad").focus();
    }
    return valor;
}

function limpiarFormulario() {
    $("#id_nacionalidad").val("0");
    $("#nombre_nacionalidad").val("");
}