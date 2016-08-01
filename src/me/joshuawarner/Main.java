package me.joshuawarner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class Main {

    public static void main(String[] args) {
        //image dimension
        int width = 32;
        int height = 32;
        //create buffered image object img
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        //file object
        File f = null;

        String[] result = pixelGen(width, height, img);

        File file = new File("pass.txt");
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.println(Arrays.toString(result));
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try{
            f = new File("pass.png");
            ImageIO.write(img, "png", f);
        }catch(IOException e){
            System.out.println("Error: " + e);
        }
    }

    private static String[] pixelGen(int width, int height, BufferedImage img) {
        String[] arr;
        arr = new String[1024];
        for(int i=0; i<1024; i++) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int a = (int) (Math.random() * 256); //alpha
                    int r = (int) (Math.random() * 256); //red
                    int g = (int) (Math.random() * 256); //green
                    int b = (int) (Math.random() * 256); //blue

                    String pass = (a << 24) + "" + (r << 16) + "" + (g << 8) + "" + b;

                    int p = (a << 24) | (r << 16) | (g << 8) | b;



                    img.setRGB(x, y, p);

                    arr[i] = pass;
                }
            }
        }

        return arr;
    }
}
