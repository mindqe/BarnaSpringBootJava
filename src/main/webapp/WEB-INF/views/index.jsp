<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="autenticar" method="post">
		<table border="1">
			<tr>
				<td>Usuario</td>
				<td><input type="text" name="usuario"></td>
			</tr>
			<tr>
				<td>Clave</td>
				<td><input type="password" name="clave"></td>
			</tr>
			<tr>
				<td colspan="2"><button type="submit">ENTRAR</button></td>

			</tr>
		</table>
	</form>
	<h1 style="color: : red;">${error }</h1>
	
</body>
</html>