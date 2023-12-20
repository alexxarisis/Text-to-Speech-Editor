package org.alexxarisis.commands;

import java.awt.event.ActionListener;

import javax.swing.event.CaretListener;
import javax.swing.event.ChangeListener;

import org.alexxarisis.client.AdvancedTextToSpeechView;

public class CommandsFactory {

	private final AdvancedTextToSpeechView view;
	
	public CommandsFactory (AdvancedTextToSpeechView view) {
		this.view = view;
	}
	
	public ActionListener createCommand(String str) {
        return switch (str) {
            case "open" -> new OpenDocument(view);
            case "save" -> new SaveDocument(view);
            case "close" -> new CloseDocument(view);
            case "edit" -> new EditDocument(view);
            case "play" -> new DocumentToSpeech(view);
            case "updateSelection" -> new UpdateSelection(view);
            case "replay" -> new ReplayCommands(view);
            case "record" -> new SetRecordingStatus(view);
            case "font" -> new ChangeFont(view);
            case "fontSize" -> new ChangeFontSize(view);
            case "colorTheme" -> new ChangeTextColorTheme(view);
            default -> null;
        };
	}
	
	public CaretListener createUpdateSelectionCommand() {
		return new UpdateSelection(view);
	}
	
	public ChangeListener createChangeListenerCommand(String str) {
        if (str.equals("voice")) {
            return new TuneAudioSettings(view);
        }
        return null;
    }
}
