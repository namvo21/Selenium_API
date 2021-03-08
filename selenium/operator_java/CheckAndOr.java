package operator_java;

public class CheckAndOr {

	public static void main(String[] args) {
		// 2 giá trị là đúng hoặc sai: TRUE FALSE

		// Các kiểu dữ liệu hay sử dụng
		// date time
		// boolean: logic
		// int/ long: số nguyên
		// String: chuỗi
		// double/ float: số thực

		boolean value_a = true;
		boolean value_b = true;
		boolean value_c = value_a && value_b;
		boolean value_d = value_a || value_b;

		// AND: 1 trong 2 mà sai thì kết quả ra sai
		System.out.println("Giá trị của C (AND) = " + value_c);

		// OR: 1 trong 2 mà đúng thì kết quả ra đúng
		System.out.println("Giá trị của D (AND) = " + value_d);
	}

}
