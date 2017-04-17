package heroes.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import heroes.model.Image;

@Service
public class ImageServiceImpl implements ImageService {

	private Map<Integer, Image> images;
	
	public ImageServiceImpl(){
		this.images = new HashMap<>();
		for (int i = 11; i < 21; i++){
			try(InputStream imgData = getClass().getClassLoader().getResourceAsStream("img/" + i + ".jpg")){
				this.images.put(i, new Image(Image.Extension.jpeg, IOUtils.toByteArray(imgData)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public Image getHeroImage(int id) {
		return images.get(id);
	}
	
	@Override
	public boolean deleteHeroImage(int id){
		return images.remove(id) != null;
	}
	
	@Override
	public boolean addHeroImage(int id, Image image) {
		this.images.put(id, image);
		return true;
	}

}
