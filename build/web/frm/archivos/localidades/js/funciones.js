/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function buscarIdLocalidad() {
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
            $("#id_localidad").val(json.id_localidad);
            $("#nombre_localidad").val(json.nombre_localidad);
            if (json.nuevo === "true") {
                $("#botonAgregar"). prop('disabled', false);
                $("#botonModificar").prop('disabled', true); 
                $("#botonEliminar").prop('disabled', true);
                siguienteCampo("#nombre_localidad", "#botonAgregar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                siguienteCampo("#nombre_localidad", "#botonModificar", true);
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

function buscarNombreLocalidad() {
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
                $("#id_localidad").val(id);
                $("#nombre_localidad").focus();
                buscarIdLocalidad();
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


function agregarLocalidad() {
    
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
            $("#id_localidad").focus();
            $("#id_localidad").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_localidad").focus();
        }
    } );
}

function modificarLocalidad() {
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
            $("#id_localidad").focus();
            $("#id_localidad").select();
        },
        
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
           
        }
    });
}

function eliminarLocalidad() {
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
            $("#id_localidad").focus();
            $("#id_localidad").select();
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
    if ($("#nombre_localidad").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Nombre no puede estar vacío.");
        $("#nombre_localidad").focus();
    }
    return valor;
}

function limpiarFormulario() {
    $("#id_localidad").val("0");
    $("#nombre_localidad").val("");
}