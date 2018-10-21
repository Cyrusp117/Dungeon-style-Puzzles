package entities;
import java.util.*;

public class Graph {

	private ArrayList<Coordinate> coordList;
	private ArrayList<Edge> edgeList;
	private int nV;
	
	private int height;
	private int width;
	
	
	
	public Graph(int height,int width) {
		this.coordList = new ArrayList<Coordinate>();
		this.edgeList = new ArrayList<Edge>();
		this.nV = 0;
		
		this.height = height;
		this.width = width;
	}
	
	/**
	 * @param c , the coordinate to be added	
	 */
	public void addCoordinate(Coordinate c) {
		this.coordList.add(c);
		
		this.nV++;
	}
	
	/**
	 * @param e, the edge to be added	
	 */
	public void addEdge(Edge e) {
		this.edgeList.add(e);
	}
	/**
	 * Creates edges for the graph between all coordinates
	 */
	public void generateEdges() {
		for (Coordinate object1: this.coordList) {
			for (Coordinate object2: this.coordList) {
				if (object2.equals(object1))
					continue;
				if (isAdjacent(object1,object2)) {
					this.addEdge(new Edge(object1,object2));
				}
			}
		}
		Collections.shuffle(this.coordList);
	}
	
	/**
	 * @param a and b , coordinates to be tested 
	 * @return a boolean result depending on whether the given coordinates are adjacent
	 */
	public boolean isAdjacent(Coordinate a, Coordinate b) {
		boolean check = false;
		int ax = a.getX();
		int ay = a.getY();
		int bx = b.getX();
		int by = b.getY();
		
		if ((ax == bx) && (ay-1 == by || ay+1 == by)) {
		    check = true;
		} else if((ax-1 == bx || ax + 1 == bx) && ay == by) {
		    check = true;
			
		}
		
		return check;
	}
	/**
	 * @param src, the starting coordinate, dest, the destination coordinate, and player, the players current coordinate
	 * @return the coordinate that is the first move in the shortest path from src to dest
	 */
	public Coordinate BFS(Coordinate src, Coordinate dest, Coordinate player) {
		Coordinate cur = null;
		int[] visited = new int[this.nV];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		int srcIndex = coordList.indexOf(src);
		int destIndex = coordList.indexOf(dest);
		int vertex;
		int w;
		boolean found = false;
		
		
		for (int i = 0; i <nV; i++) {
		    visited[i] = -1;	
		}
		visited[srcIndex] = -2;
		queue.add(srcIndex);
		
		while (queue.size() != 0) {
			vertex = queue.poll();
			//System.out.println("vertex is " + vertex);
			for (w = 0; w < nV; w++) {
			    if (w == vertex)
			    	continue;
			    
			    if (visited[w] == -1 && hasEdge(coordList.get(vertex),coordList.get(w))) {
			    	visited[w] = vertex;
			    	if ( w != coordList.indexOf(player))
			    	    queue.add(w);
			    	if (w == destIndex) {
			    		found = true;
			  
			    		break;
			    	}
			    }
			}
			
		}
		if (found) {
			int temp = destIndex;
			for (int curValue = destIndex; curValue != srcIndex; curValue = visited[curValue]) {
				temp = curValue;
			}
			cur = coordList.get(temp);
		} else {
			cur = src;
		}
		
		return cur;
	}
	/**
	 * @param src, the starting coordinate, dest, the destination coordinate
	 * @return size, the number of moves to get from src to dest
	 */
	public int sizeBFS(Coordinate src, Coordinate dest) {
        if (src.equals(dest)) {
        	return 0;
        }
		int[] visited = new int[this.nV];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		int srcIndex = coordList.indexOf(src);
		int destIndex = coordList.indexOf(dest);
		int vertex;
		int w;
		//System.out.println("Coordlist at 81 is" + coordList.get(81));
		boolean found = false;
		
		for (int i = 0; i <nV; i++) {
		    visited[i] = -1;	
		}
		visited[srcIndex] = -2;
		queue.add(srcIndex);
		
		while (queue.size() != 0) {
			vertex = queue.poll();
			//System.out.println("vertex is " + vertex);
			for (w = 0; w < nV; w++) {
			    if (w == vertex)
			    	continue;
			    
			    if (visited[w] == -1 && hasEdge(coordList.get(vertex),coordList.get(w))) {
			    	visited[w] = vertex;
			    	
			    	queue.add(w);
			    	if (w == destIndex) {
			    		found = true;
			  
			    		break;
			    	}
			    }
			}
			
		}
		int size = 0;
		if (found) {
			
			for (int curValue = destIndex; curValue != srcIndex; curValue = visited[curValue]) {
				
				size ++;
			}
			
		} else {
			size = -1;
		}
		
		return size;
	}
	
	/**
	 * @param a and b, the coordinates to be tested
	 * @return true if edge exists between a and b
	 */
	public boolean hasEdge(Coordinate a, Coordinate b) {
		boolean check = false;
	    for (Edge object: edgeList) {
	    	if (a == object.getSrc() && b == object.getDest()) {
	    		check = true;
	    		break;
	    	}
	    }
	    return check;
	}

 
	/**
	 * @param player, the current coordinate of player, enemy, the current coordinate of enemy
	 * @return the coordinate that will move the enemy on the opposite direction to the player
	 */
    public Coordinate moveAway(Coordinate player, Coordinate enemy) {
           	
        Coordinate point = enemy;
        int ax = player.getX();
        int ay = player.getY();
        int bx = enemy.getX();
        int by = enemy.getY();
        
        int differenceX = ax - bx;
        int differenceY = ay -by;
        double totalDistance;
        
       
        double curDistance = Math.hypot(differenceX,differenceY);
        	
        	for (Coordinate object: coordList) {
        		if (isAdjacent(enemy,object)) {
        		    differenceX = object.getX() - ax;
        		    differenceY = object.getY() - ay;
        		    totalDistance = Math.hypot(differenceX, differenceY);
        	
        	        if (totalDistance > curDistance) {
        	    	    curDistance = totalDistance;
        	    	    point = object;
        	        }
        		}
        	}
        
     
        
        return point;
	
    }
    /**
     * @param x,y, the 1st and 2nd coordinate values respectively
	 * @return true if the given x and y form an available point in the graph
	 */
    public boolean availablePoint(int x , int y) {
        boolean check = false;  	
    
    	for (Coordinate object: coordList) {
    	    if (x == object.getX() && y == object.getY()) {
    	    	check = true;
    	    	break;
    	    }
    	}
    		return check; 
    }
    
    /**
     *  @param x,y, the 1st and 2nd coordinate values respectively
	 *  @return the coordinate given by x and y
	 */
    public Coordinate getPoint(int x,int y) {
    	Coordinate target = null;
    	
    	for (Coordinate object: coordList) {
    	    if (x == object.getX() && y == object.getY()) {
    	    	target = object;
    	    	break;
    	    }
    	}
    		return target; 
    }
    
    /**
     * @param player, the current coordinate of player, enemy, the current coordinate of enemy
	 * @return the coordinate which is in between the player and enemy
	 */
    public Coordinate between(Coordinate player, Coordinate enemy) {
    	Coordinate target = enemy;
    	int ax = player.getX();
        int ay = player.getY();
        int bx = enemy.getX();
        int by = enemy.getY();
        
        int midpointX = (ax + bx)/2;
        int midpointY = (ay + by)/2;
    	double curDistance = -1;
    	double totalDistance;
    	
    	for (Coordinate object: coordList) {
    	    totalDistance = Math.hypot(midpointX - object.getX(), midpointY - object.getY());
    	    if (curDistance == -1 || curDistance > totalDistance) {
    	    	curDistance = totalDistance;
    	    	target = object;
    	    }
    	}
        
    	return target;
    }
    
    /**
     * @param player, the current coordinate of player, enemy, the current coordinate of enemy, hound the coordinate of hound
	 * @return the coordinate which would put hound behind player relative to enemy
	 */
    public Coordinate hound(Coordinate player , Coordinate enemy,Coordinate hound) {
    	Coordinate target = player;

    	int ax = player.getX();
        int ay = player.getY();
        int bx = enemy.getX();
        int by = enemy.getY();

        int array[] = {0,1,-1};
        int i;
     
        //player ahead in x coordinate
        int houndx; //initial
        int houndy; //initial
        //do I need other iterators?
        if (ax -bx > 0) {
        	houndx = 1;
        } else if (ax - bx < 0) { //player behind in x coordinate
        	houndx = -1;
        } else { //player same x row as 
        	houndx = 0;
        }
        //player ahead in y coordinate
        if (ay -by > 0) {
        	houndy = 1;
        } else if (ay - by < 0) { //player behind in y coordinate
        	houndy = -1;
        } else { //player same y row as 
        	houndy = 0;
        }
        int tempx = houndx;
    	int tempy = houndy;
     
        boolean check = false;
        while (!check) {
        	// hound already in position
        	if ((ax + houndx) == hound.getX() && (ay+houndy) == hound.getY()) {
        		
        		target = hound;
        		check = true;
      
        	} else {
        		
        		
        		if (houndx == 0) {
        	        for (i = 0; i < 3; i++) {
        	            if (availablePoint(ax + houndx + array[i], ay+houndy)) {
        	            	target = getPoint(ax + houndx + array[i],ay + houndy);
        	            	check = true;
        	            	break;
        	            }
        	        }
        		} else if (houndy == 0 ) {
        			for (i = 0; i < 3; i++) {
        				if (availablePoint(ax + houndx,ay + houndy + array[i])) {
        					target = getPoint(ax + houndx,ay + houndy + array[i]);
        					check = true;
        					break;
        				}
        			}
        		
        	    } else {
        	    	
        	    	if (availablePoint(ax+houndx ,ay + houndy )) {
        	    		target = getPoint(ax+houndx ,ay+houndy );
        	    		check = true;
        	    	} else if (availablePoint(ax + houndx -tempx,ay + houndy)) {
        	    		target = getPoint(ax + houndx -tempx,ay+houndy);
        	    		check = true;
        	    		
        	    	} else if (availablePoint(ax+houndx,ay +houndy - tempy)) {
        	    		target = getPoint(ax+houndx,ay +houndy - tempy);
        	    		check = true;
        	    	}
        	    tempx = houndx;
        	    tempy = houndy;
                             	    	
        	    	
        	    }
        		houndx+=houndx;
       		    houndy+=houndy;

        	    if (Math.abs(ax + houndx) > width || Math.abs(by+houndy) > height) {
        		    check = true;
        		    //System.out.println("here");
        		    target = player; //not sure about this
        	    }
        	}
        }
        
        
    	return target;
    	
    }
    /**
     * 
	 * @return coordList
	 */
    public ArrayList<Coordinate> getCoords() {
    	return this.coordList;
    }
    
    /**
	 * @return edgeList
	 */
    public ArrayList<Edge> getEdges() {
    	return this.edgeList;
    }
    /**
     * @param a and b, the two coordinates to be used
	 * @return distance between two given coordinates
	 */
    public double getDistance(Coordinate a , Coordinate b) {
        int ax = a.getX();
        int ay = a.getY();
        int bx = b.getX();
        int by = b.getY();
        double distance = Math.sqrt((ax - bx)*(ax-bx) + (ay - by)*(ay-by));
        
        
        return distance;
    }
    
    /**
     * @param point, the coordinate to be added and edges connected to
	 * 
	 */
    public void addPointAndEdges(Coordinate point) {
        addCoordinate(point);
       // System.out.println("function way gives" + point.getX() + " " + point.getY());
        
        for (Coordinate object: coordList) {
        	if(object.equals(point))
        		continue;
        	if (isAdjacent(point,object)) {
        		this.addEdge(new Edge(point,object));
        		this.addEdge(new Edge(object,point));
        	}
        }
    }
    
    /**
	 * @param point, the coordinate remove the edges connected to it in edgeList, and then remove point from coordList
	 */
    public void removePointAndEdges(Coordinate point) {
    	ArrayList<Edge> toBeDeleted = new ArrayList<Edge>();
    	//System.out.println("function way removes" + point.getX() + " " + point.getY());
         	
        for( Edge edge: edgeList) {
            if(edge.getSrc().equals(point) || edge.getDest().equals(point)) {
                toBeDeleted.add(edge);
         		    
            }
         		
        }
        for(Edge edge: toBeDeleted) {
            edgeList.remove(edge);
        }
        this.nV--;
    	 coordList.remove(point);
    }
    
    /**
     * 
	 * @return Gives back a random coordinate in the list
	 */
    public Coordinate randomCoordinate() {
    	Random randomizer = new Random();
    	Coordinate random = coordList.get(randomizer.nextInt(coordList.size()));
        return random;
    }
    
    /**
	 * Prints all the coordinates and edges
	 */
    public void printGraph() {
    	for (Coordinate object: getCoords()) {
			System.out.println(object.getX() + " " + object.getY());
		}
		
		
		System.out.println("\n Edges: ");
		for (Edge object: getEdges()) {
			object.printCoords();
		}
      }
    /**
     * @param, point to be tested
	 * @return true whether coordList has point
	 */
    public boolean hasCoordinate(Coordinate point) {
        return coordList.contains(point);
    }
    
}
