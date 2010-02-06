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
		
		// draw buildings
		for(int i = 0; i < w; i++){
			for(int j = 0; j < h; j++){
				if(city.getBuilding(i, j).getName() == "road"){
					Road temp = (Road) city.getBuilding(i, j);
					int type = temp.getType();
					paintTile(900+(j-i)*gridUnit*zoomFactor,-200+(i+j)*gridUnit*zoomFactor/2, g, gridUnit*zoomFactor,gridUnit*zoomFactor, cityTextures.getTexture(type));
				}else if(city.getBuilding(i, j).getName() == "grass"){
					paintTile(900+(j-i)*gridUnit*zoomFactor,-200+(i+j)*gridUnit*zoomFactor/2, g, gridUnit*zoomFactor,gridUnit*zoomFactor, cityTextures.getTexture(0));
				}
				else{
					PixelHelpers.paintCube(900+(j-i)*gridUnit*zoomFactor,-200+(i+j)*gridUnit*zoomFactor/2, g, city.getBuilding(i, j).getHeight(),gridUnit*zoomFactor,gridUnit*zoomFactor, city.getBuilding(i, j).getColorSet());
				}
			}
		}
	}

	private void paintTile(int x, int y, Graphics g, int w, int d, BufferedImage t){

		Graphics2D gs = (Graphics2D) g;

		int[] x3 = {x, x-w, x-w+d, x+d};
		int[] y3 = {y, y-(w/2), y-(w/2)-(d/2), y-(d/2)};

		gs.drawImage(t, x-w, y-d, new Color(0,0,0,0), this);
		g.setColor(Color.black);
		g.drawPolygon(x3, y3, 4);
	}	
	
}
