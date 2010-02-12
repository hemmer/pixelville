import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;


public class TextureSet {

	private BufferedImage[] textureSet;
	private int zoomLevel, gridUnit;
	private int cellWidth;

	public TextureSet(int zoomLevel, int gridUnit){
		this.zoomLevel = zoomLevel;
		this.gridUnit = gridUnit;
		this.cellWidth = zoomLevel*gridUnit;
		textureSet = new BufferedImage[17];

		genGrass();
		genRoad();
		genWall();
	}

	private void genGrass(){
		BufferedImage texture; 

		texture = new BufferedImage(2*cellWidth, cellWidth, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = texture.createGraphics();
		int[] xs = new int[] {0,cellWidth,2*cellWidth,cellWidth};
		int[] ys = new int[] {cellWidth/2, cellWidth, cellWidth/2, 0};
		g.setColor(new Color(144,172,93));
		g.fillPolygon(xs, ys, 4);

		// TODO: fix this
		for(int i = 0; i < 40*zoomLevel; i++){
			
			int[] pos = getRandPositionInsideTile(cellWidth);
			
			if(Math.random() < 0.5){
				g.setColor(new Color(144,172,93).darker().darker());
				g.drawLine(pos[0], pos[1], pos[0], pos[1]);
				g.setColor(new Color(144,172,93).brighter());
				g.drawLine(pos[0], pos[1]-1, pos[0], pos[1]-1);
			}else{
				g.setColor(new Color(144,172,93).darker());
				g.drawLine(pos[0], pos[1], pos[0], pos[1]);
			}
		}
		textureSet[0] = texture;
	}

	/**
	 * Gets points inside tile
	 * @param w Half width of tile
	 * @return array: 1st x-position, 2nd y-position
	 */
	private int[] getRandPositionInsideTile(int w){
		int randX, posX, randY, posY;
		Random rng = new Random();

		do{
			randX = rng.nextInt(w*2)-w;
			randY = rng.nextInt(w)-w/2;
			posX = w + randX;
			posY = w/2 + randY;
		}while(w-Math.abs(randX) < 2*Math.abs(randY)+1);
		
		return new int[] {posX, posY};
	}
	
	private void genRoad(){

		System.out.println(cellWidth);

		for(int i = 1; i <= 16; i++){
			BufferedImage texture; 

			texture = new BufferedImage(2*gridUnit*zoomLevel, cellWidth, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = texture.createGraphics();
			PixelHelpers.drawTile(cellWidth, cellWidth, g, cellWidth, cellWidth, Color.gray);	//road tile

			int stripWidth = (cellWidth)/20;
			int stripHeight = stripWidth*5;

			if(i == 9 || i == 11 || i == 13){
				PixelHelpers.drawTile(3*cellWidth/2, 3*cellWidth/4, g, stripHeight, stripWidth, Color.white);	// right stripe
				PixelHelpers.drawTile(cellWidth, cellWidth/2, g, stripHeight, stripWidth, Color.white);	// left strip
			}else if(i == 7 || i == 14 || i == 6){
				PixelHelpers.drawTile(cellWidth, cellWidth/2, g, stripWidth, stripHeight, Color.white);	// top strip
				PixelHelpers.drawTile(1*cellWidth/2, 3*cellWidth/4, g, stripWidth, stripHeight, Color.white);	// bottom stripe
			}else if(i == 3){
				PixelHelpers.drawTile(cellWidth, cellWidth/2, g, stripWidth, stripHeight, Color.white);	// top strip
				PixelHelpers.drawTile(cellWidth, cellWidth/2, g, stripHeight, stripWidth, Color.white);	// left strip
			}else if(i == 5){
				PixelHelpers.drawTile(1*cellWidth/2, 3*cellWidth/4, g, stripWidth, stripHeight, Color.white);	// bottom stripe
				PixelHelpers.drawTile(cellWidth, cellWidth/2, g, stripHeight, stripWidth, Color.white);	// left strip
			}else if(i == 10){
				PixelHelpers.drawTile(3*cellWidth/2, 3*cellWidth/4, g, stripHeight, stripWidth, Color.white);	// right stripe
				PixelHelpers.drawTile(cellWidth, cellWidth/2, g, stripWidth, stripHeight, Color.white);	// top strip
			}else if(i == 12){
				PixelHelpers.drawTile(3*cellWidth/2, 3*cellWidth/4, g, stripHeight, stripWidth, Color.white);	// right stripe
				PixelHelpers.drawTile(1*cellWidth/2, 3*cellWidth/4, g, stripWidth, stripHeight, Color.white);	// bottom stripe
			}

			textureSet[i] = texture;
		}
	}

	private void genWall(){
		BufferedImage texture; 

		texture = new BufferedImage(cellWidth, cellWidth, 1);
		Graphics2D g = texture.createGraphics();

		g.setColor(Color.red);
		int[] xs = {0,0,10,10};
		int[] ys = {0,0,10,10};
		g.fillPolygon(xs, ys, 4);

		//		textureSet[2] = new TexturePaint(texture, new Rectangle(0,0,cellWidth,cellWidth));
	}

	public BufferedImage getTexture(int index){
		return textureSet[index];
	}


}
