package ui;

import entities.Coordinate;
import entities.Entity;

public interface EditorCommand {
	void execute();
	void undo();
}
