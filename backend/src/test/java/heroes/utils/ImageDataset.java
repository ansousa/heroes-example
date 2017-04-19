package heroes.utils;

import heroes.model.Image;
import heroes.model.Image.Extension;

public class ImageDataset {
	
	public static Image getValidImage(){
		return new Image(getValidImageId(), getValidImageExtension(), getValidImageData());
	}

	public static byte[] getValidImageData() {
		return new byte[]{'a', 'a', 'a'};
	}

	public static Extension getValidImageExtension() {
		return Image.Extension.jpeg;
	}

	public static int getValidImageId() {
		return 1;
	}
	
	public static int getInvalidImageId(){
		return -1;
	}

}
