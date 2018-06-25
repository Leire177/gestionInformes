var formLength=0;
var grafico;
var listaEnfermedades;
var listaMedicmentos;
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
	debugger;
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
        var id=$(this).attr("id");
        var nombre = id.split("_");
        $('[id=formularioMed_'+nombre[1]+']').remove();
	})
	 if(formLength!=0){
	    	$("#medicamento_"+formLength).autocomplete({
	      	    source: listaMedicamentos
	      	  });
	    }
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
	 if(formLength!=0){
	    	$("#enfermedad_"+formLength).autocomplete({
	      	    source: listaEnfermedades
	      	  });
	    }
    formLength++;
}
jQuery(document).ready(function($) {
	bloquearPantalla();
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
	$('#fechaDesde').datepicker( $.datepicker.regional[ "es" ] );
	$('#fechaHasta').datepicker( $.datepicker.regional[ "es" ] );
	$('#fechaDesde').datepicker({
	    dateFormat: 'dd/mm/yy',
	    changeYear: true,
	    constrainInput: true
	 });
	$('#fechaHasta').datepicker({
		dateFormat: 'dd/mm/yy',
		changeYear: true,
		constrainInput: true
	 });
	$("#opcionGrafico").on("change",function(e){
		 $("#botonFiltrar").click();
	});
	$("#opcionFechas").on("change",function(e){
		 $("#botonFiltrar").click();
	});
	 $("#botonLimpiar").on("click",function(e){
		 $("#listaEnf_form")[0].reset();
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
				  ,fechaDesde:fechaD, fechaHasta:fechaH, order:$("#opcionFechas").val()
				 };
		 $.ajax({
		        url: "/GestionInformes/gesInformesBasico/filtroBasico",
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
		})
		$("#listaEnf_form").validate({
			rules: {
			    "fechaDesde": {
			      date: true
			    },
			    "fechaHasta": {
				   date: true
				 }
			  }
		});
	 	getListaMedicamentos();
		getListaEnfermedades();
		desbloquearPantalla();
		$('#bodyClass').addClass('in');
});