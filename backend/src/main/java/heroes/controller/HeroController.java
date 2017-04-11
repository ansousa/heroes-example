package heroes.controller;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import heroes.model.Hero;
import heroes.service.HeroService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

@RestController
public class HeroController {
	@Autowired
	private HeroService heroService;
	
	@RequestMapping(path = "/api/heroes", method = GET)
	public List<Hero> heroes(){
		return heroService.getHeroes();
	}
	
	@RequestMapping(path = "/api/hero/{id}", method = GET)
	public Hero hero(@PathVariable("id") int id, HttpServletResponse response) throws ServletException {
		Hero hero = heroService.getHero(id);
		if(hero == null)
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return hero;
	}
	
	@RequestMapping(path = "/api/hero", method = PUT)
	public Hero updateHero(@RequestBody final Hero hero, HttpServletResponse response) throws ServletException {
		Hero hero2 = heroService.updateHero(hero);
		if(hero2 == null)
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return hero2;
	}
	
	@RequestMapping(path = "/api/hero", method = POST)
	public Hero addHero(@RequestBody final Hero hero) {
		return heroService.addHero(hero);
	}
	
	@RequestMapping(path = "/api/hero", method = DELETE)
	public boolean deleteHero(@RequestBody final Hero hero, HttpServletResponse response) {
		boolean deleted = heroService.deleteHero(hero);
		if(!deleted)
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return deleted;
	}
}
