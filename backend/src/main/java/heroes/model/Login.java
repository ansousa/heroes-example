package heroes.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Login {
	@Id
    private String name;
    private String password;
    
    public Login(){}
    
	public Login(String name, String password) {
		this.setName(name);
		this.setPassword(password);
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
		Login user = null;
		try{
			user = (Login)o;
		}
		catch(ClassCastException e){
			return false;
		}
		return user != null
			&& this.name != null
			&& this.name.equals(user.name)
			&& this.password != null
			&& this.password.equals(user.password);
	}

}
