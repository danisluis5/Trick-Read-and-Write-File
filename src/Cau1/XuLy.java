package Cau1;

@SuppressWarnings("all")
public class XuLy {
	public static void main(String[]args){
		System.out.println("Kết quả = "+oddNumber(String.valueOf(18)));
		System.out.println("Kết quả = "+evenNumber(String.valueOf(17)));
	}
	/**
	 * Nến ngư�?i dùng nhập vào n 
	 * DDK1 ch�?n vào tăng dần => 1 -> n-1 lẽ
	 */
	public static String oddNumber(String number){
		String output = "";
		/**
		 * Viết thuật toán xử lý
		 */
		int i = 1;
		while(i <= 18){
			output += i+" ";
			i += 2;
		};
		return output;
	}
	public static String evenNumber(String number){
		String output ="";
		int n = Integer.parseInt(number);
		if(n%2 == 0){
			while(n >= 0){
				output += n +" ";
				n = n - 2;
			}
		}else{
			n = n - 1;
			while(n >= 0){
				output += n +" ";
				n = n - 2;
			}
		}
		return output;
	}
}
