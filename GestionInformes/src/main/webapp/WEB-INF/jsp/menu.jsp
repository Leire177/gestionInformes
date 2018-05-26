<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarsExample03">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
          
            <a class="nav-link" href="${contextPath}/inicio/maint"><i class="fas fa-home"></i>Inicio <span class="sr-only"></span></a>
          </li>
          <li class="nav-item active">
          
            <a class="nav-link" href="${contextPath}/cargaInformes/maint"><i class="fas fa-cogs"></i>Carga de informes</a>
          </li>
          <li class="nav-item dropdown active">
         
            <a class="nav-link dropdown-toggle" href="" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="fas fa-chart-bar"></i>Información estadística</a>
            <div class="dropdown-menu" aria-labelledby="dropdown03">
              <a class="dropdown-item" href="${contextPath}/gesInformesBasico/maint">Búsqueda básica</a>
              <a class="dropdown-item" href="${contextPath}/gesInformesAvanzado/maint">Búsqueda avanzada</a>
            </div>
          </li>
          
        </ul>
        <ul class="nav navbar-nav navbar-right">
    	<li><img src="../resources/css/images/logo4.png" class="logo" ></li>
    </ul>
      </div>
    </nav>