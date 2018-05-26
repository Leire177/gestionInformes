var formLength = 0;
function confPlantillaBase(){
		var long = formLength;
	    $('<div/>', {
	        'class' : 'formulario form-inline','id':'formulario_'+long,html: GetHtml()
	    }).hide().appendTo('#lineas');
	    $(".formulario").show();
	    
	    $('[id^=botonRemove_'+formLength+']').on("click",function(e){
	    	e.preventDefault();
	        e.stopImmediatePropagation();
	        var id=$(this).attr("id");
	        var nombre = id.split("_");
	        $('[id=formulario_'+nombre[1]+']').remove();
		})
		$('[id^=botonAdd_'+formLength+']').on("click",function(e){
			e.preventDefault();
	        e.stopImmediatePropagation();
	        confPlantillaBase();
		})
		$("#botonRemoveEnf_0").hide();
	    $("#botonRemoveMed_0").hide();
	    formLength++;
	}
function GetHtml(){
	var len =formLength;
	var $html = $('.formularioTemplate').clone().prop('id', 'formulario_'+len );
	
	$html.find('[id=enfermedad]').attr("id", "enfermedad_" + len).attr("name", "listaEnfsFiltro.descripcion");
	$html.find('[id=medicamento]').attr("id", "medicamento_" + len).attr("name", "listaMedsFiltro.descripcion");
	$html.find('[id=relaicon]').attr("id", "relacion_" + len);
	$html.find('[id=botonAdd]').attr("id", "botonAdd_" + len);
	$html.find('[id=botonRemove]').attr("id", "botonRemove_" + len);

	return $html.html();
}
jQuery(document).ready(function($) {
	$('#fechaDesde').datepicker({
	    format: 'dd/mm/yyyy'
	 });
	$('#fechaHasta').datepicker({
	    format: 'dd/mm/yyyy'
	 });
	
	confPlantillaBase();
	$("#botonRemove_0").hide()
desbloquearPantalla(); 
	$('#bodyClass').addClass('in');
});