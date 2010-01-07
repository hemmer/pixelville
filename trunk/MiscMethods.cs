using System;
using System.Drawing;
using System.Text;

public class MiscMethods {

	// allow slightly brighter/darker versions of a color
	public static Color brighten(Color c, double factor){
		
		int r = c.R;
        int g = c.G;
        int b = c.B;
        int a = c.A;
        
        int i = (int)(1.0/(1.0-factor));
        
        if ( r == 0 && g == 0 && b == 0) {
           return Color.FromArgb(i, i, i);
        }
        if ( r  > 0 && r <  i ) r = i;
        if ( g  > 0 && g <  i ) g = i;
        if ( b  > 0 && b <  i ) b = i;
        return Color.FromArgb(a, Math.Min((int)(r/factor), 255),
                         Math.Min((int)(g/factor), 255),
                         Math.Min((int)(b/factor), 255));
		
	}

	public static Color darken(Color c, double factor){
		return Color.FromArgb(c.A, Math.Max((int)(c.R*factor), 0),
                Math.Max((int)(c.G*factor), 0),
                Math.Max((int)(c.B*factor), 0));
	}
}


