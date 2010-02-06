import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;

public class Road extends Building{

	int type;
	
	public Road(int type){
		super(0, new Color(0,0,0));
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getHeight(){
		return 0;
	}

	@Override
	public int getTextureIndex() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "road";
	}

}
