package plp.puma;

public class Teste {

	public Teste() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static enum E {
		A, B, C;
		public String valor;
	}

	public static class T {
		public T() {
		}

		public String valor;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * T ca = new T(); T cb = new T(); ca.valor = "A"; cb.valor = "B"; ca =
		 * cb; cb.valor = "C"; System.out.println(ca.valor);
		 */
		E a = E.A;
		a.valor = "A";
		E b = E.B;
		b.valor = "B";
		a = b;
		b = E.C;
		b.valor = "X";
		System.out.println(a == b);
		System.out.println(a.valor);
		/*
		 * System.out.println(b); System.out.println(b.valor);
		 */

		// if ( a.equals(b) ) {
		// System.out.println("aki");
		// }
		//
		// TODO Auto-generated method stub

	}

}
