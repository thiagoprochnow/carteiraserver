package br.com.guiainvestimento.util;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;

public class RegexUtil {
	private static final Pattern regexAll = Pattern.compile("/gettesouro");
	private static final Pattern regexById = Pattern.compile("/gettesouro/([0-9]*)");
	private static final Pattern regexCdiAll = Pattern.compile("/getcdi");
	private static final Pattern regexCdiDate = Pattern.compile("/getcdi/([0-9]*)");
	private static final Pattern regexProventoAll = Pattern.compile("/getprovento");
	private static final Pattern regexProventoCode = Pattern.compile("/getprovento/([A-Z0-9]{4}([0-9]|[0-9][0-9]))");
	private static final Pattern regexProventoCodeData = Pattern.compile("/getprovento/([A-Z0-9]{4}([0-9]|[0-9][0-9]))/([0-9]*)");
	private static final Pattern regexIpcaAll = Pattern.compile("/getipca");
	private static final Pattern regexFundNome = Pattern.compile("/getfundnome/(.*)");
	private static final Pattern regexFundCnpj = Pattern.compile("/getfundcnpj/(.*)");
	private static final Pattern regexFundQuoteCnpjData = Pattern.compile("/getfundquotes/(.{10}\\/.{7})/([0-9]*)");
	private static final Pattern regexDate = Pattern.compile("^[0-9]+\\/[0-9]+\\/[0-9]+$");
	private static final Pattern regexDouble = Pattern.compile("^[0-9]+,[0-9]+$");
	private static final Pattern regexText = Pattern.compile("^[a-zA-Z]*$");
	
	// Tesouro
	public static Long matchTesouroId(String requestUri) throws ServletException{
		Matcher matcher = regexById.matcher(requestUri);
		if(matcher.find() && matcher.groupCount() > 0) {
			String s = matcher.group(1);
			if(s != null && s.trim().length() > 0) {
				Long id = Long.parseLong(s);
				return id;
			}
		}
		return null;
	}
	
	// CDI
	public static Long matchCdiDate(String requestUri) throws ServletException{
		Matcher matcher = regexCdiDate.matcher(requestUri);
		if(matcher.find() && matcher.groupCount() > 0) {
			String s = matcher.group(1);
			if(s != null && s.trim().length() > 0) {
				Long id = Long.parseLong(s);
				return id;
			}
		}
		return null;
	}
	
	public boolean matchAllCDI(String requestUri) throws ServletException {
		Matcher matcher = regexCdiAll.matcher(requestUri);
		if (matcher.find()) {
			return true;
		}
		return false;
	}
	
	// Provento
	public static String matchProventoCode(String requestUri) throws ServletException{
		Matcher matcher = regexProventoCode.matcher(requestUri);
		if(matcher.find() && matcher.groupCount() > 0) {
			String s = matcher.group(1);
			return s;
		}
		return null;
	}
	
	// Provento
	public static Long matchProventoDate(String requestUri) throws ServletException{
		Matcher matcher = regexProventoCodeData.matcher(requestUri);
		if(matcher.find() && matcher.groupCount() > 0) {
			String s = matcher.group(3);
			if(s != null && s.trim().length() > 0) {
				Long id = Long.parseLong(s);
				return id;
			}
		}
		return null;
	}
	
	public boolean matchAllProvento(String requestUri) throws ServletException {
		Matcher matcher = regexProventoAll.matcher(requestUri);
		if (matcher.find()) {
			return true;
		}
		return false;
	}
	
	public boolean matchAllIpca(String requestUri) throws ServletException {
		Matcher matcher = regexIpcaAll.matcher(requestUri);
		if (matcher.find()) {
			return true;
		}
		return false;
	}
	
	public static String matchFundName(String requestUri) throws ServletException {
		Matcher matcher = regexFundNome.matcher(requestUri);
		if(matcher.find() && matcher.groupCount() > 0) {
			String s = matcher.group(1);
			s = s.trim();
			return s;
		}
		return "false";
	}
	
	public static String matchFundCnpj(String requestUri) throws ServletException {
		Matcher matcher = regexFundCnpj.matcher(requestUri);
		if(matcher.find() && matcher.groupCount() > 0) {
			String s = matcher.group(1);
			s = s.trim();
			return s;
		}
		return "false";
	}
	
	// Fund Quote Cnpj
	public static String matchFundQuoteCnpj(String requestUri) throws ServletException{
		Matcher matcher = regexFundQuoteCnpjData.matcher(requestUri);
		if(matcher.find() && matcher.groupCount() > 0) {
			String s = matcher.group(1);
			s = s.trim();
			return s;
		}
		return null;
	}
	
	// Fund Quote timestamp
	public static Long matchFundQuoteTimestamp(String requestUri) throws ServletException{
		Matcher matcher = regexFundQuoteCnpjData.matcher(requestUri);
		if(matcher.find() && matcher.groupCount() > 0) {
			String s = matcher.group(2);
			if(s != null && s.trim().length() > 0) {
				Long id = Long.parseLong(s);
				return id;
			}
		}
		return null;
	}
	
	// Util
	public static boolean isDate(String value) throws IOException{
		Matcher matcher = regexDate.matcher(value);
		if (matcher.find()) {
			return true;
		}
		return false;
	}
	
	public static boolean isDouble(String value) throws IOException{
		Matcher matcher = regexDouble.matcher(value);
		if (matcher.find()) {
			return true;
		}
		return false;
	}
	
	public static boolean isText(String value) throws IOException{
		Matcher matcher = regexText.matcher(value);
		if(matcher.find()) {
			return true;
		}
		return false;
	}
}
