package heroes.utils;

import java.util.Base64;

import heroes.model.Image;
import heroes.model.ImageFromView;

public class ImageFromViewDataset {

	public static ImageFromView getValidImageFromView(){
		return new ImageFromView(getValidImageFromViewId(), getValidImageFromViewExtension(), getValidImageFromViewData());
	}

	public static String getValidImageFromViewData() {
		return "asdasd";
	}

	public static String getValidImageFromViewExtension() {
		return "png";
	}

	public static int getValidImageFromViewId() {
		return 1;
	}

	public static int getInvalidImageFromViewId() {
		return -1;
	}
	
	public static String getInvalidImageFromViewExtension(){
		return "";
	}
	
	public static String getJpgImageFromViewExtentsion(){
		return "JPG";
	}
	
	public static String getJpgImageFromViewExtentsionAfter(){
		return "jpeg";
	}
	
	public static String getInvalidImageFromViewData(){
		return "";
	}

	public static String getValidWithMimeHeaderImageFromViewData() {
		return "asdasd,asdasd";
	}

	public static String getValidWithMimeHeaderImageFromViewDataAfter() {
		return "asdasd";
	}
	
	public static Image getValidToImage(){
		return new Image(
			getValidImageFromViewId(),
			Image.Extension.valueOf(getValidImageFromViewExtension()),
			Base64.getMimeDecoder().decode(getValidImageFromViewData())
		);
	}
	
	
}
