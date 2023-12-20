import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.alexxarisis.client.AdvancedTextToSpeechView;

import static org.junit.jupiter.api.Assertions.*;

public class CloseDocumentTest {
	
	private AdvancedTextToSpeechView view;
	
	@BeforeEach
	public void setUp() throws Exception {
		view = new AdvancedTextToSpeechView();
		
		// Like open command
		view.getDocument().setContents("Hi I'm alex!");
		view.getDocument().setSelection("alex!");
		view.setTextAndLineWrap("Hi I'm alex!", true);
		view.enableButtons();
	}
	
	@Test
	@DisplayName("Buttons: disabled.")
	public void testButtonsDisable() {
		view.getToolbarButton("close").doClick();
        assertFalse(view.getToolbarButton("close").isEnabled());
	}
	
	@Test
	@DisplayName("Document info reset/delete.")
	public void testDocumentReset() {
		view.getToolbarButton("close").doClick();
        assertEquals("", view.getDocument().getName());
        assertEquals("", view.getDocument().getContents());
        assertNull(view.getDocument().getSelection());
	}
	
	@Test
	@DisplayName("Text area empty and not editable.")
	public void testTextAreaReset() {
		view.getToolbarButton("close").doClick();
        assertEquals("", view.getTextArea().getText());
        assertFalse(view.getTextArea().isEditable());
	}
}
