package br.com.guiainvestimento.servlets;

import java.io.IOException;
import java.net.URLDecoder;
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

import br.com.guiainvestimento.domain.Fund;
import br.com.guiainvestimento.domain.FundService;
import br.com.guiainvestimento.domain.Tesouro;
import br.com.guiainvestimento.domain.TesouroService;
import br.com.guiainvestimento.util.RegexUtil;
import br.com.guiainvestimento.util.ServletUtil;

@WebServlet("/getfundnome/*")
public class FundNomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FundService service = new FundService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
			String requestUri = req.getRequestURI();
			String name = RegexUtil.matchFundName(requestUri);
			name = URLDecoder.decode(name, "UTF-8");
			String[] names = name.split(" ");
			if(name != null && name != "false") {
				List<Fund> funds = service.findByName(names);
				if(funds != null) {
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
					String json = gson.toJson(funds);
					ServletUtil.writeJSON(resp, json);
				} else {
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
					String json = gson.toJson("");
					ServletUtil.writeJSON(resp, json);
				}
			} else {
				resp.sendError(404, "Fundo nao encontrado");
			}
	}
}
