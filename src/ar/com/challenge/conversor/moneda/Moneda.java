package ar.com.challenge.conversor.moneda;


public enum Moneda {

	USD("Dolar USA"), 
	EUR("Euro"), 
	GBP("Libra Britanica"), 
	ARS("Peso Argentino"), 
	KRW("Won Surcoreano"),
	JPY("Yen Japones");

	private final String nombre;

	Moneda(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return super.toString();
	}

	@Override
	public String toString() {
		return nombre;
	}
	
}
