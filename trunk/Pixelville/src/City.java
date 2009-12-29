import java.awt.Color;

public class City {

	private int w, h;
	Building[][] map;
	final private Building road = new Building(0,Color.black);
	
	public City(int w, int h){
		this.w = w;
		this.h = h;
		map = new Building[w][h];
		populate();
	}

	public void populate(){
		for(int i = 0; i < w; i++){
			boolean isRoad = (i%2 == 0) ? true : false;
			for(int j = 0; j < h; j++){
				Color rand = new Color((int)(Math.random()*250), (int)(Math.random()*250),(int)(Math.random()*250));
				map[i][j] = (isRoad) ? road : new Building((int) (Math.random()*100), rand);
			}
		}
	}

	public int getW() {return w;}
	public void setW(int w) {this.w = w;}
	public int getH() {return h;}
	public void setH(int h) {this.h = h;}
	
	public Building getBuilding(int i, int j){
		return map[i][j];
	}

}
