package heroes.exception;

public class ImageNotFoundException extends Exception{
	private static final long serialVersionUID = 6699207797339674787L;
	
	public ImageNotFoundException(){}
	public ImageNotFoundException(int id){
		super("Image with id: " + id + " doesn't exist.");
	}
}
