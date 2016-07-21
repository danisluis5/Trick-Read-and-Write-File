package Cau2;

@SuppressWarnings("all")
public class NhanVien {
	private String MaNhanVien;
	private String TenNhanVien;
	private String NgaySinh;
	private String DiaChiEmail;
	private int ThuNhap;
	
	public NhanVien() {
		super();
	}
	
	public NhanVien(String maNhanVien, int thuNhap) {
		super();
		MaNhanVien = maNhanVien;
		ThuNhap = thuNhap;
	}

	public NhanVien(String maNhanVien, String tenNhanVien, String ngaySinh, String diaChiEmail) {
		super();
		MaNhanVien = maNhanVien;
		TenNhanVien = tenNhanVien;
		NgaySinh = ngaySinh;
		DiaChiEmail = diaChiEmail;
	}

	public NhanVien(String maNhanVien, String tenNhanVien, String ngaySinh, String diaChiEmail, int thuNhap) {
		super();
		MaNhanVien = maNhanVien;
		TenNhanVien = tenNhanVien;
		NgaySinh = ngaySinh;
		DiaChiEmail = diaChiEmail;
		ThuNhap = thuNhap;
	}

	public String getMaNhanVien() {
		return MaNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		MaNhanVien = maNhanVien;
	}

	public String getTenNhanVien() {
		return TenNhanVien;
	}

	public void setTenNhanVien(String tenNhanVien) {
		TenNhanVien = tenNhanVien;
	}

	public String getNgaySinh() {
		return NgaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		NgaySinh = ngaySinh;
	}

	public String getDiaChiEmail() {
		return DiaChiEmail;
	}

	public void setDiaChiEmail(String diaChiEmail) {
		DiaChiEmail = diaChiEmail;
	}

	public int getThuNhap() {
		return ThuNhap;
	}

	public void setThuNhap(int thuNhap) {
		ThuNhap = thuNhap;
	}
}
