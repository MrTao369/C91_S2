package com.zt;
/**
 * 
 * @author 张韬
 *工具类
 */

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

public class App {
/**
 * 加载图片的方法
 * @param path
 * @return
 */
	public static BufferedImage getImg(String path) {
		
		BufferedImage img=null;
		try {
			//通过IO流来读取图片
			
			img=ImageIO.read(new FileInputStream(path));

			return img;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
}
