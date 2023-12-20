package org.alexxarisis.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TxtWriter implements IDocumentWriter{

	@Override
	public void write(String path, String contents) {
		try {
			boolean fileCreated = new File(path).createNewFile();
			int counter = 0;

			while (!fileCreated) {
				counter++;
				// Add (number) in the end of the name
				path = String.format("%s(%d).txt",
						path.substring(0, path.length()-4),
						counter
				);

				fileCreated = new File(path).createNewFile();
			}

			FileWriter writer = new FileWriter(path);
			writer.write(contents);
			writer.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
