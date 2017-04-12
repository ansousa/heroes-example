package heroes.model;

public class Image {
	public static enum Extension{JPEG, PNG}
	
	private Extension extension;
	private byte[] data;
	
	public Image(){}
	
	public Image(Extension extension, byte[] data) {
		super();
		this.extension = extension;
		this.data = data;
	}

	public Extension getExtension() {
		return extension;
	}
	
	public void setExtension(Extension extension) {
		this.extension = extension;
	}
	
	public byte[] getData() {
		return data;
	}
	
	public void setData(byte[] data) {
		this.data = data;
	}
	
	
}
