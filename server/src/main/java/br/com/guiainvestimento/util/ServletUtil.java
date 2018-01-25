package br.com.guiainvestimento.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ServletUtil {
	public static void writeJSON(HttpServletResponse response, String json) throws IOException {
		if(json != null) {
			PrintWriter writer = response.getWriter();
			response.setContentType("application/json;charset=UTF-8");
			writer.write(json);
			writer.close();
		}
	}
}
