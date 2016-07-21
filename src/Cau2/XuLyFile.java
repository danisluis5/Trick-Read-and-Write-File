package Cau2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import org.eclipse.swt.custom.ST;

@SuppressWarnings("all")
public class XuLyFile {
	
	private static String tbName = "nhanvien";
	/**
	 * Xử lý phần đọc file với tệp data1.txt
	 * @param nhanVien
	 * @return
	 * @throws ParseException
	 */
	public static void readFile1(String namefile){
		String output = "";
		System.out.println("THÊM MỚI NHÂN VIÊN VÀO TRONG CƠ SỞ DỮ LIỆU");
		try {
			FileInputStream input = new FileInputStream(new File(namefile));
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			String linedata = "";	
			int i = 1;
			while((linedata = br.readLine())!=null){
				linedata = linedata.trim();
				output += linedata +"\n";
				insertData1(linedata,i);
				i++;
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("tên file không tồn tại");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("dữ liệu không hợp lệ");
		}
		System.out.println("/ ---------- data1.txt ---------- /");
		System.out.println("DỮ LIỆU CHỨA TRONG FILE: ");
		System.out.println(output);
	}
	public static void insertData1(String linedata,int i){
		String[] arr = null;
		arr = linedata.split(",");
		NhanVien nhanVien = new NhanVien(arr[0], arr[1], arr[2], arr[3]);
		try {
			if(addNhanVien(nhanVien) > 0){
				System.out.println("Thêm thành công thông tin nhân viên thứ "+i);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static int addNhanVien(NhanVien nhanVien) throws ParseException{
		Connection mConnect = new MyConnection().getConnectMySQL();
		String sql = "INSERT INTO "+tbName+"(MaNhanVien,TenNhanVien,NgaySinh,DiaChiEmail,ThuNhap) VALUES (?,?,?,?,?)";
		int result = 0;
		PreparedStatement pst = null;
		String lastCrawlDate = nhanVien.getNgaySinh();
		Date utilDate = new SimpleDateFormat("dd/MM/yyyy").parse(lastCrawlDate);
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); 
		try {
			pst = mConnect.prepareStatement(sql);
			pst.setString(1,nhanVien.getMaNhanVien());
			pst.setString(2,nhanVien.getTenNhanVien());
			pst.setDate(3,sqlDate);
			pst.setString(4, nhanVien.getDiaChiEmail());
			pst.setInt(5, 0);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} finally{
			try {
				pst.close();
				mConnect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	public static ArrayList<String> getListMaNV(){
		Connection mConnect = new MyConnection().getConnectMySQL();
		String sql = "SELECT MaNhanVien FROM "+tbName;
		ArrayList<String> listMaNV = new ArrayList<String>();
		ResultSet rs = null;
		Statement st = null;
		try {
			st = mConnect.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				listMaNV.add(rs.getString("MaNhanVien"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} finally{
			try {
				rs.close();
				st.close();
				mConnect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		return listMaNV;
	}
	/**
	 * Xử lý phần đọc file với tệp data2.txt
	 * @param nhanVien
	 * @return
	 * @throws ParseException
	 */
	public static void readFile2(String namefile){
		String output = "";
		System.out.println("CẬP NHẬT THÔNG TIN NHÂN VIÊN TRONG CƠ SỞ DỮ LIỆU");
		try {
			FileInputStream input = new FileInputStream(new File(namefile));
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			String linedata = "";	
			int i = 1;
			ArrayList<MyError> list = new ArrayList<MyError>();
			while((linedata = br.readLine())!=null){
				linedata = linedata.trim();
				output += linedata +"\n";
				String[] arr = linedata.split(",");
				list.add(new MyError(arr[0],arr[1]));
			}
			insertData2(list);
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		System.out.println("/ ---------- data2.txt ---------- /");
		System.out.println("DỮ LIỆU CHỨA TRONG FILE: ");
		System.out.println(output);
	}
	public static void insertData2(ArrayList<MyError> list){
		/**
		 * Catch error data2.txt
		 */
		boolean cont = true;
		String error = "";
		int row = 1;
		ArrayList<String> errorx = new ArrayList<String>();
		for(MyError myError : list){
			if(!myError.getMsgx().equals(getListMaNV().get(0)) && !myError.getMsgx().equals(getListMaNV().get(1))){
				if(!String.valueOf(myError.getMsgy()).matches("\\d+")){
					errorx.add(new String("Dòng "+row+": Mã nhân viên không tồn tại,Thu nhập không phải là số"));
					cont = false;
				}else{
					errorx.add(new String("Dòng "+row+": Mã nhân viên không tồn tại"));
					cont = false;
				}
			}else if(!String.valueOf(myError.getMsgy()).matches("\\d+")){
				errorx.add(new String("Dòng "+row+": Thu nhập không phải là số"));
				cont = false;
			}
			row++;
		}
		if(!cont){
			System.out.println("DANH SÁCH LỖI: ");
			for (String string : errorx) {
				System.out.println(string);
			}
			writeFile(errorx);
		}
		
		if(cont){
			int temp = 0;
			int index = 1;
			for(String maNhanVien : getListMaNV()){
				for (MyError myError : list) {
					if(maNhanVien.equals(myError.getMsgx())){
						temp += Integer.parseInt(myError.getMsgy());
						/**
						 * Update data
						 */
						try {
							if(updateNhanVien(new NhanVien(maNhanVien,temp)) > 0){
								System.out.println("Cập nhật thành công thông tin");
							}
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
						}
					}else{
						//
					}
				}
				temp = 0;
			}
		}else{
			System.out.println("Dữ liệu lỗi, Cập nhật thất bại!");
		}
	}
	public static int updateNhanVien(NhanVien nhanVien) throws ParseException{
		Connection mConnect = new MyConnection().getConnectMySQL();
		String sql = "UPDATE "+tbName+" SET ThuNhap = ? WHERE MaNhanVien = ?"; 
		int result = 0;
		PreparedStatement pst = null;
		try {
			pst = mConnect.prepareStatement(sql);
			pst.setInt(1,nhanVien.getThuNhap());
			pst.setString(2,nhanVien.getMaNhanVien());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} finally{
			try {
				pst.close();
				mConnect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	public static void writeFile(ArrayList<String> listError){
		System.out.print("Nhập tên file ghi[Ví dụ error2.txt]: ");
		String fileName = new Scanner(System.in).nextLine();
		File file = new File(fileName);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
			for (String string : listError) {
				br.write(string);
				br.newLine();
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	public static void main(String[]args){
		System.out.print("Nhập tên file thứ nhất[ví dụ: data1.txt]: ");
		String file1 = new Scanner(System.in).nextLine().trim();
		readFile1(file1);
		System.out.print("Nhập tên file thứ hai[ví dụ: data2.txt]: ");
		String file2 = new Scanner(System.in).nextLine().trim();
		readFile2(file2);
	}
}
