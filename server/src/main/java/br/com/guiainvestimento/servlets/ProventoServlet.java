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
		
		if(req.getParameter("enviar") != null) {
			String id1 = req.getParameter("id1");
			String codigo1 = req.getParameter("codigo1");
			String data1 = req.getParameter("data1");
			String valor1 = req.getParameter("valor1");
			String tipo1 = req.getParameter("tipo1");
			
			String id2 = req.getParameter("id2");
			String codigo2 = req.getParameter("codigo2");
			String data2 = req.getParameter("data2");
			String valor2 = req.getParameter("valor2");
			String tipo2 = req.getParameter("tipo2");
			
			String id3 = req.getParameter("id3");
			String codigo3 = req.getParameter("codigo3");
			String data3 = req.getParameter("data3");
			String valor3 = req.getParameter("valor3");
			String tipo3 = req.getParameter("tipo3");
			
			String id4 = req.getParameter("id4");
			String codigo4 = req.getParameter("codigo4");
			String data4 = req.getParameter("data4");
			String valor4 = req.getParameter("valor4");
			String tipo4 = req.getParameter("tipo4");
			
			String id5 = req.getParameter("id5");
			String codigo5 = req.getParameter("codigo5");
			String data5 = req.getParameter("data5");
			String valor5 = req.getParameter("valor5");
			String tipo5 = req.getParameter("tipo5");
			
			if(codigo1 != "") {
				Provento provento = new Provento();
				if(id1 != "") {
					provento = service.getProvento(Long.valueOf(id1));
				}
				
				valor1 = valor1.replace(",",".");
				tipo1 = tipo1.toUpperCase();
				codigo1 = codigo1.toUpperCase();
				
				// Get date to save as last update date
				String DATE_FORMAT_NOW = "dd/MM/yyyy";
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
				String atualizado = sdf.format(cal.getTime());
				
				provento.setCodigo(codigo1);
				
				// Get date timestamp
				long timestamp = 0;
				
				// Date in timestamp
				try {
					Date date = sdf.parse(data1);
					timestamp = date.getTime()/1000;
					data1 = sdf.format(date);
				} catch (ParseException e) {
				    e.printStackTrace();
				}
				provento.setData(data1);
				provento.setTimestamp(timestamp);
				
				provento.setValor(Double.parseDouble(valor1));
				provento.setTipo(tipo1);
				provento.setAtualizado(atualizado);
				service.save(provento);
				
			}
			
			if(codigo2 != "") {
				Provento provento = new Provento();
				if(id2 != "") {
					provento = service.getProvento(Long.valueOf(id2));
				}
				
				valor2 = valor2.replace(",",".");
				tipo2 = tipo2.toUpperCase();
				codigo2 = codigo2.toUpperCase();
				
				// Get date to save as last update date
				String DATE_FORMAT_NOW = "dd/MM/yyyy";
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
				String atualizado = sdf.format(cal.getTime());
				
				provento.setCodigo(codigo2);
				
				// Get date timestamp
				long timestamp = 0;
				
				// Date in timestamp
				try {
					Date date = sdf.parse(data2);
					timestamp = date.getTime()/1000;
					data2 = sdf.format(date);
				} catch (ParseException e) {
				    e.printStackTrace();
				}
				provento.setData(data2);
				provento.setTimestamp(timestamp);
				
				provento.setValor(Double.parseDouble(valor2));
				provento.setTipo(tipo2);
				provento.setAtualizado(atualizado);
				service.save(provento);
				
			}
			
			if(codigo3 != "") {
				Provento provento = new Provento();
				if(id3 != "") {
					provento = service.getProvento(Long.valueOf(id3));
				}
				
				valor3 = valor3.replace(",",".");
				tipo3 = tipo3.toUpperCase();
				codigo3 = codigo3.toUpperCase();
				
				// Get date to save as last update date
				String DATE_FORMAT_NOW = "dd/MM/yyyy";
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
				String atualizado = sdf.format(cal.getTime());
				
				provento.setCodigo(codigo3);
				
				// Get date timestamp
				long timestamp = 0;
				
				// Date in timestamp
				try {
					Date date = sdf.parse(data3);
					timestamp = date.getTime()/1000;
					data3 = sdf.format(date);
				} catch (ParseException e) {
				    e.printStackTrace();
				}
				provento.setData(data3);
				provento.setTimestamp(timestamp);
				
				provento.setValor(Double.parseDouble(valor3));
				provento.setTipo(tipo3);
				provento.setAtualizado(atualizado);
				service.save(provento);
				
			}
			
			if(codigo4 != "") {
				Provento provento = new Provento();
				if(id4 != "") {
					provento = service.getProvento(Long.valueOf(id4));
				}
				
				valor4 = valor4.replace(",",".");
				tipo4 = tipo4.toUpperCase();
				codigo4 = codigo4.toUpperCase();
				
				// Get date to save as last update date
				String DATE_FORMAT_NOW = "dd/MM/yyyy";
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
				String atualizado = sdf.format(cal.getTime());
				
				provento.setCodigo(codigo4);
				
				// Get date timestamp
				long timestamp = 0;
				
				// Date in timestamp
				try {
					Date date = sdf.parse(data4);
					timestamp = date.getTime()/1000;
					data4 = sdf.format(date);
				} catch (ParseException e) {
				    e.printStackTrace();
				}
				provento.setData(data4);
				provento.setTimestamp(timestamp);
				
				provento.setValor(Double.parseDouble(valor4));
				provento.setTipo(tipo4);
				provento.setAtualizado(atualizado);
				service.save(provento);
				
			}
			
			if(codigo5 != "") {
				Provento provento = new Provento();
				if(id5 != "") {
					provento = service.getProvento(Long.valueOf(id5));
				}
				
				valor5 = valor5.replace(",",".");
				tipo5 = tipo5.toUpperCase();
				codigo5 = codigo5.toUpperCase();
				
				// Get date to save as last update date
				String DATE_FORMAT_NOW = "dd/MM/yyyy";
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
				String atualizado = sdf.format(cal.getTime());
				
				provento.setCodigo(codigo5);
				
				// Get date timestamp
				long timestamp = 0;
				
				// Date in timestamp
				try {
					Date date = sdf.parse(data5);
					timestamp = date.getTime()/1000;
					data5 = sdf.format(date);
				} catch (ParseException e) {
				    e.printStackTrace();
				}
				provento.setData(data5);
				provento.setTimestamp(timestamp);
				
				provento.setValor(Double.parseDouble(valor5));
				provento.setTipo(tipo5);
				provento.setAtualizado(atualizado);
				service.save(provento);
				
			}
			
			String contextPath= req.getContextPath();
			resp.sendRedirect(resp.encodeRedirectURL(contextPath + "/provento/cadastroProvento.jsp"));
			
			
		} else if (req.getParameter("atualizar") != null) {
			// Updates all incomes with updated values
			String[] codigos = {"BOVA11","BRAX11","CSMO11","DIVO11","ECOO11","FIND11","GOVE11","ISUS11","MATB11","MILA11","MOBI11","PIBB11","SMAL11","UTIP11","XBOV11","ADHM3","AELP3","TIET11","TIET3","TIET4","AFLU3","AFLU5","AFLU6","AFLT3","RPAD3","RPAD5","RPAD6","ALSC3","ALPA3","ALPA4","ALTS11","ALTS3","ALUP11","ALUP3","ALUP4","ABEV3","CBEE3","ARZZ3","ATOM3","AZEV3","AZEV4","AZUL4","BTOW3","BAHI3","BEES3","BEES4","BDLL3","BDLL4","BTTL3","BTTL4","BALM3","BALM4","BBSE3","ABCB4","BRIV3","BRIV4","BAZA3","BBDC3","BBDC4","BBAS11","BBAS12","BBAS3","BPAC11","BPAC3","BPAC5","BGIP3","BGIP4","BPAR3","BRSR3","BRSR5","BRSR6","IDVL3","IDVL4","BMIN3","BMIN4","BMEB3","BMEB4","BNBR3","BPAN4","BPAT33","PINE3","PINE4","SANB11","SANB3","SANB4","BSAN33","BMKS3","BIOM3","BSEV3","B3SA3","BOBR3","BOBR4","HCBR3","BRIN3","BRML3","BRPR3","BRAP3","BRAP4","BBRK3","BPHA3","AGRO3","BRKM3","BRKM5","BRKM6","BFRE11","BFRE12","BSLI3","BSLI4","BRFS3","BRQB3","BBTG11","BBTG12","BBTG35","BBTG36","CAMB3","CAMB4","CCRO3","CCXC3","RANI3","RANI4","MAPT3","MAPT4","ELET3","ELET5","ELET6","CLSC3","CLSC4","CELP3","CELP5","CELP6","CELP7","AALR3","CESP3","CESP5","CESP6","CABB3","PCAR3","PCAR4","CASN3","CASN4","GPAR3","CEGR3","CEEB3","CEEB5","CEEB6","CEBR3","CEBR5","CEBR6","CMIG3","CMIG4","CEPE3","CEPE5","CEPE6","COCE3","COCE5","COCE6","ENMA3B","ENMA5B","ENMA6B","CSRN3","CSRN5","CSRN6","CEED3","CEED4","EEEL3","EEEL4","FESA3","FESA4","CEDO3","CEDO4","CGAS3","CGAS5","HBTS3","HBTS5","HBTS6","HGTX3","CATA3","CATA4","LCAM3","MSPA3","MSPA4","CPLE3","CPLE5","CPLE6","PEAB3","PEAB4","SBSP3","CSMG3","SAPR3","SAPR4","CSAB3","CSAB4","CSNA3","CTNM3","CTNM4","CTSA3","CTSA4","CTSA8","CIEL3","CMSA3","CMSA4","CNSY3","ODER3","ODER4","BRGE11","BRGE12","BRGE3","BRGE5","BRGE6","BRGE7","BRGE8","CALI3","CALI4","LIXC3","LIXC4","TEND3","CTAX3","CORR3","CORR4","CZLT33","RLOG3","CSAN3","CPFE3","CPRE3","CRDE3","CREM3","CRPG3","CRPG5","CRPG6","CARD3","CTCA3","TRPL3","TRPL4","CVCB3","CYRE3","CCPR3","DASA3","PNVL3","PNVL4","DIRR3","DOHL3","DOHL4","DTCY3","DTCY4","DAGB33","DTEX3","ECOR3","ENBR3","EALT3","EALT4","ELEK3","ELEK4","EKTR3","EKTR4","LIPR3","ELPL3","ELPL4","EMAE3","EMAE4","EMBR3","ECPR3","ECPR4","ENMT3","ENMT4","ENGI11","ENGI3","ENGI4","ENEV3","EGIE3","EQTL3","ESTC3","ETER3","EUCA3","EUCA4","EVEN3","BAUH3","BAUH4","EZTC3","VSPT3","VSPT4","FHER3","FBMC3","FBMC4","FIBR3","CRIV3","CRIV4","FNCN3","FLRY3","FJTA3","FJTA4","FOMS3","FRAS3","ANIM3","GFSA3","GSHP3","GGBR3","GGBR4","GOLL4","GPIV33","GPCP3","GPCP4","CGRA3","CGRA4","GRND3","GRUC3","GRUC6","GUAR3","GUAR4","HAGA3","HAGA4","HBOR3","HETA3","HETA4","HOOT3","HOOT4","HYPE3","IDNT3","IGBR3","IGTA3","JBDU3","JBDU4","ROMI3","INEP3","INEP4","PARD3","MEAL3","FIGE3","FIGE4","MYPK3","SQRM11","SQRM3","ITUB3","ITUB4","ITSA3","ITSA4","ITEC3","JBSS3","MLFT3","MLFT4","JHSF3","JFEN3","JOPA3","JOPA4","LFFE3","LFFE4","JSLG3","CTKA3","CTKA4","KEPL3","KLBN11","KLBN3","KLBN4","KROT3","LIGT3","LINX3","RENT3","LOGN3","LAME3","LAME4","LHER3","LHER4","LREN3","LPSB3","LUPA3","MDIA3","MSRO3","MGLU3","MAGG3","LEVE3","MGEL3","MGEL4","ESTR3","ESTR4","POMO3","POMO4","MRFG3","AMAR3","MEND3","MEND5","MEND6","MERC3","MERC4","FRIO3","MTIG3","MTIG4","GOAU3","GOAU4","RSUL3","RSUL4","MTSA3","MTSA4","MILS3","MMAQ3","MMAQ4","BEEF3","MNPR3","MMXM3","MOAR3","MOVI3","MRVE3","MULT3","MPLU3","MNDL3","NAFG3","NAFG4","NATU3","NORD3","NRTQ3","NUTR3","ODPV3","OGSA3","OIBR3","OIBR4","OGXP3","OSXB3","OFSA3","PATI3","PATI4","PRBC4","PMAM3","PTBL3","PDGR3","PRIO3","PETR3","PETR4","PTNT3","PTNT4","PLAS3","PPAR3","FRTA3","PSSA3","POSI3","PRCA11","PRCA12","PRCA3","PFRM3","PRML3","QGEP3","QUAL3","QUSW3","RADL3","RAPT3","RAPT4","RCSL3","RCSL4","REDE3","REDE4","RPMG3","RNEW11","RNEW3","RNEW4","LLIS3","GEPA3","GEPA4","RDNI3","RSID3","RAIL3","SNSY3","SNSY5","SNSY6","STBP3","SCAR3","SMTO3","AHEB3","AHEB5","AHEB6","SLED3","SLED4","PSEG3","PSEG4","SHUL3","SHUL4","SNSL3","SEER3","APTI3","APTI4","SLCE3","SMLE3","SEDU3","SSBR3","SOND3","SOND5","SOND6","SPRI3","SPRI5","SPRI6","SGPS3","STKF3","SULA11","SULA3","SULA4","NEMO3","NEMO5","NEMO6","SUZB5","SUZB6","SHOW3","TRPN3","TOYB3","TOYB4","TECN3","TCSA3","TCNO3","TCNO4","TGMA3","TEKA3","TEKA4","TKNO3","TKNO4","TELB3","TELB4","VIVT3","VIVT4","TESA3","TXRX3","TXRX4","TIMP3","TOTS3","TPIS3","TAEE11","TAEE3","TAEE4","LUXM3","LUXM4","TRIS3","TUPY3","UGPA3","UCAS3","UNIP3","UNIP5","UNIP6","USIM3","USIM5","USIM6","VALE3","VALE5","VLID3","VVAR11","VVAR3","VVAR4","VIVR3","VULC3","WEGE3","MWET3","MWET4","WHRL3","WHRL4","WSON33","WIZS3","SGAS3","SGAS4","IRBR3","CRFB3","SAPR11","SUZB3","OMGE3","RHPY3","BRDT3"};
			for(String codigo : codigos) {
				List<Provento> proventos = service.fetchProventos(codigo);
				List<Provento> oldProventos = service.getProventosByCodigo(codigo);
				if(!oldProventos.isEmpty()) {
					Provento lastProvento = oldProventos.get(0);
					long lastTimestamp = lastProvento.getTimestamp();
					for(Provento provento: proventos) {
						if(provento.getTimestamp() > lastTimestamp) {
							service.save(provento);
						} else {
							break;
						}
					}
				} else {
					for(Provento provento: proventos) {
						service.save(provento);
					}
				}
			}
			String contextPath= req.getContextPath();
			resp.sendRedirect(resp.encodeRedirectURL(contextPath + "/provento/cadastroProvento.jsp"));
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		String requestUri = req.getRequestURI();
		String codigo = RegexUtil.matchProventoCode(requestUri);
		Long timestamp = RegexUtil.matchProventoDate(requestUri);
		if(timestamp != null) {
			List<Provento> proventos = service.getProventoByDateCode(timestamp, codigo);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(proventos);
			ServletUtil.writeJSON(resp, json);
		} else {
			List<Provento> proventos = service.getProventosByCodigo(codigo);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(proventos);
			ServletUtil.writeJSON(resp, json);
		}
	}
}
