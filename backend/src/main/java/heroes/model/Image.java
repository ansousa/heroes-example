package heroes.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Image {
	public static enum Extension{jpeg, png}
	
	@Id
	private int id;
	@Enumerated(EnumType.STRING)
	private Extension extension;
	@Lob
	private byte[] data;
	
	public Image(){}
	
	public Image(int id, Extension extension, byte[] data) {
		this.setId(id);
		this.setExtension(extension);
		this.setData(data);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if(id < 0)
			throw new IllegalArgumentException();
		this.id = id;
	}

	public Extension getExtension() {
		return extension;
	}
	
	public void setExtension(Extension extension) {
		if(extension == null)
			throw new IllegalArgumentException();
		this.extension = extension;
	}
	
	public byte[] getData() {
		return data;
	}
	
	public void setData(byte[] data) {
		if(data == null)
			throw new IllegalArgumentException();
		this.data = data;
	}
	
	
}
