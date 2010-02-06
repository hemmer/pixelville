import java.awt.Color;


public class MiscMethods {

	// because Java's version doesn't support alpha :(
	public static Color brighten(Color c, double factor){
		
		int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();
        int a = c.getAlpha();
        
        int i = (int)(1.0/(1.0-factor));
        
        if ( r == 0 && g == 0 && b == 0) {
           return new Color(i, i, i);
        }
        if ( r  > 0 && r <  i ) r = i;
        if ( g  > 0 && g <  i ) g = i;
        if ( b  > 0 && b <  i ) b = i;
        return new Color(Math.min((int)(r/factor), 255),
                         Math.min((int)(g/factor), 255),
                         Math.min((int)(b/factor), 255), a);
		
	}
	
	// because Java's version doesn't support alpha :(
	public static Color darken(Color c, double factor){
		return new Color(Math.max((int)(c.getRed()  *factor), 0),
                Math.max((int)(c.getGreen()*factor), 0),
                Math.max((int)(c.getBlue() *factor), 0), c.getAlpha());
	}
}
