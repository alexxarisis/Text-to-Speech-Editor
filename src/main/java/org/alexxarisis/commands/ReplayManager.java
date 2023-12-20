package org.alexxarisis.commands;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ReplayManager {

	private final List<ActionListener> commands;
	private boolean recordingStatus;
	
	public ReplayManager() {
		commands = new ArrayList<>();
		recordingStatus = false;
	}
	
	public void replay() {
		for (ActionListener command : commands) {
			command.actionPerformed(null);
		}
	}
	
	public void addCommand(ActionListener command) {
		commands.add(command);
	}
	
	public void startRecording() {
		recordingStatus = true;
		commands.clear();
	}
	
	public void endRecording() {
		recordingStatus = false;
	}
	
	public boolean isActiveRecording() {
		return recordingStatus;
	}
}
