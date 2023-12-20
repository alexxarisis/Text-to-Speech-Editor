import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.alexxarisis.client.AdvancedTextToSpeechView;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateSelectionTest {
	
	private AdvancedTextToSpeechView view;
	
	@BeforeEach
	public void setUp() throws Exception {
		view = new AdvancedTextToSpeechView();
		
		// get some new text in there
		String text = "Hi i'm alex.\nNice to meet you!";
		view.setTextAndLineWrap(text, false);
		view.enableButtons();
		
		// set caret to start
		view.getTextArea().setCaretPosition(0);
	}
	
	// Check if use-selected-text button is enabled
	@Test
	@DisplayName("Use selected text button becomes enabled")
	public void testUseSelectedTextButtonEnabled() {
		view.getTextArea().moveCaretPosition(12);

        assertTrue(view.getToolbarButton("useSelectedText").isEnabled());
	}
	
	// Check if when clicking to use the selected text,
	// it updates over to the Document and vice-versa
	@Test
	@DisplayName("Use selected text button becomes enabled")
	public void testUpdateSelectedText() {
		view.getTextArea().moveCaretPosition(12);
		
		// if selected text is the right one
		view.getToolbarButton("useSelectedText").doClick();
        assertEquals(view.getDocument().getSelection(), view.getTextArea().getSelectedText());
		
		// if selected text on Document is null, cuz we don't use it
		view.getToolbarButton("useSelectedText").doClick();
        assertNull(view.getDocument().getSelection());
	}
}