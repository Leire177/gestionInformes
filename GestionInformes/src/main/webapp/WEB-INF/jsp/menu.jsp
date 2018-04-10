<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!-- <nav class="navbar navbar-expand-lg navbar-light bg-light"> -->
<!--   <div class="collapse navbar-collapse" data-toggle="collapse"id="navbarNav"> -->
<!--     <ul class="navbar-nav"> -->
<!--       <li class="nav-item active"> -->
<%--         <a class="nav-link" href="${contextPath}/inicio/maint">Inicio</a> --%>
<!--       </li> -->
<!--       <li class="nav-item"> -->
<%--         <a class="nav-link" href="${contextPath}/cargaInformes/maint">Carga de informes</a> --%>
<!--       </li> -->
<!--       <li class="nav-item dropdown"> -->
<!--         <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> -->
<!--           Gestión de informes -->
<!--         </a> -->
<!--         <div class="dropdown-menu" aria-labelledby="navbarDropdown"> -->
<!--           <a class="dropdown-item .bg-light" href="#">Búsqueda básica</a> -->
<!--           <a class="dropdown-item .bg-light" href="#">Búsqueda relacionada</a> -->
<!--         </div> -->
<!--       </li> -->
<!--     </ul> -->
<!--   </div> -->
<!-- </nav> -->
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarsExample03">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
            <a class="nav-link" href="${contextPath}/inicio/maint">Inicio <span class="sr-only"></span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${contextPath}/cargaInformes/maint">Carga de informes</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Información estadística</a>
            <div class="dropdown-menu" aria-labelledby="dropdown03">
              <a class="dropdown-item" href="#">Búsqueda básica</a>
              <a class="dropdown-item" href="#">Búsqueda relacionada</a>
            </div>
          </li>
        </ul>
      </div>
    </nav>