package images;
import images.APImage;
import images.Pixel;
import java.util.*;
public class Brighten {
    public static void main(String[]args){
        Scanner reader = new Scanner(System.in);
        APImage image = new APImage("smokey.jpg");
        image.draw();
        for (Pixel p : image) {
            int red = p.getRed();
            int green = p.getGreen();
            int blue = p.getBlue() ;
            if (red + 20 <= 255) {
                p.setRed(red + 20);
            }
            if (blue + 20 <= 255) {
                p.setBlue(blue + 20);
            }
            if (green + 20 <= 255) {
                p.setGreen(green + 20);
            }
        }
        image.draw();
    }
}
