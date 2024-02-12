package ImageProcessing;

/**
 * The ImageDriver class contains the main method to run the image processing application
 * It initializes a menu object invokes its menu method to display the menu
 * @author Manh Hang
 */
public class ImageDriver {
	/**
	 * The main method of the ImageDriver class  
	 * It creates a menu object and calls on it to display the menu
	 * 
	 * @param args The command-line arguments
	 */
	public static void main (String [] args) {
	    Menu m = new Menu();
	    m.menu();
	  }
}
