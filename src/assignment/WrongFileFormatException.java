package assignment;

@SuppressWarnings("serial")
public class WrongFileFormatException extends Exception{
	public String Errorx;

	public WrongFileFormatException(String Errorx) {
		super();
		this.Errorx = Errorx;
	}
	
}
