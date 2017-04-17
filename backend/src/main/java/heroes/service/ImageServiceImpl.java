package heroes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import heroes.model.Image;
import heroes.repository.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService{
	@Autowired
	private ImageRepository repository;

	@Override
	public Image getHeroImage(int id) {
		return repository.findOne(id);
	}

	@Override
	public boolean deleteHeroImage(int id) {
		Image image = repository.findOne(id);
		if(image != null){
			repository.delete(repository.findOne(id));
			return true;
		}
		return false;
	}

	@Override
	public boolean addHeroImage(Image image) {
		return repository.save(image) != null;
	}

}
