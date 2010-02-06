import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;


public class TextureSet {

	private BufferedImage[] textureSet;
	private int zoomLevel, gridUnit;

	public TextureSet(int zoomLevel, int gridUnit){
		this.zoomLevel = zoomLevel;
		this.gridUnit = gridUnit;

		textureSet = new BufferedImage[17];

		genGrass();
		genRoad();
		genWall();
	}

	private void genGrass(){
		BufferedImage texture; 

		texture = new BufferedImage(2*gridUnit*zoomLevel, gridUnit*zoomLevel, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = texture.createGraphics();
		int[] xs = new int[] {0,gridUnit*zoomLevel,2*gridUnit*zoomLevel,gridUnit*zoomLevel};
		int[] ys = new int[] {gridUnit*zoomLevel/2, gridUnit*zoomLevel, gridUnit*zoomLevel/2, 0};
		g.setColor(new Color(144,172,93));
		g.fillPolygon(xs, ys, 4);

		// todo: fix this
		for(int i = 0; i < 20*zoomLevel; i++){
			int randX = (int) (Math.random()*gridUnit*zoomLevel);
			int randY = (int) (Math.random()*gridUnit*zoomLevel);

			g.setColor(new Color(144,172,93).darker());
			g.drawLine(randX, randY, randX, randY);

			g.setColor(new Color(144,172,93).brighter());
			g.drawLine(randX, randY-1, randX, randY-1);
		}
		textureSet[0] = texture;
	}

	private void genRoad(){

		System.out.println(gridUnit*zoomLevel);
		
		for(int i = 1; i <= 16; i++){
			BufferedImage texture; 

			texture = new BufferedImage(2*gridUnit*zoomLevel, gridUnit*zoomLevel, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = texture.createGraphics();
			PixelHelpers.drawTile(gridUnit*zoomLevel, gridUnit*zoomLevel, g, gridUnit*zoomLevel, gridUnit*zoomLevel, Color.gray.darker());	//road tile
			
			int stripWidth = (gridUnit*zoomLevel)/20;
			int stripHeight = stripWidth*5;
			
			if(i == 9 || i == 11 || i == 13){
				PixelHelpers.drawTile(3*gridUnit*zoomLevel/2, 3*gridUnit*zoomLevel/4, g, stripHeight, stripWidth, Color.white);	// right stripe
				PixelHelpers.drawTile(gridUnit*zoomLevel, gridUnit*zoomLevel/2, g, stripHeight, stripWidth, Color.white);	// left strip
			}else if(i == 7 || i == 14 || i == 6){
				PixelHelpers.drawTile(gridUnit*zoomLevel, gridUnit*zoomLevel/2, g, stripWidth, stripHeight, Color.white);	// top strip
				PixelHelpers.drawTile(1*gridUnit*zoomLevel/2, 3*gridUnit*zoomLevel/4, g, stripWidth, stripHeight, Color.white);	// bottom stripe
			}else if(i == 3){
				PixelHelpers.drawTile(gridUnit*zoomLevel, gridUnit*zoomLevel/2, g, stripWidth, stripHeight, Color.white);	// top strip
				PixelHelpers.drawTile(gridUnit*zoomLevel, gridUnit*zoomLevel/2, g, stripHeight, stripWidth, Color.white);	// left strip
			}else if(i == 5){
				PixelHelpers.drawTile(1*gridUnit*zoomLevel/2, 3*gridUnit*zoomLevel/4, g, stripWidth, stripHeight, Color.white);	// bottom stripe
				PixelHelpers.drawTile(gridUnit*zoomLevel, gridUnit*zoomLevel/2, g, stripHeight, stripWidth, Color.white);	// left strip
			}else if(i == 10){
				PixelHelpers.drawTile(3*gridUnit*zoomLevel/2, 3*gridUnit*zoomLevel/4, g, stripHeight, stripWidth, Color.white);	// right stripe
				PixelHelpers.drawTile(gridUnit*zoomLevel, gridUnit*zoomLevel/2, g, stripWidth, stripHeight, Color.white);	// top strip
			}
			else{
				g.setColor(Color.black);
//				g.drawString(i + "", 10,10);
			}
			
			textureSet[i] = texture;
		}
	}

	private void genWall(){
		BufferedImage texture; 

		texture = new BufferedImage(gridUnit*zoomLevel, gridUnit*zoomLevel, 1);
		Graphics2D g = texture.createGraphics();

		g.setColor(Color.red);
		int[] xs = {0,0,10,10};
		int[] ys = {0,0,10,10};
		g.fillPolygon(xs, ys, 4);

		//		textureSet[2] = new TexturePaint(texture, new Rectangle(0,0,gridUnit*zoomLevel,gridUnit*zoomLevel));
	}

	public BufferedImage getTexture(int index){
		return textureSet[index];
	}


}
