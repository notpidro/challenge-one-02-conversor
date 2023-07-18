package ar.com.challenge.conversor.moneda;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class MonedasData {

	public static ArrayList<Object> getValores(Moneda monedaUno, Moneda monedaDos) throws IOException {

		String mainURL = "https://br.investing.com/currencies/";

		int maxBodySizeBytes = 1024 * 1024;
		int timeoutMillis = 5000;

		ArrayList<Object> lista = new ArrayList<>();

		Document getData = Jsoup
				.connect(mainURL + monedaUno.getNombre().toLowerCase() + "-" + monedaDos.getNombre().toLowerCase())
				.maxBodySize(maxBodySizeBytes).timeout(timeoutMillis).get();

		Element cotizacion = getData.selectFirst("[data-test=instrument-price-last]");
		Element hora = getData.selectFirst("[datetime]");

		lista.add(hora.text());
		lista.add(Double.parseDouble(cotizacion.text().replace(".", "").replace(",", ".")));

		return lista;
	}

}
