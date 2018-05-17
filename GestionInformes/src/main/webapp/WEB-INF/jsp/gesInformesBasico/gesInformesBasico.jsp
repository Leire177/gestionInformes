<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="gesInformes" class="cargaInformes">
	<div class="formularioTemplateEnf" id="formularioEnf"
		style="display: none">

		<input type="hidden" name="id" class="form-control" id="id" />
		<div class="col-md-9">
			<input id="enfermedad" name="listaEnfsFiltro.descripcion" class="form-control">
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
			<input id="medicamento" name="listaMedsFiltro.descripcion" class="form-control">
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
							<select id="listaHospitales" name="hospital.id" class="form-control"></select>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="form-row">
					<div class="col-md-6">
						<div class="col-md-6">
							<label for="hospital">Fecha desde</label>
						</div>
						<div class="col-md-4">
							<input id="fechaDesde" name="fechaDesde">
						</div>
					</div>
					<div class="col-md-6">
						<div class="col-md-4">
							<label for="fechaHasta">Fecha hasta</label>
						</div>
						<div class="col-md-4">
							<input id="fechaHasta" name="fechaHasta" >
						</div>
					</div>
				</div>
			</div>
			<input id="botonFiltrar" type="button" tabindex="5" value="Filtrar"
				class="btn btn-outline-info float-md-right" style="margin-bottom: 15px;">

		</form>
	</fieldset>
	<fieldset id="fieldsetGrafico" style="display:none;"class="fieldset">
		<div>
		<select id="opcionGrafico"  class="form-control">
		<option value="lines">Gr�fico de lineas</option>
		<option value="bars">Gr�fico de barras</option>
		</select>
		</div>
		<div id="crearCamvas" style="width:80%;text-align:center;">
			<canvas id="myChart" ></canvas>
		</div>
	</fieldset>
</div>