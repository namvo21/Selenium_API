package operator_java;

public class CheckFor {

	public static void main(String[] args) {
		for (int i = 0; i < 19; i++) {
			System.out.println("Duyệt lần thứ " + i);
			
			if (i == 10)
			{
				System.out.println("Thỏa điều kiện");
				break;
			}
			
			if(10 == 10)
			{
				System.out.println("Vào vòng if");
			}
		}
	}

}
