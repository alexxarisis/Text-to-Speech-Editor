package org.alexxarisis.output;

public class DocumentWriterFactory {
	
	public IDocumentWriter createWriter(String extension, String encryption) {
        // determine writer based on type of the file
		IDocumentWriter writer = switch (extension) {
            case ".docx" -> new WordWriter();
            case ".xlsx" -> new ExcelWriter();
            default -> new TxtWriter();
        };

        return switch (encryption) {
            case "Rot-13" -> new WriterRot13Decorator(writer);
            case "AtBash" -> new WriterAtbashDecorator(writer);
            default -> writer;
        };
	}
}
