package org.alexxarisis.output;

public class WriterRot13Decorator extends WriterDecorator{

	public WriterRot13Decorator(IDocumentWriter writer) {
		super(writer);
	}

	@Override
	public void write(String path, String contents) {
		super.write(path, Encoder.rot13(contents));
	}
}