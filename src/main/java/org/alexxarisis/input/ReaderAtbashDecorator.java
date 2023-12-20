package org.alexxarisis.input;

public class ReaderAtbashDecorator extends ReaderDecorator{

	public ReaderAtbashDecorator(IDocumentReader reader) {
		super(reader);
	}
	
	@Override
	public String read(String path) {
		return Decoder.atbash(super.read(path));
	}
}