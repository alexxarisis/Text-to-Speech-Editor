import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.alexxarisis.output.Encoder;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.alexxarisis.client.AdvancedTextToSpeechView;
import org.alexxarisis.commands.SaveDocument;
import org.junit.jupiter.api.io.TempDir;

public class SaveDocumentTest {
	
	private AdvancedTextToSpeechView view;
	private SaveDocument saveCommand;
	@TempDir
	private File tempDir;
	
	@BeforeEach
	public void setUp() throws Exception {
		view = new AdvancedTextToSpeechView();
		saveCommand = new SaveDocument(view);
		String text = """
                Hello,
                test,
                here

                ########""";
		// Like open command
		view.getDocument().setContents(text);
		view.setTextAndLineWrap(text, true);
		view.enableButtons();
	}
	
	// CHECK DIFFERENT EXTENSIONS
	@Test
	@DisplayName("Save .txt | No encryption.")
	public void testSaveTxtFile() {
		String file = "savedPlainTxt";
		// set values
		saveCommand.setName(file);
		saveCommand.setPath(tempDir.getAbsolutePath());
		saveCommand.setFileType(".txt");
		saveCommand.setEncryption("None");
		
		// load the file through the command (see replay() javadoc)
		saveCommand.replay();

		String output = readTxt(file);
		assertEquals(view.getDocument().getContents(), output);
	}
	
	@Test
	@DisplayName("Save .docx | No encryption.")
	public void testSaveWordFile() {
		String file = "savedDocx";
		// set values
		saveCommand.setName(file);
		saveCommand.setPath(tempDir.getAbsolutePath());
		saveCommand.setFileType(".docx");
		saveCommand.setEncryption("None");
		
		// load the file through the command (see replay() javadoc)
		saveCommand.replay();

		String output = readWordFile(file);
		assertEquals(view.getDocument().getContents(), output);
	}
	
	@Test
	@DisplayName("Save .xlsx | No encryption.")
	public void testSaveXlsxFile() {
		String file = "savedXlsxTxt";
		// set values
		saveCommand.setName(file);
		saveCommand.setPath(tempDir.getAbsolutePath());
		saveCommand.setFileType(".xlsx");
		saveCommand.setEncryption("None");
		
		// load the file through the command (see replay() javadoc)
		saveCommand.replay();

		String output = readExcelFile(file);
		assertEquals(view.getDocument().getContents(), output);
		
	}

	// CHECK ENCRYPTIONS
	@Test
	@DisplayName("Encryption: Atbash")
	public void testSaveTxtFileAtbash() {
		String file = "savedAtbashTxt";
		// set values
		saveCommand.setName(file);
		saveCommand.setPath(tempDir.getAbsolutePath());
		saveCommand.setFileType(".txt");
		saveCommand.setEncryption("AtBash");
		
		// load the file through the command (see replay() javadoc)
		saveCommand.replay();

		String docEncoded = Encoder.atbash(view.getDocument().getContents());
		String output = readTxt(file);
		assertEquals(docEncoded, output);
	}
	
	@Test
	@DisplayName("Encryption: Rot-13")
	public void testSaveTxtFileRot13() {
		String file = "savedRot-13Txt";
		// set values
		saveCommand.setName(file);
		saveCommand.setPath(tempDir.getAbsolutePath());
		saveCommand.setFileType(".txt");
		saveCommand.setEncryption("Rot-13");
		
		// load the file through the command (see replay() javadoc)
		saveCommand.replay();
		
		String docEncoded = Encoder.rot13(view.getDocument().getContents());
		String output = readTxt(file);
		assertEquals(docEncoded, output);
	}
	
	// Below are methods to open the saved files
	private String readTxt(String name) {
		String contents = "";
		String path = tempDir.getAbsolutePath() + name + ".txt";
		
		try {
			BufferedReader reader = Files.newBufferedReader(Paths.get(path));
			contents = reader.lines().collect(Collectors.joining("\n"));
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contents;
	}

	private String readWordFile(String name) {
		String contents = "";
		String path = tempDir.getAbsolutePath() + name + ".docx";
		
		try {
			FileInputStream inputStream = new FileInputStream(path);
			BufferedInputStream buffer = new BufferedInputStream(inputStream);
			XWPFDocument  document = new XWPFDocument(buffer);
			List<XWPFParagraph> paragraphs = document.getParagraphs();
			
			StringBuilder bobOMastoras = new StringBuilder();
			
			for (XWPFParagraph paragraph : paragraphs)
				bobOMastoras.append(paragraph.getText());
			buffer.close();
			inputStream.close();
			document.close();
			
			contents = bobOMastoras.toString();
			return contents;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contents;
	}

	private String readExcelFile(String name) {
		String contents = "";
		String path = tempDir.getAbsolutePath() + name + ".xlsx";
		
		try {
			FileInputStream inputStream = new FileInputStream(path);
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			StringBuilder builder = new StringBuilder();

            for (Row row : sheet) {
                builder.append(row.getCell(0).toString());
                builder.append("\n");
            }
			workbook.close();
			
			contents = builder.toString();
			// Remove the last appended \n
			return contents.substring(0, contents.length()-1);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return contents;
	}
}
