<%@page import="br.com.guiainvestimento.domain.ProventoService"%>
<%@page import="br.com.guiainvestimento.domain.Provento"%>
<%@page import="java.util.List"%>

<html>
<body>
	<h2>Cadastro Provento</h2>
	
	<form method="post" action="<%=request.getContextPath()%>/getprovento">
		<table width="1000">
			<tr>
				<td width="200">
					Codigo:<br>
					<input type="text" name="codigo1" />
					<br><br>
					Data:<br>
					<input type="text" name="data1" />
					<br><br>
					Valor:<br>
					<input type="text" name="valor1" />
					<br><br>
					Tipo:<br>
					<input type="text" name="tipo1" />
					<br><br>
					Id:<br>
					<input type="text" name="id1" />
					<br><br>
				</td>

				<td width="200">
					Codigo:<br>
					<input type="text" name="codigo2" />
					<br><br>
					Data:<br>
					<input type="text" name="data2" />
					<br><br>
					Valor:<br>
					<input type="text" name="valor2" />
					<br><br>
					Tipo:<br>
					<input type="text" name="tipo2" />
					<br><br>
					Id:<br>
					<input type="text" name="id2" />
					<br><br>
				</td>

				<td width="200">
					Codigo:<br>
					<input type="text" name="codigo3" />
					<br><br>
					Data:<br>
					<input type="text" name="data3" />
					<br><br>
					Valor:<br>
					<input type="text" name="valor3" />
					<br><br>
					Tipo:<br>
					<input type="text" name="tipo3" />
					<br><br>
					Id:<br>
					<input type="text" name="id3" />
					<br><br>
				</td>

				<td width="200">
					Codigo:<br>
					<input type="text" name="codigo4" />
					<br><br>
					Data:<br>
					<input type="text" name="data4" />
					<br><br>
					Valor:<br>
					<input type="text" name="valor4" />
					<br><br>
					Tipo:<br>
					<input type="text" name="tipo4" />
					<br><br>
					Id:<br>
					<input type="text" name="id4" />
					<br><br>
				</td>

				<td width="200">
					Codigo:<br>
					<input type="text" name="codigo5" />
					<br><br>
					Data:<br>
					<input type="text" name="data5" />
					<br><br>
					Valor:<br>
					<input type="text" name="valor5" />
					<br><br>
					Tipo:<br>
					<input type="text" name="tipo5" />
					<br><br>
					Id:<br>
					<input type="text" name="id5" />
					<br><br>
				</td>
			</tr>
		</table>
		<input type="submit" name="enviar" value="Enviar" />
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
		   long timestamp = 1538352000;
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
