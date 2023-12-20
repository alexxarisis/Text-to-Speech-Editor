package org.alexxarisis.output;

public abstract class WriterDecorator implements IDocumentWriter{
	
	private final IDocumentWriter writer;
	
	public WriterDecorator(IDocumentWriter writer) {
		this.writer = writer;
	}
	
	@Override
	public void write(String path, String contents) {
		writer.write(path, contents);
	}
}