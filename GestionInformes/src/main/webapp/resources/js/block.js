function bloquearPantalla(mensaje,callback){
	
	mensaje = " Cargando...";
	
	jQuery.blockUI({	
		css: {
			    border: 'none', 
			    width:	'100%',
			    height: 'auto',
			    left: '0px',
			    backgroundColor: '#FFF',
			    '-webkit-border-radius': '0px', 
			    '-moz-border-radius': '0px',
			    'border-radius': '0px',
				'background-size': '12%',
			    opacity: 0.8, 
			    color: '#F0256F'
		}
		, message: function(){
			return(
					jQuery('<div class="loadText">')
						.prepend(jQuery("<img>")
							.addClass("imgLoadMessage")
							.attr("src","/GestionInformes/resources/css/images/load.gif")));
		}
		, baseZ: 1004
		, onBlock: function() {
			if(callback !== undefined && typeof callback === "function"){
				callback();
			}
        }
	});

}

function desbloquearPantalla(callback){
	jQuery('.aplic')
		.show('fade', 200, function(){
			jQuery.unblockUI({ 
                onUnblock: function(){ 
                	if(callback !== undefined && typeof callback === "function"){
        				callback();
        			}
                } 
            });
		});
}
jQuery(document).ready(function($) {
	bloquearPantalla();
});

