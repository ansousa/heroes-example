package heroes.model;

public class UserLogin {
    private String name;
    private String password;
    
    public UserLogin(){}
    
	public UserLogin(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public boolean equals(Object o){
		UserLogin user = null;
		try{
			user = (UserLogin)o;
		}
		catch(ClassCastException e){
			return false;
		}
		return this.name != null
			&& this.name.equals(user.name)
			&& this.password != null
			&& this.password.equals(user.name);
	}

}
