package com.kh.common.wrapper;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncryptWrapper extends HttpServletRequestWrapper {
	// 부모클래스의 기본생성자가 없으므로,
	// 파라미터생성자를 명시적으로 작성
	public EncryptWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String key) {
		String value = "";
		
		// 패스워드가 전달됐을때만 암호화
		if(key != null && 
				("password".equals(key) || "password_new".equals(key))) {
			System.out.println("암호화 전 : " + super.getParameter(key));
			
			value = getSha512(super.getParameter(key));
			
			System.out.println("암호화 후 : " + value);
		}
		else {
			// 비밀번호가 아닌 것들은 부모꺼를 사용
			value = super.getParameter(key);
		}
		
		return value;
	}
	
	/**
	 * 해싱은 복호화가 되지 않는다. (=> 원래 암호가 무엇이었는지 알 수 없다)
	 * 둘 다 암호화 기법이지만 Hash는 단방향 암호화 기법이고 Encryption은 양방향 암호화 기법이다.
	 * 암호화처리 메소드
	 * @param parameter
	 * @return
	 */
	private String getSha512(String password) {
		String encPwd = null;
		MessageDigest md = null;
		
		// 1. 암호화객체 생성 sha-512방식
		try {
			md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// 2. 사용자가 입력한 password를 바이트배열로 변환
		byte[] bytes = password.getBytes(Charset.forName("utf-8"));
		
		// 3. md객체에 바이트배열전달해서 갱신
		md.update(bytes);
		
		// 4. 암호화처리(byte배열로 리턴)
		byte[] encBytes = md.digest(); // encBytes는 현재 사용자가 읽을 수 없는 상태이다.
		
		// 5. Base64인코더를 사용해서 암호화된 바이트배열을 문자열로 변환
		// base64 인코딩은 byte array 를 64개의 문자로 이루어진 문자열로 변환하는 방법이다.
		encPwd = Base64.getEncoder().encodeToString(encBytes);
		
		return encPwd;
	}
	
}
