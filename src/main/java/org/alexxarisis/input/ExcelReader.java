package org.alexxarisis.input;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader implements IDocumentReader {

	@Override
	public String read(String path) {
		String contents = "";

		try {
			FileInputStream inputStream = new FileInputStream(path);
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			StringBuilder builder = new StringBuilder();

            for (Row row : sheet) {
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    builder.append(cell.toString());
                    builder.append("\t");
                }
                builder.append("\n");
            }
			workbook.close();

			contents = builder.toString();
			// remove last appended \t\n
			contents = contents.substring(0, contents.length()-2);
			return contents;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return contents;
	}
}