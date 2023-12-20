import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.alexxarisis.client.AdvancedTextToSpeechView;
import org.alexxarisis.commands.OpenDocument;
import org.alexxarisis.model.FakeTTSFacade;
import util.TestsResourcesUtil;

public class ReplayCommandsTest {
	
	private AdvancedTextToSpeechView view;
	private FakeTTSFacade audioManager;
	private StringBuilder text;
	
	@BeforeEach
	public void setUp() throws Exception {
		view = new AdvancedTextToSpeechView();
		audioManager = new FakeTTSFacade();
		text = new StringBuilder();
		// start recording
		view.getToolbarButton("record").doClick();
	}
	
	@Test
	@DisplayName("Open->Play->Open->Play")
	public void testReplay() {
		// FIRST FILE
		view.getReplayManager().addCommand(OpenDocument("play"));
		// speak
		view.getToolbarButton("play").doClick();
		// record the contents played the first time
		text.append(view.getDocument().getContents());

		// SECOND FILE
		view.getReplayManager().addCommand(OpenDocument("helloWorld"));
		// speak
		view.getToolbarButton("play").doClick();
		// record the contents played the first time
		text.append(view.getDocument().getContents());
		
		// stop recording
		view.getToolbarButton("record").doClick();
		
		// Set FakeTTSFacade to record the replayed audio
		view.getDocument().setAudioManager(audioManager);
		// replay
		view.getToolbarButton("replay").doClick();

        assertTrue(audioManager.getPlayedContents().contentEquals(text));
	}
	
	@Test
	@DisplayName("Open->Select->Play->Open->Play->Select->Play")
	public void testReplay2() {
		// FIRST FILE - selection
		view.getReplayManager().addCommand(OpenDocument("play"));
		// make selection
		view.getTextArea().setCaretPosition(0);
		view.getTextArea().moveCaretPosition(2);
		// choose to use the selection
		view.getToolbarButton("useSelectedText").doClick();
		// speak
		view.getToolbarButton("play").doClick();
		// record the contents played the first time
		text.append(view.getDocument().getSelection());

		// SECOND FILE - all content
		view.getReplayManager().addCommand(OpenDocument("helloWorld"));
		// make selection
		view.getTextArea().setCaretPosition(0);
		view.getTextArea().moveCaretPosition(6);
		// choose to not use the selection (had it selected before)
		view.getToolbarButton("useSelectedText").doClick();
		// speak
		view.getToolbarButton("play").doClick();
		// record the contents played the first time
		text.append(view.getDocument().getContents());
		
		// THIRD FILE - selection
		// make selection
		view.getTextArea().setCaretPosition(6);
		view.getTextArea().moveCaretPosition(12);
		// choose to use the selection
		view.getToolbarButton("useSelectedText").doClick();
		// speak
		view.getToolbarButton("play").doClick();
		// record the contents played the first time
		text.append(view.getDocument().getSelection());
		
		// stop recording
		view.getToolbarButton("record").doClick();
		
		// Set FakeTTSFacade to record the replayed audio
		view.getDocument().setAudioManager(audioManager);
		// replay
		view.getToolbarButton("replay").doClick();

        assertTrue(audioManager.getPlayedContents().contentEquals(text));
	}
	
	// Opens a document through the OpenDocument command.
	private OpenDocument OpenDocument(String name) {
		// open
		OpenDocument openCommand = new OpenDocument(view);

		// set values
		openCommand.setName(name);
		openCommand.setPath(TestsResourcesUtil.getResourcePath(name + ".txt"));
		openCommand.setFileType("txt");
		openCommand.setLineWrap(true);
		openCommand.setEncryption("None");
				
		// Load the file through the command (see replay() javadoc)
		// To update document fields
		openCommand.replay();

		return openCommand;
	}
}
