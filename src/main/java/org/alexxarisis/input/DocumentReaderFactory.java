package org.alexxarisis.input;

public class DocumentReaderFactory {
	
	public IDocumentReader createReader(String fileType, String encryptionType) {
		// determine reader based on type of the file
		IDocumentReader reader = switch (fileType) {
            case "docx" -> new WordReader();
            case "xlsx" -> new ExcelReader();
            default -> new TxtReader();
        };

        // determine reader based on encryption of the file
        return switch (encryptionType) {
            case "Rot-13" -> new ReaderRot13Decorator(reader);
            case "AtBash" -> new ReaderAtbashDecorator(reader);
            default -> reader;
        };
	}
}
