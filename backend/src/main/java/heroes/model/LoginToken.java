package heroes.model;

public class LoginToken {
    private String token;
    
    public LoginToken(){}

    public LoginToken(String token) {
        this.setToken(token);
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
    
}
