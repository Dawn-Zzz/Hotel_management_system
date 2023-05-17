CREATE DATABASE Hotel_management;
USE Hotel_management;

CREATE TABLE `User`(
	ID NVARCHAR(50) NOT NULL,
    Pass NVARCHAR(50) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE ChucDanh(
	MaChucDanh CHAR(10) NOT NULL,
    TenChucDanh NVARCHAR(50) NOT NULL,
    PRIMARY KEY (MaChucDanh)
);

CREATE TABLE NhanVien(
	MaNhanVien INT AUTO_INCREMENT,
	CCCD CHAR(12) NOT NULL,
    SoDienThoai CHAR (10),
    TenNhanVien NVARCHAR(50) NOT NULL,
    NgaySinh DATE,
    ID NVARCHAR(50) NOT NULL,
    MaChucDanh CHAR(10) NOT NULL,
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
    HienTrang NVARCHAR(20) NOT NULL,
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

CREATE TABLE HoaDon(
	MaHoaDon CHAR(10) NOT NULL,
    NgayLapHoaDon DATE NOT NULL,
    TongTienPhong FLOAT NOT NULL,
    TongTienDichVu FLOAT,
    TongTien FLOAT NOT NULL,
    MaKhachHang INT NOT NULL,
    MaNhanVien INT NOT NULL,
    PRIMARY KEY (MaHoaDon)
);

CREATE TABLE PhongCoThietBi(
	MaThietBi CHAR(10) NOT NULL,
    MaPhong CHAR(10) NOT NULL,
    HienTrang NVARCHAR(20) NOT NULL
);

CREATE TABLE PhieuThuePhong(
	MaPhieu CHAR(10) NOT NULL,
    NgayLap DATE NOT NULL,
    ThoiGianNhanPhong DATETIME NOT NULL,
    ThoiGianTraPhong DATETIME NOT NULL,
    SoNguoiO INT NOT NULL,
    TienCoc FLOAT,
    HinhThucThue NVARCHAR(50) NOT NULL,
    HienTrang NVARCHAR(50) NOT NULL,
    MaKhachHang INT NOT NULL,
    MaNhanVien INT NOT NULL, 
    MaPhong CHAR(10) NOT NULL, 
    PRIMARY KEY (MaPhieu)
);

CREATE TABLE HoaDonDichVu(
	SoLuong INT NOT NULL,
	MaHoaDon CHAR(10) NOT NULL,
    MaDichVu CHAR(10) NOT NULL
);

CREATE TABLE HoaDonPhong(
	MaHoaDon CHAR(10) NOT NULL,
    MaPhong CHAR(10) NOT NULL
);

-- CREATE TABLE HinhThucThue(
-- 	MaHinhThucThue CHAR(10) NOT NULL,
-- 	TenHinhThucThue NVARCHAR(50) NOT NULL,
--     Gia FLOAT NOT NULL,
--     MaLoaiPhong CHAR(10) NOT NULL,
--     PRIMARY KEY (MaHinhThucThue)
-- );

-- Tạo khóa ngoại cho các thực thể
ALTER TABLE NhanVien
ADD FOREIGN KEY (ID) REFERENCES `User`(ID);
ALTER TABLE NhanVien
ADD FOREIGN KEY (MaChucDanh) REFERENCES ChucDanh(MaChucDanh);
ALTER TABLE Phong 
ADD FOREIGN KEY (MaLoaiPhong) REFERENCES LoaiPhong(MaLoaiPhong);
ALTER TABLE HoaDon 
ADD FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang);
ALTER TABLE HoaDon 
ADD FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien);
ALTER TABLE PhongCoThietBi
ADD FOREIGN KEY (MaThietBi) REFERENCES ThietBi(MaThietBi);
ALTER TABLE PhongCoThietBi
ADD FOREIGN KEY (MaPhong) REFERENCES Phong(MaPhong);
ALTER TABLE PhieuThuePhong 
ADD FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang);
ALTER TABLE PhieuThuePhong 
ADD FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien);
ALTER TABLE PhieuThuePhong
ADD FOREIGN KEY (MaPhong) REFERENCES Phong(MaPhong);
ALTER TABLE HoaDonDichVu
ADD FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon);
ALTER TABLE HoaDonDichVu
ADD FOREIGN KEY (MaDichVu) REFERENCES DichVu(MaDichVu);
ALTER TABLE HoaDonPhong
ADD FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon);
ALTER TABLE HoaDonPhong
ADD FOREIGN KEY (MaPhong) REFERENCES Phong(MaPhong);
-- ALTER TABLE HinhThucThue
-- ADD FOREIGN KEY (MaLoaiPhong) REFERENCES LoaiPhong(MaLoaiPhong);

-- Tạo ràng buộc cho các quan hệ 

-- ALTER TABLE NhanVien ADD CONSTRAINT NV_NgaySinh CHECK (NgaySinh<=sysdate());
-- ALTER TABLE LoaiPhong ADD CONSTRAINT LP_GiaLoaiPhong CHECK(GiaLoaiPhong>0);
-- ALTER TABLE DichVu ADD CONSTRAINT DV_GiaDichVu CHECK(GiaDichVu>0);
-- ALTER TABLE PhieuThuePhong ADD CONSTRAINT PTP_NgayLap CHECK(NgayLap<=sysdate());
-- ALTER TABLE PhieuThuePhong ADD CONSTRAINT PTP_SoNguoiO CHECK(SoNguoiO>0);
-- ALTER TABLE PhieuThuePhong ADD CONSTRAINT PTP_SoLuongPhong CHECK(SoLuongPhong>0);
-- ALTER TABLE PhieuThuePhong ADD CONSTRAINT PTP_TienCoc CHECK(TienCoc>=0);
-- ALTER TABLE HoaDonChiTiet ADD CONSTRAINT HDCT_SoLuongPhong CHECK(SoLuongPhong>0);
-- ALTER TABLE HoaDonChiTiet ADD CONSTRAINT HDCT_SoLuongDichVu CHECK(SoLuongDichVu>=0);
-- ALTER TABLE HoaDonChiTiet ADD CONSTRAINT HDCT_TienPhong CHECK(TienPhong>0);
-- ALTER TABLE HoaDonChiTiet ADD CONSTRAINT HDCT_TienDichVu CHECK(TienDichVu>0);

-- Thêm dữ liệu cho bảng 

INSERT INTO `User` (ID, Pass) VALUES ('TK001', 'gd001');
INSERT INTO `User` (ID, Pass) VALUES ('TK002', 'lt002');
INSERT INTO `User` (ID, Pass) VALUES ('TK003', 'pv003');
INSERT INTO `User` (ID, Pass) VALUES ('TK004', 'kt004');

INSERT INTO ChucDanh (MaChucDanh, TenChucDanh) VALUE ('CD001', 'Giám đốc');
INSERT INTO ChucDanh (MaChucDanh, TenChucDanh) VALUE ('CD002', 'Nhân viên lễ tân');
INSERT INTO ChucDanh (MaChucDanh, TenChucDanh) VALUE ('CD003', 'Nhân viên phục vụ');
INSERT INTO ChucDanh (MaChucDanh, TenChucDanh) VALUE ('CD004', 'Nhân viên kế toán');

INSERT INTO NhanVien (CCCD, SoDienThoai, TenNhanVien, NgaySinh, ID, MaChucDanh) VALUES ('312848541545', '0384845484', 'Nguyễn Thanh Tùng', '2014-12-03','TK001', 'CD001');
INSERT INTO NhanVien (CCCD, SoDienThoai, TenNhanVien, NgaySinh, ID, MaChucDanh) VALUES ('325484544487', '0352819756', 'Nguyễn Linh Chi', '2001-11-09','TK002', 'CD002');
INSERT INTO NhanVien (CCCD, SoDienThoai, TenNhanVien, NgaySinh, ID, MaChucDanh) VALUES ('338897894465', '0335852676', 'Lê Trung Kiên', '2002-10-08','TK002', 'CD002');
INSERT INTO NhanVien (CCCD, SoDienThoai, TenNhanVien, NgaySinh, ID, MaChucDanh) VALUES ('348784867879', '0359518415', 'Trần Thị Linh', '2003-07-11','TK002', 'CD002');
INSERT INTO NhanVien (CCCD, SoDienThoai, TenNhanVien, NgaySinh, ID, MaChucDanh) VALUES ('358998746564', '0341595625', 'Lê Thanh Hoa', '2002-07-12','TK003', 'CD003');
INSERT INTO NhanVien (CCCD, SoDienThoai, TenNhanVien, NgaySinh, ID, MaChucDanh) VALUES ('368965568995', '0348485156', 'Trương Tịnh Nghi', '1987-04-04','TK003', 'CD003');
INSERT INTO NhanVien (CCCD, SoDienThoai, TenNhanVien, NgaySinh, ID, MaChucDanh) VALUES ('374556446545', '0384876557', 'Hồ Minh Hải', '1997-01-14','TK003', 'CD003');
INSERT INTO NhanVien (CCCD, SoDienThoai, TenNhanVien, NgaySinh, ID, MaChucDanh) VALUES ('386262316515', '0347847846', 'Phạm Hoài Sơn', '1999-05-03','TK004', 'CD004');
INSERT INTO NhanVien (CCCD, SoDienThoai, TenNhanVien, NgaySinh, ID, MaChucDanh) VALUES ('394985151561', '0359597166', 'Trần Thanh Khoa', '2000-09-11','TK004', 'CD004');
INSERT INTO NhanVien (CCCD, SoDienThoai, TenNhanVien, NgaySinh, ID, MaChucDanh) VALUES ('303265315155', '0384898448', 'Phạm Minh Hùng', '2000-08-07','TK004', 'CD004');

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
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Đỗ Đăng Khoa', '027816492584', '2004-12-07', '0148948948', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Đinh Mạnh Ninh', '031682494279', '1989-12-06', '0248486948', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Hà Minh Khôi', '044388592546', '1999-11-28', '0489926256', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Nguyễn Thanh Bình', '051953595761', '2003-06-11', '0584895166', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Trần Mai Hương', '064481545465', '2003-06-11', '0658914889', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Đinh Minh Chính', '076565456654', '2003-06-11', '0759592666', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Nguyễn Lan Hương', '084448489454', '2003-06-11', '0848486984', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Hà Thiên Thiên', '098854542254', '2003-06-11', '0958984498', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Đinh Thanh Bình', '018451541369', '2003-06-11', '0318589648', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Nguyễn Thị Đinh Phương', '158956568895', '1897-02-11', '0328858958', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Trần Anh Vũ', '185925485565', '2004-08-10', '0338484874', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Chu Nhật Hạ', '145856295959', '1989-11-03', '0345985959', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Võ Nhật Hạ', '118482959262', '1999-12-18', '0368946595', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Hồ Hoài Anh', '128958598958', '2003-01-19', '0372959518', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Cao Thái Sơn', '138865689484', '2003-06-23', '0388595166', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Cao Mỹ Dung', '168948565859', '2003-05-04', '0394845484', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Nguyễn Thanh Dung', '179895625265', '2003-08-02', '0218486568', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Phan Minh Hy', '198482589562', '2003-12-04', '0224894515', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Nguyễn Bảo Châu', '104856489598', '2003-11-01', '0234985548', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Phạm An Nhiên', '201848848948', '2003-02-02', '0258489615', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Nguyễn Bảo Nhật Lệ', '218565265659', '2004-03-03', '0268454854', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Huỳnh Bảo Ngọc', '228764988949', '1989-04-04', '0279596159', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Đỗ Quốc Bảo', '234848994878', '1999-05-05', '0285648951', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Lê Nhật Minh', '249856265988', '2001-06-06', '0293484648', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Phạm An Gia', '254845164896', '1999-06-07', '0919595985', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Phạm Gia Phúc', '268598565896', '1989-06-15', '0925945165', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Nguyễn Thúy An', '279598561959', '1979-06-23', '0934851545', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Phạm Diệu Linh', '289859844894', '1997-06-28', '0948998565', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Lê Đan Tú', '298978489496', '2002-06-30', '0984894484', NULL);
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Trần Lê Cát Tường', '306456455454', '2003-05-11', '0712621823', 'Vip');
INSERT INTO KhachHang (TenKhachHang, CCCD, NgaySinh, SoDienThoai, LoaiKhachHang) VALUES ('Đinh Nguyễn Đoan Trang', '316563164618', '2004-01-07', '0728948948', NULL);
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

INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('101', '0', 'LP001');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('102', '1', 'LP003');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('103', '1', 'LP005');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('104', '1', 'LP007');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('105', '0', 'LP009');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('106', '0', 'LP011');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('201', '0', 'LP002');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('202', '1', 'LP004');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('203', '1', 'LP006');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('204', '1', 'LP008');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('205', '1', 'LP010');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('206', '0', 'LP012');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('301', '0', 'LP001');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('302', '0', 'LP003');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('303', '1', 'LP005');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('304', '1', 'LP007');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('305', '0', 'LP009');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('306', '1', 'LP011');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('401', '0', 'LP002');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('402', '0', 'LP004');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('403', '1', 'LP006');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('404', '1', 'LP008');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('405', '0', 'LP010');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('406', '1', 'LP012');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('501', '1', 'LP001');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('502', '1', 'LP003');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('503', '1', 'LP005');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('504', '0', 'LP007');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('505', '1', 'LP009');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('506', '1', 'LP011');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('601', '0', 'LP002');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('602', '1', 'LP004');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('603', '1', 'LP006');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('604', '1', 'LP008');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('605', '0', 'LP010');
INSERT INTO Phong (MaPhong, HienTrang, MaLoaiPhong) VALUES ('606', '1', 'LP012');

INSERT INTO ThietBi (MaThietBi, TenThietBi) VALUES ('TB001', 'Tủ lạnh');
INSERT INTO ThietBi (MaThietBi, TenThietBi) VALUES ('TB002', 'Ti vi');
INSERT INTO ThietBi (MaThietBi, TenThietBi) VALUES ('TB003', 'Máy lạnh');

INSERT INTO DichVu (MaDichVu, TenDichVu, GiaDichVu) VALUES ('DV001', 'Nhà Hàng','300000');
INSERT INTO DichVu (MaDichVu, TenDichVu, GiaDichVu) VALUES ('DV002', 'Spa','200000');
INSERT INTO DichVu (MaDichVu, TenDichVu, GiaDichVu) VALUES ('DV003', 'Hồ Bơi','50000');
INSERT INTO DichVu (MaDichVu, TenDichVu, GiaDichVu) VALUES ('DV004', 'Gym','50000');
INSERT INTO DichVu (MaDichVu, TenDichVu, GiaDichVu) VALUES ('DV005', 'Giặt, ủi','100000');


-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT001', 'Phòng đơn, giờ', '150000', 'LP001');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT002', 'Phòng hai giường đơn, giờ ', '290000', 'LP003');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT003', 'Phòng đôi, giờ', '290000', 'LP005');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT004', 'Phòng hai giường đôi, giờ', '540000', 'LP007');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT005', 'Phòng ba giường đôi, giờ', '720000', 'LP009');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT006', 'Phòng bốn giường đôi, giờ', '800000', 'LP011');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT007', 'Phòng đơn cao cấp, giờ', '225000', 'LP002');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT008', 'Phòng hai giường đơn cao cấp, giờ', '435000', 'LP004');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT009', 'Phòng đôi cao cấp, giờ', '435000', 'LP006');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT010', 'Phòng hai giường đôi cao cấp, giờ', '810000', 'LP008');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT011', 'Phòng ba giường đôi cao cấp, giờ', '1080000', 'LP010');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT012', 'Phòng bốn giường đôi cao cấp, giờ', '1200000', 'LP012');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT013', 'Phòng đơn, ngày', '350000', 'LP001');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT014', 'Phòng hai giường đơn, ngày ', '650000', 'LP003');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT015', 'Phòng đôi, ngày', '650000', 'LP005');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT016', 'Phòng hai giường đôi, ngày', '1200000', 'LP007');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT017', 'Phòng ba giường đôi, ngày', '1500000', 'LP009');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT018', 'Phòng bốn giường đôi, ngày', '1600000', 'LP011');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT019', 'Phòng đơn cao cấp, ngày', '525000', 'LP002');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT020', 'Phòng hai giường đơn cao cấp, ngày', '975000', 'LP004');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT021', 'Phòng đôi cao cấp, ngày', '975000', 'LP006');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT022', 'Phòng hai giường đôi cao cấp, ngày', '1800000', 'LP008');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT023', 'Phòng ba giường đôi cao cấp, ngày', '2250000', 'LP010');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT024', 'Phòng bốn giường đôi cao cấp, ngày', '2400000', 'LP012');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT025', 'Phòng đơn, đêm', '270000', 'LP001');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT026', 'Phòng hai giường đơn, đêm ', '500000', 'LP003');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT027', 'Phòng đôi, đêm', '500000', 'LP005');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT028', 'Phòng hai giường đôi, đêm', '700000', 'LP007');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT029', 'Phòng ba giường đôi, đêm', '900000', 'LP009');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT030', 'Phòng bốn giường đôi, đêm', '1200000', 'LP011');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT031', 'Phòng đơn cao cấp, đêm', '405000', 'LP002');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT032', 'Phòng hai giường đơn cao cấp, đêm', '750000', 'LP004');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT033', 'Phòng đôi cao cấp, đêm', '750000', 'LP006');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT034', 'Phòng hai giường đôi cao cấp, đêm', '1050000', 'LP008');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT035', 'Phòng ba giường đôi cao cấp, đêm', '1350000', 'LP010');
-- INSERT INTO HinhThucThue (MaHinhThucThue, TenHinhThucThue, Gia, MaLoaiPhong) VALUES ('HTT036', 'Phòng bốn giường đôi cao cấp, đêm', '1800000', 'LP012');


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

INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT001', '2023-05-16', '2023-05-16 07:00:00', '2023-05-18 07:00:00', '1', NULL, 'Ngày', 'Đã trả phòng', '1', '2', '101');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT002', '2023-05-20', '2023-05-24 08:00:00', '2023-05-28 08:00:00', '2', NULL, 'Ngày', 'Chưa nhận phòng', '2', '3', '103');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT003', '2023-05-20', '2023-05-25 21:00:00', '2023-05-26 12:00:00', '1', NULL, 'Đêm', 'Đã hủy phòng', '3', '2', '201');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT004', '2023-05-20', '2023-05-25 09:00:00', '2023-05-25 12:00:00', '5', NULL, 'Giờ', 'Chưa nhận phòng', '4', '4', '205');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT005', '2023-05-14', '2023-05-13 13:00:00', '2023-05-19 13:00:00', '2', NULL, 'Ngày', 'Đã trả phòng', '5', '2', '302');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT006', '2023-05-21', '2023-05-23 07:30:00', '2023-05-23 10:30:00', '8', NULL, 'Giờ', 'Đã nhận phòng', '6', '4', '306');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT007', '2023-05-16', '2023-05-16 21:00:00', '2023-05-17 12:00:00', '1', NULL, 'Đêm', 'Đã trả phòng', '7', '3', '401');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT008', '2023-05-22', '2023-05-25 21:00:00', '2023-05-26 12:00:00', '1', NULL, 'Đêm', 'Chưa nhận phòng', '8', '4', '501');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT009', '2023-05-21', '2023-05-23 13:00:00', '2023-05-29 13:00:00', '2', '200000', 'Ngày', 'Đã nhận phòng', '9', '2', '602');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT010', '2023-05-22', '2023-05-23 16:00:00', '2023-05-26 16:00:00', '3', '300000', 'Ngày', 'Đã nhận phòng', '10', '2', '604');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT011', '2023-05-23', '2023-05-26 16:00:00', '2023-05-26 20:00:00', '2', '100000', 'Giờ', 'Chưa nhận phòng', '11', '4', '502');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT012', '2023-05-20', '2023-05-21 05:00:00', '2023-05-29 05:00:00', '4', '500000', 'Ngày', 'Đã nhận phòng', '12', '3', '104');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT013', '2023-05-20', '2023-05-21 08:00:00', '2023-05-27 08:00:00', '7', '100000', 'Ngày', 'Đã nhận phòng', '13', '2', '506');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT014', '2023-05-21', '2023-05-22 10:00:00', '2023-05-26 10:00:00', '1', NULL, 'Ngày', 'Đã nhận phòng', '14', '3', '303');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT015', '2023-05-20', '2023-05-23 21:00:00', '2023-05-24 12:00:00', '2', NULL, 'Đêm', 'Đã nhận phòng', '15', '4', '203');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT016', '2023-05-22', '2023-05-23 20:00:00', '2023-05-23 22:00:00', '8', NULL, 'Giờ', 'Đã nhận phòng', '16', '3', '406');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT017', '2023-05-23', '2023-05-27 20:00:00', '2023-05-27 23:00:00', '2', NULL, 'Giờ', 'Chưa nhận phòng', '17', '2', '603');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT018', '2023-05-21', '2023-05-23 21:00:00', '2023-05-23 23:00:00', '3', NULL, 'Giờ', 'Đã nhận phòng', '18', '4', '404');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT019', '2023-05-23', '2023-05-25 07:30:00', '2023-05-29 07:30:00', '7', NULL, 'Ngày', 'Đã hủy phòng', '19', '3', '106');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT020', '2023-05-20', '2023-05-21 21:00:00', '2023-05-22 12:00:00', '4', NULL, 'Đêm', 'Đã trả phòng', '20', '2', '504');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT021', '2023-05-19', '2023-05-23 21:00:00', '2023-05-24 12:00:00', '3', '100000', 'Đêm', 'Đã nhận phòng', '21', '3', '304');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT022', '2023-05-21', '2023-05-23 21:00:00', '2023-05-24 12:00:00', '4', NULL, 'Đêm', 'Đã nhận phòng', '22', '4', '204');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT023', '2023-05-22', '2023-05-25 20:00:00', '2023-05-29 20:00:00', '7', '500000', 'Ngày', 'Chưa nhận phòng', '23', '2', '606');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT024', '2023-05-19', '2023-05-20 14:00:00', '2023-05-26 14:00:00', '2', NULL, 'Ngày', 'Đã nhận phòng', '24', '3', '403');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT025', '2023-05-20', '2023-05-23 07:00:00', '2023-05-29 07:00:00', '2', NULL, 'Ngày', 'Đã nhận phòng', '25', '2', '202');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT026', '2023-05-21', '2023-05-23 10:00:00', '2023-05-23 14:00:00', '1', NULL, 'Giờ', 'Đã nhận phòng', '26', '4', '503');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT027', '2023-05-20', '2023-05-26 11:00:00', '2023-05-29 14:00:00', '2', NULL, 'Giờ', 'Chưa nhận phòng', '27', '3', '102');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT028', '2023-05-21', '2023-05-23 18:00:00', '2023-05-23 23:00:00', '5', NULL, 'Giờ', 'Đã nhận phòng', '28', '4', '505');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT029', '2023-05-22', '2023-05-24 19:00:00', '2023-05-25 22:00:00', '2', NULL, 'Giờ', 'Đã hủy phòng', '29', '2', '402');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT030', '2023-05-09', '2023-05-10 16:00:00', '2023-05-21 16:00:00', '6', NULL, 'Ngày', 'Đã trả phòng', '30', '3', '305');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT031', '2023-05-14', '2023-05-14 08:00:00', '2023-05-19 08:00:00', '2', NULL, 'Ngày', 'Đã trả phòng', '31', '3', '103');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT032', '2023-05-12', '2023-05-18 21:00:00', '2023-05-19 12:00:00', '1', NULL, 'Đêm', 'Đã trả phòng', '32', '2', '201');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT033', '2023-05-15', '2023-05-16 09:00:00', '2023-05-16 12:00:00', '5', NULL, 'Giờ', 'Đã trả phòng', '33', '4', '205');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT034', '2023-05-13', '2023-05-16 07:30:00', '2023-05-16 10:30:00', '8', NULL, 'Giờ', 'Đã trả phòng', '34', '4', '306');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT035', '2023-05-15', '2023-05-15 21:00:00', '2023-05-16 12:00:00', '1', NULL, 'Đêm', 'Đã trả phòng', '35', '4', '501');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT036', '2023-05-05', '2023-05-09 13:00:00', '2023-05-19 13:00:00', '2', '200000', 'Ngày', 'Đã trả phòng', '36', '2', '602');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT037', '2023-05-07', '2023-05-08 16:00:00', '2023-05-17 16:00:00', '3', '300000', 'Ngày', 'Đã trả phòng', '37', '2', '604');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT038', '2023-05-10', '2023-05-16 16:00:00', '2023-05-16 20:00:00', '2', '100000', 'Giờ', 'Đã trả phòng', '38', '4', '502');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT039', '2023-05-08', '2023-05-12 05:00:00', '2023-05-19 05:00:00', '4', '500000', 'Ngày', 'Đã trả phòng', '39', '3', '104');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT040', '2023-05-09', '2023-05-14 08:00:00', '2023-05-19 08:00:00', '7', '100000', 'Ngày', 'Đã trả phòng', '40', '2', '506');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT041', '2023-05-10', '2023-05-12 10:00:00', '2023-05-18 10:00:00', '1', NULL, 'Ngày', 'Đã trả phòng', '41', '3', '303');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT042', '2023-05-14', '2023-05-16 21:00:00', '2023-05-17 12:00:00', '2', NULL, 'Đêm', 'Đã trả phòng', '42', '4', '203');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT043', '2023-05-15', '2023-05-16 20:00:00', '2023-05-16 22:00:00', '8', NULL, 'Giờ', 'Đã trả phòng', '43', '3', '406');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT044', '2023-05-16', '2023-05-16 20:00:00', '2023-05-16 23:00:00', '2', NULL, 'Giờ', 'Đã trả phòng', '44', '2', '603');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT045', '2023-05-12', '2023-05-16 21:00:00', '2023-05-16 23:00:00', '3', NULL, 'Giờ', 'Đã trả phòng', '45', '4', '404');
INSERT INTO PhieuThuePhong (MaPhieu, NgayLap, ThoiGianNhanPhong, ThoiGianTraPhong, SoNguoiO, TienCoc, HinhThucThue, HienTrang, MaKhachHang, MaNhanVien, MaPhong) VALUES ('PT046', '2023-05-09', '2023-05-10 07:30:00', '2023-05-17 07:30:00', '7', NULL, 'Ngày', 'Đã trả phòng', '46', '3', '106');

INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TongTienPhong, TongTienDichVu, TongTien, MaKhachHang, MaNhanVien) VALUES ('HD001', '2023-05-18', '700000', '600000', '1300000', '1', '8');
INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TongTienPhong, TongTienDichVu, TongTien, MaKhachHang, MaNhanVien) VALUES ('HD002', '2023-05-19', '3250000', '50000', '3300000', '31', '9');
INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TongTienPhong, TongTienDichVu, TongTien, MaKhachHang, MaNhanVien) VALUES ('HD003', '2023-05-19', '405000', '300000', '705000', '32', '10');
INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TongTienPhong, TongTienDichVu, TongTien, MaKhachHang, MaNhanVien) VALUES ('HD004', '2023-05-16', '3240000', '350000', '3590000', '33', '9');
INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TongTienPhong, TongTienDichVu, TongTien, MaKhachHang, MaNhanVien) VALUES ('HD005', '2023-05-19', '3900000', '450000', '4350000', '5', '10');
INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TongTienPhong, TongTienDichVu, TongTien, MaKhachHang, MaNhanVien) VALUES ('HD006', '2023-05-16', '2400000', '300000', '2700000', '34', '8');
INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TongTienPhong, TongTienDichVu, TongTien, MaKhachHang, MaNhanVien) VALUES ('HD007', '2023-05-16', '405000', '50000', '455000', '7', '9');
INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TongTienPhong, TongTienDichVu, TongTien, MaKhachHang, MaNhanVien) VALUES ('HD008', '2023-05-16', '270000', '50000', '320000', '35', '8');
INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TongTienPhong, TongTienDichVu, TongTien, MaKhachHang, MaNhanVien) VALUES ('HD009', '2023-05-19', '9750000', NULL, '9550000', '36', '8');
INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TongTienPhong, TongTienDichVu, TongTien, MaKhachHang, MaNhanVien) VALUES ('HD010', '2023-05-17', '16200000', '300000', '16200000', '37', '10');
INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TongTienPhong, TongTienDichVu, TongTien, MaKhachHang, MaNhanVien) VALUES ('HD011', '2023-05-16', '1160000', '100000', '1160000', '38', '8');
INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TongTienPhong, TongTienDichVu, TongTien, MaKhachHang, MaNhanVien) VALUES ('HD012', '2023-05-19', '8400000', '100000', '8000000', '39', '9');
INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TongTienPhong, TongTienDichVu, TongTien, MaKhachHang, MaNhanVien) VALUES ('HD013', '2023-05-19', '8000000', '300000', '8200000', '40', '9');
INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TongTienPhong, TongTienDichVu, TongTien, MaKhachHang, MaNhanVien) VALUES ('HD014', '2023-05-18', '3900000', NULL, '3900000', '41', '8');
INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TongTienPhong, TongTienDichVu, TongTien, MaKhachHang, MaNhanVien) VALUES ('HD015', '2023-05-17', '750000', NULL, '750000', '42', '10');
INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TongTienPhong, TongTienDichVu, TongTien, MaKhachHang, MaNhanVien) VALUES ('HD016', '2023-05-16', '2400000', NULL, '2400000', '43', '10');
INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TongTienPhong, TongTienDichVu, TongTien, MaKhachHang, MaNhanVien) VALUES ('HD017', '2023-05-16', '1305000', NULL, '1305000', '44', '10');
INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TongTienPhong, TongTienDichVu, TongTien, MaKhachHang, MaNhanVien) VALUES ('HD018', '2023-05-16', '1620000', '600000', '2220000', '45', '9');
INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TongTienPhong, TongTienDichVu, TongTien, MaKhachHang, MaNhanVien) VALUES ('HD019', '2023-05-17', '11200000', NULL, '11200000', '46', '9');
INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TongTienPhong, TongTienDichVu, TongTien, MaKhachHang, MaNhanVien) VALUES ('HD020', '2023-05-22', '700000', '400000', '1100000', '20', '9');
INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TongTienPhong, TongTienDichVu, TongTien, MaKhachHang, MaNhanVien) VALUES ('HD021', '2023-05-21', '16500000', '500000', '17000000', '30', '9');

INSERT INTO HoaDonPhong (MaHoaDon, MaPhong) VALUES ('HD001', '101');
INSERT INTO HoaDonPhong (MaHoaDon, MaPhong) VALUES ('HD002', '103');
INSERT INTO HoaDonPhong (MaHoaDon, MaPhong) VALUES ('HD003', '201');
INSERT INTO HoaDonPhong (MaHoaDon, MaPhong) VALUES ('HD004', '205');
INSERT INTO HoaDonPhong (MaHoaDon, MaPhong) VALUES ('HD005', '302');
INSERT INTO HoaDonPhong (MaHoaDon, MaPhong) VALUES ('HD006', '306');
INSERT INTO HoaDonPhong (MaHoaDon, MaPhong) VALUES ('HD007', '401');
INSERT INTO HoaDonPhong (MaHoaDon, MaPhong) VALUES ('HD008', '501');
INSERT INTO HoaDonPhong (MaHoaDon, MaPhong) VALUES ('HD009', '602');
INSERT INTO HoaDonPhong (MaHoaDon, MaPhong) VALUES ('HD010', '604');
INSERT INTO HoaDonPhong (MaHoaDon, MaPhong) VALUES ('HD011', '502');
INSERT INTO HoaDonPhong (MaHoaDon, MaPhong) VALUES ('HD012', '104');
INSERT INTO HoaDonPhong (MaHoaDon, MaPhong) VALUES ('HD013', '506');
INSERT INTO HoaDonPhong (MaHoaDon, MaPhong) VALUES ('HD014', '303');
INSERT INTO HoaDonPhong (MaHoaDon, MaPhong) VALUES ('HD015', '203');
INSERT INTO HoaDonPhong (MaHoaDon, MaPhong) VALUES ('HD016', '406');
INSERT INTO HoaDonPhong (MaHoaDon, MaPhong) VALUES ('HD017', '603');
INSERT INTO HoaDonPhong (MaHoaDon, MaPhong) VALUES ('HD018', '404');
INSERT INTO HoaDonPhong (MaHoaDon, MaPhong) VALUES ('HD019', '106');
INSERT INTO HoaDonPhong (MaHoaDon, MaPhong) VALUES ('HD020', '504');
INSERT INTO HoaDonPhong (MaHoaDon, MaPhong) VALUES ('HD021', '305');

INSERT INTO HoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD001', 'DV001');
INSERT INTO HoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD001', 'DV005');
INSERT INTO HoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD001', 'DV002');
INSERT INTO HoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD002', 'DV004');
INSERT INTO HoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD003', 'DV001');
INSERT INTO HoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD004', 'DV003');
INSERT INTO HoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD004', 'DV001');
INSERT INTO HoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD005', 'DV005');
INSERT INTO HoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD005', 'DV003');
INSERT INTO HoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD005', 'DV001');
INSERT INTO HoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('6', 'HD006', 'DV004');
INSERT INTO HoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD007', 'DV003');
INSERT INTO HoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD008', 'DV003');
INSERT INTO HoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD010', 'DV001');
INSERT INTO HoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD011', 'DV004');
INSERT INTO HoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD011', 'DV003');
INSERT INTO HoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD012', 'DV005');
INSERT INTO HoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD013', 'DV001');
INSERT INTO HoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('3', 'HD018', 'DV002');
INSERT INTO HoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('2', 'HD020', 'DV003');
INSERT INTO HoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('3', 'HD020', 'DV005');
INSERT INTO HoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('1', 'HD021', 'DV001');
INSERT INTO HoaDonDichVu (SoLuong, MaHoaDon, MaDichVu) VALUES ('4', 'HD021', 'DV003');