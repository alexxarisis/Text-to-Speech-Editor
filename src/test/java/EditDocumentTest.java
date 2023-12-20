import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.alexxarisis.client.AdvancedTextToSpeechView;

import static org.junit.jupiter.api.Assertions.*;

public class EditDocumentTest {
	
	private AdvancedTextToSpeechView view;
	private String text;
	
	@BeforeEach
	public void setUp() throws Exception {
		view = new AdvancedTextToSpeechView();
		
		text = "Hi i'm alex.\nNice to meet you!";
		// Like open command
		view.getDocument().setContents(text);
		view.setTextAndLineWrap(text, true);
		view.enableButtons();
	}
	
	@Test
	@DisplayName("Edit mode activated.")
	public void testEditButtonToggled() {
		view.getToolbarButton("edit").doClick();	// enter edit mode
        assertTrue(view.getTextArea().isEditable());
	}
	
	@Test
	@DisplayName("Edit mode deactivated.")
	public void testEditButtonToggleOff() {
		view.getToolbarButton("edit").doClick();	// enter edit mode
		view.getToolbarButton("edit").doClick();	// exit edit mode
        assertFalse(view.getTextArea().isEditable());
	}
	
	// When we finish editing the file, check if the contents are changed
	@Test
	@DisplayName("Edited text saved, after exiting edit mode.")
	public void testDocumentContentsAfterEdit() {
		view.getToolbarButton("edit").doClick();	// enter edit mode
		
		text = "Hi i'm alex.";
		view.setTextAndLineWrap(text, false);		// set new text on JTextArea
		view.getToolbarButton("edit").doClick();	// exit edit mode

        assertEquals(text, view.getDocument().getContents());
	}
	
}
