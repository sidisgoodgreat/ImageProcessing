public class Pixel {
  private int red;
  private int green; 
  private int blue;

  // creates a Pixel object and sets the red, green, blue values to the 3 values passed in
  public Pixel(int r, int g, int b) {
    red = r;
    green = g;
    blue = b;
    Pixel.setColor(red,green,blue);
  }
  // returns Pixel's red value
  public int getRed() {
    return red;
  }
  // returns Pixel's green value
  public int getGreen() {
    return green;
  }
  // returns Pixel's blue value
  public int getBlue() {
    return blue;
  }
  // sets red to the value r that is passed in
  public void setRed(int r) {
    red = r;
  }
  // sets green to the value g that is passed in 
  public void setGreen(int g) {
    green = g;
  }
  // sets blue to the value b that is passed in
  public void setBlue(int b) {
    blue = b;
  }
  // Returns a clone of this pixel
  public Pixel Clone() {
    return new Pixel(red,green,blue);
  }
  // Prints out String representation of the Pixel, the red green and blue values.
   public String toString(){
    return "R: "+red+" G: "+green+" B: "+blue;
  }
}//end of class


      
    
    
