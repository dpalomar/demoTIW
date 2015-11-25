<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.error{color:red;}
.info{color:gray;}
a.escribir {
	background-color:#44c767;
	-moz-border-radius:28px;
	-webkit-border-radius:28px;
	border-radius:28px;
	border:1px solid #18ab29;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:Arial;
	font-size:17px;
	padding:16px 31px;
	text-decoration:none;
	text-shadow:0px 1px 0px #2f6627;
}
a.escribir:hover {
	background-color:#5cbf2a;
}
a.escribir:active {
	position:relative;
	top:1px;
}
a.leer {
	background-color:#455bc7;
	-moz-border-radius:28px;
	-webkit-border-radius:28px;
	border-radius:28px;
	border:1px solid #192dab;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:Arial;
	font-size:17px;
	padding:16px 31px;
	text-decoration:none;
	text-shadow:0px 1px 0px #282d66;
}
a.leer:hover {
	background-color:#2a36bd;
}
a.leer:active {
	position:relative;
	top:1px;
}

</style>
</head>
<body>
<!-- Inclusión de una cabecera que muestra/oculta la opción Logout -->
<jsp:include page="header.jsp"></jsp:include>
<h1>${mensaje }  <span class="info">(Este mensaje proviene de un EJB)</span></h1>
<h2>Tu nombre es: ${sessionScope.usuario.nombre} <span class="info">(Este mensaje proviene de la sesion)</span></h2> <!-- Esto saca el objeto usuario almacenado en la sesion por el LoginServlet y accede a su propiedad nombre -->
<h2>tu clave es: ${param.password } <span class="info">(Este mensaje proviene del request)</span></h2> <!-- Esto saca el parametro pasado por el usuario y recogido dentro del objeto request -->
<c:if test="${empty param.password }">
<p class="error">Si ves el nombre de usuario pero no la password es porque el nombre esta almacenado en session, mientras que la password en request.</p>
</c:if>
<h3>Aqui tienes el listado de usuarios</h3>
<c:if test="${empty usuarios }"> <!-- usuarios es un atributo metido en el request por eso no es necesario ponerle el prefijo param -->
<p class="error">Si no ves usuarios es porque has accedido directamente a la pagina y por tanto no has pasado
por el servlet controlador y no hay datos en el objeto request.
</p>
</c:if>
<table border="1">
<tr>
<th>Nombre</th>
<th>Apellidos</th>
</tr>
<c:forEach items="${usuarios }" var="usuario"> <!-- recorremos todos los objetos de la coleccion usuarios y cada objeto devuelto lo asignamos a la variable usuario -->
<tr>
	<td>${usuario.nombre }</td> <!-- Usuario es un POJO por lo que podemos acceder a sus propiedades sin necesidad de get/set -->
	<td>${usuario.apellidos }</td>
	<c:if test="${usuario.id != sessionScope.usuario.id }">
		<td ><a href="escribirMensaje.jsp?idFrom=${sessionScope.usuario.id }&idTo=${usuario.id}" class="escribir">Enviar Mensaje</a></td>
	</c:if>
		<c:if test="${usuario.id == sessionScope.usuario.id }">
		<td ><a href="mensajes?accion=leer" class="leer">Leer Mensajes</a></td>
	</c:if>
</tr>

</c:forEach>
</table>
<p><a href="login.jsp">Volver</a></p>
</div>
</body>
</html>




