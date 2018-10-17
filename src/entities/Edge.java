package entities;

public class Edge {

	private String name;
	private Coordinate src;
	private Coordinate dest;
	
	public Edge(Coordinate src, Coordinate dest) {
		this.src = src;
		this.dest = dest;
	}

	public String getName() {
		return name;
	}

	public Coordinate getSrc() {
		return src;
	}

	public Coordinate getDest() {
		return dest;
	}
	
	public void printCoords() {
		System.out.println("src: " + src.getX() + " " + src.getY() + " " + "dest: " + dest.getX() + " " + dest.getY());
	}


	
	
	
}
