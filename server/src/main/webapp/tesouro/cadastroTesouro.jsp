<html>
<body>
	<h2>Cadastro Tesouro</h2>
	
	<form method="post" action="<%=request.getContextPath()%>/cadastroTesouro">
		Nome:<br>
		<input type="text" name="nome" />
		<br><br>
		Valor:<br>
		<input type="text" name="valor" />
		<br><br>
		Data Fim:<br>
		<input type="text" name="data_fim" />
		<br><br>
		Tipo:<br>
		<input type="text" name="tipo" />
		<br><br>
		<input type="submit" name="Enviar" />
	</form>
</body>
</html>
