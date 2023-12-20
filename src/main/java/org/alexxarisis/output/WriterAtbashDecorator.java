package org.alexxarisis.output;

public class WriterAtbashDecorator extends WriterDecorator{

	public WriterAtbashDecorator(IDocumentWriter writer) {
		super(writer);
	}

	@Override
	public void write(String path, String contents) {
		super.write(path, Encoder.atbash(contents));
	}
}