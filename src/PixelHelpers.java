import java.awt.Color;
import java.awt.Graphics;

public class PixelHelpers {


	public static void drawTile(int x, int y, Graphics g, int w, int d, Color fill){

		int[] x3 = {x, x-w, x-w+d, x+d};
		int[] y3 = {y, y-(w/2), y-(w/2)-(d/2), y-(d/2)};
		g.setColor(fill);
		g.fillPolygon(x3, y3, 4);
	}
	
	public static void drawTile(int x, int y, Graphics g, int w, int d, Color fill, Color stroke){

		int[] x3 = {x, x-w, x-w+d, x+d};
		int[] y3 = {y, y-(w/2), y-(w/2)-(d/2), y-(d/2)};
		g.setColor(fill);
		g.fillPolygon(x3, y3, 4);
		g.setColor(stroke);
		g.drawPolygon(x3, y3, 4);
	}


	/**
	 * 
	 * @param x x-coord of cube base
	 * @param y y-coord of cube base
	 * @param g Graphics reference
	 * @param h Height of cube
	 * @param w Width of cube
	 * @param d Depth of cube
	 * @param c Array of colours for the cube (base, dark, light)
	 */
	public static void paintCube(int x, int y, Graphics g, int h, int w, int d, Color[] c){

		// only paint sides/highlights etc if building has height
		if(h != 0){

			int[] x1 = {x, x, x-w, x-w};
			int[] y1 = {y, y-h, y-h-(w/2), y-(w/2)};
			g.setColor(c[1]);
			g.fillPolygon(x1, y1, 4);


			int[] x2 = {x,x,x+d,x+d};
			int[] y2 = {y, y-h, y-h-(d/2), y-(d/2)};
			g.setColor(c[2]);
			g.fillPolygon(x2, y2, 4);
			g.setColor(Color.BLACK);
			//g.drawPolygon(x2, y2, 4);

			int[] x3 = {x, x-w, x-w+d, x+d};
			int[] y3 = {y-h, y-(w/2)-h, y-(w/2)-(d/2)-h, y-(d/2)-h};
			g.setColor(c[0]);
			g.fillPolygon(x3, y3, 4);

			//highlights
			g.setColor(c[3]);
			g.drawLine(x1[0], y1[0], x1[1], y1[1]);			// left front edge
			g.drawLine(x1[0], y1[1], x1[2]+1, y1[2]+1);		// front vertex
			g.drawLine(x2[0], y2[1], x2[2]-1, y2[2]+1);		// right front edge

			//shadows
			g.setColor(c[4]);
			g.drawLine(x1[2], y1[2], x1[3], y1[3]);			// left vertical edge
			g.drawLine(x2[2], y2[2], x2[3], y2[3]);			// right vertical edge
			g.drawLine(x3[1], y3[1], x3[2]-1, y3[2]+1);			// back left edge
			g.drawLine(x3[2], y3[2], x3[3]-1, y3[3]-1);			// back right edge
			g.drawLine(x1[0], y1[0], x1[3]+1, y1[3]+1);			// left bottom edge
			g.drawLine(x2[0], y2[0], x2[3]-1, y2[3]+1);			// right bottom edge



		}
//		else{
//
//
//			int[] x3 = {x, x-w, x-w+d, x+d};
//			int[] y3 = {y-h, y-(w/2)-h, y-(w/2)-(d/2)-h, y-(d/2)-h};
//
//			if(t != null){
//				gs.setPaint(t);
//				gs.fillPolygon(x3, y3, 4);
//				g.setColor(Color.BLACK);
//				g.drawPolygon(x3, y3, 4);
//			}else{
//				g.setColor(c[0]);
//				g.fillPolygon(x3, y3, 4);
//				g.setColor(Color.BLACK);
//				g.drawPolygon(x3, y3, 4);
//			}
//
//
//		}


		//g.drawLine(300, 300, 340, 320);

		//System.out.println(x3[0] + " " + x3[1] + " " + x3[2] + " " + x3[3]);
		//System.out.println(y3[0] + " " + y3[1] + " " + y3[2] + " " + y3[3]);

		// dont draw polygon due to double line bug
		////		g.drawLine(x3[0]+1, y3[0], x3[1], y3[1]);
		//		
		//		System.out.println(x3[0] + ", " + y3[0] + "] [" + x3[1] + ", " + y3[1]);
		//		System.out.println("[" + (x3[0]-x3[1]) + ", " + (y3[0]-y3[1]) + "]" );
		//
		//		//g.drawLine(x3[2], y3[2], x3[1], y3[1]);
		//g.drawLine(x3[3], y3[3], x3[2], y3[2]);
		//g.drawLine(x3[3], y3[3], x3[0], y3[0]);

	}

	
}
