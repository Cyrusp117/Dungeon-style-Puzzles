package ui;

import java.util.Stack;

public class EditorInvoker {
	private final Stack<EditorCommand> history = new Stack<>();
	
	/**
	 * 
	 * @param cmd the Command to invoke
	 */
	public void invoke(final EditorCommand cmd) {
		cmd.execute();
		this.history.push(cmd);
	}
	
	/**
	 * Undo the last invoked command
	 */
	public void undo() {
		if(history.empty()) {
			return;
		}
		
		EditorCommand cmdToUndo = history.pop();
		cmdToUndo.undo();
	}
}
