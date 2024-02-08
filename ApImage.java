import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
//It took me some youtube vids and "research" but I managed to do this so far. I re-did everything cause we did it wrong.
public class APImage {
    private Pixel[][] image;
	
    public APImage() {
	    //Insert FILE DIALOG HERE
        blackImage(200,200);
    }
	
    public APImage(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            ArrayList<String> lines = new ArrayList<>();
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            scanner.close();

            image = new Pixel[lines.size()][lines.get(0).split("\\s+").length];

        } catch (FileNotFoundException error) {
            System.err.println("FileNotFoundException: " + error.getMessage());

            blackImage(200,200);
        }
    }
	public APImage(int width, int height){
		blackImage(width,height);
	}


	private void blackImage(int width, int height) {
		//Because height refers to number of rows, height goes first
		image = new Pixel[height][width];
		for(int i=0;i<image.length;i++) {
			for(Pixel p:image[i]) {
				p=new Pixel(0,0,0);
			}
		}
	}


    public int getWidth() {
        return image[0].length;
    }

    public int getHeight() {
        return image.length;
    }
    public Pixel getPixel(int x, int y) {
        return image[y][x];
    }
    public void setPixel(int x, int y, Pixel z) {
        image[y][x] = z;
    }
    public void draw() {
        // Idk how to do this tbh
    }


    public APImage clone() {
        APImage clonedImage = new APImage(this.getWidth(), this.getHeight());
        for (int i = 0; i < this.getHeight(); i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                clonedImage.setPixel(j, i, this.getPixel(j, i)); 
            }
        }
        return clonedImage;
    }


    public String toString() {
        return "Filename: "+"filenamegoeshere"+"\n"
		+"Width: "+getWidth()+"\n"
		+"Height: "+getHeight(); // depends on how we plan to do this
    }

}

        
