package heroes.exception;

import heroes.model.Hero;

public class HeroNotFoundException extends Exception {
	private static final long serialVersionUID = 1503072745505756281L;
	
	public HeroNotFoundException(){}
	public HeroNotFoundException(int id){
		super("Hero with id: " + id + " doesn't exist.");
	}
}
