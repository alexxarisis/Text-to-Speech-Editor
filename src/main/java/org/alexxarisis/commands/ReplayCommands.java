package org.alexxarisis.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.alexxarisis.client.AdvancedTextToSpeechView;

public class ReplayCommands implements ICommand, ActionListener{

	private final AdvancedTextToSpeechView view;
	
	public ReplayCommands(AdvancedTextToSpeechView view) {
		this.view = view;
	}

	@Override
	public void execute() {
		view.getReplayManager().replay();
		view.debug("Replaying");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// not going to store it to replayManager list, so just execute
		execute();
	}
}
