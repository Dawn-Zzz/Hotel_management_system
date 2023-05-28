package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import DAO.BillDAO;
import DAO.OrderServiceDAO;
import DAO.ReservationDAO;
import view.AddServiceView;
import view.RoomView;

public class ServiceController implements ActionListener {

	private AddServiceView addServiceView;
	
	public ServiceController(AddServiceView addServiceView) {
		super();
		this.addServiceView = addServiceView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (!addServiceView.getServiceField1().getText().equals("0")) 
			OrderServiceDAO.getInstance().insertOrderService(BillDAO.getInstance().getMaHoaDonByMaPhieu(addServiceView.getNumberCode()),OrderServiceDAO.getInstance().getMaDichVuByNameService("Nhà Hàng"), Integer.parseInt(addServiceView.getServiceField1().getText())); 
		if (!addServiceView.getServiceField2().getText().equals("0"))
			OrderServiceDAO.getInstance().insertOrderService(BillDAO.getInstance().getMaHoaDonByMaPhieu(addServiceView.getNumberCode()),OrderServiceDAO.getInstance().getMaDichVuByNameService("Spa"), Integer.parseInt(addServiceView.getServiceField2().getText()));
		if (!addServiceView.getServiceField3().getText().equals("0"))
			OrderServiceDAO.getInstance().insertOrderService(BillDAO.getInstance().getMaHoaDonByMaPhieu(addServiceView.getNumberCode()),OrderServiceDAO.getInstance().getMaDichVuByNameService("Hồ Bơi"), Integer.parseInt(addServiceView.getServiceField3().getText()));
		if (!addServiceView.getServiceField4().getText().equals("0"))
			OrderServiceDAO.getInstance().insertOrderService(BillDAO.getInstance().getMaHoaDonByMaPhieu(addServiceView.getNumberCode()),OrderServiceDAO.getInstance().getMaDichVuByNameService("Gym"), Integer.parseInt(addServiceView.getServiceField4().getText()));
		if (!addServiceView.getServiceField5().getText().equals("0"))
			OrderServiceDAO.getInstance().insertOrderService(BillDAO.getInstance().getMaHoaDonByMaPhieu(addServiceView.getNumberCode()),OrderServiceDAO.getInstance().getMaDichVuByNameService("Giặt, ủi"), Integer.parseInt(addServiceView.getServiceField5().getText()));
		
		ReservationDAO.getInstance().updateStatusReservation(addServiceView.getNumberCode(), "Đã trả phòng");
		BillDAO.getInstance().updateIdStaff(BillDAO.getInstance().getMaHoaDonByMaPhieu(addServiceView.getNumberCode()));
        ((DefaultTableModel) RoomView.getInstance().getRoomTable().getModel()).setRowCount(0);
        ReservationDAO.getInstance().selectAll(RoomView.getInstance().getRoomTable());
        
        addServiceView.dispose();
	}
}
