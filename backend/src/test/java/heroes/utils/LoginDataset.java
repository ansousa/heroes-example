package heroes.utils;

import heroes.model.Login;

public class LoginDataset {

	public static Login getValidLogin(){
		return new Login(getValidLoginName(), getValidLoginPassword());
	}

	public static String getValidLoginPassword() {
		return "b";
	}

	public static String getValidLoginName() {
		return "a";
	}
	
	public static String getDifferentValidLoginName(){
		return "b";
	}
	
	public static String getDifferentValidLoginPassword(){
		return "a";
	}
	
}
