package br.com.guiainvestimento.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import br.com.guiainvestimento.util.RegexUtil;

public class ProventoService {
	private ProventoDAO db = new ProventoDAO();
	public List<Provento> getProventos(){
		try {
			List<Provento> proventos = db.getProventos();
			return proventos;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Provento>();
		}
	}
	
	public Provento getProvento(Long id) {
		try {
			return db.getProventoById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean save(Provento provento) {
		try {
			db.save(provento);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Provento> getProventoByData(long timestamp, String codigo){
		try {
			return db.getProventoByDate(timestamp, codigo);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Provento> getProventosByCodigo(String codigo){
		try {
			return db.getProventosByCodigo(codigo);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// Parser and read the incomes from the fundamentos page
	public List<Provento> fetchProventos(String codigo) throws IOException {
		// Make a URL to the web page
        URL url = new URL("http://fundamentus.com.br/proventos.php?papel="+codigo);

        // Get the input stream through URL Connection
        URLConnection con = url.openConnection();
        InputStream is =con.getInputStream();

        // Once you have the Input Stream, it's just plain old Java IO stuff.

        // For this case, since you are interested in getting plain-text web page
        // I'll use a reader and output the text content to System.out.

        // For binary content, it's better to directly read the bytes from stream and write
        // to the target file.


        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line = null;
    	boolean start = false;
    	List<Provento> proventos = new ArrayList<>();
    	
		// Get date to save as last update date
		String DATE_FORMAT_NOW = "dd/MM/yyyy";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		String atualizado = sdf.format(cal.getTime());
    	
    	Provento provento = new Provento();
    	provento.setCodigo(codigo);
    	provento.setAtualizado(atualizado);
        // read each line and write to System.out
        while ((line = br.readLine()) != null) {
        	// Start reading each <tr> and setting values
        	if(start == true && line.indexOf("</tbody>") == -1 && line.indexOf("<td>") > -1) {
        		String[] values = StringUtils.substringsBetween(line, "<td>", "</td>");
        		for (String value : values) {
        			if(RegexUtil.isDate(value)) {
            			// Date
        				//Get timestamp
        				String pattern = "dd/MM/yyyy";
        				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        				Date date = null;
        				try {
        					date = simpleDateFormat.parse(value);
        				} catch (ParseException e) {
        					e.printStackTrace();
        				}
        				Long timestamp = date.getTime();
        				
        				provento.setData(value);
        				provento.setTimestamp(timestamp);
        			} else if (RegexUtil.isDouble(value)) {
        				// Value
        				double valueDouble = 0;
        				value = value.replaceAll(",", ".");
        				valueDouble = Double.parseDouble(value); 
        				provento.setValor(valueDouble);
        			} else if (value.indexOf("JRS") != -1 || value.indexOf("Juros") != -1 || value.indexOf("JUROS") != -1 || value.indexOf("DIV") != -1 || value.indexOf("Div") != -1) {
        				// Tipo
        				if(value.indexOf("JRS") != -1 || value.indexOf("Juros") != -1 || value.indexOf("JUROS") != -1) {
        					value = "JCP";
        				} else {
        					value = "DIV";
        				}
        				provento.setTipo(value);
        			} else {
        				// None of the types, end of reading
        				if(provento.getTipo() != null && provento.getData() != null && provento.getValor() > 0) {
        					proventos.add(provento);
        				}
        				// Clear for new income
        				provento = new Provento();
        				provento.setCodigo(codigo);
        		    	provento.setAtualizado(atualizado);
        			}
        		}
        	}
        	
        	// Found tbody, start reading each <tr>
        	if(line.indexOf("<tbody>") != -1) {
        		start = true;
        	}
        	
        	// Found /tbody, end of <tr>, stop reading
        	if(line.indexOf("</tbody>") != -1) {
        		start = false;
        		break;
        	}
        }
        return proventos;
	}
}
