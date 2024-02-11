
public class Filters(){
  	public APImage blackAndWhite(APImage ap) {
		APImage result = ap.clone();
		for(Pixel p: result) {
			int r=p.getRed(),b=p.getBlue(),g=p.getGreen();
			int avg = (r+g+b)/3;
			if(avg<128) {
				p.setRed(0);
				p.setGreen(0);
				p.setBlue(0);
			} else {
				p.setRed(255);
				p.setGreen(255);
				p.setBlue(255);
			}
		}
		return result;
	}
	public APImage grayScale(APImage ap) {
		APImage result = ap.clone();
		for(Pixel p: result) {
			int r=p.getRed(),b=p.getBlue(),g=p.getGreen();
			int avg = (r+g+b)/3;
			p.setRed(avg);
			p.setGreen(avg);
			p.setBlue(avg);
		}
		return result;
	}
	public APImage lumGrayScale(APImage ap) {
		APImage result = ap.clone();
		for(Pixel p: result) {
			double r=p.getRed()*.299,b=p.getBlue()*.114,g=p.getGreen()*.587;
			int avg = ((int)(r+g+b))/3;
			p.setRed(avg);
			p.setGreen(avg);
			p.setBlue(avg);
		}
		return result;
	}
	public APImage rotate180(APImage ap) {
		return rotate90(rotate90(ap,true),true);
	}
	public APImage rotate90(APImage ap,boolean isLeft) {
		APImage result = new APImage(ap.getHeight(),ap.getWidth());
		for(int i=0;i<ap.getWidth();i++) {
			for(int j=0;j<ap.getHeight();j++) {
				if(isLeft) {
					result.setPixel(j, ap.getWidth()-1-i, ap.getPixel(i, j));
				} else {
					result.setPixel(ap.getHeight()-1-j, i, ap.getPixel(i, j));
				}
				
			}
		}
		result.draw();
		return result;
	}
	public APImage sepia(APImage ap) {
		APImage result = ap.clone();
		grayScale(result);
		for(Pixel p: result) {
			if(p.getRed()<63) {
				p.setRed((int)(p.getRed()*1.1));
				p.setBlue((int)(p.getBlue()*.9));
			} else if(p.getRed()<192) {
				p.setRed((int)(p.getRed()*1.15));
				p.setBlue((int)(p.getBlue()*.85));
			}else {
				p.setRed(Math.min((int)(p.getRed()*1.08), 255));
				p.setBlue((int)(p.getBlue()*0.93));
			}
		}
		return result;
	}
	public APImage negative(APImage ap) {
		APImage result = ap.clone();
		for(Pixel p: result) {
			p.setRed(255-p.getRed());
			p.setGreen(255-p.getGreen());
			p.setBlue(255-p.getBlue());
		}
		return result;
	}
	public APImage posterize(APImage ap) {
		APImage result = ap.clone();
		int r1=((int) (Math.random()*256));
		int g1=((int) (Math.random()*256));
		int b1=((int) (Math.random()*256));
		int r2=((int) (Math.random()*256));
		int g2=((int) (Math.random()*256));
		int b2=((int) (Math.random()*256));
		for(Pixel p: result) {
			int r=p.getRed(),b=p.getBlue(),g=p.getGreen();
			int avg = (r+g+b)/3;
			if(avg<128) {
				p.setRed(r1);
				p.setGreen(g1);
				p.setBlue(b1);
			} else {
				p.setRed(r2);
				p.setGreen(g2);
				p.setBlue(b2);
			}
		}
		return result;
	}
}// end of class
