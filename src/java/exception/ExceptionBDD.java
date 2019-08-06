package exception;

public class ExceptionBDD  extends Exception{
	
	private static final long serialVersionUID = 1L;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ExceptionBDD() {
		super();
		this.message = "Problème avec la base de donnée, voyez avec le technicien.";
	}

}
