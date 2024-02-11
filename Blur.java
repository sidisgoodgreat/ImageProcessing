package images;

import images.APImage;
import images.Pixel;
import java.util.Scanner;

public class Blur {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        APImage image = new APImage("smokey.jpg");
        int width = image.getWidth();
        int height = image.getHeight();


        APImage tempImage = image.clone();


        for (int x = 1; x < width - 1; x++) {
            for (int y = 1; y < height - 1; y++) {
                Pixel thisPixel = tempImage.getPixel(x, y);
                Pixel up = tempImage.getPixel(x, y - 1);
                Pixel down = tempImage.getPixel(x, y + 1);
                Pixel left = tempImage.getPixel(x - 1, y);
                Pixel right = tempImage.getPixel(x + 1, y);


                int avgRed = (thisPixel.getRed() + up.getRed() + down.getRed() + left.getRed() + right.getRed()) / 5;
                int avgGreen = (thisPixel.getGreen() + up.getGreen() + down.getGreen() + left.getGreen() + right.getGreen()) / 5;
                int avgBlue = (thisPixel.getBlue() + up.getBlue() + down.getBlue() + left.getBlue() + right.getBlue()) / 5;

                image.getPixel(x, y).setRed(avgRed);
                image.getPixel(x, y).setGreen(avgGreen);
                image.getPixel(x, y).setBlue(avgBlue);
            }
        }

        System.out.println("Press return to continue");
        reader.nextLine();
        image.draw();
    }
}
