var formLength=0;
function crearGrafico(data){
//	$("#myChart").remove();
//	""
	var fechas = new Array();
	var numInformes = new Array();
	
 for(var i = 0;i<data.length;i++){
	 fechas.push(data[i].fecha);
// fechas.push(i);
	 numInformes.push(data[i].numInformes);
 }
	 if($("#opcionGrafico").val()=="bars"){
		 crearGraficoBar(fechas,numInformes);
	 }else{
		 crearGraficoLine(fechas,numInformes);
	 }
}
function crearGraficoLine(fechas,numInformes){
	new Chart(document.getElementById("myChart"), {
		  type: 'line',
		  data: {
		    labels: fechas,
		    datasets: [{ 
		        data: numInformes,
		        label: "Nº Informes",
		        borderColor: "#3e95cd",
		        fill: false
		      }
		    ]
		  },
		  options: {
		    title: {
		      display: true,
		      text: ''
		    },
		    responsive:true
		  }
		});
}
function crearGraficoBar(fechas,numInformes){
	new Chart(document.getElementById("myChart"), {
	    type: 'bar',
	    data: {
	    	labels: fechas,
	      datasets: [
	        {
	          label: "Nº Informes",
	          backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f","#e8c3b9","#c45850"],
	          data:numInformes
	        }
	      ]
	    },
	    options: {
	      legend: { display: false },
	      title: {
	        display: true,
	        responsive:true,
	        text: ''
	      }
	    }
	});
	
}
jQuery(document).ready(function($) {
	
	
	function confPlantillaMed(){
		var long = formLength;

	    $('<div/>', {
	        'class' : 'formulario form-inline','id':'formularioMed_'+long,html: GetHtmlMed()
	    }).hide().appendTo('#divMed');
	    $(".formulario").show();
	    $('[id=enfermedad_'+formLength+']').hide();
	    $('[id=botonAddEnf_'+formLength+']').hide();
	    $('[id=botonRemoveEnf'+formLength+']').hide();
	    
	    $('[id=botonAddMed_'+formLength+']').on("click",function(e){
	    	e.preventDefault();
	        e.stopImmediatePropagation();
			confPlantillaMed();
		})
		$('[id=botonRemoveMed_'+formLength+']').on("click",function(e){
	    	e.preventDefault();
	        e.stopImmediatePropagation();
	        debugger;
	        var id=$(this).attr("id");
	        var nombre = id.split("_");
	        $('[id=formularioMed_'+nombre[1]+']').remove();
		})
		
		
		
	    formLength++;

	}
	function confPlantillaEnf(){
		var long = formLength;

	    $('<div/>', {
	        'class' : 'formulario form-inline','id':'formularioEnf_'+long,html: GetHtmlEnf()
	    }).hide().appendTo('#divEnf');
	    $(".formulario").show();
	    
	    $('[id=medicamento_'+formLength+']').hide();
	    $('[id=botonAddMed_'+formLength+']').hide();
	    $('[id=botonRemoveMed_'+formLength+']').hide();
		$('[id^=botonAddEnf_'+formLength+']').on("click",function(e){
			e.preventDefault();
	        e.stopImmediatePropagation();
			confPlantillaEnf();
		});
		$('[id=botonRemoveEnf_'+formLength+']').on("click",function(e){
	    	e.preventDefault();
	        e.stopImmediatePropagation();
	        debugger;
	        var id=$(this).attr("id");
	        var nombre = id.split("_");
	        $('[id=formularioEnf_'+nombre[1]+']').remove();
		})
	    formLength++;
	}
	function confPlantillaBase(){
		var long = formLength;
	    $('<div/>', {
	        'class' : 'formulario form-inline','id':'formularioEnf_'+long,html: GetHtmlEnf()
	    }).hide().appendTo('#divEnf');
	    $(".formulario").show();
	    $('<div/>', {
	        'class' : 'formulario form-inline','id':'formularioMed_'+long,html: GetHtmlMed()
	    }).hide().appendTo('#divMed');
	    $(".formulario").show();
	    
	    $('[id=botonAddMed_'+formLength+']').on("click",function(e){
	    	e.preventDefault();
	        e.stopImmediatePropagation();
			confPlantillaMed();
		})
		$('[id^=botonAddEnf_'+formLength+']').on("click",function(e){
			e.preventDefault();
	        e.stopImmediatePropagation();
			confPlantillaEnf();
		})
		$("#botonRemoveEnf_0").hide();
	    $("#botonRemoveMed_0").hide();
	    formLength++;
	}
	function GetHtmlMed(){
		var len =formLength;
		var $html = $('.formularioTemplateMed').clone().prop('id', 'formularioMed_'+len );
		
		$html.find('[id=medicamento]').attr("id", "medicamento_" + len).attr("name", "listaMedsFiltro.descripcion");
		$html.find('[id=botonAddMed]').attr("id", "botonAddMed_" + len);
		$html.find('[id=botonRemoveMed]').attr("id", "botonRemoveMed_" + len);

		return $html.html();
	}
	function GetHtmlEnf(){
		var len =formLength;
		var $html = $('.formularioTemplateEnf').clone().prop('id', 'formularioEnf_'+len );
		
		$html.find('[id=enfermedad]').attr("id", "enfermedad_" + len).attr("name", "listaEnfsFiltro.descripcion");
		$html.find('[id=botonAddEnf]').attr("id", "botonAddEnf_" + len);
		$html.find('[id=botonRemoveEnf]').attr("id", "botonRemoveEnf_" + len);

		return $html.html();
	}
	$.ajax({
        url: "/GestionInformes/hospitales/getAll",
        datatype : "application/json",
        type: "GET",
        async: false,
        success: function (data) {
       	 var $dropdown = $("#listaHospitales");
       	 $.each(data, function() {
       	     $dropdown.append($("<option />").val(this.id).text(this.nombre));
       	 });
        },
        error(jqXHR, textStatus, errorThrown) {
            alert('Something wrong happened because: ' + errorThrown)
        }
    });
	confPlantillaBase();
	$('[id=botonAddMed_0]').on("click",function(e){
		e.preventDefault();
        e.stopImmediatePropagation();
		confPlantillaMed();
	})
	$('[id=botonAddEnf_0]').on("click",function(e){
		e.preventDefault();
        e.stopImmediatePropagation();
        confPlantillaEnf();
	})
	$('#fechaDesde').datepicker({
	    format: 'dd/mm/yyyy'
	 });
	$('#fechaHasta').datepicker({
	    format: 'dd/mm/yyyy'
	 });
	$("#opcionGrafico").on("change",function(e){
		 $("#botonFiltrar").click();
	});
	 $("#botonFiltrar").on("click",function(e){
	    	// /SUBMIT
		 bloquearPantalla();
		 var datos= new Object();
		 var enfs= new Array();
		 var meds= new Array();
		 $("input[id^='enfermedad_']").each(function(i){
			 var enfermedad = new Object();
			 enfermedad.descripcion = $(this).val();
			 enfs.push(enfermedad);
		 }); 
		 $("input[id^='medicamento_']").each(function(i){
			 var medicamento = new Object();
			 medicamento.descripcion = $(this).val();
			 meds.push(medicamento);
		 });
		 var hosp = new Object();
		 var fechaD = $("#fechaDesde").val();
		 var fechaH = $("#fechaHasta").val();
		 hosp.id = $("#listaHospitales").val();
		 datos={
				 listaEnfsFiltro: enfs, 
				 listaMedsFiltro:meds, 
				 hospital:hosp
				  ,fechaDesde:fechaD, fechaHasta:fechaH
				 };
		 $.ajax({
		        url: "/GestionInformes/gesInformesBasico/filtroBasico",
		        type: "POST"
		        , contentType: "application/json"
				, data:JSON.stringify(datos)
		        ,async: false,
		        success: function (data) {
		        	desbloquearPantalla();
		        	crearGrafico(data);
		        	$("#fieldsetGrafico").show();
		        },
		        error(jqXHR, textStatus, errorThrown) {
		        	desbloquearPantalla();
		            alert('Something wrong happened because: ' + errorThrown)
		        }
		    });
		})
desbloquearPantalla(); 
});