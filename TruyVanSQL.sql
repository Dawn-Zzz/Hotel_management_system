-- lấy ra danh sách hoá đơn
SELECT hd.MaHoaDon, hd.NgayLapHoaDon, kh.TenKhachHang, (hd.TienPhong + IFNULL(hd.TienDichVu, 0)) AS TongTien, nv.TenNhanVien 
FROM HoaDon hd 
INNER JOIN KhachHang kh ON kh.MaKhachHang = hd.MaKhachHang 
INNER JOIN NhanVien nv ON nv.MaNhanVien = hd.MaNhanVien;

-- Lấy ra các thông tin hoá đơn
SELECT kh.TenKhachHang, nv.TenNhanVien, (hd.TienPhong + IFNULL(hd.TienDichVu, 0)) AS TongTien, hd.TienPhong, hd.TienDichVu, hd.NgayLapHoaDon FROM HoaDon hd
INNER JOIN KhachHang kh ON kh.MaKhachHang = hd.MaKhachHang 
INNER JOIN NhanVien nv ON nv.MaNhanVien = hd.MaNhanVien ;


-- lấy ra tên và hiện trạng của các phòng
SELECT p.MaPhong, tb.TenThietBi, pctb.HienTrang FROM PhongCoThietBi pctb 
INNER JOIN Phong p ON p.MaPhong = pctb.MaPhong 
INNER JOIN ThietBi tb ON tb.MaThietBi = pctb.MaThietBi; 

UPDATE PhongCoThietBi pctb 
INNER JOIN ThietBi tb ON tb.MaThietBi = pctb.MaThietBi 
SET pctb.HienTrang = 'Hỏng' 
WHERE (pctb.MaPhong = '101') AND (tb.TenThietBi = 'Tủ lạnh') ;

-- lấy thông tin bảng khách hàng
SELECT *  FROM KhachHang kh ;

-- Join 2 bảng: thiết bị, phòng có thiết bị
SELECT *
FROM thietbi tb
INNER JOIN phongcothietbi ptb ON tb.MaThietBi = ptb.MaThietBi;

-- join 2 bảng: phòng, phòng có thiết bị
SELECT *
FROM phong p
INNER JOIN phongcothietbi ptb ON p.MaPhong = ptb.MaPhong;

-- join 3 bảng thiết bị, phòng có thiết bị, phòng
SELECT *
FROM thietbi tb
INNER JOIN phongcothietbi ptb ON tb.MaThietBi = ptb.MaThietBi
INNER JOIN phong p ON ptb.MaPhong = p.MaPhong;

-- join 2 bảng: khách hàng, phiếu thuê phòng
SELECT *
FROM khachhang kh
INNER JOIN phieuthue ptp ON kh.MaKhachHang = ptp.MaKhachHang;

-- join 3 bảng: khách hàng, phiếu thuê phòng, phòng
SELECT *
FROM phieuthue ptp
INNER JOIN phong p ON ptp.MaPhong = p.MaPhong
INNER JOIN khachhang kh ON ptp.MaKhachHang = kh.MaKhachHang;

-- join 2 bảng: phòng, loại phòng
SELECT *
FROM phong p
INNER JOIN loaiphong lp ON p.MaLoaiPhong = lp.MaLoaiPhong;

-- join 2 bảng: khách hàng, hóa đơn
SELECT *
FROM khachhang kh 
INNER JOIN hoadon hd ON kh.MaKhachHang = hd.MaKhachHang;

-- join 3 bảng: khách hàng, phiếu thuê phòng, nhân viên 
SELECT *
FROM khachhang kh
INNER JOIN phieuthue ptp ON kh.MaKhachHang= ptp.MaKhachHang
INNER JOIN nhanvien nv ON ptp.MaNhanVien = nv.MaNhanVien;

-- join 3 bảng: khách hàng, hóa đơn, nhân viên 
SELECT *
FROM khachhang kh 
INNER JOIN hoadon hd ON kh.MaKhachHang = hd.MaKhachHang
INNER JOIN nhanvien nv ON hd.MaNhanVien = nv.MaNhanVien;

-- join 3 bảng: hóa đơn, hóa đơn dịch vụ, dịch vụ
SELECT hd.MaHoaDon, dv.TenDichVu, hddv.SoLuong, dv.GiaDichVu
FROM hoadon hd
INNER JOIN chitiethoadondichvu hddv ON hd.MaHoaDon = hddv.MaHoaDon
INNER JOIN dichvu dv ON hddv.MaDichVu = dv.MaDichVu;

SELECT hd.TongTienPhong, hd.TongTienDichVu
FROM khachhang kh 
INNER JOIN hoadon hd ON kh.CCCD = hd.CCCD
INNER JOIN nhanvien nv ON hd.CCCD_NV = nv.CCCD_NV
WHERE hd.MaHoaDon = "HD005";

SELECT  ptp.ThoiGianNhanPhong, ptp.ThoiGianTraPhong, ptp.SoNguoiO, p.MaPhong, ptp.HinhThucThue,
		CASE ptp.HinhThucThue 
            WHEN N'Đêm' THEN lp.GiaQuaDem 
            WHEN N'Ngày' THEN lp.GiaTheoNgay 
            WHEN N'Giờ' THEN lp.GiaTheoGio 
            ELSE NULL 
       END AS Gia
FROM khachhang kh
INNER JOIN phieuthuephong ptp ON kh.CCCD = ptp.CCCD
INNER JOIN phong p ON ptp.MaPhong = p.MaPhong
INNER JOIN loaiphong lp ON p.MaLoaiPhong = lp.MaLoaiPhong;

UPDATE PhongCoThietBi pctb 
INNER JOIN ThietBi tb ON tb.MaThietBi = pctb.MaThietBi
SET pctb.HienTrang = "Hỏng"
WHERE (pctb.MaPhong = "103" AND tb.TenThietBi = "Ti vi");

SELECT COUNT(*) INTO khachHangCount
FROM KhachHang
WHERE MaKhachHang = maKhachHang;

 SELECT MaPhieu, NgayLap, MaKhachHang, MaNhanVien INTO @maHoaDon,@ngayLapHoaDon, @maKhachHang,@maNhanVien
        FROM PhieuThuePhong
        WHERE MaPhieu = '9';
SELECT @maHoaDon,@ngayLapHoaDon,@tongTienPhong,@tongTienDichVu,@maKhachHang,@maNhanVien;
SELECT SUM(TienPhong + IFNULL(TienDichVu, 0)) AS Tong FROM HoaDon hd;
SELECT MaKhachHang, MAX(TongTienPhong + TongTienDichVu) AS SoTienLonNhat
FROM HoaDon
GROUP BY MaKhachHang;

SELECT
  KhachHang.TenKhachHang,
  MAX(DATEDIFF(PhieuThue.ThoiGianTraPhong, PhieuThue.ThoiGianNhanPhong)) AS ThoiGianThue
FROM
  KhachHang
JOIN
  PhieuThue ON KhachHang.MaKhachHang = PhieuThue.MaKhachHang
GROUP BY
  KhachHang.TenKhachHang
ORDER BY
  ThoiGianThue DESC
LIMIT 1;

SELECT *
FROM phieuthue ptp
INNER JOIN phong p ON ptp.MaPhong = p.MaPhong
WHERE p.MaPhong = '101'
    AND (p.HienTrang = '2' OR (ptp.HienTrang LIKE N'Chưa nhận phòng' OR ptp.HienTrang LIKE N'Đã nhận phòng'));

