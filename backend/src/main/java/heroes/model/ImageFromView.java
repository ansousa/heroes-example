package heroes.model;

import org.springframework.util.Base64Utils;

public class ImageFromView {
	private String extension, data;

	public ImageFromView(){}
	
	public ImageFromView(String extension, String data) {
		this.extension = extension;
		this.data = data;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public Image toImage(){
		Image.Extension ext = Image.Extension.valueOf(this.extension);
		byte[] data = Base64Utils.decodeFromString(this.data);
		return new Image(ext, data);
	}
}
