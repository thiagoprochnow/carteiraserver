<%@page import="br.com.guiainvestimento.domain.CdiService"%>
<%@page import="br.com.guiainvestimento.domain.Cdi"%>
<%@page import="java.util.List"%>

<html>
<body>
	<h2>Cadastro CDI</h2>
	
	<form method="post" action="<%=request.getContextPath()%>/getcdi">
		Valor:<br>
		<input type="text" name="valor" />
		<br><br>
		Data:<br>
		<input type="text" name="data_string" />
		<br><br>
		<input type="submit" name="Enviar" />
		<br><br><br>
		
		<table width="600" border="1">
		<tr>
			<td>ID</td>
			<td>VALOR</td>
			<td>DATA</td>
			<td>DATA STRING</td>
			<td>ATUALIZADO</td>
		</tr>
		
		<% CdiService service = new CdiService(); 
		   List<Cdi> cdis = service.getCdis();
		   String output = "";
		   for (Cdi cdi : cdis) {
			   output += "<tr>";
			   output += "<td>" + cdi.getId() + "</td>";
			   output += "<td>" + cdi.getValor() + "</td>";
			   output += "<td>" + cdi.getData() + "</td>";
			   output += "<td>" + cdi.getDataString() + "</td>";
			   output += "<td>" + cdi.getAtualizado() + "</td>";
			   output += "</tr>";
			}
		   out.write(output);
		   %>
		</table>
	</form>
</body>
</html>
