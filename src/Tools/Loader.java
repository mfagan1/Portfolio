/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Loader {
	
	public BufferedImage loadResource(String path, String fileType) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getClassLoader().getResource(path + "." + fileType));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

}