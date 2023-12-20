package org.alexxarisis.input;

public abstract class ReaderDecorator implements IDocumentReader {

	private final IDocumentReader reader;
	
	public ReaderDecorator(IDocumentReader reader) {
		this.reader = reader;
	}
	
	@Override
	public String read(String path) {
		return reader.read(path);
	}
}