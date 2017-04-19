package heroes.model;

import org.junit.Test;

import static heroes.utils.ImageFromViewDataset.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

public class ImageFromViewTest {

	@Test
	public void emptyConstructorTest(){
		new ImageFromView();
	}
	
	@Test
	public void constructorTest(){
		ImageFromView image = new ImageFromView(getValidImageFromViewId(), getValidImageFromViewExtension(), getValidImageFromViewData());
		assertThat(image.getId(), is(equalTo(getValidImageFromViewId())));
		assertThat(image.getExtension(), is(equalTo(getValidImageFromViewExtension())));
		assertThat(image.getData(), is(equalTo(getValidImageFromViewData())));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setWrongIdTest(){
		getValidImageFromView().setId(getInvalidImageFromViewId());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setWrongExtensionTest(){
		getValidImageFromView().setExtension(getInvalidImageFromViewExtension());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setNullExtensionTest(){
		getValidImageFromView().setExtension(null);
	}
	
	@Test
	public void setJpegExtensionTest(){
		ImageFromView image = getValidImageFromView();
		image.setExtension(getJpgImageFromViewExtentsion());
		assertThat(image.getExtension(), is(equalTo(getJpgImageFromViewExtentsionAfter())));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setWrongDataTest(){
		getValidImageFromView().setData(getInvalidImageFromViewData());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setNullDataTest(){
		getValidImageFromView().setData(null);
	}
	
	@Test
	public void setDataWithMimeTest(){
		ImageFromView image = getValidImageFromView();
		image.setData(getValidWithMimeHeaderImageFromViewData());
		assertThat(image.getData(), is(equalTo(getValidWithMimeHeaderImageFromViewDataAfter())));
	}
	
	@Test
	public void toImageTest(){
		Image image = getValidImageFromView().toImage();
		Image expected = getValidToImage();
		assertThat(image.getId(), is(equalTo(expected.getId())));
		assertThat(image.getExtension(), is(equalTo(expected.getExtension())));
		assertThat(image.getData(), is(equalTo(expected.getData())));
	}
	
}
