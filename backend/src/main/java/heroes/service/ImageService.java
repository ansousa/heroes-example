package heroes.service;

import heroes.model.Image;

public interface ImageService {
	public Image getHeroImage(int id);
	public boolean deleteHeroImage(int id);
	public boolean addHeroImage(Image image);
}
