CREATE DATABASE Hotel_management;
USE Hotel_management;

CREATE TABLE NguoiDung(
	TaiKhoan NVARCHAR(50) NOT NULL,
    MatKhau NVARCHAR(50) NOT NULL,
    Quyen INT,
    PRIMARY KEY (TaiKhoan)
);

CREATE TABLE NhanVien(
	MaNhanVien INT AUTO_INCREMENT,
	CCCD CHAR(12),
    SoDienThoai CHAR (10),
    TenNhanVien NVARCHAR(50) NOT NULL,
    NgaySinh DATE,
    ChucDanh NVARCHAR(50),
    TaiKhoan NVARCHAR(50) NOT NULL,
    PRIMARY KEY (MaNhanVien)
);

CREATE TABLE LoaiPhong(
	MaLoaiPhong CHAR(10) NOT NULL,
    TenLoaiPhong NVARCHAR(50) NOT NULL,
    GiaTheoGio FLOAT NOT NULL,
    GiaTheoNgay FLOAT NOT NULL,
    GiaQuaDem FLOAT NOT NULL,
    PRIMARY KEY (MaLoaiPhong)
);

CREATE TABLE KhachHang(
	MaKhachHang INT AUTO_INCREMENT,
	CCCD CHAR(12) NOT NULL,
    TenKhachHang NVARCHAR(50) NOT NULL,
    LoaiKhachHang NVARCHAR(50),
    NgaySinh DATE,
    SoDienThoai CHAR(10),
    PRIMARY KEY (MaKhachHang)
);

CREATE TABLE Phong(
	MaPhong CHAR(10) NOT NULL,
    HienTrang CHAR(2) NOT NULL,
    MaLoaiPhong CHAR(10) NOT NULL,
    PRIMARY KEY (MaPhong)
);

CREATE TABLE ThietBi(
	MaThietBi CHAR(10) NOT NULL, 
    TenThietBi NVARCHAR(50) NOT NULL, 
    PRIMARY KEY (MaThietBi) 
);

CREATE TABLE DichVu(
	MaDichVu CHAR(10) NOT NULL, 
    TenDichVu NVARCHAR(50) NOT NULL, 
    GiaDichVu FLOAT NOT NULL, 
    PRIMARY KEY (MaDichVu)
);

CREATE TABLE PhieuThue( 
	MaPhieu INT AUTO_INCREMENT, 
    NgayLap DATE NOT NULL, 
    ThoiGianNhanPhong DATETIME NOT NULL, 
    ThoiGianTraPhong DATETIME NOT NULL, 
    SoNguoiO INT NOT NULL, 
    HinhThucThue NVARCHAR(50) NOT NULL, 
    HienTrang NVARCHAR(50) NOT NULL, 
    MaKhachHang INT NOT NULL, 
    MaNhanVien INT NOT NULL, 
    MaPhong CHAR(10) NOT NULL, 
    PRIMARY KEY (MaPhieu)
);

CREATE TABLE HoaDon(
	MaHoaDon CHAR(10) NOT NULL, 
    NgayLapHoaDon DATE, 
    TienPhong FLOAT, 
    TienDichVu FLOAT, 
    MaKhachHang INT NOT NULL, 
    MaNhanVien INT, 
    MaPhieu INT NOT NULL,
    PRIMARY KEY (MaHoaDon)
);

CREATE TABLE PhongCoThietBi( 
	MaThietBi CHAR(10) NOT NULL, 
    MaPhong CHAR(10) NOT NULL, 
    HienTrang NVARCHAR(20) NOT NULL, 
    PRIMARY KEY (MaThietBi, MaPhong)
);

CREATE TABLE ChiTietHoaDonDichVu(
	SoLuong INT NOT NULL,
	MaHoaDon CHAR(10) NOT NULL,
    MaDichVu CHAR(10) NOT NULL,
    PRIMARY KEY (MaHoaDon, MaDichVu)
);

-- Trigger
DELIMITER //
CREATE TRIGGER update_Phong_HienTrang
AFTER UPDATE ON PhieuThue
FOR EACH ROW 
BEGIN
    IF NEW.HienTrang = 'Đã nhận phòng' THEN
        UPDATE Phong SET HienTrang = '1' WHERE MaPhong = NEW.MaPhong;
    ELSEIF NEW.HienTrang = 'Đã trả phòng' THEN
        UPDATE Phong SET HienTrang = '0' WHERE MaPhong = NEW.MaPhong;
    END IF;
END;

CREATE TRIGGER update_TaiKhoan_Quyen
AFTER UPDATE ON NhanVien
FOR EACH ROW 
BEGIN
    IF NEW.ChucDanh LIKE 'Nhân viên%' THEN
        UPDATE NguoiDung SET Quyen = '2' WHERE TaiKhoan = NEW.TaiKhoan;
--     ELSEIF NEW. = 'Đã trả phòng' THEN
--         UPDATE Phong SET HienTrang = '0' WHERE MaPhong = NEW.MaPhong;-- 
    END IF;
END;

CREATE TRIGGER update_phongcothietbi
AFTER UPDATE ON PhongCoThietBi
FOR EACH ROW
BEGIN
    DECLARE count_hong INT;
    DECLARE count_tot INT;
    SET count_hong = (SELECT COUNT(*) FROM PhongCoThietBi WHERE MaPhong = NEW.MaPhong AND HienTrang = 'Hỏng');
    SET count_tot = (SELECT COUNT(*) FROM PhongCoThietBi WHERE MaPhong = NEW.MaPhong AND HienTrang = 'Tốt');

    IF count_hong > 0 THEN
        UPDATE Phong SET HienTrang = '2' WHERE MaPhong = NEW.MaPhong;
    ELSE
        IF (SELECT COUNT(*) FROM PhongCoThietBi WHERE MaPhong = NEW.MaPhong AND HienTrang != 'Tốt') = 0 THEN
            UPDATE Phong SET HienTrang = '0' WHERE MaPhong = NEW.MaPhong;
        END IF;
    END IF;
END //

CREATE TRIGGER Trg_TaoHoaDon
AFTER UPDATE ON PhieuThue
FOR EACH ROW
BEGIN
    -- Biến tạm thời
    SET @maHoaDon = NULL;
    SET @ngayLapHoaDon = NULL;
    SET @tienPhong = NULL;
    SET @tienDichVu = NULL;
    SET @maKhachHang = NULL;
    SET @maNhanVien = NULL;
    SET @soLuongHoaDon = NULL;
    
    IF NEW.HienTrang = 'Đã nhận phòng' THEN
        -- Đếm số lượng hoá đơn hiện có trong bảng HoaDon
        SELECT COUNT(*) INTO @soLuongHoaDon FROM HoaDon;
        
        -- Tạo mã hoá đơn
        SET @maHoaDon = CONCAT('HD', LPAD(@soLuongHoaDon + 1, 3, '0'));
        -- Update MaHoaDon và MaPhieu cho dòng cập nhật
		INSERT INTO HoaDon (MaHoaDon, MaPhieu, MaKhachHang) VALUES (@maHoaDon, NEW.MaPhieu, NEW.MaKhachHang);
	END IF;
    
    IF NEW.HienTrang = 'Đã trả phòng' THEN
        -- Lấy mã hoá đơn từ bảng HoaDon dựa trên Mã Phiếu
        SELECT MaHoaDon INTO @maHoaDon FROM HoaDon WHERE MaPhieu = NEW.MaPhieu;
        
        -- Gán các giá trị khác từ dòng được cập nhật
        SET @ngayLapHoaDon = CURDATE();
        SET @maKhachHang = NEW.MaKhachHang;
        
        -- Tính tổng tiền phòng từ bảng PhieuThuePhong
        SELECT CASE NEW.HinhThucThue
            WHEN N'Đêm' THEN lp.GiaQuaDem
            WHEN N'Ngày' THEN lp.GiaTheoNgay * TIMESTAMPDIFF(DAY, NEW.ThoiGianNhanPhong, NEW.ThoiGianTraPhong)
            WHEN N'Giờ' THEN lp.GiaTheoGio * TIMESTAMPDIFF(HOUR, NEW.ThoiGianNhanPhong, NEW.ThoiGianTraPhong)
        END INTO @tienPhong
        FROM PhieuThue ptp
        INNER JOIN Phong p ON ptp.MaPhong = p.MaPhong
        INNER JOIN LoaiPhong lp ON p.MaLoaiPhong = lp.MaLoaiPhong
        WHERE ptp.MaPhieu = NEW.MaPhieu;
        
        SELECT LoaiKhachHang INTO @loaiKhach FROM KhachHang WHERE MaKhachHang = @maKhachHang;
		IF @loaiKhach = 'Vip' THEN
			SET @tienPhong = @tienPhong * 0.9; -- Giảm 10% cho tiền phòng
		END IF;
        
        -- Tính tổng tiền dịch vụ từ bảng HoaDonDichVu
        SELECT SUM(SoLuong * dv.GiaDichVu) INTO @tienDichVu
        FROM ChiTietHoaDonDichVu cthdv
        INNER JOIN DichVu dv ON cthdv.MaDichVu = dv.MaDichVu
        WHERE cthdv.MaHoaDon = @maHoaDon;
        
        -- Cập nhật thông tin hoá đơn
        UPDATE HoaDon
        SET NgayLapHoaDon = @ngayLapHoaDon,
            TienPhong = @tienPhong,
            TienDichVu = @tienDichVu,
            MaKhachHang = @maKhachHang
        WHERE MaHoaDon = @maHoaDon;
    END IF;
END //

DELIMITER ;


-- Tạo khóa ngoại cho các quan hệ 

ALTER TABLE NhanVien
ADD FOREIGN KEY (TaiKhoan) REFERENCES NguoiDung(TaiKhoan);
ALTER TABLE Phong 
ADD FOREIGN KEY (MaLoaiPhong) REFERENCES LoaiPhong(MaLoaiPhong);
ALTER TABLE HoaDon 
ADD FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang);
ALTER TABLE HoaDon 
ADD FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien);
ALTER TABLE HoaDon 
ADD FOREIGN KEY (MaPhieu) REFERENCES PhieuThue(MaPhieu);
ALTER TABLE PhongCoThietBi
ADD FOREIGN KEY (MaThietBi) REFERENCES ThietBi(MaThietBi);
ALTER TABLE PhongCoThietBi
ADD FOREIGN KEY (MaPhong) REFERENCES Phong(MaPhong);
ALTER TABLE PhieuThue
ADD FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang);
ALTER TABLE PhieuThue 
ADD FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien);
ALTER TABLE PhieuThue
ADD FOREIGN KEY (MaPhong) REFERENCES Phong(MaPhong);
ALTER TABLE ChiTietHoaDonDichVu
ADD FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon);
ALTER TABLE ChiTietHoaDonDichVu
ADD FOREIGN KEY (MaDichVu) REFERENCES DichVu(MaDichVu);

-- ALTER TABLE HinhThucThue
-- ADD FOREIGN KEY (MaLoaiPhong) REFERENCES LoaiPhong(MaLoaiPhong);

-- Tạo ràng buộc cho các quan hệ 

ALTER TABLE LoaiPhong ADD CONSTRAINT chk_GiaTheoGio CHECK (GiaTheoGio>0);
ALTER TABLE LoaiPhong ADD CONSTRAINT chk_GiaTheoNgay CHECK (GiaTheoNgay>0);
ALTER TABLE LoaiPhong ADD CONSTRAINT chk_GiaQuaDem CHECK (GiaQuaDem>0);
ALTER TABLE DichVu ADD CONSTRAINT chk_GiaDichVu CHECK (GiaDichVu>0);
ALTER TABLE PhieuThue ADD CONSTRAINT chk_NgayLap CHECK (NgayLap<=sysdate());
ALTER TABLE PhieuThue ADD CONSTRAINT chk_SoNguoiO CHECK (SoNguoiO>0);
ALTER TABLE PhieuThue ADD CONSTRAINT chk_ThoiGianNhanPhongThoiGianTraPhong CHECK (ThoiGianNhanPhong <= ThoiGianTraPhong);
ALTER TABLE KhachHang ADD CONSTRAINT chk_Tuoi CHECK (NgaySinh <= DATE_SUB(sysdate(), INTERVAL 18 YEAR));  
ALTER TABLE NhanVien ADD CONSTRAINT chk_TuoiNhanVien CHECK (NgaySinh <= DATE_SUB(sysdate(), INTERVAL 18 YEAR));
ALTER TABLE PhieuThue ADD CONSTRAINT uq_PhieuThue UNIQUE (MaPhieu);
ALTER TABLE PhieuThue ADD CONSTRAINT chk_ThoiGianTraPhongThoiGianNhanPhong 
CHECK (ThoiGianNhanPhong >= NgayLap AND ThoiGianTraPhong >= NgayLap);
ALTER TABLE PhieuThue ADD CONSTRAINT chk_SoNguoi CHECK (SoNguoiO >=1 AND SoNguoiO <=8);

-- Thêm dữ liệu cho bảng 
INSERT INTO NguoiDung (TaiKhoan, MatKhau, Quyen) VALUES ('admin', 'admin', 1);
INSERT INTO NguoiDung (TaiKhoan, MatKhau, Quyen) VALUES ('tanphat2k3', 'tanphat2k3', 2);
INSERT INTO NguoiDung (TaiKhoan, MatKhau, Quyen) VALUES ('hongphuc2k3', 'hongphuc2k3', 3);
INSERT INTO NguoiDung (TaiKhoan, MatKhau, Quyen) VALUES ('binhminh2k3', 'binhminh2k3', 4);
-- INSERT INTO NguoiDung (TaiKhoan, MatKhau, Quyen) VALUES ('TK005', 'tk005', 2);
-- INSERT INTO NguoiDung (TaiKhoan, MatKhau, Quyen) VALUES ('TK006', 'tk006', 2);
-- INSERT INTO NguoiDung (TaiKhoan, MatKhau, Quyen) VALUES ('TK007', 'tk007', 2);
-- INSERT INTO NguoiDung (TaiKhoan, MatKhau, Quyen) VALUES ('TK008', 'tk008', 2);
-- INSERT INTO NguoiDung (TaiKhoan, MatKhau, Quyen) VALUES ('TK009', 'tk009', 2);
-- INSERT INTO NguoiDung (TaiKhoan, MatKhau, Quyen) VALUES ('TK010', 'tk010', 2); 

INSERT INTO NhanVien (CCCD, SoDienThoai, TenNhanVien, NgaySinh, ChucDanh, TaiKhoan) VALUES ('312848541545', '0384845484', 'Võ Thị Tường VI', '2003-12-03', 'Giám đốc', 'admin');
INSERT INTO NhanVien (CCCD, SoDienThoai, TenNhanVien, NgaySinh, ChucDanh, TaiKhoan) VALUES ('325484544487', '0352819756', 'Nguyễn Tấn Phát', '2001-11-09', 'Nhân viên kế toán', 'tanphat2k3');
INSERT INTO NhanVien (CCCD, SoDienThoai, TenNhanVien, NgaySinh, ChucDanh, TaiKhoan) VALUES ('338897894465', '0335852676', 'Lê Hồng Phúc', '2002-10-08', 'Nhân viên lễ tân', 'hongphuc2k3');
INSERT INTO NhanVien (CCCD, SoDienThoai, TenNhanVien, NgaySinh, ChucDanh, TaiKhoan) VALUES ('348784867879', '0359518415', 'Nguyễn Bình Minh', '2003-07-11', 'Nhân viên phục vụ ', 'binhminh2k3');

INSERT INTO LoaiPhong (MaLoaiPhong, TenLoaiPhong, GiaTheoGio, GiaTheoNgay, GiaQuaDem) VALUES ('LP001', 'Phòng đơn', '150000', '350000', '270000');
INSERT INTO LoaiPhong (MaLoaiPhong, TenLoaiPhong, GiaTheoGio, GiaTheoNgay, GiaQuaDem) VALUES ('LP002', 'Phòng đơn cao câp', '225000', '525000', '405000');
INSERT INTO LoaiPhong (MaLoaiPhong, TenLoaiPhong, GiaTheoGio, GiaTheoNgay, GiaQuaDem) VALUES ('LP003', 'Phòng hai giường đơn', '290000', '650000', '500000');
INSERT INTO LoaiPhong (MaLoaiPhong, TenLoaiPhong, GiaTheoGio, GiaTheoNgay, GiaQuaDem) VALUES ('LP004', 'Phòng hai giường đơn cao cấp', '435000', '975000', '750000');
INSERT INTO LoaiPhong (MaLoaiPhong, TenLoaiPhong, GiaTheoGio, GiaTheoNgay, GiaQuaDem) VALUES ('LP005', 'Phòng đôi', '290000', '650000', '500000');
INSERT INTO LoaiPhong (MaLoaiPhong, TenLoaiPhong, GiaTheoGio, GiaTheoNgay, GiaQuaDem) VALUES ('LP006', 'Phòng đôi cao cấp', '435000', '975000', '750000');
INSERT INTO LoaiPhong (MaLoaiPhong, TenLoaiPhong, GiaTheoGio, GiaTheoNgay, GiaQuaDem) VALUES ('LP007', 'Phòng hai giường đôi', '540000', '1200000', '700000');
INSERT INTO LoaiPhong (MaLoaiPhong, TenLoaiPhong, GiaTheoGio, GiaTheoNgay, GiaQuaDem) VALUES ('LP008', 'Phòng hai giường đôi cao cấp', '810000', '1800000', '1050000');
INSERT INTO LoaiPhong (MaLoaiPhong, TenLoaiPhong, GiaTheoGio, GiaTheoNgay, GiaQuaDem) VALUES ('LP009', 'Phòng ba giường đôi', '720000', '1500000', '900000');
INSERT INTO LoaiPhong (MaLoaiPhong, TenLoaiPhong, GiaTheoGio, GiaTheoNgay, GiaQuaDem) VALUES ('LP010', 'Phòng ba giường đôi cao cấp', '1080000', '2250000', '1350000');
INSERT INTO LoaiPhong (MaLoaiPhong, TenLoaiPhong, GiaTheoGio, GiaTheoNgay, GiaQuaDem) VALUES ('LP011', 'Phòng bốn giường đôi', '800000', '1600000', '1200000');
INSERT INTO LoaiPhong (MaLoaiPhong, TenLoaiPhong, GiaTheoGio, GiaTheoNgay, GiaQuaDem) VALUES ('LP012', 'Phòng bốn giường đôi cao cấp', '1200000', '2400000', '1800000');

INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Trần Bình Trọng', '012303010263', '2003-06-11', '0352621823', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Đỗ Đăng Khoa', '027816492584', '2003-12-07', '0148948948', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Đinh Mạnh Ninh', '031682494279', '1989-12-06', '0248486948', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Hà Minh Khôi', '044388592546', '1999-11-28', '0489926256', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Nguyễn Thanh Bình', '051953595761', '2003-06-11', '0584895166', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Trần Mai Hương', '064481545465', '2003-06-11', '0658914889', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Đinh Minh Chính', '076565456654', '2003-06-11', '0759592666', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Nguyễn Lan Hương', '084448489454', '2003-06-11', '0848486984', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Hà Thiên Thiên', '098854542254', '2003-06-11', '0958984498', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Đinh Thanh Bình', '018451541369', '2003-06-11', '0318589648', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Nguyễn Thị Đinh Phương', '158956568895', '1897-02-11', '0328858958', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Trần Anh Vũ', '185925485565', '2002-08-10', '0338484874', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Chu Nhật Hạ', '145856295959', '1989-11-03', '0345985959', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Võ Nhật Hạ', '118482959262', '1999-12-18', '0368946595', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Hồ Hoài Anh', '128958598958', '2003-01-19', '0372959518', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Cao Thái Sơn', '138865689484', '2003-06-23', '0388595166', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Cao Mỹ Dung', '168948565859', '2003-05-04', '0394845484', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Nguyễn Thanh Dung', '179895625265', '2003-08-02', '0218486568', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Phan Minh Hy', '198482589562', '2003-12-04', '0224894515', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Nguyễn Bảo Châu', '104856489598', '2003-11-01', '0234985548', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Phạm An Nhiên', '201848848948', '2003-02-02', '0258489615', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Nguyễn Bảo Nhật Lệ', '218565265659', '2001-03-03', '0268454854', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Huỳnh Bảo Ngọc', '228764988949', '1989-04-04', '0279596159', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Đỗ Quốc Bảo', '234848994878', '1999-05-05', '0285648951', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Lê Nhật Minh', '249856265988', '2001-06-06', '0293484648', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Phạm An Gia', '254845164896', '1999-06-07', '0919595985', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Phạm Gia Phúc', '268598565896', '1989-06-15', '0925945165', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Nguyễn Thúy An', '279598561959', '1979-06-23', '0934851545', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Phạm Diệu Linh', '289859844894', '1997-06-28', '0948998565', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Lê Đan Tú', '298978489496', '2002-06-30', '0984894484', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Trần Lê Cát Tường', '306456455454', '2003-05-11', '0712621823', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Đinh Nguyễn Đoan Trang', '316563164618', '2001-01-07', '0728948948', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Hồ Lê Anh Thư', '329884465646', '1989-03-06', '0738486948', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Võ Thiên Ân', '339528415454', '1999-02-28', '0749926256', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Nguyễn Thị Bảo Khánh', '349896596695', '2003-04-11', '0754895166', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Lê Nguyễn Khôi Nguyên', '358484651545', '2003-09-11', '0768914889', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Trần Minh Nhật', '366546564545', '2003-07-11', '0779592666', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Võ Hạo Nhiên', '372564514656', '2003-05-11', '0788486984', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Lê Thanh Phong', '388854542254', '2003-03-11', '0798984498', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Nguyễn Đông Quân', '398451541369', '2003-09-11', '0808589648', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Lê Minh Triết', '408956568895', '1897-02-11', '0818858958', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Nguyễn Mạnh Hùng', '415925485565', '2004-05-10', '0828484874', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Trần Lê Đăng Khoa', '425856295959', '1989-04-03', '0835985959', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Hồ Lê Trúc Vy', '438482959262', '1999-09-18', '0848946595', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Hồ Trọng Nghĩa', '448958598958', '2003-08-19', '0852959518', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Cao Việt Hoàng', '458865689484', '2003-05-23', '0868595166', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Cao Việt Hùng', '458865689483', '2003-05-23', '0868595166', NULL);

INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('101', 0, 'LP001');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('102', 0, 'LP003');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('103', 0, 'LP005');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('104', 0, 'LP007');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('105', 0, 'LP009');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('106', 0, 'LP011');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('201', 0, 'LP002');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('202', 0, 'LP004');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('203', 0, 'LP006');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('204', 0, 'LP008');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('205', 0, 'LP010');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('206', 0, 'LP012');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('301', 0, 'LP001');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('302', 0, 'LP003');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('303', 0, 'LP005');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('304', 0, 'LP007');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('305', 0, 'LP009');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('306', 0, 'LP011');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('401', 0, 'LP002');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('402', 0, 'LP004');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('403', 0, 'LP006');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('404', 0, 'LP008');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('405', 0, 'LP010');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('406', 0, 'LP012');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('501', 0, 'LP001');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('502', 0, 'LP003');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('503', 0, 'LP005');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('504', 0, 'LP007');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('505', 0, 'LP009');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('506', 0, 'LP011');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('601', 0, 'LP002');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('602', 0, 'LP004');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('603', 0, 'LP006');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('604', 0, 'LP008');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('605', 0, 'LP010');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('606', 0, 'LP012');

INSERT INTO ThietBi (MaThietBi, TenThietBi) VALUES ('TB001', 'Tủ lạnh');
INSERT INTO ThietBi (MaThietBi, TenThietBi) VALUES ('TB002', 'Ti vi');
INSERT INTO ThietBi (MaThietBi, TenThietBi) VALUES ('TB003', 'Máy lạnh');

INSERT INTO DichVu (MaDichVu, TenDichVu, GiaDichVu) VALUES ('DV001', 'Nhà Hàng','300000');
INSERT INTO DichVu (MaDichVu, TenDichVu, GiaDichVu) VALUES ('DV002', 'Spa','200000');
INSERT INTO DichVu (MaDichVu, TenDichVu, GiaDichVu) VALUES ('DV003', 'Hồ Bơi','50000');
INSERT INTO DichVu (MaDichVu, TenDichVu, GiaDichVu) VALUES ('DV004', 'Gym','50000');
INSERT INTO DichVu (MaDichVu, TenDichVu, GiaDichVu) VALUES ('DV005', 'Giặt, ủi','100000');


INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('101', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('101', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('101', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('102', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('102', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('102', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('103', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('103', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('103', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('104', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('104', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('104', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('105', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('105', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('105', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('106', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('106', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('106', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('201', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('201', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('201', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('202', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('202', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('202', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('203', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('203', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('203', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('204', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('204', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('204', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('205', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('205', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('205', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('206', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('206', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('206', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('301', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('301', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('301', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('302', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('302', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('302', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('303', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('303', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('303', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('304', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('304', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('304', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('305', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('305', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('305', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('306', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('306', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('306', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('401', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('401', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('401', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('402', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('402', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('402', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('403', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('403', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('403', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('404', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('404', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('404', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('405', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('405', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('405', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('406', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('406', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('406', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('501', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('501', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('501', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('502', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('502', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('502', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('503', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('503', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('503', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('504', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('504', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('504', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('505', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('505', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('505', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('506', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('506', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('506', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('601', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('601', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('601', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('602', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('602', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('602', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('603', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('603', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('603', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('604', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('604', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('604', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('605', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('605', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('605', 'TB003', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('606', 'TB001', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('606', 'TB002', 'Tốt');
INSERT INTO PhongCoThietBi (MaPhong, MaThietBi, HienTrang) VALUES ('606', 'TB003', 'Tốt');

INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-16', '2023-05-30 07:00:00', '2023-05-31 07:00:00', '1', 'Ngày', 'Chưa nhận phòng', '1', '2', '101');
INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-20', '2023-05-29 08:00:00', '2023-06-01 08:00:00', '2', 'Ngày', 'Chưa nhận phòng', '2', '3', '103');
INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-20', '2023-05-25 21:00:00', '2023-05-26 12:00:00', '1', 'Đêm', 'Chưa nhận phòng', '3', '2', '201');
INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-20', '2023-05-25 09:00:00', '2023-05-25 12:00:00', '5', 'Giờ', 'Chưa nhận phòng', '4', '4', '205');
INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-13', '2023-05-13 13:00:00', '2023-05-19 13:00:00', '2', 'Ngày', 'Chưa nhận phòng', '5', '2', '302');
INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-21', '2023-05-23 07:30:00', '2023-05-23 10:30:00', '8', 'Giờ', 'Chưa nhận phòng', '6', '4', '306');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-16', '2023-05-16 21:00:00', '2023-05-17 12:00:00', '1', 'Đêm', 'Đã trả phòng', '7', '3', '401');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-22', '2023-05-25 21:00:00', '2023-05-26 12:00:00', '1', 'Đêm', 'Chưa nhận phòng', '8', '4', '501');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-21', '2023-05-23 13:00:00', '2023-05-29 13:00:00', '2', 'Ngày', 'Đã nhận phòng', '9', '2', '602');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-22', '2023-05-23 16:00:00', '2023-05-26 16:00:00', '3', 'Ngày', 'Đã nhận phòng', '10', '2', '604');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-23', '2023-05-26 16:00:00', '2023-05-26 20:00:00', '2', 'Giờ', 'Chưa nhận phòng', '11', '4', '502');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-20', '2023-05-21 05:00:00', '2023-05-29 05:00:00', '4', 'Ngày', 'Đã nhận phòng', '12', '3', '104');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-20', '2023-05-21 08:00:00', '2023-05-27 08:00:00', '7', 'Ngày', 'Đã nhận phòng', '13', '2', '506');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-21', '2023-05-22 10:00:00', '2023-05-26 10:00:00', '1', 'Ngày', 'Đã nhận phòng', '14', '3', '303');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-20', '2023-05-23 21:00:00', '2023-05-24 12:00:00', '2', 'Đêm', 'Đã nhận phòng', '15', '4', '203');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-22', '2023-05-23 20:00:00', '2023-05-23 22:00:00', '8', 'Giờ', 'Đã nhận phòng', '16', '3', '406');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-23', '2023-05-27 20:00:00', '2023-05-27 23:00:00', '2', 'Giờ', 'Chưa nhận phòng', '17', '2', '603');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-21', '2023-05-23 21:00:00', '2023-05-23 23:00:00', '3', 'Giờ', 'Đã nhận phòng', '18', '4', '404');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-23', '2023-05-25 07:30:00', '2023-05-29 07:30:00', '7', 'Ngày', 'Đã hủy phòng', '19', '3', '106');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-20', '2023-05-21 21:00:00', '2023-05-22 12:00:00', '4', 'Đêm', 'Đã trả phòng', '20', '2', '504');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-19', '2023-05-23 21:00:00', '2023-05-24 12:00:00', '3', 'Đêm', 'Đã nhận phòng', '21', '3', '304');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-21', '2023-05-23 21:00:00', '2023-05-24 12:00:00', '4', 'Đêm', 'Đã nhận phòng', '22', '4', '204');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-22', '2023-05-25 20:00:00', '2023-05-29 20:00:00', '7', 'Ngày', 'Chưa nhận phòng', '23', '2', '606');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-19', '2023-05-20 14:00:00', '2023-05-26 14:00:00', '2', 'Ngày', 'Đã nhận phòng', '24', '3', '403');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-20', '2023-05-23 07:00:00', '2023-05-29 07:00:00', '2', 'Ngày', 'Đã nhận phòng', '25', '2', '202');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-21', '2023-05-23 10:00:00', '2023-05-23 14:00:00', '1', 'Giờ', 'Đã nhận phòng', '26', '4', '503');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-20', '2023-05-26 11:00:00', '2023-05-29 14:00:00', '2', 'Giờ', 'Chưa nhận phòng', '27', '3', '102');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-21', '2023-05-23 18:00:00', '2023-05-23 23:00:00', '5', 'Giờ', 'Đã nhận phòng', '28', '4', '505');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-22', '2023-05-24 19:00:00', '2023-05-25 22:00:00', '2', 'Giờ', 'Đã hủy phòng', '29', '2', '402');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-09', '2023-05-10 16:00:00', '2023-05-21 16:00:00', '6', 'Ngày', 'Đã trả phòng', '30', '3', '305');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-14', '2023-05-14 08:00:00', '2023-05-19 08:00:00', '2', 'Ngày', 'Đã trả phòng', '31', '3', '103');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-12', '2023-05-18 21:00:00', '2023-05-19 12:00:00', '1', 'Đêm', 'Đã trả phòng', '32', '2', '201');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-15', '2023-05-16 09:00:00', '2023-05-16 12:00:00', '5', 'Giờ', 'Đã trả phòng', '33', '4', '205');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-13', '2023-05-16 07:30:00', '2023-05-16 10:30:00', '8', 'Giờ', 'Đã trả phòng', '34', '4', '306');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-15', '2023-05-15 21:00:00', '2023-05-16 12:00:00', '1', 'Đêm', 'Đã trả phòng', '35', '4', '501');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-05', '2023-05-09 13:00:00', '2023-05-19 13:00:00', '2', 'Ngày', 'Đã trả phòng', '36', '2', '602');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-07', '2023-05-08 16:00:00', '2023-05-17 16:00:00', '3', 'Ngày', 'Đã trả phòng', '37', '2', '604');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-10', '2023-05-16 16:00:00', '2023-05-16 20:00:00', '2', 'Giờ', 'Đã trả phòng', '38', '4', '502');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-08', '2023-05-12 05:00:00', '2023-05-19 05:00:00', '4', 'Ngày', 'Đã trả phòng', '39', '3', '104');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-09', '2023-05-14 08:00:00', '2023-05-19 08:00:00', '7', 'Ngày', 'Đã trả phòng', '40', '2', '506');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-10', '2023-05-12 10:00:00', '2023-05-18 10:00:00', '1', 'Ngày', 'Đã trả phòng', '41', '3', '303');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-14', '2023-05-16 21:00:00', '2023-05-17 12:00:00', '2', 'Đêm', 'Đã trả phòng', '42', '4', '203');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-15', '2023-05-16 20:00:00', '2023-05-16 22:00:00', '8', 'Giờ', 'Đã trả phòng', '43', '3', '406');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-16', '2023-05-16 20:00:00', '2023-05-16 23:00:00', '2', 'Giờ', 'Đã trả phòng', '44', '2', '603');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-12', '2023-05-16 21:00:00', '2023-05-16 23:00:00', '3', 'Giờ', 'Đã trả phòng', '45', '4', '404');
-- INSERT INTO PhieuThue (NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('2023-05-09', '2023-05-10 07:30:00', '2023-05-17 07:30:00', '7', 'Ngày', 'Đã trả phòng', '46', '3', '106');

-- INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TienPhong, TienDichVu, MaKhachHang, MaNhanVien, MaPhieu) VALUES ('HD001', '2023-05-18', '700000', '600000', '1', '8', '1');
-- INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TienPhong, TienDichVu, MaKhachHang, MaNhanVien, MaPhieu) VALUES ('HD002', '2023-05-19', '3250000', '50000', '31', '9', '31');
-- INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TienPhong, TienDichVu, MaKhachHang, MaNhanVien, MaPhieu) VALUES ('HD003', '2023-05-19', '405000', '300000', '32', '10', '32');
-- INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TienPhong, TienDichVu, MaKhachHang, MaNhanVien, MaPhieu) VALUES ('HD004', '2023-05-16', '3240000', '350000', '33', '9', '33');
-- INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TienPhong, TienDichVu, MaKhachHang, MaNhanVien, MaPhieu) VALUES ('HD005', '2023-05-19', '3900000', '450000', '5', '10', '5');
-- INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TienPhong, TienDichVu, MaKhachHang, MaNhanVien, MaPhieu) VALUES ('HD006', '2023-05-16', '2400000', '300000', '34', '8', '34');
-- INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TienPhong, TienDichVu, MaKhachHang, MaNhanVien, MaPhieu) VALUES ('HD007', '2023-05-16', '405000', '50000', '7', '9', '7');
-- INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TienPhong, TienDichVu, MaKhachHang, MaNhanVien, MaPhieu) VALUES ('HD008', '2023-05-16', '270000', '50000', '35', '8', '35');
-- INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TienPhong, TienDichVu, MaKhachHang, MaNhanVien, MaPhieu) VALUES ('HD009', '2023-05-19', '9750000', NULL, '36', '8', '36');
-- INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TienPhong, TienDichVu, MaKhachHang, MaNhanVien, MaPhieu) VALUES ('HD010', '2023-05-17', '16200000', '300000', '37', '10', '37');
-- INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TienPhong, TienDichVu, MaKhachHang, MaNhanVien, MaPhieu) VALUES ('HD011', '2023-05-16', '1160000', '100000', '38', '8', '38');
-- INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TienPhong, TienDichVu, MaKhachHang, MaNhanVien, MaPhieu) VALUES ('HD012', '2023-05-19', '8400000', '100000', '39', '9', '39');
-- INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TienPhong, TienDichVu, MaKhachHang, MaNhanVien, MaPhieu) VALUES ('HD013', '2023-05-19', '8000000', '300000', '40', '9', '40');
-- INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TienPhong, TienDichVu, MaKhachHang, MaNhanVien, MaPhieu) VALUES ('HD014', '2023-05-18', '3900000', NULL, '41', '8', '41');
-- INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TienPhong, TienDichVu, MaKhachHang, MaNhanVien, MaPhieu) VALUES ('HD015', '2023-05-17', '750000', NULL, '42', '10', '42');
-- INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TienPhong, TienDichVu, MaKhachHang, MaNhanVien, MaPhieu) VALUES ('HD016', '2023-05-16', '2400000', NULL, '43', '10', '43');
-- INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TienPhong, TienDichVu, MaKhachHang, MaNhanVien, MaPhieu) VALUES ('HD017', '2023-05-16', '1305000', NULL, '44', '10', '44');
-- INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TienPhong, TienDichVu, MaKhachHang, MaNhanVien, MaPhieu) VALUES ('HD018', '2023-05-16', '1620000', '600000', '45', '9', '45');
-- INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TienPhong, TienDichVu, MaKhachHang, MaNhanVien, MaPhieu) VALUES ('HD019', '2023-05-17', '11200000', NULL, '46', '9', '46');
-- INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TienPhong, TienDichVu, MaKhachHang, MaNhanVien, MaPhieu) VALUES ('HD020', '2023-05-22', '700000', '400000', '20', '9', '20');
-- INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TienPhong, TienDichVu, MaKhachHang, MaNhanVien, MaPhieu) VALUES ('HD021', '2023-05-21', '16500000', '500000', '30', '9', '30');

-- INSERT INTO ChiTietHoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD001', 'DV001');
-- INSERT INTO ChiTietHoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD001', 'DV005');
-- INSERT INTO ChiTietHoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD001', 'DV002');
-- INSERT INTO ChiTietHoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD002', 'DV004');
-- INSERT INTO ChiTietHoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD003', 'DV001');
-- INSERT INTO ChiTietHoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD004', 'DV003');
-- INSERT INTO ChiTietHoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD004', 'DV001');
-- INSERT INTO ChiTietHoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD005', 'DV005');
-- INSERT INTO ChiTietHoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD005', 'DV003');
-- INSERT INTO ChiTietHoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD005', 'DV001');
-- INSERT INTO ChiTietHoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('6', 'HD006', 'DV004');
-- INSERT INTO ChiTietHoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD007', 'DV003');
-- INSERT INTO ChiTietHoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD008', 'DV003');
-- INSERT INTO ChiTietHoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD010', 'DV001');
-- INSERT INTO ChiTietHoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD011', 'DV004');
-- INSERT INTO ChiTietHoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD011', 'DV003');
-- INSERT INTO ChiTietHoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD012', 'DV005');
-- INSERT INTO ChiTietHoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD013', 'DV001');
-- INSERT INTO ChiTietHoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('3', 'HD018', 'DV002');
-- INSERT INTO ChiTietHoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('2', 'HD020', 'DV003');
-- INSERT INTO ChiTietHoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('3', 'HD020', 'DV005');
-- INSERT INTO ChiTietHoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD021', 'DV001');
-- INSERT INTO ChiTietHoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('4', 'HD021', 'DV003');