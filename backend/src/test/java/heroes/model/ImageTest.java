package heroes.model;

import org.junit.Test;

import static heroes.utils.ImageDataset.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

public class ImageTest {
	
	@Test
	public void emptyConstructorTest(){
		new Image();
	}

	@Test
	public void constructorTest(){
		Image image = new Image(getValidImageId(), getValidImageExtension(), getValidImageData());
		assertThat(image.getId(), is(equalTo(getValidImageId())));
		assertThat(image.getExtension(), is(equalTo(getValidImageExtension())));
		assertThat(image.getData(), is(equalTo(getValidImageData())));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setWrongIdTest(){
		getValidImage().setId(getInvalidImageId());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setNullExtensionTest(){
		getValidImage().setExtension(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setNullDataTest(){
		getValidImage().setData(null);
	}
	
	/*
	 * For code coverage completeness
	 */
	@Test
	public void imageExtensionEnumTest(){
		Image.Extension.values();
		Image.Extension.valueOf("jpeg");
	}
	
}
