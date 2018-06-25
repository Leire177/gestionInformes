<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="gesInformesAvanzado" class="cargaInformes">
	<div class="formularioTemplate" id="formulario" style="display: none">

		<input type="hidden" name="id" class="form-control" id="id" />
		<div class="col-md-4">
			<input id="enfermedad" name="listaEnfssFiltro.descripcion"
				class="form-control">
		</div>
		<div class="col-md-2">
			<select id="relEnfMed" class="form-control" disabled="disabled">
				<option value="1">Causado por</option>
			</select>
		</div>
		<div class="col-md-4">
			<input id="medicamento" name="listaMedsFiltro.descripcion"
				class="form-control">
		</div>
		<img id="botonAdd" src="../resources/css/images/addIcon.png"
			style="width: 74px; margin-left: -12px;"> <img id="botonRemove"
			src="../resources/css/images/removeIcon.png"
			style="width: 74px; margin-left: -30px;">
	</div>
	<fieldset class="fieldset">
		<form id="listaEnf_form" class="form-horizontal">

			<div class="form-row">
				<div id="divEnf" class="col-md-4">
					<label for="enfLabel" class="control-label col-md-6">Enfermedad</label>
				</div>
				<div id="divEnf" class="col-md-2"></div>
				<div id="divMed" class="col-md-4">
					<label for="medLabel" class="control-label col-md-6">Medicamento</label>
				</div>
			</div>
			<div id="lineas"></div>

			<div class="form-group">
				<div class="form-row">
					<div class="col-md-12">
						<div class="col-md-4">
							<label for="hospital">Hospital</label>
						</div>
						<div class="col-md-4">
							<select id="listaHospitales" name="hospital.id"
								class="form-control"></select>
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
						<div class="col-md-6">
							<input id="fechaDesde" name="fechaDesde" class="form-control">
						</div>
					</div>
					<div class="col-md-6">
						<div class="col-md-6">
							<label for="fechahasta">Fecha hasta</label>
						</div>
						<div class="col-md-6">
							<input id="fechaHasta" name="fechaHasta" class="form-control">
						</div>
					</div>
				</div>
			</div>
			<div class="float-md-right">
				<input id="botonFiltrar" type="button" tabindex="5" value="Filtrar"
					class="btn btn-outline-info"
					style="margin-bottom: 15px;"> <input id="botonLimpiar"
					type="button" tabindex="5" value="Limpiar"
					class="btn btn-outline-info"
					style="margin-bottom: 15px;">
			</div>

		</form>
	</fieldset>
	<fieldset id="fieldsetGrafico" style="display: none;" class="fieldset">
		<div>
			<select id="opcionGrafico" class="form-control">
				<option value="lines">Gráfico de lineas</option>
				<option value="bars">Gráfico de barras</option>
			</select>
		</div>
		<div>
			<select id="opcionFechas" class="form-control">
				<option value="anyos">Años</option>
				<option value="meses">Meses</option>
				<option value="dias">Días</option>
			</select>
		</div>
		<div id="crearCamvas" class="col-md-6 text-center">
			<canvas id="myChart"></canvas>
		</div>
	</fieldset>
</div>