import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class MapPainter extends JPanel {
	
	City city;
	int panelWidth = 300;
	int panelHeight = 300;
	int cellSize = 15;
	boolean displayHeights = false;
	
	public MapPainter(City city){
		this.city = city;
		panelWidth = city.getW()*cellSize;
		panelHeight = city.getH()*cellSize;
		setPreferredSize(new Dimension(panelWidth,panelHeight));
	}
	
	public void paintComponent(Graphics g){
		int w = city.getW();
		int h = city.getH();
		
	    g.setFont(new Font("mono", Font.PLAIN, 8));
		
		for(int i = 0; i < w; i++){
			for(int j = 0; j< h; j++){

				g.setColor(city.getBuilding(i,j).getColor());
				g.fillRect(i*panelWidth/w, j*panelHeight/h, panelWidth/w, panelHeight/h);
				if(displayHeights){
					g.setColor(Color.white);
					g.drawString(city.getBuilding(i, j).getHeight() + "m", i*panelWidth/w, (j+1)*panelHeight/h);
				}
			}
		}
	}

}
