var formLength = 0;
var grafico;
var listaEnfermedades;
var listaMedicamentos;

function crearGrafico(data){
	var fechas = new Array();
	var numInformes = new Array();
	
	 for(var i = 0;i<data.length;i++){
		 fechas.push(fechasGrafico(data[i].fechaStr));
		 numInformes.push(data[i].numInformes);
		 
	 }
	 dibujarGrafico(fechas,numInformes);
	 
}
function fechasGrafico(fecha){
	
	//MODIFICAR
	var meses = new Array ("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre");
	var rdo;
	var split = fecha.split('/');
	var date = new Date(split[2], split[1] - 1, split[0]); //Y M D 
	if($("#opcionFechas").val()=="anyos"){
		rdo=date.getFullYear();
	}
	if($("#opcionFechas").val()=="meses"){
		rdo=meses[date.getMonth()] + " " +date.getFullYear();
	}
	
	if($("#opcionFechas").val()=="dias"){
		rdo = fecha;
	}
	
	return rdo;
	
}

function dibujarGrafico(fechas,numInformes){
	var ctx = document.getElementById('myChart').getContext('2d');

	var purple_orange_gradient = ctx.createLinearGradient(0, 0, 0, 600);
	purple_orange_gradient.addColorStop(0, 'orange');
	purple_orange_gradient.addColorStop(1, 'purple');
	
	if(grafico == undefined ||$("#opcionGrafico").val()=="lines" ){
		if(grafico!=undefined){
			grafico.destroy();
		}
		grafico = new Chart(ctx, {
			  type: 'line',
			  data: {
			    labels: fechas,
			    datasets: [{ 
			        data: numInformes,
			        label: "Informes",
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
			    scales: {
			    	yAxes: [{
			    		gridLines: {
	                        color: "rgba(0, 0, 0, 0)",
	                    },
	                    ticks: {
	                        beginAtZero: true,
	                        max: Math.max.apply(Math, numInformes)+ 5,
	                        userCallback: function(label, index, labels) {
	                            // when the floored value is the same as the value we have a whole number
	                            if (Math.floor(label) === label) {
	                                return label;
	                            }

	                        },
	                    }
			        }],
			        xAxes: [{
			            display: true,
			            ticks: {
			                beginAtZero: true,
			                min: 0
			            },
			            gridLines: {
	                        color: "rgba(172, 204, 226, 1)",
	                    }
			        }]
			    },
			    responsive:true
			  }
			});
		grafico.update();
	}else{
		if($("#opcionGrafico").val()=="bars"){
			grafico.destroy();
			grafico = new Chart(ctx, {
				  type: 'bar',
				  data: {
				    labels: fechas,
				    datasets: [{ 
				        data: numInformes,
				        label: "Informes",
				        borderColor: "#3e95cd",
				        backgroundColor: purple_orange_gradient,
				        fill: false
				      }
				    ]
				  },
				  options: {
				    title: {
				      display: true,
				      text: ''
				    },
				    scales: {
				    	yAxes: [{
				    		gridLines: {
		                        color: "rgba(0, 0, 0, 0)",
		                    },
		                    ticks: {
		                        beginAtZero: true,
		                        max: Math.max.apply(Math, numInformes)+ 5,
		                        userCallback: function(label, index, labels) {
		                            // when the floored value is the same as the value we have a whole number
		                            if (Math.floor(label) === label) {
		                                return label;
		                            }

		                        },
		                    }
				        }],
				        xAxes: [{
				            display: true,
				            ticks: {
				                beginAtZero: true,
				                min: 0
				            },
				            gridLines: {
		                        color: "rgba(172, 204, 226, 1)",
		                    }
				        }]
				    },
				    responsive:true
				  }
				});
			grafico.update();
		}
	}	
	
	
}
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
	    
	    if(formLength!=0){
	    	$("#medicamento_"+formLength).autocomplete({
	      	    source: listaMedicamentos
	      	  });
	    	$("#enfermedad_"+formLength).autocomplete({
	      	    source: listaEnfermedades
	      	  });
	    }
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
function getListaEnfermedades(){
	$.ajax({
        url: "/GestionInformes/enfermedades/getAll",
        datatype : "application/json",
        type: "GET",
        async: true,
        success: function (data) {
        listaEnfermedades = new Array();
       	 for(var i=0; i<data.length;i++){
       		 listaEnfermedades.push(data[i].descripcion);
       	 }

	 	$("#enfermedad_0").autocomplete({
	 	    source: listaEnfermedades
	 	  });
        },
        error(jqXHR, textStatus, errorThrown) {
            alert('Something wrong happened because: ' + errorThrown)
        }
    });
	
}
function getListaMedicamentos(){
	$.ajax({
        url: "/GestionInformes/medicamentos/getAll",
        datatype : "application/json",
        type: "GET",
        async: true,
        success: function (data) {
        	listaMedicamentos = new Array();
       	 for(var i=0; i<data.length;i++){
       		 listaMedicamentos.push(data[i].descripcion);
       	 }
      	$("#medicamento_0").autocomplete({
  	    source: listaMedicamentos
  	  });
        },
        error(jqXHR, textStatus, errorThrown) {
            alert('Something wrong happened because: ' + errorThrown)
        }
    });
	
}
jQuery(document).ready(function($) {
	$.ajax({
        url: "/GestionInformes/hospitales/getAll",
        datatype : "application/json",
        type: "GET",
        async: true,
        success: function (data) {
       	 var $dropdown = $("#listaHospitales");
       	$dropdown.append($("<option />").val("").text(""));
       	 $.each(data, function() {
       	     $dropdown.append($("<option />").val(this.id).text(this.nombre));
       	 });
        },
        error(jqXHR, textStatus, errorThrown) {
            alert('Something wrong happened because: ' + errorThrown)
        }
    });
	$('#fechaDesde').datepicker( $.datepicker.regional[ "es" ] );
	$('#fechaHasta').datepicker( $.datepicker.regional[ "es" ] );
	$('#fechaDesde').datepicker({
	    format: 'dd/mm/yyyy'
	 });
	$('#fechaHasta').datepicker({
	    format: 'dd/mm/yyyy'
	 });
	
	confPlantillaBase();
	$("#botonRemove_0").hide();
	
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
			  ,fechaDesde:fechaD, fechaHasta:fechaH, order:$("#opcionFechas").val()
			 };
	 $.ajax({
	        url: "/GestionInformes/gesInformesAvanzado/filtroAvanzado",
	        type: "POST"
	        , contentType: "application/json"
			, data:JSON.stringify(datos)
	        ,async: true,
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
	});
	$("#opcionFechas").on("change",function(e){
		 $("#botonFiltrar").click();
	});
	$("#opcionGrafico").on("change",function(e){
		 $("#botonFiltrar").click();
	});
	$("#botonLimpiar").on("click",function(e){
		 $("#listaEnf_form")[0].reset();
	 });

	getListaMedicamentos();
	getListaEnfermedades();
desbloquearPantalla(); 
	$('#bodyClass').addClass('in');
});