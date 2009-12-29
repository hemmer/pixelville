import java.awt.Color;
import java.util.Random;

public class City {

	private int w, h;
	Building[][] map;
	final private Building road = new Building(0,Color.black);
	final private Building grass = new Building(0,new Color(144,172,93));
	
	public City(int w, int h){
		this.w = w;
		this.h = h;
		map = new Building[w][h];
		initilise();
		populate();
	}
	
	public void initilise(){
		for(int i = 0; i < w; i ++){
			for(int j = 0; j < h; j++){
				map[i][j] = grass;
			}
		}
	}

	public void populate(){
		boolean isRoad = false;
		
		for(int i = 0; i < w; i++){
			for(int j = 0; j < h; j++){
				isRoad = (j%3 == 0 || i%3 == 0) ? true : false;
				//  modifier   if(Math.random() < 0.1) isRoad = false;
				System.out.println(getHeight(i,j,20,50));
				map[i][j] = (isRoad) ? road : new Building(getHeight(i, j, 5, 70), randColor(200));
			}
		}
	}
	
	public int getHeight(int i, int j, int minHeight, int maxHeight){
		double height = Math.exp(-1.0*Math.pow( (double)((i-w/2.0)/(w/2)), 2.0) -1.0*Math.pow( (double)((j-h/2.0)/(h/2)), 2.0));
		return minHeight + (int)(height*(maxHeight-minHeight));
	}

	private Color randColor(int alpha){
		return new Color((int)(Math.random()*256), (int)(Math.random()*256),(int)(Math.random()*256), alpha);
	}
	
	public int getW() {return w;}
	public void setW(int w) {this.w = w;}
	public int getH() {return h;}
	public void setH(int h) {this.h = h;}
	
	public Building getBuilding(int i, int j){
		return map[i][j];
	}

}
