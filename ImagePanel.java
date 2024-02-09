package images;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
   private Image image;

   public ImagePanel(Image var1, int var2, int var3) {
      this.setBackground(Color.white);
      this.image = var1;
      this.setPreferredSize(new Dimension(var2, var3));
   }

   public void paintComponent(Graphics var1) {
      super.paintComponent(var1);
      if (this.image != null) {
         var1.drawImage(this.image, 0, 0, Color.white, this);
      }

   }

   public void setImage(Image var1, int var2, int var3) {
      this.image = var1;
      this.setPreferredSize(new Dimension(var2, var3));
      this.repaint();
   }
}
