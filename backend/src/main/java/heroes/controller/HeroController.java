package heroes.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import heroes.exception.HeroNotFoundException;
import heroes.exception.ImageNotFoundException;
import heroes.model.Hero;
import heroes.model.Image;
import heroes.model.ImageFromView;
import heroes.service.HeroService;
import heroes.service.ImageService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

@RestController
public class HeroController {
	@Autowired
	private HeroService heroService;
	@Autowired
	private ImageService imageService;
	
	@RequestMapping(path = "/api/heroes", method = GET)
	public List<Hero> heroes(){
		return heroService.getHeroes();
	}
	
	@RequestMapping(path = "/api/hero/{id}", method = GET)
	public Hero hero(@PathVariable("id") int id, HttpServletResponse response){
		try{
			return heroService.getHero(id);
		}
		catch(HeroNotFoundException e){
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return null;
	}
	
	@RequestMapping(path = "/api/hero", method = PUT)
	public Hero updateHero(@RequestBody final Hero hero, HttpServletResponse response){
		try{
			return heroService.updateHero(hero);
		}
		catch(HeroNotFoundException e){
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return null;
	}
	
	@RequestMapping(path = "/api/hero", method = POST)
	public Hero addHero(@RequestBody final Hero hero) {
		return heroService.addHero(hero);
	}
	
	@RequestMapping(path = "/api/hero/{id}", method = DELETE)
	public void deleteHero(@PathVariable("id") int id, HttpServletResponse response) {
		try{
			heroService.deleteHero(id);
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			return;
		}
		catch(HeroNotFoundException e){
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}
	
	@RequestMapping(path = "/api/hero/img/{id}", method = GET)
	public String getHeroImg(@PathVariable("id") int id, HttpServletResponse response){
		try{
			Image img = imageService.getHeroImage(id);
			response.setHeader("Content-Type", "image/" + img.getExtension().toString());
			return "data:image/" + img.getExtension().toString().toLowerCase() + ";base64," + Base64Utils.encodeToString(img.getData());
		}
		catch(ImageNotFoundException e){
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return null;
	}
	
	@RequestMapping(path = "/api/hero/img/{id}", method = DELETE)
	public void deleteHeroImg(@PathVariable("id") int id, HttpServletResponse response){
		try{
			imageService.deleteHeroImage(id);
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}
		catch(ImageNotFoundException e){
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}
	
	@RequestMapping(path = "/api/hero/img/{id}", method = PUT)
	public void addHeroImg(@PathVariable("id") int id, @RequestBody final ImageFromView image, HttpServletResponse response){
		imageService.addHeroImage(image.toImage());
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}
}
