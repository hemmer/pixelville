import java.awt.Color;


public class House extends Building{

	public House(int height, Color baseColor) {
		super(height, baseColor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getTextureIndex() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "House";
	}

}
