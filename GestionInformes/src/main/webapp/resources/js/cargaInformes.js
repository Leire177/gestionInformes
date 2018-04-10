jQuery(document).ready(function($) {

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
	 $( "#cargaFicherosForm" ).validate({
		  rules: {
		    "ficheros": {
		      required: true,
		      extension: "ann|txt"
		    }
		  }
		});
	 
});