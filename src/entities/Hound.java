package entities;

import java.awt.event.KeyEvent;

import javafx.scene.input.KeyCode;
import java.util.*;

public class Hound extends Enemy {
    private Hunter hunter;
    private final int houndSmellRange = 5;
    private final double houndRadiusRange = 2*Math.sqrt(2);
	
	public Hound(Coordinate position) {
		super(position);
		this.hunter = null;
		this.keyCode = KeyCode.Z;
		
	}

	@Override
	public Coordinate getTargetSpace(Coordinate co, Graph g,Coordinate closestPickup,ArrayList<Entity> entities) {
		Coordinate target = null;
		int closestBonePath = -1;
		Bone closest = null;
		int curLength;
		for (Entity object: entities) {
			if (object instanceof Bone) {
				
				Bone bone = (Bone) object;
				g.addPointAndEdges(bone.getPosition());
			    curLength = g.sizeBFS(position, bone.getPosition());
			    g.removePointAndEdges(bone.getPosition());
			    if (curLength != -1 && curLength < houndSmellRange && g.getDistance(position, bone.getPosition()) < houndRadiusRange && (closestBonePath == -1 || curLength < closestBonePath ) ) {
				    closestBonePath = curLength;
				    closest = bone;
			    }
			}
		}
		if (closestBonePath != -1) {
			if (g.isAdjacent(position, closest.getPosition())) {
				target = position;
				closest.reduceLifeTime(); //lifetime of bone reduced here
			} else {
			    g.addPointAndEdges(closest.getPosition());
			    target = closest.getPosition();
			}
		} else if (hunter != null) {
			target = g.hound(co,hunter.getPosition(),this.position);
			
		} else {
			target = co;
		}
	
		return target;
	}
	
	/**
	 * 
	 * @param hunter - the hunter the hound wants to follow
	 */
	public void setHunter (Hunter hunter) {
		this.hunter = hunter;
	}
	
	/**
	 * @return "Hound"
	 */
	public String getName() {
		return "Hound";
	}
	
}
