public class Filter {

    public APImage blackAndWhite(APImage ap) {
        APImage result = ap.clone();
        for (Pixel p : result) {
            int r = p.getRed(), b = p.getBlue(), g = p.getGreen();
            int avg = (r + g + b) / 3;
            if (avg < 128) {
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
        for (Pixel p : result) {
            int r = p.getRed(), b = p.getBlue(), g = p.getGreen();
            int avg = (r + g + b) / 3;
            p.setRed(avg);
            p.setGreen(avg);
            p.setBlue(avg);
        }
        return result;
    }

    public APImage lumGrayScale(APImage ap) {
        APImage result = ap.clone();
        for (Pixel p : result) {
            double r = p.getRed() * .299, b = p.getBlue() * .114, g = p.getGreen() * .587;
            int avg = (int) (r + g + b);
            p.setRed(avg);
            p.setGreen(avg);
            p.setBlue(avg);
        }
        return result;
    }

    public APImage rotate180(APImage ap) {
        return rotate90(rotate90(ap, true), true);
    }

    public APImage rotate90(APImage ap, boolean isLeft) {
        APImage result = new APImage(ap.getHeight(), ap.getWidth());
        for (int i = 0; i < ap.getWidth(); i++) {
            for (int j = 0; j < ap.getHeight(); j++) {
                if (isLeft) {
                    result.setPixel(j, ap.getWidth() - 1 - i, ap.getPixel(i, j));
                } else {
                    result.setPixel(ap.getHeight() - 1 - j, i, ap.getPixel(i, j));
                }
            }
        }
        return result;
    }

    public APImage sepia(APImage ap) {
        APImage result = grayScale(ap); // Apply grayscale first
        for (Pixel p : result) {
            int red = p.getRed();
            if (red < 63) {
                p.setRed((int) (red * 1.1));
                p.setBlue((int) (p.getBlue() * .9));
            } else if (red < 192) {
                p.setRed((int) (red * 1.15));
                p.setBlue((int) (p.getBlue() * .85));
            } else {
                p.setRed(Math.min((int) (red * 1.08), 255));
                p.setBlue((int) (p.getBlue() * 0.93));
            }
        }
        return result;
    }

    public APImage negative(APImage ap) {
        APImage result = grayScale(ap.clone());
        for (Pixel p : result) {
            p.setRed(255 - p.getRed());
            p.setGreen(255 - p.getGreen());
            p.setBlue(255 - p.getBlue());
        }
        return result;
    }

    public APImage posterize(APImage ap) {
        APImage result = ap.clone();
        int r1 = (int) (Math.random() * 256);
        int g1 = (int) (Math.random() * 256);
        int b1 = (int) (Math.random() * 256);
        int r2 = (int) (Math.random() * 256);
        int g2 = (int) (Math.random() * 256);
        int b2 = (int) (Math.random() * 256);
        for (Pixel p : result) {
            int r = p.getRed(), b = p.getBlue(), g = p.getGreen();
            int avg = (r + g + b) / 3;
            if (avg < 128) {
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

    public APImage blur(APImage ap) {
        APImage result = ap.clone();
        int width = result.getWidth();
        int height = result.getHeight();

        for (int x = 1; x < width - 1; x++) {
            for (int y = 1; y < height - 1; y++) {
                int avgRed = 0, avgGreen = 0, avgBlue = 0;
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        Pixel p = result.getPixel(x + dx, y + dy);
                        avgRed += p.getRed();
                        avgGreen += p.getGreen();
                        avgBlue += p.getBlue();
                    }
                }
                Pixel p = result.getPixel(x, y);
                p.setRed(avgRed / 9);
                p.setGreen(avgGreen / 9);
                p.setBlue(avgBlue / 9);
            }
        }
        return result;
    }

    public APImage darken(APImage ap, int amount) {
        APImage result = ap.clone();
        for (Pixel p : result) {
            p.setRed(Math.max(p.getRed() - amount, 0));
            p.setGreen(Math.max(p.getGreen() - amount, 0));
            p.setBlue(Math.max(p.getBlue() - amount, 0));
        }
        return result;
    }

    public APImage brighten(APImage ap, int amount) {
        APImage result = ap.clone();
        for (Pixel p : result) {
            p.setRed(Math.min(p.getRed() + amount, 255));
            p.setGreen(Math.min(p.getGreen() + amount, 255));
            p.setBlue(Math.min(p.getBlue() + amount, 255));
        }
        return result;
    }

    public APImage colorFilter(APImage ap, int filterRed, int filterGreen, int filterBlue) {
        APImage result = ap.clone();
        for (Pixel p : result) {
            p.setRed(clamp(p.getRed() + filterRed));
            p.setGreen(clamp(p.getGreen() + filterGreen));
            p.setBlue(clamp(p.getBlue() + filterBlue));
        }
        return result;
    }

    private int clamp(int value) {
        return Math.max(0, Math.min(value, 255));
    }
    
     public APImage shrink(APImage ap, int factor) {
    	APImage result = new APImage(ap.getHeight()/factor,ap.getWidth()/factor);
    	for(int w=0;w<result.getWidth();w++) {
			for(int h=0;h<result.getHeight();h++) {
				result.setPixel(w, h, ap.getPixel(w*factor, h*factor));
			}
		}
    	return result;
    }
    public APImage enlarge(APImage ap, int factor) {
    	APImage result = new APImage(ap.getWidth()*factor,ap.getHeight()*factor);
    	for(int w=0;w<result.getWidth();w++) {
			for(int h=0;h<result.getHeight();h++) {
				result.setPixel(w, h, ap.getPixel(w/factor, h/factor));
			}
		}
    	return result;
    }
	private Pixel[][] apiToArray(APImage ap){
    	Pixel[][] result = new Pixel[ap.getWidth()][ap.getHeight()];
    	for(int w=0;w<result.length;w++) {
			for(int h=0;h<result[0].length;h++) {
				result[w][h]=ap.getPixel(w, h);
			}
		}
    	return result;
    }
    private APImage arrayToAPImage(Pixel[][] arr) {
    	APImage result = new APImage(arr.length,arr[0].length);
    	for(int w=0;w<result.getWidth();w++) {
			for(int h=0;h<result.getHeight();h++) {
				result.setPixel(w, h, arr[w][h]);
			}
		}
    	return result;
    }
    public APImage sharpen(APImage ap, int sharpenAmount, int edgeThreshold) {
        Pixel[][] pixels = apiToArray(ap);
        Pixel[][] resultPixels = new Pixel[ap.getWidth()][ap.getHeight()];

        for (int x = 0; x < ap.getWidth(); x++) {
            resultPixels[x][0] = pixels[x][0].clone();
            resultPixels[x][ap.getHeight() - 1] = pixels[x][ap.getHeight() - 1].clone();
        }
        for (int y = 0; y < ap.getHeight(); y++) {
            resultPixels[0][y] = pixels[0][y].clone();
            resultPixels[ap.getWidth() - 1][y] = pixels[ap.getWidth() - 1][y].clone();
        }

        for (int x = 1; x < ap.getWidth() - 1; x++) {
            for (int y = 1; y < ap.getHeight() - 1; y++) {
                Pixel currentPixel = pixels[x][y];
                int currentLuminance = calculateLuminance(currentPixel);
                int neighborLuminance = calculateNeighborLuminance(pixels, x, y);

                if (Math.abs(currentLuminance - neighborLuminance) > edgeThreshold) {
                    resultPixels[x][y] = new Pixel(
                            clamp(currentPixel.getRed() + sharpenAmount),
                            clamp(currentPixel.getGreen() + sharpenAmount),
                            clamp(currentPixel.getBlue() + sharpenAmount)
                    );
                } else {
                    resultPixels[x][y] = currentPixel.clone();
                }
            }
        }

        return arrayToAPImage(resultPixels);
    }

    private int calculateNeighborLuminance(Pixel[][] pixels, int x, int y) {
        int sumLuminance = 0;
        int count = 0;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                sumLuminance += calculateLuminance(pixels[x + dx][y + dy]);
                count++;
            }
        }
        return sumLuminance / count;
    }

    private int calculateLuminance(Pixel pixel) {
    	return (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
    }
}

