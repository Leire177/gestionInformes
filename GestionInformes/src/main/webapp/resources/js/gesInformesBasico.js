var formLength=0;
jQuery(document).ready(function($) {
	var ctx = document.getElementById("myChart").getContext('2d');
	var myChart = new Chart(ctx, {
	    type: 'bar',
	    data: {
	        labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
	        datasets: [{
	            label: '# of Votes',
	            data: [12, 19, 3, 5, 2, 3],
	            backgroundColor: [
	                'rgba(255, 99, 132, 0.2)',
	                'rgba(54, 162, 235, 0.2)',
	                'rgba(255, 206, 86, 0.2)',
	                'rgba(75, 192, 192, 0.2)',
	                'rgba(153, 102, 255, 0.2)',
	                'rgba(255, 159, 64, 0.2)'
	            ],
	            borderColor: [
	                'rgba(255,99,132,1)',
	                'rgba(54, 162, 235, 1)',
	                'rgba(255, 206, 86, 1)',
	                'rgba(75, 192, 192, 1)',
	                'rgba(153, 102, 255, 1)',
	                'rgba(255, 159, 64, 1)'
	            ],
	            borderWidth: 1
	        }]
	    },
	    options: {
	        scales: {
	            yAxes: [{
	                ticks: {
	                    beginAtZero:true
	                }
	            }]
	        }
	    }
	});
	
	function confPlantillaMed(){
		debugger;
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
		
		
		
	    formLength++;

	}
	function confPlantillaEnf(){
		debugger;
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
		})
	    formLength++;
	}
	function confPlantillaBase(){
		var long = formLength;
		debugger;
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
	    formLength++;
	}
	function GetHtmlMed(){
		var len =formLength;
		var $html = $('.formularioTemplateMed').clone().prop('id', 'formularioMed_'+len );
		
		$html.find('[id=medicamento]').attr("id", "medicamento_" + len).attr("name", "medicamento" + len);
		$html.find('[id=botonAddMed]').attr("id", "botonAddMed_" + len);
		$html.find('[id=botonRemoveMed]').attr("id", "botonRemoveMed_" + len);

		return $html.html();
	}
	function GetHtmlEnf(){
		var len =formLength;
		var $html = $('.formularioTemplateEnf').clone().prop('id', 'formularioEnf_'+len );
		
		$html.find('[id=enfermedad]').attr("id", "enfermedad_" + len).attr("name", "enfermedad" + len);
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
	  $('#fechaDesde').datepicker();
	 $('#fechaHasta').datepicker();
desbloquearPantalla(); 
});