import java.awt.Color;
//import java.util.Random;

public class City {

	private int w, h;
	Building[][] map;
	int border = 4;
	final private Building road = new Building(0,new Color(0,0,0));
	final private Building grass = new Building(0,new Color(144,172,93, 0));
	final private Building river = new Building(0,new Color(135,206,250));
	int numberIntersections = 0;
	int widthFactor = 4;
	int heightFactor = 4;
	
	public City(int w, int h, int border){
		this.w = w;
		this.h = h;
		this.border = border;
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
		double test = Math.random();
		// if there is to be a river, decide horizontal or vertical
		if(test > 0.4){
			boolean riverHorz = true;
			int lim = w;
			if(test > 0.5){
				 riverHorz = false;
				 lim = h;
			}
			int pos = (int)(Math.random()*lim);
			for(int r = 0; r < lim; r++){
				if(riverHorz){
					map[r][pos] = river;
				}else{
					map[pos][r] = river;
				}
			}
		}
		
		
		
		
	}

	public void populate(){
		boolean isRoad = false;
		boolean[][] intersections = new boolean[w][h];
		
		for(int i = border; i < w - border; i++){
			for(int j = border; j < h - border; j++){
				isRoad = (j%heightFactor == 0 || i%widthFactor == 0) ? true : false;
				if(j%heightFactor == 0 && i%widthFactor == 0 && j > border && j < (h-border-1) && i > border && i < (w-border-1)){
					intersections[i][j] = true;
					numberIntersections++;
				}
				//System.out.println(getHeight(i,j,20,50));
				//map[i][j] = (isRoad) ? road : map[i][j];
				map[i][j] = (isRoad) ? road : new Building(getHeight(i, j, 5, 70), randColor(150));
			}
		}
		
		int count = (int)Math.sqrt(numberIntersections);
		while(count > 0){
			int randX = (int)( (w-2*border-2*widthFactor)*Math.random())+border+widthFactor;
			int randY = (int)( (h-2*border-2*heightFactor)*Math.random())+border+heightFactor;
			
			//System.out.println(numberIntersections + " " + randX + "  " + randY + " " + count);
			
			if(intersections[randX][randY]){
				//map[randX][randY] = river;
				intersections[randX][randY] = false;
				count--;
				int direction = 1;//(int) (Math.random()*4);  // 0-N, 1-E, 2-S, 3-W
				int length = (direction == 0 || direction == 2) ? heightFactor : widthFactor;
				System.out.println(numberIntersections + " " + randX + "  " + randY + " " + count);
				deleteRoad(randX, randY, length-1, direction);
			}
			
		}
	}
	
	public void deleteRoad(int startX, int startY, int length, int direction){
		if(direction == 0){
			for(int i = 1; i <= length; i++){
				map[startX][startY-i] = new Building(getHeight(startX, startY-i, 5, 70), randColor(200));
			}
		}else if(direction == 1){
			for(int i = 1; i <= length; i++){
				map[startX+i][startY] = new Building(getHeight(startX+i, startY, 5, 70), randColor(200));;
			}
		}else if(direction == 2){
			for(int i = 1; i <= length; i++){
				map[startX][startY+i] = new Building(getHeight(startX, startY+i, 5, 70), randColor(200));;
			}
		}else if(direction == 3){
			for(int i = 1; i <= length; i++){
				map[startX-i][startY] = new Building(getHeight(startX-i, startY, 5, 70), randColor(200));;
			}
		}else{
			System.out.println("Eh?"); //this should NOT happen
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
