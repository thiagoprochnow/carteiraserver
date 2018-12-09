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
import br.com.guiainvestimento.domain.FundGain;
import br.com.guiainvestimento.domain.FundQuote;
import br.com.guiainvestimento.domain.FundQuoteService;
import br.com.guiainvestimento.domain.FundService;
import br.com.guiainvestimento.domain.Tesouro;
import br.com.guiainvestimento.domain.TesouroService;
import br.com.guiainvestimento.util.RegexUtil;
import br.com.guiainvestimento.util.ServletUtil;

@WebServlet("/getfundgain/*")
public class FundGainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FundQuoteService service = new FundQuoteService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
			String requestUri = req.getRequestURI();
			String cnpj = RegexUtil.matchFundQuoteCnpj(requestUri);
			Long timestamp = RegexUtil.matchFundQuoteTimestamp(requestUri);
			cnpj = URLDecoder.decode(cnpj, "UTF-8");
			if(cnpj != null && cnpj != "false") {
				FundQuote fund = service.findByCnpjData(cnpj,timestamp);
				FundQuote latestFund = service.findByCnpjLatest(cnpj);
				if(fund != null && latestFund != null) {
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
					long fundQuote = Long.parseLong(fund.getQuote().replace(".", ""));
					long latestQuote = Long.parseLong(latestFund.getQuote().replace(".", ""));
					double rate = (double)latestQuote/fundQuote;
					FundGain fundGain = new FundGain();
					fundGain.setCnpj(cnpj);
					fundGain.setRate(String.valueOf(rate));
					String json = gson.toJson(fundGain);
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
