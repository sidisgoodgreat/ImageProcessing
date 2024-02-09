package images;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class APImage extends JFrame implements Iterable<Pixel> {
   private static final int WIDTH = 200;
   private static final int HEIGHT = 200;
   private Pixel[] pixels;
   private Image image;
   private ImagePanel imagePanel;
   private String fileName;

   public APImage(int var1, int var2) {
      this.fileName = "";
      this.image = this.createImage(this.getBlankImage(var1, var2));
      this.setWindowAttributes();
   }

   public APImage(String var1) {
      this.fileName = var1;
      this.image = this.getImageFromFile(var1);
      this.setWindowAttributes();
   }

   public APImage() {
      File var1 = this.openFileDialog();
      if (var1 == null) {
         this.fileName = "";
         this.image = this.createImage(this.getBlankImage(200, 200));
      } else {
         this.fileName = var1.getName();
         this.image = this.getImageFromFile(this.fileName);
      }

      this.setWindowAttributes();
   }

   private APImage(APImage var1) {
      this(var1.getWidth(), var1.getHeight());

      for(int var2 = 0; var2 < var1.getWidth(); ++var2) {
         for(int var3 = 0; var3 < var1.getHeight(); ++var3) {
            Pixel var4 = var1.getPixel(var2, var3);
            Pixel var5 = this.getPixel(var2, var3);
            var5.setRed(var4.getRed());
            var5.setGreen(var4.getGreen());
            var5.setBlue(var4.getBlue());
         }
      }

   }

   public int getWidth() {
      return this.image.getWidth(this);
   }

   public int getHeight() {
      return this.image.getHeight(this);
   }

   public Pixel getPixel(int var1, int var2) {
      return this.pixels[var2 * this.image.getWidth(this) + var1];
   }

   public void setPixel(int var1, int var2, Pixel var3) {
      this.pixels[var2 * this.image.getWidth(this) + var1] = var3;
   }

   public void draw() {
      this.setVisible(true);
      this.updateImage();
   }

   public APImage clone() {
      return new APImage(this);
   }

   public String toString() {
      return this.image == null ? null : "IMAGE\nFile name: " + this.fileName + "\n" + "Width: " + this.getWidth() + "\n" + "Height: " + this.getHeight();
   }

   public Iterator<Pixel> iterator() {
      return new APImage.ImageIterator();
   }

   public boolean save() {
      if (this.fileName.equals("")) {
         return this.saveAs();
      } else {
         try {
            int var1 = this.image.getWidth(this);
            int var2 = this.image.getHeight(this);
            int[] var3 = this.pixelsToInts(this.pixels);
            BufferedImage var4 = new BufferedImage(var1, var2, 1);
            var4.setRGB(0, 0, var1, var2, var3, 0, var1);
            File var5 = new File(this.fileName);
            ImageIO.write(var4, "jpg", var5);
            return true;
         } catch (Exception var6) {
            System.err.println(var6);
            return false;
         }
      }
   }

   public boolean saveAs() {
      File var1 = this.saveFileDialog();
      if (var1 == null) {
         return false;
      } else {
         this.fileName = var1.getName();
         this.setTitle(this.fileName);
         return this.save();
      }
   }

   private void setWindowAttributes() {
      this.setTitle(this.fileName);
      this.imagePanel = new ImagePanel(this.image, this.image.getWidth(this), this.image.getHeight(this));
      Container var1 = this.getContentPane();
      var1.setBackground(Color.white);
      var1.add(this.imagePanel);
      this.pack();
      this.setDefaultCloseOperation(3);
   }

   private void updateImage() {
      int var1 = this.image.getWidth(this);
      int var2 = this.image.getHeight(this);
      this.image = this.createImage(new MemoryImageSource(var1, var2, this.pixelsToInts(this.pixels), 0, var1));
      this.imagePanel.setImage(this.image, var1, var2);
   }

   private Pixel[] imageToPixels(Image var1) {
      int var2 = var1.getWidth(this);
      int var3 = var1.getHeight(this);
      int[] var4 = new int[var2 * var3];
      PixelGrabber var5 = new PixelGrabber(var1, 0, 0, var2, var3, var4, 0, var2);

      try {
         var5.grabPixels();
      } catch (InterruptedException var7) {
         System.err.println("Interrupted waiting for pixels!");
         return null;
      }

      if ((var5.getStatus() & 128) != 0) {
         System.err.println("Image fetch aborted or errored");
         return null;
      } else {
         return this.intsToPixels(var4);
      }
   }

   private Pixel[] intsToPixels(int[] var1) {
      Pixel[] var2 = new Pixel[var1.length];

      for(int var3 = 0; var3 < var1.length; ++var3) {
         var2[var3] = new Pixel(var1[var3]);
      }

      return var2;
   }

   private int[] pixelsToInts(Pixel[] var1) {
      int[] var2 = new int[var1.length];

      for(int var3 = 0; var3 < var1.length; ++var3) {
         var2[var3] = var1[var3].getValue();
      }

      return var2;
   }

   private ImageProducer getBlankImage(int var1, int var2) {
      this.pixels = new Pixel[var1 * var2];

      for(int var3 = 0; var3 < this.pixels.length; ++var3) {
         this.pixels[var3] = new Pixel(0, 0, 0);
      }

      return new MemoryImageSource(var1, var2, this.pixelsToInts(this.pixels), 0, var1);
   }

   private Image getImageFromFile(String var1) {
      BufferedImage var2 = null;

      try {
         File var3 = new File(var1);
         var2 = ImageIO.read(var3);

         while(true) {
            if (var2.getWidth(this) >= 0) {
               this.pixels = this.imageToPixels(var2);
               break;
            }
         }
      } catch (Exception var4) {
         System.out.println(var4);
      }

      return var2;
   }

   private File openFileDialog() {
      JFileChooser var1 = new JFileChooser();
      var1.setFileFilter(new ImageFileFilter(".jpg", "JPEG images (*.jpg)"));
      int var2 = var1.showOpenDialog((Component)null);
      if (var2 == 1) {
         return null;
      } else {
         try {
            File var3 = var1.getSelectedFile();
            return var3;
         } catch (Exception var4) {
            JOptionPane.showMessageDialog((Component)null, var4.toString());
            return null;
         }
      }
   }

   private File saveFileDialog() {
      JFileChooser var1 = new JFileChooser();
      var1.setFileFilter(new ImageFileFilter(".jpg", "JPEG images (*.jpg)"));
      int var2 = var1.showSaveDialog((Component)null);
      if (var2 == 1) {
         return null;
      } else {
         try {
            File var3 = var1.getSelectedFile();
            return var3;
         } catch (Exception var4) {
            JOptionPane.showMessageDialog((Component)null, var4.toString());
            return null;
         }
      }
   }

   private class ImageIterator implements Iterator<Pixel> {
      private int pos;

      private ImageIterator() {
         this.pos = 0;
      }

      public boolean hasNext() {
         return this.pos < APImage.this.pixels.length;
      }

      public Pixel next() {
         if (!this.hasNext()) {
            throw new NoSuchElementException();
         } else {
            ++this.pos;
            return APImage.this.pixels[this.pos - 1];
         }
      }

      public void remove() {
         throw new UnsupportedOperationException();
      }

      // $FF: synthetic method
      ImageIterator(Object var2) {
         this();
      }
   }
}
