package br.com.guiainvestimento.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.guiainvestimento.domain.Ipca;
import br.com.guiainvestimento.domain.IpcaService;
import br.com.guiainvestimento.domain.Provento;
import br.com.guiainvestimento.util.RegexUtil;
import br.com.guiainvestimento.util.ServletUtil;

@WebServlet("/getipca/*")
public class IpcaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IpcaService service = new IpcaService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		
		String id = req.getParameter("id");
		String ano = req.getParameter("ano");
		String mes = req.getParameter("mes");
		String valor = req.getParameter("valor");
			
		if(ano != "" && mes != "" && valor != "") {
			Ipca ipca = new Ipca();
			if(id != "") {
				ipca = service.getIpca(Long.valueOf(id));
			}
	
			// Get date to save as last update date
			String DATE_FORMAT_NOW = "dd/MM/yyyy";
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
			String atualizado = sdf.format(cal.getTime());
				
			ipca.setAno(Integer.parseInt(ano));
			ipca.setMes(Integer.parseInt(mes));
			ipca.setValor(Double.valueOf(valor));
			ipca.setAtualizado(atualizado);

			service.save(ipca);
				
			String contextPath = req.getContextPath();
			resp.sendRedirect(resp.encodeRedirectURL(contextPath + "/ipca/cadastroIPCA.jsp"));
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		String requestUri = req.getRequestURI();

		// Todos os CDIs
		List<Ipca> ipcas = service.getIpcas();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(ipcas);
		ServletUtil.writeJSON(resp, json);
	}
}
