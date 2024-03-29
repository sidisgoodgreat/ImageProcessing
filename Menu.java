package imageProcessor;
package imageProcessor;
import images.*;
import java.util.Scanner;
/**
 * The Menu class represents the user UI for the Image processing application
 * It gives the User numerous options and displays the result
 * @author Ethan, Sid, Manh, Anmol
 */
public class Menu {
	private APImage ap,clone;
	private Filter f = new Filter();
	
	/**
	 * Construct a new menu object and initializes the image based on user input
	 * If the user chooses to process a stock image, it loads the "smokey.jpg" image,
	 * otherwise it initializes a blank object
	 * 
	 * author: Ethan
	 */
	public Menu() {
		Scanner s = new Scanner(System.in);
		
		System.out.println("Welcome to the Image Processor"
							+"\nWhat would you like to do?"
							+"\nProcess image on computer(COM)"
							+"\nProcess stock image(STOCK)");
		String choice = chooseStringCycle(new String[] {"COM","STOCK"});
		if("COM".equals(choice)) {
			ap=new APImage();
		} else {
			ap = new APImage("smokey.jpg");
		}
		clone=ap.clone();
		clone.draw();
	}
	/**
	 * Displays the main menu and processes user input until the user chooses to end the program.
	 * Author: Anmol
	 */
	public void menu() {
		System.out.println("You are processing: "+"\n"+clone+"\nWhat would you like to do?"+
				"\nBlack and White - Convert the image to black and white - BW"
				+ "\nGrayscale - Convert the image to grayscale - GS"
				+ "\nLuminance Grayscale - Convert the image to luminance grayscale - LGS"
				+ "\nRotate 90 Degrees - Rotate the image 90 degrees clockwise or counterclockwise - 90R/90L"
				+ "\nRotate 180 Degrees - Rotate the image 180 degrees - 180"
				+ "\nSepia - Apply a sepia filter to the image - SEP"
				+ "\nDarken - Darken the colors of the image - DARK"
				+ "\nBrighen - Brighten the colors of the image - BRIGHT"
				+ "\nColor Filter - Apply a chosen color over the image - COLOR"
				+ "\nPosterize - Change the image's colors to two random ones - POS"
				+ "\nPhotographic Negative - Convert the image to a photographic negative - NEG"
				+ "\nSharpen - Sharpen the edges of the image - SHARP"
				+ "\nBlur - Blurs the image - BLUR"
				+ "\nShrink - Shrinks the image by a given factor - SHRINK"
				+ "\nEnlarge - Enlarges the image by a given factor - LARGE"
				+ "\nRevert - Reverts the image to the orignal - REVERT"
				+ "\nEnd - Ends the program and displays your new image - END");
		String choice = optionCycle();
		while(!"END".equals(choice)) {
			imageProcess(choice);
			choice=optionCycle();
		}
		System.out.println("Thank you for using Image Processor. Here is your image along with the original");
		display();
	}
	/**
	 * Helper method used to input for choosing  from a list of Strings
	 * Author: Sid
	 * 
	 * @param list List of Strings to choose from
	 * @return the user's chosen string
	 */
	private String chooseStringCycle(String[] list) {
		Scanner s = new Scanner(System.in);
		String choice = s.nextLine().toUpperCase();
		while(!isOption(choice,list)) {
			System.out.print("Invalid choice; choose again:");
			choice = s.nextLine().toUpperCase();
		}
		return choice;
	}
	
	/**
	 * Checks if the provided choice is valid among the given list of options.
	 * Author: Anmol
	 * 
	 * @param choice The user's inputed choice
	 * @param list The List of Options to choose from
	 * @return True if choice is valid, false if not
	 */
	private boolean isOption(String choice,String[] list) {
		for(String s:list) {
			if(s.equals(choice)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Helper method to handle user input for choosing an integer within a specified range.
	 * Author: Manh 
	 * 
	 * @param lower The Lower bound of the integer range
	 * @param upper The Upper bound of the Integer range
	 * @return The user's chosen integer thats within range
	 */
	private int chooseInteger(int lower, int upper) {
		Scanner s = new Scanner(System.in);
		System.out.print("Choose a value from "+lower+" to "+upper);
		int choice=s.nextInt();
		while(choice<lower||choice>upper) {
			System.out.println("invalid, please choose another integer: ");
			choice=s.nextInt();
		}
		return choice;
	}
	/**
	 * Displays the available options and processes the user's choice.
	 * Author: Sid
	 * 
	 * @return The user's chosen choice
	 */
	private String optionCycle() {
		String choice = chooseStringCycle(new String[] {"BW","GS","LGS","90R","90L","180","SEP","DARK"
				,"BRIGHT","COLOR","POS","NEG","SHARP","BLUR","SHRINK","LARGE","REVERT","END"});
		return choice;
	}
	/**
	 * Processes the user's chosen image processing operation.
	 * Author: Ethan
	 * 
	 * @param choice The user's chosen operation
	 */
	private void imageProcess(String choice) {
		if("BW".equals(choice)) {
			clone = f.blackAndWhite(clone);
		} else if("GS".equals(choice)) {
			clone = f.grayScale(clone);
		}else if("LGS".equals(choice)) {
			clone = f.lumGrayScale(clone);
		}else if("90R".equals(choice)) {
			clone = f.rotate90(clone,false);
		}else if("90L".equals(choice)) {
			clone = f.rotate90(clone,true);
		}else if("180".equals(choice)) {
			clone = f.rotate180(clone);
		}else if("SEP".equals(choice)) {
			clone = f.sepia(clone);
		}else if("DARK".equals(choice)) {
			clone = f.darken(clone,chooseInteger(0,100));
		}else if("BRIGHT".equals(choice)) {
			clone = f.brighten(clone,chooseInteger(0,100));
		}else if("COLOR".equals(choice)) {
			clone = f.colorFilter(clone,chooseInteger(0,255),chooseInteger(0,255),chooseInteger(0,255));
		}else if("POS".equals(choice)) {
			clone = f.posterize(clone);
		}else if("NEG".equals(choice)) {
			clone = f.negative(clone);
		}else if("SHARP".equals(choice)) {
			clone = f.sharpen(clone,chooseInteger(0,100));
		}else if("BLUR".equals(choice)) {
			clone = f.blur(clone);
		}else if("SHRINK".equals(choice)) {
			clone = f.shrink(clone,chooseInteger(0,10));
		}else if("LARGE".equals(choice)) {
			clone = f.enlarge(clone,chooseInteger(0,10));
		}else if("REVERT".equals(choice)) {
			clone = ap.clone();;
		}
		System.out.println("Would you like to see the result?(Y/N)");
		String yn = chooseStringCycle(new String[] {"Y","N"});
		if("Y".equals(yn)) {
			clone.draw();
		}
	}
	/**
	 * Displays the processed image along with the original image if requested by the user.
	 * Author: Sid
	 */
	private void display() {
		ap.draw();
		clone.draw();
	}
}
