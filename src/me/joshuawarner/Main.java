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
        //image dimensions
        int width = 32;
        int height = 32;

        //file initializers
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        File txtFile = new File("private.txt");
        File lengthFile = new File("private_length.txt");
        File imgFile = new File("public.png");

        //password array from pixelGen function
        String[] resultArr = pixelGen(width, height, img);

        //stringBuilder to remove braces & commas between array indices
        StringBuilder builder = new StringBuilder();
        //loop through each index of resultArr and concatenate all indices together
        for(String resultString : resultArr) {
            builder.append(resultString);
        }

        //set final result to builder value
        String resultFinal = builder.toString();

        //log pass_private length for informative purposes
        System.out.println("private size is " + resultFinal.length());

        //write private to txt file
        try {
            PrintWriter writer = new PrintWriter(txtFile);
            writer.println(resultFinal);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //write private_length to txt file
        try {
            PrintWriter lengthWriter = new PrintWriter(lengthFile);
            lengthWriter.println(resultFinal.length());
            lengthWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //write public to png image file
        try{
            ImageIO.write(img, "png", imgFile);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //method to fill image pixels as well as private array
    private static String[] pixelGen(int width, int height, BufferedImage img) {
        //initialize array for rgba indices
        String[] arr = new String[1024];

        //loop through each index of array
        for(int i=0; i<1024; i++) {
            //loop through each y value
            for (int y = 0; y < height; y++) {
                //loop through each x value
                for (int x = 0; x < width; x++) {
                    int a = (int) (Math.random() * 256); //alpha
                    int r = (int) (Math.random() * 256); //red
                    int g = (int) (Math.random() * 256); //green
                    int b = (int) (Math.random() * 256); //blue

                    //set string to rgba value from current pixel
                    String pass = r + "" + g + "" + b + "" + a;

                    int p = (a << 24) | (r << 16) | (g << 8) | b;


                    //set current xy value to pixel color p
                    img.setRGB(x, y, p);

                    //add current rgba value to index
                    arr[i] = pass;
                }
            }
        }
        //return array to main function
        return arr;
    }
}
