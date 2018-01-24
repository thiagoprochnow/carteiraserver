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

import br.com.guiainvestimento.domain.Tesouro;
import br.com.guiainvestimento.domain.TesouroService;

@WebServlet("/cadastroTesouro")
public class TesouroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		String nome = req.getParameter("nome");
		String valor = req.getParameter("valor");
		String data_fim = req.getParameter("data_fim");
		String tipo = req.getParameter("tipo");
		
		if(nome != "" && data_fim != "" && tipo != "") {
			TesouroService service = new TesouroService();
			Tesouro tesouro = new Tesouro();
	
			List<Tesouro> tesouros = service.findByName(nome);
			
			// Loads the one already saved, to update
			if(!tesouros.isEmpty()) {
				tesouro = service.findByName(nome).get(0);
			} else {
				tesouro.setNome(nome);
			}

			// Get date to save as last update date
			String DATE_FORMAT_NOW = "dd/MM/yyyy";
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
			String atualizado = sdf.format(cal.getTime());
			
			tesouro.setValor(Double.parseDouble(valor));
			tesouro.setData(data_fim);
			tesouro.setTipo(tipo);
			tesouro.setAtualizado(atualizado);
			service.save(tesouro);
			
			String contextPath= req.getContextPath();
			resp.sendRedirect(resp.encodeRedirectURL(contextPath + "/tesouro/cadastroTesouro.jsp"));
		}
	}
}
