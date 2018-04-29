<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="subidaFicheros" class="cargaInformes image2">
<c:if test="${feedback eq true}">
		<div class="alert alert-success" style="margin-top: 15px;">
			<strong>Correcto!</strong> Todos los informes se han cargado correctamente.
		</div>
	</c:if>
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
				class="btn btn-outline-info float-md-right" style="margin-bottom: 15px;">
		</form>
	</fieldset>
	
	<c:if test="${not empty listaErroneos}">
		<div class="alert alert-warning" style="margin-top: 15px;">
			<strong>Cuidado!</strong> Los siguientes informes no han podido ser
			cargados. Revise que el nombre del archivo sea correcto, que se ha
			adjuntado su correspondiente fichero.txt y cárguelos nuevamente.
		</div>
		<div id="tablaIncorrectos">
			<fieldset class="fieldset">
				<table class="tableAplic table table-hover">

					<!--Table head-->
					<thead>
						<tr>
							<th>#</th>
							<th>Informe</th>
						</tr>
					</thead>
					<!--Table head-->

					<!--Table body-->
					<tbody>
						<c:forEach items="${listaErroneos}" var="erroneo" varStatus="loop">
							<tr>
								<th scope="row">${loop.index + 1}</th>
								<td>${erroneo.nombreInforme}</td>

							</tr>
						</c:forEach>
					</tbody>
					<!--Table body-->

				</table>
			</fieldset>
		</div>
	</c:if>
</div>