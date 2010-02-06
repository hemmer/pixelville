import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;

public abstract class Building {

	private int height = 0;
	private Color[] colourSet;
	protected TexturePaint[][] textureSet;
	private Color baseColour;
	protected int gridUnit = 20;
	 
	public Building(int height, Color baseColor){
		this.height = height;
		this.baseColour = baseColor;
		generateColourSet();
	}
	
	private void generateColourSet() {
		colourSet = new Color[] {baseColour, MiscMethods.brighten(baseColour, 0.7), MiscMethods.darken(baseColour, 0.7), MiscMethods.brighten(baseColour, 0.5),MiscMethods.darken(baseColour, 0.5)};
	}

	public int getHeight(){
		return height;
	}
	
	public Color[] getColorSet(){
		return this.colourSet;
	}
	
	public TexturePaint[] getTextureSet(int zoomLevel){
		return null;
	}

	abstract public int getTextureIndex();
	abstract public String getName();
}