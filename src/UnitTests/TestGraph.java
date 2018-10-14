package UnitTests;
	import entities.*;
	import ui.*;
	import java.util.*;


public class TestGraph {


		
		
		public static void main(String[] args) {
			Game game = new Game(11,11);
			Graph g = game.generateGraph();
			g.generateEdges();
			System.out.println("Vertexes: ");
			System.out.println("x value | y value");
			for (Coordinate object: g.getCoords()) {
				System.out.println(object.getX() + " " + object.getY());
			}
			
			System.out.println("\n Edges: ");
			for (Edge object: g.getEdges()) {
				object.printCoords();
			}
			//game.addEntity(new Hunter(new Coordinate(32,32)));
			//game.addEntity(new Hunter(new Coordinate(32,96)));
			g = game.generateGraph();
			g.generateEdges();
			System.out.println("Vertexes: ");
			System.out.println("x value | y value");
			for (Coordinate object: g.getCoords()) {
				System.out.println(object.getX() + " " + object.getY());
			}
			
			System.out.println("\n Edges: ");
			for (Edge object: g.getEdges()) {
				object.printCoords();
			}
			
			Coordinate bfs = g.BFS(new Coordinate(1,2), new Coordinate(2,3),new Coordinate(4,8));
			System.out.println("Bfs result : " + bfs.getX() + " " + bfs.getY());
			
			System.out.println("Vertexes: ");
			System.out.println("x value | y value");
			for (Coordinate object: g.getCoords()) {
				System.out.println(object.getX() + " " + object.getY());
			}
			
			Coordinate moveAway = g.moveAway(new Coordinate(3,2), new Coordinate(3,1));
			System.out.println("moveAway result : " + moveAway.getX() + " " + moveAway.getY());
			
			Coordinate between = g.between(new Coordinate(1,1), new Coordinate(1,2));
			System.out.println("between result : " + between.getX() + " " + between.getY());
			
			Coordinate hound = g.hound(new Coordinate(10,7), new Coordinate(10,6),new Coordinate(6,7));
			System.out.println("Hunter up 1: hound result : " + hound.getX() + " " + hound.getY());
			
			hound = g.hound(new Coordinate(10,7), new Coordinate(10,8),new Coordinate(6,7));
			System.out.println("Hunter down 1: hound result : " + hound.getX() + " " + hound.getY());
			
			hound = g.hound(new Coordinate(10,7), new Coordinate(11,7),new Coordinate(6,7));
			System.out.println("Hunter right: hound result : " + hound.getX() + " " + hound.getY());
			
			hound = g.hound(new Coordinate(10,7), new Coordinate(11,6),new Coordinate(6,7));
			System.out.println("Hunter left: hound result : " + hound.getX() + " " + hound.getY());
			
			hound = g.hound(new Coordinate(10,7), new Coordinate(11,8),new Coordinate(6,7));
			System.out.println("Hunter bottom right:hound result : " + hound.getX() + " " + hound.getY());
			
			
		}

	}


