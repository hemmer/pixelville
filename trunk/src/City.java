import java.awt.Color;
//import java.util.Random;

public class City {

	private int w, h;
	Building[][] map;
	boolean[][] roads;		// true - road, false - no road
	private int border = 4;
	int numberIntersections = 0;
	int widthFactor = 2;
	int heightFactor = widthFactor;
	
	public City(int w, int h, int border){
		this.w = w+2*border;
		this.h = h+2*border;
		this.border = border;
		map = new Building[w+2*border][h+2*border];
		roads = new boolean[w+2*border][h+2*border];

		initilise();
		populate();
	}
	
	public void initilise(){
		for(int i = 0; i < w; i ++){
			for(int j = 0; j < h; j++){
				map[i][j] = new Grass();
			}
		}
//		double test = Math.random();
//		
//		// if there is to be a river, decide horizontal or vertical
//		if(test > 0.0){
//			boolean riverHorz = true;
//			int lim = w;
//			if(test > 0.5){
//				 riverHorz = false;
//				 lim = h;
//			}
//			int pos = (int)(Math.random()*lim);
//			for(int r = 0; r < lim; r++){
//				if(riverHorz){
//					map[r][pos] = river;
//					map[r][pos+1] = river;
//					map[r][pos+2] = river;
//					map[r][pos+3] = river;
//
//				}else{
//					map[pos][r] = river;
//					map[pos+1][r] = river;
//					map[pos+2][r] = river;
//					map[pos+3][r] = river;
//				}
//			}
//		}
		
	}
	
	/**
	 *  Generates building types, heights colours etc.
	 */
	public void populate(){
		boolean isRoad = false;
		boolean[][] intersections = new boolean[w][h];
		
		for(int i = border; i < w - border; i++){
			for(int j = border; j < h - border; j++){
				roads[i][j] = (j%heightFactor == 0 || i%widthFactor == 0) ? true : false;
				
				if(j%heightFactor == 0 && i%widthFactor == 0 && j > border && j < (h-border-1) && i > border && i < (w-border-1)){
					intersections[i][j] = true;
					numberIntersections++;
				}

				//fix this mess
				map[i][j] = (roads[i][j]) ? null : new House(getHeight(i, j, 5, 70), randColor(200));
			}
		}
		
		
//		// removes certain roads 
//		int count = (int)Math.sqrt(numberIntersections);
//		while(count > 0){
//			int randX = (int)( (w-2*border-2*widthFactor)*Math.random())+border+widthFactor;
//			int randY = (int)( (h-2*border-2*heightFactor)*Math.random())+border+heightFactor;
//			
//			//System.out.println(numberIntersections + " " + randX + "  " + randY + " " + count);
//			
//			if(intersections[randX][randY]){
//				//map[randX][randY] = river;
//				intersections[randX][randY] = false;
//				count--;
//				int direction = 1;//(int) (Math.random()*4);  // 0-N, 1-E, 2-S, 3-W
//				int length = (direction == 0 || direction == 2) ? heightFactor : widthFactor;
//				//deleteRoad(randX, randY, length-1, direction);
//			}
//			
//		}
		
		// finally work out what kind of road it is
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
				if(roads[i][j]){
					int roadCount = 0;
					int[][] pos = getNeighbouringCells(i, j);
					for(int k = 0; k < pos.length; k++){
						if(roads[pos[k][0]][pos[k][1]]) roadCount += Math.pow(2,k);
					
						map[i][j] = new Road(roadCount);
					}
				}
			}
		}
	}
	
	private int[][] getNeighbouringCells(int i, int j){
		return new int[][] {{i,j-1}, {i-1, j}, {i+1,j}, {i,j+1}};
	}
	
//	public void deleteRoad(int startX, int startY, int length, int direction){
//		if(direction == 0){
//			for(int i = 1; i <= length; i++){
//				map[startX][startY-i] = new Building(getHeight(startX, startY-i, 5, 70), randColor(200));
//			}
//		}else if(direction == 1){
//			for(int i = 1; i <= length; i++){
//				map[startX+i][startY] = new Building(getHeight(startX+i, startY, 5, 70), randColor(200));;
//			}
//		}else if(direction == 2){
//			for(int i = 1; i <= length; i++){
//				map[startX][startY+i] = new Building(getHeight(startX, startY+i, 5, 70), randColor(200));;
//			}
//		}else if(direction == 3){
//			for(int i = 1; i <= length; i++){
//				map[startX-i][startY] = new Building(getHeight(startX-i, startY, 5, 70), randColor(200));;
//			}
//		}else{
//			System.out.println("Eh?"); //this should NOT happen
//		}
//	}
	
	public int getHeight(int i, int j, int minHeight, int maxHeight){
		double height = Math.exp(-1.0*Math.pow( (double)((i-w/2.0)/(w/4)), 2.0) -1.0*Math.pow( (double)((j-h/2.0)/(h/4)), 2.0));
		return minHeight + (int)((1+Math.random()*3)*height*(maxHeight-minHeight));
	}

	/**
	 * Generates a random colour, of specified alpha
	 * @param alpha 
	 * @return Random colour (with transparency)
	 */
	private Color randColor(int alpha){
		return new Color((int)(Math.random()*256), (int)(Math.random()*256),(int)(Math.random()*256), alpha);
	}
	
	/**
	 * Generates a random colour
	 * @return Random colour (without transparency)
	 */
	private Color randColor(){
		return new Color((int)(Math.random()*256), (int)(Math.random()*256),(int)(Math.random()*256));
	}

	public int getW() {return w;}
	public void setW(int w) {this.w = w;}
	public int getH() {return h;}
	public void setH(int h) {this.h = h;}
	
	public Building getBuilding(int i, int j){
		return map[i][j];
	}

	public int getBorder() {
		return border;
	}

	public void setBorder(int border) {
		this.border = border;
	}

}
