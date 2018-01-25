package br.com.guiainvestimento.util;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import br.com.guiainvestimento.domain.ListaTesouro;
import br.com.guiainvestimento.domain.Tesouro;

public class JAXBUtil {
	private static JAXBUtil instance;
	private static JAXBContext context;
	public static JAXBUtil getInstance() {
		return instance;
	}
	
	static {
		try {
			context = JAXBContext.newInstance(ListaTesouro.class, Tesouro.class);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String toXML(Object object) throws IOException {
		try {
			StringWriter writer = new StringWriter();
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(object, writer);
			String xml = writer.toString();
			return xml;
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}
}
