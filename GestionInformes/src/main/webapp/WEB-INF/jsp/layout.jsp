<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
"http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<spring:url value="/resources/css/main.css" var="maincss" />
<spring:url value="/resources/css/jquery-ui.min.css" var="uicss" />
<spring:url value="/resources/css/jquery-ui.structure.min.css" var="structurecss" />
<spring:url value="/resources/css/jquery-ui.theme.min.css" var="themcss" />
<spring:url value="/resources/css/images" var="images" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.11/css/all.css" integrity="sha384-p2jx59pefphTFIpeqCcISO9MdVfIm4pNnsL08A6v5vaQc4owkQqxMV8kg4Yvhaw/" crossorigin="anonymous">
 <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.7/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
<!--  <link href="https://cdn.jsdelivr.net/npm/gijgo@1.9.6/css/gijgo.min.css" rel="stylesheet" type="text/css" /> -->
<!--  <script src="https://cdnjs.cloudflare.com/ajax/libs/gijgo/1.9.6/combined/js/gijgo.min.js "></script> -->

<link href="${maincss}" rel="stylesheet" />
<link href="${uicss}" rel="stylesheet" />
<link href="${themecss}" rel="stylesheet" />
<link href="${structurecss}" rel="stylesheet" />
<spring:url value="/resources/js/jquery.blockUI.js" var="blockUI" />
<script type="text/javascript" src="${blockUI}"></script> 
<spring:url value="/resources/js/block.js" var="block" />
<script type="text/javascript" src="${block}"></script> 
<spring:url value="/resources/js/Chart.js" var="charts" />
<script type="text/javascript" src="${charts}"></script> 
<spring:url value="/resources/js/jquery-ui.min.js" var="jsUI" />
<script type="text/javascript" src="${jsUI}"></script> 
<spring:url value="/resources/js/datepicker-es.js" var="jsUIEs" />
<script type="text/javascript" src="${jsUIEs}"></script> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title><tiles:insertAttribute name="title" ignore="true" /></title>  
</head>  
<body>  
 	<div id="bodyClass" class="aplic aplic-fade">
		<div class="contenedor">
			<!-- Cabecera -->
			<tiles:insertAttribute name="header" />
			<!-- Menu -->
			<tiles:insertAttribute name="menu" />
			<!-- Contenidos -->
			<div class="content">
				<tiles:insertAttribute name="body" />
			</div>
			<!-- Pie -->
			<tiles:insertAttribute name="footer" />
			<!-- Includes JS -->
			<tiles:insertAttribute name="includes" />
		</div>
	</div>
	

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>  
</html>  