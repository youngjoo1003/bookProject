package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnPool {	//연결고장시 servers의 톰켓 server.xml과 context.xml을 확인하세요
	
	//필드
	public Connection con;	//데이터베이스 연결
	public Statement stmt;	//인파라미터가 없는 sql문 담당
	public PreparedStatement pstmt;	//인파라미터가 있는 동적 쿼리문 담당
	public ResultSet rs;	//select로 나온 결과값을 표형식으로 출력 담당
	
	//생성자
	public DBConnPool() {
		try {
			//커넥션 풀 (DataSource) 얻기
			Context initCtx = new InitialContext();	//import javax.naming.*
			//Context : 네이밍 서비스에서 이름과 실제 객체를 연결해주는 개념
			//InitialContext : 네이밍 서비스를 이용하기 위한 시작점(객체 생성)
			
			Context ctx = (Context)initCtx.lookup("java:comp/env");
			//lookup() 메서드에 이름을 건네 원하는 객체를 찾아옴
			//java:comp/env -> 현재 웹 애플리케이션 루트 디렉토리
			//모든 자원은 java:comp/env 아래에 위치한다.
			
			DataSource source = (DataSource)ctx.lookup("dbcp_myoracle");
			//전역 자원에 설정한 이름을 이용해서 데이터 소스객체로 얻어옴
			//import javax.sql.DataSource;
			
			//커넥션 풀을 통해 연결 얻기
			con = source.getConnection();	//커넥션 풀을 통해 연결 얻기
			
			System.out.println("DB 커넥션 풀 연결 성공");
		} catch (Exception e) {
			System.out.println("DB 커넥션 풀 연결 실패");
			e.printStackTrace();
		}
	} //객체 생성시 자동으로 커넥션 풀 객체 생성용
	
	//메서드
	//메서드 연결 해제(자원 반납)
	public void close() {
		try {
			if (rs != null) {
				rs.close();
				System.out.println("ResultSet 종료 성공");
			}
			if (stmt != null) {
				stmt.close();
				System.out.println("Statement 종료 성공");
			}
			if (pstmt != null) {
				pstmt.close();
				System.out.println("PreparedStatement 종료 성공");
			}
			if (con != null) {
				con.close();
				System.out.println("Connection 종료 성공");
			}
		} catch (Exception e) {
			System.out.println("close()메서드 실행 실패");
			e.printStackTrace();
		}
	}

}
