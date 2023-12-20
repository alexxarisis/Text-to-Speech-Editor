import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.alexxarisis.client.AdvancedTextToSpeechView;
import org.alexxarisis.model.FakeTTSFacade;

public class TuneAudioSettingsTest {
	
	private AdvancedTextToSpeechView view;
	
	@BeforeEach
	public void setUp() throws Exception {
		view = new AdvancedTextToSpeechView();
		view.getDocument().setAudioManager(new FakeTTSFacade());
	}
	
	@Test
	@DisplayName("Volume: changed")
	public void testVolumeChanged() {
		int volume = 10;
		// Change the slider -> triggers command
		view.getAudioSettings("volume").setValue(volume);
		// was 1.0
		assertEquals(volume, view.getDocument().getAudioManager().getVolume());
	}
	
	@Test
	@DisplayName("Pitch: changed")
	public void testPitchChange() {
		int pitch = 150;
		// Change the slider -> triggers command
		view.getAudioSettings("pitch").setValue(pitch);
		// was 100.0
		assertEquals(pitch, view.getDocument().getAudioManager().getPitch());
	}
	
	@Test
	@DisplayName("Rate: changed")
	public void testRateChange() {
		int rate = 300;
		// Change the slider -> triggers command
		view.getAudioSettings("rate").setValue(rate);
		// was 150.0
		assertEquals(rate, view.getDocument().getAudioManager().getRate());
	}
}