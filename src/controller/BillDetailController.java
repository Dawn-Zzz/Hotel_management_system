package controller;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import view.BillDetailView;

public class BillDetailController implements ActionListener {
	private BillDetailView billDetailView;

	public BillDetailController(BillDetailView billDetailView) {
		// TODO Auto-generated constructor stub
		super();
		this.billDetailView = billDetailView;
	}

	// Hàm Tạo và lưu file PDF phiếu mượn

	//String id = (String) billDetailView.getBillID().getText();
	private void ActionSavePDF(String id) {

		// Vẽ tất cả hình ảnh trong jPanel thành file PDF

		try {

			Dimension size = BillDetailView.panel.getSize();

			BufferedImage image = new BufferedImage(size.width * 2, size.height, BufferedImage.TYPE_INT_RGB);

			Graphics2D g2 = image.createGraphics();

			BillDetailView.panel.paint(g2);

			Document document = new Document();

			FileOutputStream fileOutputStream = new FileOutputStream(

					"File PDF BillDetail/Bill Detail[" + id + "].pdf");

			PdfWriter.getInstance(document, fileOutputStream);

			document.open();

			com.itextpdf.text.Image pdfImage = com.itextpdf.text.Image.getInstance(new java.awt.image.BufferedImage(

					image.getColorModel(), image.copyData(null), image.isAlphaPremultiplied(), null), null);

			document.add(pdfImage);

			document.close();

		} catch (FileNotFoundException e2) {

			e2.printStackTrace();

		} catch (BadElementException e2) {

			e2.printStackTrace();

		} catch (DocumentException e2) {

			e2.printStackTrace();

		} catch (IOException e2) {

			e2.printStackTrace();

		}

		// Mở file pdf của phiếu mượn

		try {

			File file = new File("File PDF BillDetail/Bill Detail[" + id + "].pdf");

			Desktop.getDesktop().open(file);

		} catch (IOException e1) {

			e1.printStackTrace();

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ActionSavePDF("0");
	}
}
