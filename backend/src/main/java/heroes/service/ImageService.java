package heroes.service;

import heroes.exception.ImageNotFoundException;
import heroes.model.Image;

public interface ImageService {
	public Image getHeroImage(int id) throws ImageNotFoundException;
	public void deleteHeroImage(int id) throws ImageNotFoundException;
	public Image addHeroImage(Image image);
}
