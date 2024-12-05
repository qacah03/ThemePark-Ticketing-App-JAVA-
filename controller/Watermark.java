package controller;

import java.io.IOException;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

class Watermark extends PdfPageEventHelper {
    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        Image watermark = null;
		try {
			watermark = Image.getInstance("C:\\Users\\Acer\\Downloads\\themepark-watermark.png");
		} catch (BadElementException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        watermark.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
        watermark.setAbsolutePosition(0, 0); // Position at the bottom layer
        try {
			writer.getDirectContentUnder().addImage(watermark);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
