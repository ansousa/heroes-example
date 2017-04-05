package heroes.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import heroes.model.Hero;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class HeroController {
	@RequestMapping(path = "/api/heroes", method = GET)
	public List<Hero> heroes(){
		Hero[] heroes = {
				new Hero(11, "Mr. Nice"),
				new Hero(12, "Narco"),
				new Hero(13, "Bombasto"),
				new Hero(14, "Celeritas"),
				new Hero(15, "Magneta"),
				new Hero(16, "RubberMan"),
				new Hero(17, "Dynama"),
				new Hero(18, "Dr IQ"),
				new Hero(19, "Magma"),
				new Hero(20, "Tornado"),
		};
		return new ArrayList<Hero>(Arrays.asList(heroes));
	}
}
