package heroes.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import heroes.model.UserLogin;

@Service
public class UserLoginServiceImpl implements UserLoginService{

	public UserLoginServiceImpl(){}
	
	public List<UserLogin> getUserLogins(){
		return new ArrayList<UserLogin>(Arrays.asList(new UserLogin[]{
			new UserLogin("a", "a"),
			new UserLogin("b", "b")
		}));
	}
}
