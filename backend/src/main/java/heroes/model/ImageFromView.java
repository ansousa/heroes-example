package heroes.model;

import java.util.Base64;


public class ImageFromView {
	private int id;
	private String extension, data;

	public ImageFromView(){}
	
	public ImageFromView(int id, String extension, String data) {
		this.setId(id);
		this.setExtension(extension);
		this.setData(data);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension.toLowerCase().equals("jpg") ? "jpeg" :extension.toLowerCase();
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		String[] split = data.split(",");
		this.data = split.length == 2 ? split[1] : data;
	}
	
	public Image toImage(){
		Image.Extension ext = Image.Extension.valueOf(this.extension);
		byte[] data = Base64.getMimeDecoder().decode(this.data);
		return new Image(this.id, ext, data);
	}
}
