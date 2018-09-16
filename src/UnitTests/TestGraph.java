package UnitTests;
	import entities.*;
	import ui.*;
	import java.util.*;


public class TestGraph {


		
		
		public static void main(String[] args) {
			Game game = new Game("testEnemy",96,96);
			Graph g = game.generateGraph();
			g.generateEdges();
			System.out.println("Vertexes: ");
			System.out.println("x value | y value");
			for (Coordinate object: g.getCoords()) {
				System.out.println(object.getxPosition() + " " + object.getyPosition());
			}
			
			System.out.println("\n Edges: ");
			for (Edge object: g.getEdges()) {
				object.printCoords();
			}
			game.addEntity(new Hunter(new Coordinate(32,32)));
			game.addEntity(new Hunter(new Coordinate(32,96)));
			g = game.generateGraph();
			g.generateEdges();
			System.out.println("Vertexes: ");
			System.out.println("x value | y value");
			for (Coordinate object: g.getCoords()) {
				System.out.println(object.getxPosition() + " " + object.getyPosition());
			}
			
			System.out.println("\n Edges: ");
			for (Edge object: g.getEdges()) {
				object.printCoords();
			}
			
			Coordinate bfs = g.BFS(new Coordinate(32,64), new Coordinate(32,96));
			System.out.println("Bfs result : " + bfs.getxPosition() + " " + bfs.getyPosition());
			// TODO Auto-generated method stub
			System.out.println("Vertexes: ");
			System.out.println("x value | y value");
			for (Coordinate object: g.getCoords()) {
				System.out.println(object.getxPosition() + " " + object.getyPosition());
			}
			
			Coordinate moveAway = g.moveAway(new Coordinate(32,64), new Coordinate(32,96));
			System.out.println("moveAway result : " + moveAway.getxPosition() + " " + moveAway.getyPosition());
			
			Coordinate between = g.between(new Coordinate(96,0), new Coordinate(96,32));
			System.out.println("between result : " + between.getxPosition() + " " + between.getyPosition());
		}

	}


