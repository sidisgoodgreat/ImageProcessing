package images;

import images.APImage;
import images.Pixel;
import java.util.Scanner;

public class ColorFilter {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the RGB values for the color filter:");
        System.out.print("Red value (-255 to 255): ");
        int filterRed = reader.nextInt();
        System.out.print("Green value (-255 to 255): ");
        int filterGreen = reader.nextInt();
        System.out.print("Blue value (-255 to 255): ");
        int filterBlue = reader.nextInt();

        APImage image = new APImage("smokey.jpg");
        image.draw();

        for (Pixel p : image) {
            int newRed = clamp(p.getRed() + filterRed);
            int newGreen = clamp(p.getGreen() + filterGreen);
            int newBlue = clamp(p.getBlue() + filterBlue);

            p.setRed(newRed);
            p.setGreen(newGreen);
            p.setBlue(newBlue);
        }

        System.out.println("Press return to continue...");
        reader.nextLine();
        reader.nextLine();
        image.draw();
    }

    private static int clamp(int value) {
        return Math.max(0, Math.min(value, 255));
    }
}
