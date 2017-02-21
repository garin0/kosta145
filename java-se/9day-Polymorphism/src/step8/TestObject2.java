package step8;

// java.lang.Object �ֻ��� Ŭ������ toString() �޼��带 �������̵��Ͽ� 
// ��ü�� �ּҰ� ��� ��ü�� �Ӽ������� ��ȯ�ϵ��� ó���Ѵ�.
class Company {
	private String name;
	private String address;

	public Company(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}

class Company2 {
	private String name;
	private String address;

	public Company2(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	// Object class�� toString �޼��带 �������̵�

	@Override
	public String toString() {
		return "ȸ���: "+name+" �ּ�: "+address;
	}
	
}

public class TestObject2 {

	public static void main(String[] args) {
		Company c1 = new Company("Google", "�̱�");
		Company c2 = new Company("���̹�", "�д�");
		System.out.println(c1.toString()); // �ּҰ� ���
		System.out.println(c1); // �ּҰ� ���
		System.out.println(c2); // ���� �����ϰ� ��ü�� �ּҰ� ���
		System.out.println("==================");
		Company2 com = new Company2("�����ϰ���Ʈ", "�Ǳ�");
		System.out.println(com.toString()); //�ּҰ� ��� ��ü ���� ���
		System.out.println(com);
		System.out.println("==================");
		Object objCom[] = {
				new Company2("����", "����"),
				new Company2("NC	", "�Ǳ�"),
				new Company2("����īī��", "����")
		};
		for(int i = 0 ; i < objCom.length ; i++){
			System.out.println(objCom[i]);
		}
	}

}