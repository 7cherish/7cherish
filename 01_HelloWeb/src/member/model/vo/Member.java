package member.model.vo;
/**
 * VO를 나타내는 여러 가지
 * 
 * VO : Value Object의 약자
 * DTO : Data Transfer Object의 약자
 * DO : Domain Object의 약자
 * Entity : VO클래스를 가리킨다.
 * Bean
 * 
 * VO클래스란? 데이터베이스의 한 행의 정보가 담길 수 있는 객체
 * VO클래스의 필드명과 DB테이블의 컬럼명을 같게 하는것이 관례이다.
 * 
 * 
 */
import java.sql.Date;

public class Member {
	
	private String memberId;
	private String password;
	private String memberName;
	private String gender;		//char를 다루는 sql패키지 메소드가 없기 때문에 String 사용
	private int age;
	private String email;
	private String phone;
	private String address;
	
	private String hobby;
	private Date enrollDate;	//java.sql.Date -> java.util.Date를 상속한 자식 클래스임

	

	public Member() {}
	
	public Member(String memberId, String password, String memberName, 
				  String gender, int age, String email, String phone,
				  String address, String hobby, Date enrollDate) {
		this.memberId = memberId;
		this.password = password;
		this.memberName = memberName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
		this.enrollDate = enrollDate;

	}


	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	
	@Override
	public String toString() {
		return memberId + "\t" + password + "\t" + memberName + "\t" 
			   + gender + "\t" + age + "\t" + email + "\t" + phone + "\t" 
			   + address + "\t" + hobby + "\t" + enrollDate;
	}

}
