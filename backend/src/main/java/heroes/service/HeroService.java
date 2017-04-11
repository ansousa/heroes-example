package heroes.service;

import java.util.List;

import heroes.model.Hero;

public interface HeroService {
	public List<Hero> getHeroes();
	public Hero getHero(int id);
	public Hero updateHero(Hero hero);
	public Hero addHero(Hero hero);
	public boolean deleteHero(Hero hero);
}
