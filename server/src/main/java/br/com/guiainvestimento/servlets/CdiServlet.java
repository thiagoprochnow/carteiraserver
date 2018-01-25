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

import br.com.guiainvestimento.domain.Cdi;
import br.com.guiainvestimento.domain.CdiService;
import br.com.guiainvestimento.util.RegexUtil;
import br.com.guiainvestimento.util.ServletUtil;

@WebServlet("/getcdi/*")
public class CdiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CdiService service = new CdiService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		String valor = req.getParameter("valor");
		String data_string = req.getParameter("data_string");
		
		if(valor != "" && data_string != "") {
			Cdi cdi = new Cdi();
	
			List<Cdi> cdis = service.findByData(data_string);
			
			// Loads the one already saved, to update
			if(!cdis.isEmpty()) {
				cdi = service.findByData(data_string).get(0);
			} else {
				cdi.setDataString(data_string);
			}

			// Get date to save as last update date
			String DATE_FORMAT_NOW = "dd/MM/yyyy";
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
			String atualizado = sdf.format(cal.getTime());
			
			long timestamp = 0;
			
			// Date in timestamp
			try {
				Date date = sdf.parse(data_string);
				timestamp = date.getTime()/1000;
			} catch (ParseException e) {
			    e.printStackTrace();
			}
			
			cdi.setValor(Double.parseDouble(valor));
			cdi.setData(timestamp);
			cdi.setDataString(data_string);
			cdi.setAtualizado(atualizado);
			service.save(cdi);
			
			String contextPath= req.getContextPath();
			resp.sendRedirect(resp.encodeRedirectURL(contextPath + "/cdi/cadastroCDI.jsp"));
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		String requestUri = req.getRequestURI();
		Long timestamp = RegexUtil.matchCdiDate(requestUri);
		if(timestamp != null) {
			List<Cdi> cdis = service.getCdisByDate(timestamp);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(cdis);
			ServletUtil.writeJSON(resp, json);
		} else {
			// Todos os CDIs
			List<Cdi> cdis = service.getCdis();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(cdis);
			ServletUtil.writeJSON(resp, json);
		}
	}
}
