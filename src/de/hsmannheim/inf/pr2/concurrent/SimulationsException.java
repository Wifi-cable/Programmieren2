package de.hsmannheim.inf.pr2.concurrent;

public class SimulationsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SimulationsException() {
		System.out.println("excption wurde geworfen");
		this.getMessage();
	}

	public SimulationsException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public SimulationsException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public SimulationsException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public SimulationsException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
