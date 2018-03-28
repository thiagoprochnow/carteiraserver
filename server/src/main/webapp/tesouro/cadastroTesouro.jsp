<%@page import="br.com.guiainvestimento.domain.Provento"%>
<%@page import="br.com.guiainvestimento.domain.TesouroService"%>
<%@page import="br.com.guiainvestimento.domain.Tesouro"%>
<%@page import="java.util.List"%>

<html>
<body>
	<h2>Cadastro Tesouro</h2>
	
	<form method="post" action="<%=request.getContextPath()%>/gettesouro">
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
		<br><br><br>
		
		<table width="600" border="1">
		<tr>
			<td>ID</td>
			<td>NOME</td>
			<td>VALOR</td>
			<td>DATA FIM</td>
			<td>TIPO</td>
			<td>ATUALIZADO</td>
		</tr>
		
		<% TesouroService service = new TesouroService();
		   List<Tesouro> tesouros = service.getTesouros();
		   String output = "";
		   for (Tesouro tesouro : tesouros) {
			   output += "<tr>";
			   output += "<td>" + tesouro.getId() + "</td>";
			   output += "<td>" + tesouro.getNome() + "</td>";
			   output += "<td>" + tesouro.getValor() + "</td>";
			   output += "<td>" + tesouro.getData() + "</td>";
			   output += "<td>" + tesouro.getTipo() + "</td>";
			   output += "<td>" + tesouro.getAtualizado() + "</td>";
			   output += "</tr>";
			}
		   out.write(output);
		   %>
		</table>
	</form>
</body>
</html>
