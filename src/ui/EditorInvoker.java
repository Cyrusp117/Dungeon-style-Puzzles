package ui;

import java.util.Stack;

public class EditorInvoker {
	private final Stack<EditorCommand> history = new Stack<>();
	
	public void invoke(final EditorCommand cmd) {
		cmd.execute();
		this.history.push(cmd);
	}
	
	public void undo() {
		if(history.empty()) {
			System.out.print("Empty stack");
			return;
		}
		
		EditorCommand cmdToUndo = history.pop();
		cmdToUndo.undo();
	}
}
