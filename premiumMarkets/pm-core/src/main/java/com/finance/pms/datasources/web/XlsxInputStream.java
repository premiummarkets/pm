package com.finance.pms.datasources.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tools.ant.filters.StringInputStream;

public class XlsxInputStream extends InputStream {
	
	private StringInputStream in;
	
	public XlsxInputStream(InputStream responseBodyAsStream) throws IOException {
		
		// For storing data into CSV files
		StringBuffer data = new StringBuffer();
		XSSFWorkbook wBook = null;
		try {
		    // Get the workbook object for XLSX file
		   
			wBook = new XSSFWorkbook(responseBodyAsStream);
		    // Get first sheet from the workbook
		    XSSFSheet sheet = wBook.getSheetAt(0);
		    Row row;
		    Cell cell;
		    // Iterate through each rows from first sheet
		    Iterator<Row> rowIterator = sheet.iterator();

		    while (rowIterator.hasNext()) {
		        row = rowIterator.next();

		        // For each row, iterate through each columns
		        Iterator<Cell> cellIterator = row.cellIterator();
		        while (cellIterator.hasNext()) {

		            cell = cellIterator.next();

		            switch (cell.getCellType()) {
		                case Cell.CELL_TYPE_BOOLEAN:
		                    data.append(cell.getBooleanCellValue() + ",");

		                    break;
		                case Cell.CELL_TYPE_NUMERIC:
		                    data.append(cell.getNumericCellValue() + ",");

		                    break;
		                case Cell.CELL_TYPE_STRING:
		                    data.append(cell.getStringCellValue() + ",");
		                    break;

		                case Cell.CELL_TYPE_BLANK:
		                    data.append("" + ",");
		                    break;
		                default:
		                    data.append(cell + ",");

		            }
		        }
		        data.append("\n");
		    }

		} catch (Exception ioe) {
		    ioe.printStackTrace();
		} finally {
			if (wBook != null) wBook.close();
		}
		in = new StringInputStream(data.toString());
		
	}

	public int hashCode() {
		return in.hashCode();
	}

	public int read(byte[] b) throws IOException {
		return in.read(b);
	}

	public int read() throws IOException {
		return in.read();
	}

	public int read(byte[] b, int off, int len) throws IOException {
		return in.read(b, off, len);
	}

	public boolean equals(Object obj) {
		return in.equals(obj);
	}

	public void mark(int limit) {
		in.mark(limit);
	}

	public int available() throws IOException {
		return in.available();
	}

	public boolean markSupported() {
		return in.markSupported();
	}

	public void reset() throws IOException {
		in.reset();
	}

	public void close() throws IOException {
		in.close();
	}

	public long skip(long n) throws IOException {
		return in.skip(n);
	}

	public String toString() {
		return in.toString();
	}

}
