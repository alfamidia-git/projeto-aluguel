package exceptions;

public class VendedorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public VendedorException(String erro) {
		super(erro);
	}
}
