package heroes.service;

import java.util.List;

import heroes.exception.HeroNotFoundException;
import heroes.model.Hero;

public interface HeroService {
	public List<Hero> getHeroes();
	public Hero getHero(int id) throws HeroNotFoundException;
	public Hero updateHero(Hero hero) throws HeroNotFoundException;
	public Hero addHero(Hero hero);
	public void deleteHero(int id) throws HeroNotFoundException;
}
