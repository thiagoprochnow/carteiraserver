<%@page import="br.com.guiainvestimento.domain.ProventoService"%>
<%@page import="br.com.guiainvestimento.domain.Provento"%>
<%@page import="java.util.List"%>

<html>
<body>
	<h2>Cadastro Provento</h2>
	
	<form method="post" action="<%=request.getContextPath()%>/getprovento">
		Codigo:<br>
		<input type="text" name="codigo" />
		<br><br>
		Data:<br>
		<input type="text" name="data" />
		<br><br>
		Valor:<br>
		<input type="text" name="valor" />
		<br><br>
		Tipo:<br>
		<input type="text" name="tipo" />
		<br><br>
		Id:<br>
		<input type="text" name="id" />
		<br><br>
		<input type="submit" name="enviar" value="Enviar" />
		<br><br><br><br><br>
		<input type="submit" name="atualizar" value="Atualizar Dados" />
		<br><br><br><br><br>
		<table width="600" border="1">
		<tr>
			<td>ID</td>
			<td>CODIGO</td>
			<td>DATA</td>
			<td>DATA STRING</td>
			<td>VALOR</td>
			<td>TIPO</td>
			<td>ATUALIZADO</td>
		</tr>
		
		<% ProventoService service = new ProventoService();
		   // Timestamp of 1 de January de 2018 às 18:44:02
		   long timestamp = 1514832242;
		   List<Provento> proventos = service.getProventoByDate(timestamp);
		   String output = "";
		   for (Provento provento : proventos) {
			   output += "<tr>";
			   output += "<td>" + provento.getId() + "</td>";
			   output += "<td>" + provento.getCodigo() + "</td>";
			   output += "<td>" + provento.getTimestamp() + "</td>";
			   output += "<td>" + provento.getData() + "</td>";
			   output += "<td>" + provento.getValor() + "</td>";
			   output += "<td>" + provento.getTipo() + "</td>";
			   output += "<td>" + provento.getAtualizado() + "</td>";
			   output += "</tr>";
			}
		   out.write(output);
		   %>
		</table>
	</form>
</body>
</html>
