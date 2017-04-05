package heroes.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import heroes.UserLogin;

public class UserLoginDao {

	public static List<UserLogin> getUserLogins(){
		return new ArrayList<UserLogin>(Arrays.asList(new UserLogin[]{
			new UserLogin("a", "a"),
			new UserLogin("b", "b")
		}));
	}
}
