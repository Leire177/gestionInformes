<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="subidaFicheros" class="cargaInformes image2">
	<fieldset class="fieldset">
		<form id="cargaFicherosForm"
			action="/GestionInformes/cargaInformes/uploadFiles" method="post"
			enctype="multipart/form-data">
			<label for="informes">Informes</label>
			<div class="form-group">
				<input id="ficheros" name="ficheros" type="file"
					class="file form-control" multiple>
			</div>
			<div class="form-group">
				<label for="hospital">Hospital</label> <select id="listaHospitales"
					name="id" class="form-control"></select>
			</div>
			<input id="submit" type="submit" tabindex="5" value="Cargar ficheros"
				class="btn btn-outline-info float-md-right">
		</form>
	</fieldset>
	<div id="tablaIncorrectos" style="display:none;">
	<fieldset class="fieldset">
		<table class="table table-hover">

			<!--Table head-->
			<thead>
				<tr>
					<th>#</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Username</th>
				</tr>
			</thead>
			<!--Table head-->

			<!--Table body-->
			<tbody>
				<tr>
					<th scope="row">1</th>
					<td>Mark</td>
					<td>Otto</td>
					<td>@mdo</td>
				</tr>
				<tr>
					<th scope="row">2</th>
					<td>Jacob</td>
					<td>Thornton</td>
					<td>@fat</td>
				</tr>
				<tr>
					<th scope="row">3</th>
					<td colspan="2">Larry the Bird</td>
					<td>@twitter</td>
				</tr>
			</tbody>
			<!--Table body-->

		</table>
		</fieldset>
	</div>
</div>