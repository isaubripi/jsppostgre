/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function buscarIdPedido() {
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
            $("#id_pedido").val(json.id_pedido);
            $("#id_persona").val(json.id_cliente);
            $("nombre_persona").val(json.nombre_cliente);
            //$("#nombre_pedido").val(json.nombre_pedido);
            $("contenidoDetalle").html(json.contenido_detalle);
            if (json.nuevo === "true") {
                $("#botonAgregar"). prop('disabled', false);
                $("#botonModificar").prop('disabled', true); 
                $("#botonEliminar").prop('disabled', true);
                siguienteCampo("#nombre_pedido", "#botonAgregar", true);
                $("#detalle").prop('hidden', true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                siguienteCampo("#nombre_pedido", "#botonModificar", true);
                $("#detalle").prop('hidden', false);
            }
        },
        error: function (e) {
            $("#mensajes").html("No se pudieron recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
                
            }
        }
    });
}

function buscarNombrePedido() {
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
                //alert("hola");
                $("#panelBuscar").html("");
                $("#id_pedido").val(id);
                $("#nombre_pedido").focus();
                buscarIdPedido();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function(e) {
            $("#mensajes").html("No se pudieron buscar registros.");
        },
        complete: function(objeto, exito, error) {
            if(exito === "success") {
                
            }
        }
    });
}


function agregarPedido() {
    
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
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#botonAgregar").prop('disabled', true);
            $("#detalle").prop('hidden', false);
            $("#id_pedido").val(json.id_pedido);
            buscarIdPedido();
            //$("#id_pedido").focus;
            //$("#id_pedido").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudieron agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            //$("#id_pedido").focus();
            if (exito === "success"){
                
            }
        }
    } );
}

function modificarPedido() {
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
            $("#id_pedido").focus();
            $("#id_pedido").select();
            buscarIdPedido();
        },
        
        error: function (e) {
            $("#mensajes").html("No se pudieron modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success"){
                
            }
           
        }
    });
}

function eliminarPedido() {
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
            eliminarPedidoDetalle();
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            
            $("#id_pedido").focus();
            $("#id_pedido").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudieron eliminar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
                
            }
        }
    });
}



function buscarIdPersona() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdPersona.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_persona").val(json.id_persona);
            $("#nombre_persona").val(json.nombre_persona);
            
        },
        error: function (e) {
            $("#mensajes").html("No se pudieron recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
                
            }
        }
    });
}

function buscarNombrePersona() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombrePersona.jsp',
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
                //alert("hola");
                $("#panelBuscar").html("");
                $("#id_persona").val(id);
                $("#nombre_persona").focus();
                buscarIdPersona();
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

function validarFormulario() {
    var valor = true;
    if ($("#nombre_pedido").val().lenght < 3) {
        valor = false;
        $("#mensajes").html("Nombre no puede estar vacío.");
        $("#nombre_pedido").focus();
    }
    if ($("nombre_persona").val().lenght < 2){
        valor = false;
        $("#mensajes").html("Cliente no puede estar vacío.");
        $("#id_persona").focus();
    }
    if ($("nombre_tipopedido").val().lenght < 2){
        valor = false;
        $("#mensajes").html("Tipo pedido no puede estar vacío.");
        $("#id_tipopedido").focus();
    }
    return valor;
}

function limpiarFormulario() {
    $("#id_pedido").val("0");
    $("#nombre_pedido").val("");
    $("#nombre_tipopedido").val("");
    $("#nombre_persona").val("");
    $("#id_persona").val("0");
    $("#id_tipopedido").val("0");
    
}


function agregarLinea() {
    $("#id_detallepedido").val("0");
    $("#id_articulo").val("0");
    $("#nombre_articulo").val("");
    $("#cantidad_articulopedido").val("0");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_articulo").focus();
    $("#id_articulo").select();
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    siguienteCampo("#horas_detallepedido", "botonAgregarLinea", true);
}

function editarLinea() {
    $("#id_detallepedido").val("0");
    $("#id_articulo").val("0");
    $("#nombre_articulo").val("");
    $("#cantidad_articulopedido").val("0");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_articulo").focus();
    $("#id_articulo").select();
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdPedidoDetalle();
    siguienteCampo("#cantidad_articulopedido", "botonModificarLinea", true);
}

//pedidos articulos

function buscarIdPedidoDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdPedidoDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_articulo").val(json.id_articulo);
            $("#nombre_articulo").val(json.nombre_articulo);
            $("#cantidad_articulopedido").val(json.cantidad_articulopedido);
            //$("#nombre_pedido").val(json.nombre_pedido);
            //$("contenidoDetalle").html(json.contenido_detalle);
            
        },
        error: function (e) {
            $("#mensajes").html("No se pudieron recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
                
            }
        }
    });
}


function buscarIdPedidoPedidoDetalle() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdPedidoPedidoDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("contenidoDetalle").html(json.contenido_detalle);
           
        },
        error: function (e) {
            $("#mensajes").html("No se pudieron recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
                
            }
        }
    });
}

function agregarPedidoDetalle() {
    
    var datosFormulario = $("#formPrograma").serialize();
    var id_pedido = $("#id_pedido").val();
    datosFormulario += "&id_pedido" + id_pedido;
    $.ajax( {
        type: 'POST',
        url: 'jsp/agregarPedidoDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor ...");
        },
        success: function (json) {
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdPedido();
        },
        error: function (e) {
            $("#mensajes").html("No se pudieron agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            //$("#id_pedido").focus();
            if (exito === "success"){
                
            }
        }
    } );
}

function modificarPedidoDetalle() {
    var datosFormulario = $("#formPrograma").serialize();
    var id_pedido = $("#id_pedido").val();
    datosFormulario += "&id_pedido" + id_pedido;
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarPedidoDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdPedido();
        },
        
        error: function (e) {
            $("#mensajes").html("No se pudieron modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success"){
                
            }
           
        }
    });
}

function eliminarPedidoDetalle() {
    var datosFormulario = $("#formPrograma").serialize();
    var id_pedido = $("#id_pedido").val();
    datosFormulario += "&id_pedido" + id_pedido;
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarPedidoDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor ...");
        },
        success: function (json) {
            //eliminarPedidoDetalle();
            //limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdPedido();
            
        },
        error: function (e) {
            $("#mensajes").html("No se pudieron eliminar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
                
            }
        }
    });
}

//articulos

function buscarIdArticulo() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdArticulo.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_articulo").val(json.id_articulo);
            $("#nombre_articulo").val(json.nombre_articulo);
            
        },
        error: function (e) {
            $("#mensajes").html("No se pudieron recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
                
            }
        }
    });
}

function buscarNombreArticulo() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreArticulo.jsp',
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
                //alert("hola");
                $("#panelBuscar").html("");
                $("#id_articulo").val(id);
                $("#nombre_articulo").focus();
                buscarIdArticulo();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function(e) {
            $("#mensajes").html("No se pudieron buscar registros.");
        },
        complete: function(objeto, exito, error) {
            if(exito === "success") {
                
            }
        }
    });
}