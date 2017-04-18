package heroes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import heroes.exception.HeroNotFoundException;
import heroes.model.Hero;
import heroes.repository.HeroRepository;

@Service
public class HeroServiceImpl implements HeroService {
	
	@Autowired
	private HeroRepository repository;

	@Override
	public List<Hero> getHeroes() {
		Iterable<Hero> heroes = repository.findAll();
		List<Hero> heroList = new ArrayList<>();
		for(Hero hero: heroes){
			heroList.add(hero);
		}
		return heroList;
	}

	@Override
	public Hero getHero(int id) throws HeroNotFoundException {
		Hero hero = repository.findOne(id);
		if(hero == null)
			throw new HeroNotFoundException(id);
		return hero;
	}

	@Override
	public Hero updateHero(Hero hero) throws HeroNotFoundException {
		if(!repository.exists(hero.getId()))
			throw new HeroNotFoundException(hero.getId());
		return repository.save(hero);
	}

	@Override
	public Hero addHero(Hero hero) {
		return repository.save(hero);
	}

	@Override
	public void deleteHero(int id) throws HeroNotFoundException {
		if(!repository.exists(id))
			throw new HeroNotFoundException(id);
		repository.delete(id);
	}

}
