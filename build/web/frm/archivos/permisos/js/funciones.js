/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function buscarIdPermiso() {
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
            $("#id_permiso").val(json.id_permiso);
            $("#nombre_permiso").val(json.nombre_permiso);
            if (json.nuevo === "true") {
                $("#botonAgregar"). prop('disabled', false);
                $("#botonModificar").prop('disabled', true); 
                $("#botonEliminar").prop('disabled', true);
                siguienteCampo("#nombre_permiso", "#botonAgregar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                siguienteCampo("#nombre_permiso", "#botonModificar", true);
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

function buscarNombrePermiso() {
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
                $("#id_permiso").val(id);
                $("#nombre_permiso").focus();
                buscarIdPermiso();
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


function agregarPermiso() {
    
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
            $("#id_permiso").focus();
            $("#id_permiso").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_permiso").focus();
        }
    } );
}

function modificarPermiso() {
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
            $("#id_permiso").focus();
            $("#id_permiso").select();
        },
        
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
           
        }
    });
}

function eliminarPermiso() {
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
            $("#id_permiso").focus();
            $("#id_permiso").select();
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
    if ($("#nombre_permiso").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Nombre no puede estar vacío.");
        $("#nombre_permiso").focus();
    }
    return valor;
}

function limpiarFormulario() {
    $("#id_permiso").val("0");
    $("#nombre_permiso").val("");
}