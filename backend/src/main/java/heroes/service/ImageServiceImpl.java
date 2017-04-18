package heroes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import heroes.exception.ImageNotFoundException;
import heroes.model.Image;
import heroes.repository.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService{
	@Autowired
	private ImageRepository repository;

	@Override
	public Image getHeroImage(int id) throws ImageNotFoundException {
		Image image = repository.findOne(id);
		if(image == null)
			throw new ImageNotFoundException(id);
		return image;
	}

	@Override
	public void deleteHeroImage(int id) throws ImageNotFoundException {
		if(!repository.exists(id))
			throw new ImageNotFoundException(id);
		repository.delete(repository.findOne(id));
	}

	@Override
	public Image addHeroImage(Image image) {
		return repository.save(image);
	}

}
