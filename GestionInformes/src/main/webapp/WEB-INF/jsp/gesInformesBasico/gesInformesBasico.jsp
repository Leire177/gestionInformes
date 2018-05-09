<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="gesInformes" class="cargaInformes image2">
	<%-- 	<form> --%>
	<!-- 		<fieldset class="fieldset"> -->
	<!-- 			<div class="form-row"> -->

	<!-- 				<label for="enfermedad">Enfermedad</label> -->
	<!-- 				<div class="form-inline col-md-12"> -->
	<!-- 				<div class="col-4"> -->
	<!-- 					<input id="enfermedad" name="enfermedad" class="form-control"></div> -->
	<!-- 					<img id="boton" src="../resources/css/images/addIcon.png" -->
	<!-- 						style="width: 80px"> -->
	<!-- 				</div> -->
	<!-- 			</div> -->
	<!-- 			<div class="form-row"> -->

	<!-- 				<label for="medicamento">Medicamento</label> -->
	<!-- 				<div class="form-inline col-md-12"> -->
	<!-- 				<div class="col-4"> -->
	<!-- 					<input id="medicamento" name="medicamento" class="form-control"></div> -->
	<!-- 					<img id="boton" src="../resources/css/images/addIcon.png" -->
	<!-- 						style="width: 80px"> -->
	<!-- 				</div> -->
	<!-- 			</div> -->
	<!-- 		</fieldset> -->
	<!-- 		<fieldset class="fieldset"> -->
	<!-- 			<div> -->
	<%-- 				<canvas id="myChart" width="400" height="400"></canvas> --%>
	<!-- 			</div> -->
	<!-- 		</fieldset> -->
	<%-- 	</form> --%>
	<div class="formularioTemplateEnf" id="formularioEnf"
		style="display: none">

		<input type="hidden" name="id" class="form-control" id="id" />
		<div class="col-md-9">
			<input id="enfermedad" name="enfermedad" class="form-control">
		</div>
		<!-- 				<div class="col-md-4"> -->
		<img id="botonAddEnf" src="../resources/css/images/addIcon.png"
			style="width: 74px; margin-left: -12px;"> <img
			id="botonRemoveEnf" src="../resources/css/images/removeIcon.png"
			style="width: 74px; margin-left: -30px;">
		<!-- 					</div> -->
	</div>
	<div class="formularioTemplateMed" id="formularioMed"
		style="display: none">

		<input type="hidden" name="id" class="form-control" id="id" />
		<div class="col-md-9">
			<input id="medicamento" name="medicamento" class="form-control">
		</div>
		<img id="botonAddMed" src="../resources/css/images/addIcon.png"
			style="width: 74px; margin-left: -12px;"> <img
			id="botonRemoveMed" src="../resources/css/images/removeIcon.png"
			style="width: 74px; margin-left: -30px;">
	</div>
	<fieldset class="fieldset">
		<form id="listaEnf_form" class="form-horizontal">

			<div class="form-row">
				<div id="divEnf" class="col-md-6">
					<label for="enfLabel" class="control-label col-md-6">Enfermedad</label>
				</div>
				<div id="divMed" class="col-md-6">
					<label for="medLabel" class="control-label col-md-6">Medicamento</label>
				</div>
			</div>

			<div class="form-group">
				<div class="form-row">
					<div class="col-md-12">
						<div class="col-md-4">
							<label for="hospital">Hospital</label>
						</div>
						<div class="col-md-5">
							<select id="listaHospitales" name="id" class="form-control"></select>
						</div>
					</div>
				</div>
			</div>
			<div class="form-row">
				<div class="col-md-6">
					<div class="col-md-4">
						<label for="hospital">Fecha desde</label>
					</div>
					<div class="col-md-4">
						<input id="fechaDesde" data-date-format="mm/dd/yyyy">
					</div>
				</div>
				<div class="col-md-6">
					<div class="col-md-4">
						<label for="fechaHasta">Fecha hasta</label>
					</div>
					<div class="col-md-4">
						<input id="fechaHasta" data-date-format="mm/dd/yyyy">
					</div>
				</div>
			</div>


		</form>
	</fieldset>
	<fieldset class="fieldset">
		<div>
			<canvas id="myChart" width="400" height="400"></canvas>
		</div>
	</fieldset>
</div>