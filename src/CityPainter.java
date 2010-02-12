import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

@SuppressWarnings("serial")
public class CityPainter extends JPanel{

	int panelHeight, panelWidth;
	int gridUnit = 20;
	int zoomFactor;
	City city;
	TextureSet cityTextures;

	public CityPainter(int h, int w, City city, int zoomFactor){
		setPreferredSize(new Dimension(w,h));

		panelHeight = h;
		panelWidth = w;
		this.city = city;
		this.zoomFactor = zoomFactor;
		addMouseListener(l); 
		cityTextures = new TextureSet(zoomFactor, gridUnit);
	}

	MouseListener l = new MouseAdapter(){ public void mouseClicked (MouseEvent e) { System.exit(0); }};

	public void paintComponent(Graphics g){

		// initially have grass background
		g.setColor(new Color(144,172,93));
		g.fillRect(0, 0, panelWidth, panelHeight);

		int w = city.getW();
		int h = city.getH();

		//fix offset
		int xOffset = 900;
		int yOffset = -(city.getBorder()-1)*gridUnit*zoomFactor;
		
		// draw buildings
		for(int i = 0; i < w; i++){
			for(int j = 0; j < h; j++){
				if(city.getBuilding(i, j).getName() == "road"){
					Road temp = (Road) city.getBuilding(i, j);
					int type = temp.getType();
					paintTile(xOffset+(j-i)*gridUnit*zoomFactor,yOffset+(i+j)*gridUnit*zoomFactor/2, g, gridUnit*zoomFactor,gridUnit*zoomFactor, cityTextures.getTexture(type));
				}else if(city.getBuilding(i, j).getName() == "grass"){
					paintTile(xOffset+(j-i)*gridUnit*zoomFactor,yOffset+(i+j)*gridUnit*zoomFactor/2, g, gridUnit*zoomFactor,gridUnit*zoomFactor, cityTextures.getTexture(0));
				}
				else{
					PixelHelpers.paintCube(xOffset+(j-i)*gridUnit*zoomFactor,yOffset+(i+j)*gridUnit*zoomFactor/2, g, city.getBuilding(i, j).getHeight(),gridUnit*zoomFactor,gridUnit*zoomFactor, city.getBuilding(i, j).getColorSet());
				}
			}
		}
	}

	private void paintTile(int x, int y, Graphics g, int w, int d, BufferedImage t){
		
		// need to cast to Graphics 2D to draw image
		Graphics2D gs = (Graphics2D) g;
		
		int[] xs = {x, x-w, x-w+d, x+d};
		int[] ys = {y, y-(w/2), y-(w/2)-(d/2), y-(d/2)};

		gs.drawImage(t, x-w, y-d, new Color(0,0,0,0), this);
		g.setColor(new Color(0,0,0,50));
		g.drawPolygon(xs, ys, 4);
	}	
	
}
