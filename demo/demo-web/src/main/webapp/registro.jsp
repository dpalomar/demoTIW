<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="usuario" method="post">

<fieldset>
	<legend>Datos</legend>
<label for="nombre">Nombre:</label>
<input type="text" name="nombre" id="nombre"/>
<br />
<label for="apellidos">Apellidos:</label>
<input type="text" name="apellidos" id="apellidos"/>
<br />
<label for="nick">Nick:</label>
<input type="text" name="nick" id=""/>
<br />
<label for="password">Password:</label><input type="password" name="password" id="password" />
</fieldset>
<fieldset>
	<legend>Direcci&oacute;n</legend>
	<label for="calle">Calle:</label>
	<input type="text" name="calle" id="calle"/>
	<br />
	<label for="poblacion">Poblaci&oacute;n</label>
	<input type="text" name="poblacion" id="poblacion"/>
	<br />
	<label for="cp">C&oacute;digo Postal</label>
	<input type="number" name="cp" id="cp" />
	<br />
	<label for="numero">N&uacute;mero</label>
	<input type="number" name="numero" id="numero"/>
	<br />
</fieldset>
<fieldset>
	<legend>ROL</legend>
	<select name="rol" id="rol">
		<option value="ESTUDIANTE">ESTUDIANTE</option>
		<option value="PROFESOR">PROFESOR</option>
		<option value="ADMIN">ADMINISTRADOR</option>
	</select>
</fieldset>
<input type="submit" />

</form>
</body>
</html>