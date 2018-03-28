package br.com.guiainvestimento.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.guiainvestimento.domain.Provento;
import br.com.guiainvestimento.domain.ProventoService;
import br.com.guiainvestimento.domain.Tesouro;
import br.com.guiainvestimento.domain.TesouroService;
import br.com.guiainvestimento.util.RegexUtil;
import br.com.guiainvestimento.util.ServletUtil;

@WebServlet("/getprovento/*")
public class ProventoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProventoService service = new ProventoService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		String codigo = req.getParameter("codigo");
		String data = req.getParameter("data");
		String valor = req.getParameter("valor");
		String tipo = req.getParameter("tipo");
		
		if(codigo != "") {
			Provento provento = new Provento();

			// Get date to save as last update date
			String DATE_FORMAT_NOW = "dd/MM/yyyy";
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
			String atualizado = sdf.format(cal.getTime());
			
			provento.setCodigo(codigo);
			provento.setData(data);
			
			// Get date timestamp
			long timestamp = 0;
			provento.setTimestamp(timestamp);
			
			provento.setValor(Double.parseDouble(valor));
			provento.setTipo(tipo);
			provento.setAtualizado(atualizado);
			service.save(provento);
			
			String contextPath= req.getContextPath();
			resp.sendRedirect(resp.encodeRedirectURL(contextPath + "/provento/cadastroProvento.jsp"));
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

	}
}
