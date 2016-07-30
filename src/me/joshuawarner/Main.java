package me.joshuawarner;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Main {

    public static void main(String[] args) {
        //image dimension
        int width = 512;
        int height = 512;
        //create buffered image object img
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        //file object
        File f = null;

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                int a = (int)(Math.random()*256); //alpha
                int r = (int)(Math.random()*256); //red
                int g = (int)(Math.random()*256); //green
                int b = (int)(Math.random()*256); //blue

                int p = (a<<24) | (r<<16) | (g<<8) | b;

                img.setRGB(x, y, p);
            }
        }

        try{
            f = new File("/Users/joshua/desktop/Output.png");
            ImageIO.write(img, "png", f);
        }catch(IOException e){
            System.out.println("Error: " + e);
        }
    }
}
