package images;

public class Pixel {
   private int alpha;
   private int red;
   private int green;
   private int blue;

   public Pixel(int var1) {
      this.alpha = 255;
      this.red = 0;
      this.green = 0;
      this.blue = 0;
      this.setValue(var1);
   }

   private Pixel(int var1, int var2, int var3, int var4) {
      this.alpha = 255;
      this.red = 0;
      this.green = 0;
      this.blue = 0;
      this.red = var1;
      this.green = var2;
      this.blue = var3;
      this.alpha = var4;
   }

   public Pixel(int var1, int var2, int var3) {
      this(var1, var2, var3, 255);
   }

   private Pixel(Pixel var1) {
      this(var1.getRed(), var1.getGreen(), var1.getBlue(), var1.getAlpha());
   }

   private void setValue(int var1) {
      this.alpha = var1 >> 24 & 255;
      this.red = var1 >> 16 & 255;
      this.green = var1 >> 8 & 255;
      this.blue = var1 & 255;
   }

   public int getValue() {
      return this.alpha << 24 | this.red << 16 | this.green << 8 | this.blue;
   }

   public int getRed() {
      return this.red;
   }

   public int getGreen() {
      return this.green;
   }

   public int getBlue() {
      return this.blue;
   }

   private int getAlpha() {
      return this.alpha;
   }

   public void setRed(int var1) {
      this.red = var1;
   }

   public void setGreen(int var1) {
      this.green = var1;
   }

   public void setBlue(int var1) {
      this.blue = var1;
   }

   private void setAlpha(int var1) {
      this.alpha = var1;
   }

   public Pixel clone() {
      return new Pixel(this);
   }

   public String toString() {
      return "Pixel R: " + this.red + " G: " + this.green + " B: " + this.blue;
   }
}
