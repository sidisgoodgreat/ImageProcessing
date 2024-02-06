import java.io.*;
import java.util.Scanner;
public class ApImage {
  public ArrayList<Integer> [][] APImage() {
      ArrayList<Integer>[][] list = new ArrayList[200][200];
      return list;
  }
  public int[][] APImage(String filename) {
    int[][] fileStorer = {{1}, {2}};
    File fileName = new File(filename);
    Scanner in = new Scanner(fileName);
    int j = 0;
    String[] len = in.nextLine().trim().split("\\s+");
    for (int i = 0; i < len.length; i++) {
      j++;
    }  
    in.close(); 
    fileStorer = new int[intLength][intLength];
    in = new Scanner(inFile);

    int lineCount = 0;
    while (in.hasNextLine()) {
      String[] currentLine = in.nextLine().trim().split("\\s+"); 
      for (int i = 0; i < currentLine.length; i++) {
        fileStorer[lineCount][i] = Integer.parseInt(currentLine[i]);    
      }
      lineCount++;
   }                                 
   return fileStorer;
  }
  public APImage(int width, int height) {
		image=new Pixel[height][width];
		for(int i=0;i<image.length;i++) {
			for(Pixel p: image[i]) {
				p=new Pixel(0,0,0);
			}
		}
	}
	
	public int getWidth() {
		return image.length;
	}
	public int getHeight() {
		return image[0].length;
	}
	public Pixel getPixel(int x, int y) {
		return image[x][y];
	}
	public void setPixel(int x, int y, Pixel p) {
		image[x][y]=p;
	}
	
	
	public APImage clone() {
		return
	}

 

        
