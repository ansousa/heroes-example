package heroes.controller;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import heroes.model.Hero;
import heroes.dao.HeroDao;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
public class HeroController {
	@RequestMapping(path = "/api/heroes", method = GET)
	public List<Hero> heroes(){
		return HeroDao.getHeroes();
	}
	
	@RequestMapping(path = "/api/hero/{id}", method = GET)
	public Hero hero(@PathVariable("id") int id, HttpServletResponse response) throws ServletException {
		Hero hero = HeroDao.getHero(id);
		if(hero == null)
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return hero;
	}
	
	@RequestMapping(path = "/api/hero", method = PUT)
	public Hero updateHero(@RequestBody final Hero hero, HttpServletResponse response) throws ServletException {
		Hero hero2 = HeroDao.updateHero(hero);
		if(hero2 == null)
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return hero2;
	}
}
