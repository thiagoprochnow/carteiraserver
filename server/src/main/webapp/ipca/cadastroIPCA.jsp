<%@page import="br.com.guiainvestimento.domain.IpcaService"%>
<%@page import="br.com.guiainvestimento.domain.Ipca"%>
<%@page import="java.util.List"%>

<html>
<body>
	<h2>Cadastro IPCA</h2>
	
	<form method="post" action="<%=request.getContextPath()%>/getipca">
		Ano:<br>
		<input type="text" name="ano" />
		<br><br>
		Mes:<br>
		<input type="text" name="mes" />
		<br><br>
		Valor:<br>
		<input type="text" name="valor" />
		<br><br>
		ID:<br>
		<input type="text" name="id" />
		<br><br>
		<input type="submit" name="Enviar" />
		<br><br><br>
		
		<table width="600" border="1">
		<tr>
			<td>ID</td>
			<td>ANO</td>
			<td>MES</td>
			<td>VALOR</td>
			<td>ATUALIZADO</td>
		</tr>
		
		<% IpcaService service = new IpcaService(); 
		   List<Ipca> ipcas = service.getIpcas();
		   String output = "";
		   for (Ipca ipca : ipcas) {
			   output += "<tr>";
			   output += "<td>" + ipca.getId() + "</td>";
			   output += "<td>" + ipca.getAno() + "</td>";
			   output += "<td>" + ipca.getMes() + "</td>";
			   output += "<td>" + ipca.getValor() + "</td>";
			   output += "<td>" + ipca.getAtualizado() + "</td>";
			   output += "</tr>";
			}
		   out.write(output);
		   %>
		</table>
	</form>
</body>
</html>
