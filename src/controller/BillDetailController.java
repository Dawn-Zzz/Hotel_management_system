package controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import view.BillDetailView;
import view.BillView;
import view.editComponent.Table;

public class BillDetailController implements ActionListener {
	private BillDetailView billDetailView;
	private BillView billView = BillView.getInstance();

	public BillDetailController(BillDetailView billDetailView) {
		// TODO Auto-generated constructor stub
		super();
		this.billDetailView = billDetailView;
	}

	// Hàm Tạo và lưu file PDF phiếu mượn

	private void ActionSavePDF(Table roomInforTable,Table serviceInforTable) {
		String billIDText = (String) billDetailView.getBillID().getText();
		String guestNameText = (String) billDetailView.getGuestName().getText();
		String dateText = (String) billDetailView.getDate().getText();
		String invoicingStaffText = (String) billDetailView.getInvoicingStaff().getText();
		String totalMoneyText = (String) billDetailView.getTotalMoney().getText();
		String totalRoomText = (String) billDetailView.getTotalRoom().getText();
		String totalServiceText = (String) billDetailView.getTotalService().getText();
		
		try {
			Document document = new Document();

			FileOutputStream fileOutputStream = new FileOutputStream(

					"File PDF BillDetail/Bill Detail ["+billView.getBillTable().getValueAt(billView.getBillTable().getSelectedRow(), 0)+"].pdf");

			PdfWriter.getInstance(document, fileOutputStream);

			document.open();
			//Khởi tạo font và size
			BaseFont bf = BaseFont.createFont(".\\fonts\\arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font1 = new Font(bf, 24);
			Font font2 = new Font(bf, 12);
			
			Paragraph billID = new Paragraph(billIDText,font1);
			billID.setSpacingAfter(30);
			
			Paragraph guestName = new Paragraph(guestNameText,font2);
			//guestName.setSpacingAfter(10);
			Paragraph date = new Paragraph(dateText,font2);
			//date.setSpacingAfter(10);
			Paragraph invoicingStaff = new Paragraph(invoicingStaffText,font2);
			invoicingStaff.setIndentationLeft(50);
			Paragraph totalMoney = new Paragraph(totalMoneyText,font2);
			totalMoney.setIndentationLeft(50);
			
			Paragraph totalRoom = new Paragraph(totalRoomText,font2);
			totalRoom.setIndentationLeft(425);
			Paragraph totalService = new Paragraph(totalServiceText,font2);
			totalService.setIndentationLeft(425);
			
			// Tạo một đối tượng PdfPTable với 2 cột
	        PdfPTable tableParagraph = new PdfPTable(2);
	        tableParagraph.setWidthPercentage(100);

	     // Tạo các đối tượng PdfPCell và thêm nội dung vào
	        PdfPCell cell1 = new PdfPCell();
	        PdfPCell cell2 = new PdfPCell();
	        PdfPCell cell3 = new PdfPCell();
	        PdfPCell cell4 = new PdfPCell();
	        
	     // Thêm các đối tượng Paragraph vào các đối tượng PdfPCell
	        cell1.addElement(guestName);
	        cell2.addElement(invoicingStaff);
	        cell3.addElement(date);
	        cell4.addElement(totalMoney);
	        
	     // Bỏ viền của các ô
	        cell1.setBorder(Rectangle.NO_BORDER);
	        cell2.setBorder(Rectangle.NO_BORDER);
	        cell3.setBorder(Rectangle.NO_BORDER);
	        cell4.setBorder(Rectangle.NO_BORDER);

	        // Thêm các đối tượng PdfPCell vào PdfPTable
	        tableParagraph.addCell(cell1);
	        tableParagraph.addCell(cell2);
	        tableParagraph.addCell(cell3);
	        tableParagraph.addCell(cell4);
			
			PdfPTable pdfTable1 = new PdfPTable(roomInforTable.getColumnCount());
			pdfTable1.setSpacingBefore(50);
			pdfTable1.setWidthPercentage(100);
			float[] columnWidths = {80,100,100,130,130};
			pdfTable1.setWidths(columnWidths);
			
			// Thêm tiêu đề cột
	        for (int i = 0; i < roomInforTable.getColumnCount(); i++) {
	            PdfPCell cell = new PdfPCell(new Phrase(roomInforTable.getColumnName(i), font2));
	            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	            cell.setFixedHeight(30);
	            pdfTable1.addCell(cell);
	        }

	        // Thêm dữ liệu
	        for (int i = 0; i < roomInforTable.getRowCount(); i++) {
	            for (int j = 0; j < roomInforTable.getColumnCount(); j++) {
	                PdfPCell cell = new PdfPCell(new Phrase(roomInforTable.getValueAt(i, j).toString(), font2));
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setFixedHeight(30);
	                pdfTable1.addCell(cell);
	            }
	        }
	        
	        PdfPTable pdfTable2 = new PdfPTable(serviceInforTable.getColumnCount());
			pdfTable2.setSpacingBefore(50);
			pdfTable2.setWidthPercentage(100);
	        
	        for (int i = 0; i < serviceInforTable.getColumnCount(); i++) {
	            PdfPCell cell = new PdfPCell(new Phrase(serviceInforTable.getColumnName(i), font2));
	            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	            cell.setFixedHeight(30);
	            pdfTable2.addCell(cell);
	        }

	        // Thêm dữ liệu
	        for (int i = 0; i < serviceInforTable.getRowCount(); i++) {
	            for (int j = 0; j < serviceInforTable.getColumnCount(); j++) {
	                PdfPCell cell = new PdfPCell(new Phrase(serviceInforTable.getValueAt(i, j).toString(), font2));
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setFixedHeight(30);
	                pdfTable2.addCell(cell);
	            }
	        }
			
			document.add(billID);
			document.add(tableParagraph);
			document.add(pdfTable1);
			document.add(totalRoom);
			document.add(pdfTable2);
			document.add(totalService);
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

			File file = new File("File PDF BillDetail/Bill Detail ["+billView.getBillTable().getValueAt(billView.getBillTable().getSelectedRow(), 0)+"].pdf");

			Desktop.getDesktop().open(file);

		} catch (IOException e1) {

			e1.printStackTrace();

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ActionSavePDF(billDetailView.getRoomInforTable(),billDetailView.getServiceInforTable());
	}
}
