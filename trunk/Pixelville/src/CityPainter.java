import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class CityPainter extends JPanel{
	
	int panelHeight, panelWidth;
	int gridSize = 80;
	City city;
	
	public CityPainter(int h, int w){
		setPreferredSize(new Dimension(w,h));

		panelHeight = h;
		panelWidth = w;
		city = new City(5,5);
	}	
	
	public void paintComponent(Graphics g){

		g.setColor(new Color(144,172,93));
		g.fillRect(0, 0, panelWidth, panelHeight);
		
		g.setColor(Color.BLACK);

		int horGrids = panelWidth/gridSize;
		//int verGrids = panelHeight/gridSize;
		
		for(int i = -horGrids; i < horGrids + 1; i++){
			g.drawLine(i*gridSize, 0, i*gridSize+2*panelHeight, panelHeight);
			g.drawLine((i+horGrids)*gridSize, 0, (i+horGrids)*gridSize-2*panelHeight, panelHeight);
		}
		
		int w = city.getW();
		int h = city.getH();
		

		for(int i = 0; i < w; i++){
			for(int j = 0; j < h; j++){
				paintCube(240+(j-i)*gridSize/2,120+(i+j)*gridSize/4, g, city.getBuilding(i, j).getHeight(),40,40, city.getBuilding(i, j).getColor());
			}
		}
		
		//paintCube(160,240,g, 60, 80, 160, new Color(130,99,90));
		//paintCube(160,180,g, 50, 60, 160, new Color(100,99,90));

		//paintCube(280,300,g, 68, 68, 168, new Color(95,79,120));


		
	}
	
	public void paintCube(int x, int y, Graphics g, int h, int w, int d, Color c){
		int[] x1 = {x, x, x-w, x-w};
		int[] y1 = {y, y-h, y-h-(w/2), y-(w/2)};
		g.setColor(c);
		g.fillPolygon(x1, y1, 4);
		g.setColor(Color.BLACK);
		g.drawPolygon(x1, y1, 4);
		
		int[] x2 = {x,x,x+d,x+d};
		int[] y2 = {y, y-h, y-h-(d/2), y-(d/2)};
		g.setColor(c.darker());
		g.fillPolygon(x2, y2, 4);
		g.setColor(Color.BLACK);
		g.drawPolygon(x2, y2, 4);
		
		int[] x3 = {x, x-w, x-w+d, x+d};
    	int[] y3 = {y-h, y-(w/2)-h, y-(w/2)-(d/2)-h, y-(d/2)-h};
		g.setColor(c.brighter());
		g.fillPolygon(x3, y3, 4);
		g.setColor(Color.BLACK);
		g.drawPolygon(x3, y3, 4);
	}

}
