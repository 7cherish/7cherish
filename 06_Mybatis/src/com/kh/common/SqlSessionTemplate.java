package com.kh.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/**
 * 
 * @author Gray
 * 만들어진 설정파일(mybatis-config.xml)을 로드해서 MyBatis객체를 생성한다.
 * 객체 생성 이후 MyBatis를 이용하여 데이터베이스 처리를 하려면
 * getSqlSessionFactory() 메소드를 이용하여 객체를 생성하고 API를 호출하여 사용하면 된다.
 */
public class SqlSessionTemplate {
	// SingletonPattern으로 지정
	
	public static SqlSession getSqlSession() {
		SqlSession session = null;
		String resource = "/mybatis-config.xml"; // 설정파일 위치

		try {
			// InputStream으로 Mybatis의 설정 정보를 읽어 온다.
			InputStream is = Resources.getResourceAsStream(resource);// resource라는 스트림을 건내주면 인풋스트림타입으로 돌려준다.
			// 읽어 온 Mybatis의 설정 정보를 바탕으로 SqlSessionFactoryBuilder를 생성한다.
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder(); // factory객체로 부터
			// 빌더 클래스에서 공장을 만듦
			// factory를 짓는 객체 bulid를 하면 공장이 나오고
			// 공장에서 부터 session이 나옴
			SqlSessionFactory factory = builder.build(is);
			// 공장으로부터 설정파일 읽어다가 그대로 공장짓는사람이 지음
			session = factory.openSession(false); // 트랜잭션여부. auto커밋 여부를 지정할 수 있는데 우리는 직접 할 것이다.
			// 세션을 얻어내기 위해서는 공장이 필요한데, 
			// 그걸 가져오는것이 SqlSessionFactoryBuilder다.

		} catch (IOException e) {
			e.printStackTrace();
		}

		return session;

	}
}
