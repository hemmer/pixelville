using System;
using System.Collections.Generic;
using System.Drawing;
using System.Text;

public class City {

	private int w, h;
	Building[,] map;
	int border = 4;
	private Building road = new Building(0,Color.Black);
	private Building grass = new Building(0,Color.Transparent);
	private Building river = new Building(0,Color.Aqua);
	int numberIntersections = 0;
	int widthFactor = 3;
	int heightFactor = 3;
    Random r = new Random();//(300);
	
	public City(int w, int h, int border, bool normal){
		this.w = w+2*border;
		this.h = h+2*border;
		this.border = border;
		map = new Building[w+2*border,h+2*border];
		initilise();
        if(normal)
            populateNormal();
        else
            populateDystopic();
	}
	
	public void initilise(){
		for(int i = 0; i < w; i ++){
			for(int j = 0; j < h; j++){
				map[i,j] = grass;
			}
		}
//		double test = Math.random();
//		
//		// if there is to be a river, decide horizontal or vertical
//		if(test > 1.0){
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
//
//				}else{
//					map[pos][r] = river;
//					map[pos+1][r] = river;
//
//				}
//			}
//		}
		
	}

	public void populateNormal(){
		bool isRoad = false;
		bool[,] intersections = new bool[w,h];
		
		for(int i = border; i < w - border; i++){
			for(int j = border; j < h - border; j++){
				isRoad = (j%heightFactor == 0 || i%widthFactor == 0) ? true : false;
				if(j%heightFactor == 0 && i%widthFactor == 0 && j > border && j < (h-border-1) && i > border && i < (w-border-1)){
					intersections[i,j] = true;
					numberIntersections++;
				}
				//System.out.println(getHeight(i,j,20,50));
				//fix this mess
				map[i,j] = (isRoad) ? road : map[i,j];
				map[i,j] = (isRoad || map[i,j] == river) ? map[i,j] : new Building(getHeight(i, j, 5, 200), Color.FromArgb(200, r.Next(256), r.Next(256), r.Next(256)));
			}
		}
		
		int count = (int)Math.Sqrt(numberIntersections);
		while(count > 0){
			int randX = r.Next(w-2*border-2*widthFactor)+border+widthFactor;
			int randY = r.Next(h-2*border-2*heightFactor)+border+heightFactor;
			
			//System.out.println(numberIntersections + " " + randX + "  " + randY + " " + count);
			
			if(intersections[randX,randY]){
				//map[randX][randY] = river;
				intersections[randX,randY] = false;
				count--;
				int direction = 1;//(int) (Math.random()*4);  // 0-N, 1-E, 2-S, 3-W
				int length = (direction == 0 || direction == 2) ? heightFactor : widthFactor;
				deleteRoad(randX, randY, length-1, direction);
			}
			
		}
	}

    public void populateDystopic()
    {
        bool isRoad = false;
        bool[,] intersections = new bool[w, h];

        for (int i = border; i < w - border; i++)
        {
            for (int j = border; j < h - border; j++)
            {
                isRoad = (j % heightFactor == 0 || i % widthFactor == 0) ? true : false;
                if (j % heightFactor == 0 && i % widthFactor == 0 && j > border && j < (h - border - 1) && i > border && i < (w - border - 1))
                {
                    intersections[i, j] = true;
                    numberIntersections++;
                }
                //System.out.println(getHeight(i,j,20,50));
                //fix this mess
                map[i, j] = (isRoad) ? road : map[i, j];
                int baseCol = r.Next(200);
                map[i, j] = (isRoad || map[i, j] == river) ? map[i, j] : new Building(getHeight(i, j, 5, 70), Color.FromArgb(baseCol, baseCol, baseCol));
            }
        }

        int count = (int)Math.Sqrt(numberIntersections);
        while (count > 0)
        {
            int randX = r.Next(w - 2 * border - 2 * widthFactor) + border + widthFactor;
            int randY = r.Next(h - 2 * border - 2 * heightFactor) + border + heightFactor;

            //System.out.println(numberIntersections + " " + randX + "  " + randY + " " + count);

            if (intersections[randX, randY])
            {
                //map[randX][randY] = river;
                intersections[randX, randY] = false;
                count--;
                int direction = 1;//(int) (Math.random()*4);  // 0-N, 1-E, 2-S, 3-W
                int length = (direction == 0 || direction == 2) ? heightFactor : widthFactor;
                deleteRoad(randX, randY, length - 1, direction);
            }

        }
    }
	
	public void deleteRoad(int startX, int startY, int length, int direction){
		if(direction == 0){
			for(int i = 1; i <= length; i++){
				map[startX,startY-i] = new Building(getHeight(startX, startY-i, 5, 70), randColor(200));
			}
		}else if(direction == 1){
			for(int i = 1; i <= length; i++){
				map[startX+i,startY] = new Building(getHeight(startX+i, startY, 5, 70), randColor(200));;
			}
		}else if(direction == 2){
			for(int i = 1; i <= length; i++){
				map[startX,startY+i] = new Building(getHeight(startX, startY+i, 5, 70), randColor(200));;
			}
		}else if(direction == 3){
			for(int i = 1; i <= length; i++){
				map[startX-i,startY] = new Building(getHeight(startX-i, startY, 5, 70), randColor(200));;
			}
		}else{
			Console.WriteLine("Eh?"); //this should NOT happen
		}
	}
	
	public int getHeight(int i, int j, int minHeight, int maxHeight){
		double height = Math.Exp(-1.0*Math.Pow( (double)((i-w/2.0)/(w/4)), 2.0) -1.0*Math.Pow( (double)((j-h/2.0)/(h/4)), 2.0));
		return minHeight + (int)(height*(maxHeight-minHeight));
	}

	private Color randColor(int alpha){
        return Color.FromArgb(alpha, r.Next(256), r.Next(256), r.Next(256));
	}
	
	public int getW() {return w;}
	public void setW(int w) {this.w = w;}
	public int getH() {return h;}
	public void setH(int h) {this.h = h;}
	
	public Building getBuilding(int i, int j){
		return map[i,j];
	}

}


