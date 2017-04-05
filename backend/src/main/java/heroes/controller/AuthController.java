package heroes.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import heroes.dao.UserLoginDao;
import heroes.model.LoginToken;
import heroes.model.UserLogin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class AuthController {
	@RequestMapping(path = "/api/auth/login", method = POST)
	public LoginToken login(@RequestBody final UserLogin login, HttpServletResponse response) throws ServletException {
		if(login.getName() == null || login.getPassword() == null || !UserLoginDao.getUserLogins().contains(login)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}
		return new LoginToken(
			Jwts.builder()
				.setSubject(login.getName())
				.signWith(SignatureAlgorithm.HS256, "secretkey")
				.setExpiration(new Date((long)(System.currentTimeMillis() + 8.64e7)))
				.compact()
		);
	}
}
