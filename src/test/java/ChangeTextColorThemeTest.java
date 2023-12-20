import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.alexxarisis.client.AdvancedTextToSpeechView;

public class ChangeTextColorThemeTest {
	
	private AdvancedTextToSpeechView view;
	
	@BeforeEach
	public void setUp() throws Exception {
		view = new AdvancedTextToSpeechView();
	}
	
	@Test
	@DisplayName("Color theme: white.")
	public void testColorThemeWhite() {
		view.getBlackThemeButton().doClick();
		
		// we start at black background and white text
		// so when we changed it, text color should be absolute black
		Color textColor = view.getTextArea().getDisabledTextColor();
		assertEquals(Color.black, textColor);
	}
	
	@Test
	@DisplayName("Color theme: black again.")
	public void testColorThemeBackToBlack() {
		// we enter white theme mode
		view.getBlackThemeButton().doClick();
		// and change back to black
		view.getBlackThemeButton().doClick();

		Color expectedColor = new Color(245, 245, 250);
		Color textColor = view.getTextArea().getDisabledTextColor();

		assertEquals(expectedColor, textColor);
	}
}
