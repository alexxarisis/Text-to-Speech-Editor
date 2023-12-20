package org.alexxarisis.input;

public class ReaderRot13Decorator extends ReaderDecorator{
	
	public ReaderRot13Decorator(IDocumentReader reader) {
		super(reader);
	}
	
	@Override
	public String read(String path) {
		return Decoder.rot13(super.read(path));
	}
}
