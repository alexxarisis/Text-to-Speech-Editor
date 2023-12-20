import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.alexxarisis.client.AdvancedTextToSpeechView;
import org.alexxarisis.commands.OpenDocument;
import util.TestsResourcesUtil;

public class OpenDocumentTest {

	private AdvancedTextToSpeechView view;
	private OpenDocument openCommand;
	
	@BeforeEach
	public void setUp() throws Exception {
		view = new AdvancedTextToSpeechView();
		openCommand = new OpenDocument(view);
	}

	@Test
	@DisplayName("Open .txt | No encryption.")
	public void testOpenTxtFile() {
		// set values
		openCommand.setName("plainText");
		openCommand.setPath(TestsResourcesUtil.getResourcePath("plainText.txt"));
		openCommand.setFileType("txt");
		openCommand.setLineWrap(true);
		openCommand.setEncryption("None");
		
		// load the file through the command (see replay() javadoc)
		openCommand.replay();

		assertEquals(getExpectedText(), view.getDocument().getContents());
	}
	
	@Test
	@DisplayName("Open .docx | No encryption.")
	public void testOpenDocxFile() {
		// set values
		openCommand.setName("plainText");
		openCommand.setPath(TestsResourcesUtil.getResourcePath("plainText.docx"));
		openCommand.setFileType("docx");
		openCommand.setLineWrap(true);
		openCommand.setEncryption("None");
		// load the file through the command (see replay() javadoc)
		openCommand.replay();

		assertEquals(getExpectedText(), view.getDocument().getContents());
	}
	
	@Test
	@DisplayName("Open .xlsx | No encryption.")
	public void testOpenExcelFile() {
		// set values
		openCommand.setName("plainText");
		openCommand.setPath(TestsResourcesUtil.getResourcePath("plainText.xlsx"));
		openCommand.setFileType("xlsx");
		openCommand.setLineWrap(false);
		openCommand.setEncryption("None");
				
		// load the file through the command (see replay() javadoc)
		openCommand.replay();
		String expectedText = getExpectedText().replace("\n", "\t\n");
		assertEquals(expectedText, view.getDocument().getContents());
	}

	// CHECK DECRYPTIONS
	@Test
	@DisplayName("Decryption: Atbash.")
	public void testOpenTxtFileAtbash() {
		// set values
		openCommand.setName("atbashText");
		openCommand.setPath(TestsResourcesUtil.getResourcePath("atbashText.txt"));
		openCommand.setFileType("txt");
		openCommand.setLineWrap(true);
		openCommand.setEncryption("AtBash");
		
		// load the file through the command (see replay() javadoc)
		openCommand.replay();
		
		// check to see if we decrypted the contents correctly.
        assertEquals(getExpectedText(), view.getDocument().getContents());
	}
	
	@Test
	@DisplayName("Decryption: Rot13.")
	public void testOpenTxtFileRot13() {
		// set values
		openCommand.setName("rot13Text");
		openCommand.setPath(TestsResourcesUtil.getResourcePath("rot13Text.txt"));
		openCommand.setFileType("txt");
		openCommand.setLineWrap(true);
		openCommand.setEncryption("Rot-13");
		
		// load the file through the command (see replay() javadoc)
		openCommand.replay();
		
		// check to see if we decrypted the contents correctly.
        assertEquals(getExpectedText(), view.getDocument().getContents());
	}
	
	// Below are the expected outcomes, depending on the reader.
	private String getExpectedText() {
		return """
                Hello,
                test,
                here

                ########""";
	}
}