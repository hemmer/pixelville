import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class CityPainter extends JPanel{
	
	int panelHeight, panelWidth;
	int gridSize = 80;
	City city;
	
	public CityPainter(int h, int w, City city){
		setPreferredSize(new Dimension(w,h));

		panelHeight = h;
		panelWidth = w;
		this.city = city;
	}
	
	public void paintComponent(Graphics g){

		// initially have grass
		g.setColor(new Color(144,172,93));
		g.fillRect(0, 0, panelWidth, panelHeight);
		
		

		int horGrids = panelWidth/gridSize;
		int verGrids = 2*panelHeight/gridSize;
		/*
		g.setColor(Color.BLACK);
		// draw grid line
		for(int i = -verGrids; i <= horGrids; i++){
			g.drawLine(i*gridSize, 0, i*gridSize+2*panelHeight, panelHeight);
		}
		for(int i = 1; i <= horGrids + verGrids; i++){
			g.drawLine(i*gridSize, 0, i*gridSize-2*panelHeight, panelHeight);
		} */
		
		int w = city.getW();
		int h = city.getH();
		
		// draw buildings
		for(int i = 0; i < w; i++){
			for(int j = 0; j < h; j++){
				paintCube(400+(j-i)*gridSize/2,50+(i+j)*gridSize/4, g, city.getBuilding(i, j).getHeight(),40,40, city.getBuilding(i, j).getColor());
			}
		}
		
		//paintCube(400,400,g, 60, 80, 160, new Color(130,99,90,200));
		//paintCube(160,180,g, 50, 60, 160, new Color(100,99,90));

		//paintCube(280,300,g, 68, 68, 168, new Color(95,79,120));


		
	}
	
	/**
	 * 
	 * @param x x-coord of cube base
	 * @param y y-coord of cube base
	 * @param g Graphics reference
	 * @param h Height of cube
	 * @param w Width of cube
	 * @param d Depth of cube
	 * @param c Cube color
	 */
	public void paintCube(int x, int y, Graphics g, int h, int w, int d, Color c){
		int[] x1 = {x, x, x-w, x-w};
		int[] y1 = {y, y-h, y-h-(w/2), y-(w/2)};
		g.setColor(c);
		g.fillPolygon(x1, y1, 4);
		g.setColor(Color.BLACK);
		g.drawPolygon(x1, y1, 4);
		
		int[] x2 = {x,x,x+d,x+d};
		int[] y2 = {y, y-h, y-h-(d/2), y-(d/2)};
		g.setColor(MiscMethods.darken(c, 0.7));
		g.fillPolygon(x2, y2, 4);
		g.setColor(Color.BLACK);
		g.drawPolygon(x2, y2, 4);
		
		int[] x3 = {x, x-w, x-w+d, x+d};
    	int[] y3 = {y-h, y-(w/2)-h, y-(w/2)-(d/2)-h, y-(d/2)-h};
		g.setColor(MiscMethods.brighten(c, 0.7));
		g.fillPolygon(x3, y3, 4);
		g.setColor(Color.BLACK);
		g.drawPolygon(x3, y3, 4);
	}

}
