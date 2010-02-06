import java.awt.Color;

public class Grass extends Building{

 
	public Grass(){
		super(0, new Color(60,136,34));
	}

	public int getHeight(){
		return 0;
	}

	@Override
	public int getTextureIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "grass";
	}
	
}
