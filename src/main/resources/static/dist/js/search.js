$(function () {	
	$(".btn-search-product").on("click",function(){
		request = {"idProduct": $("[name=id]").val()};
		$.post("/Products/search",request,function(responseProduct){
			if(responseProduct != null) {
				$("#description").removeClass("invisible")
								.text(responseProduct.description);
			}
			else{
				$("#description").addClass("invisible")
				construirMensajeLogin("No existe el producto dentro del sistema", "info");
			}
		}).fail(function(){
			mensajeErrorServidor("No se pudo buscar el producto");
		});
	});
});