package br.com.guiainvestimento.servlets;

import java.io.IOException;

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
		resp.getWriter().print("Nome: " + nome + " , Valor: " + valor + " , Data Fim: " + data_fim + " , Tipo: " + tipo);
		TesouroService service = new TesouroService();
		Tesouro tesouro = new Tesouro();
		tesouro.setNome(nome);
		tesouro.setValor(Double.parseDouble(valor));
		tesouro.setData(data_fim);
		tesouro.setTipo(tipo);
		service.save(tesouro);
	}
}
