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
		Um:<br>
		<input type="text" name="um" />
		<br><br>
		Tres:<br>
		<input type="text" name="tres" />
		<br><br>
		Seis:<br>
		<input type="text" name="seis" />
		<br><br>
		Doze:<br>
		<input type="text" name="doze" />
		<br><br>
		Anual:<br>
		<input type="text" name="anual" />
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
			<td>UM MES</td>
			<td>TRES MESES</td>
			<td>SEIS MESES</td>
			<td>DOZE MESES</td>
			<td>ANUAL</td>
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
			   output += "<td>" + ipca.getUm() + "</td>";
			   output += "<td>" + ipca.getTres() + "</td>";
			   output += "<td>" + ipca.getSeis() + "</td>";
			   output += "<td>" + ipca.getDoze() + "</td>";
			   output += "<td>" + ipca.getAnual() + "</td>";
			   output += "<td>" + ipca.getAtualizado() + "</td>";
			   output += "</tr>";
			}
		   out.write(output);
		   %>
		</table>
	</form>
</body>
</html>
