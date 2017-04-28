package heroes.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;

import heroes.exception.ImageNotFoundException;
import heroes.model.Image;
import heroes.utils.TestConfig;

import static heroes.utils.ImageDataset.*;
import static heroes.utils.HeroDataset.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@PropertySource("classpath:application.properties")
@DatabaseSetup("/dataset/default.xml")
@ContextConfiguration(classes = {TestConfig.class}, loader = AnnotationConfigContextLoader.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class ImageServiceTest {

	@Autowired
	private ImageService imageService;
	
	@Test
	@ExpectedDatabase(value = "/dataset/default.xml", table = "image")
	public void getHeroImageTest() throws ImageNotFoundException{
		Image image = getValidImage();
		Image result = imageService.getHeroImage(image.getId());
		assertThat(result.getId(), is(equalTo(image.getId())));
		assertThat(result.getExtension(), is(equalTo(image.getExtension())));
		assertThat(result.getData(), is(equalTo(image.getData())));
	}
	
	@Test(expected = ImageNotFoundException.class)
	@ExpectedDatabase(value = "/dataset/default.xml", table = "image")
	public void getHeroImageImageNotFoundExceptionTest() throws ImageNotFoundException{
		imageService.getHeroImage(getInvalidImageId());
	}
	
	@Test
	@ExpectedDatabase(value = "/dataset/delete-image.xml", table = "image")
	public void deleteHeroImageTest() throws ImageNotFoundException{
		imageService.deleteHeroImage(getValidImageId());
	}

	@Test(expected = ImageNotFoundException.class)
	@ExpectedDatabase(value = "/dataset/default.xml", table = "image")
	public void deleteHeroImageImageNotFoundExceptionTest() throws ImageNotFoundException{
		imageService.deleteHeroImage(getInvalidImageId());
	}
	
	@Test
	@ExpectedDatabase(value = "/dataset/add-image.xml", table = "image")
	public void addHeroImageTest() throws ImageNotFoundException{
		imageService.addHeroImage(new Image(getExistentHeroIdWithoutImage(), getValidImageExtension(), getValidImageData()));
	}
}
