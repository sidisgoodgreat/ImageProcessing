package images;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class ImageFileFilter extends FileFilter {
   private String extension;
   private String description;

   public ImageFileFilter(String var1, String var2) {
      this.extension = var1;
      this.description = var2;
   }

   public boolean accept(File var1) {
      return var1.getName().toLowerCase().endsWith(this.extension) || var1.isDirectory();
   }

   public String getDescription() {
      return this.description;
   }
}
