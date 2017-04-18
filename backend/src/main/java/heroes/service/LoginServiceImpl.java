package heroes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import heroes.model.Login;
import heroes.repository.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private LoginRepository repository;
	@Override
	public boolean exists(Login login) {
		Login login2 = repository.findOne(login.getName());
		return login2 != null && login2.equals(login);
	}
}
