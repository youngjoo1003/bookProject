package dao;

import java.sql.SQLException;

import common.DBConnPool;
import dto.UserDTO;

public class UserDAO extends DBConnPool {

	public UserDAO() {
		super();
	}
	
	public UserDTO loginUser(String userId, String userPwd) {
		UserDTO dto = new UserDTO();
		String query = "SELECT * FROM USERS WHERE USER_ID=? AND USER_PW=?"; 
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setUser_Id(rs.getString("User_Id"));
				dto.setUser_Pw(rs.getString("User_Pw"));
				dto.setUser_Name(rs.getString("User_Name"));
				dto.setUser_Email(rs.getString("User_Email"));
				dto.setUser_Tel(rs.getString("User_Tel"));
				dto.setUser_Address(rs.getString("User_Address"));
				dto.setUser_Date(rs.getDate("User_Date"));
				dto.setUser_Manager(rs.getInt("User_Manager"));
			}
			
		} catch (Exception e) {
			System.out.println("getUserDTO()메서드 오류 UserDAO를 확인하세요");
			e.printStackTrace();
		}
		return dto;
	}
	
	//회원 등록
	public int registerUser(String user_Id, String user_Pw, String user_Name, String user_Email, String user_Tel, String user_Address) {
		int result = 0; //0이면 실패 1이면 성공
	        
        try {
			//아이디 중복체크
			String query = "SELECT USER_ID FROM USERS WHERE USER_ID = ?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, user_Id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return result;
			} else {
				
				//데이터베이스에 입력값 등록
				String query2 = "INSERT INTO USERS (USER_ID, USER_PW, USER_NAME, USER_EMAIL, USER_TEL, USER_ADDRESS) VALUES (?, ?, ?, ?, ?, ?)";
				
				pstmt = con.prepareStatement(query2);
				pstmt.setString(1, user_Id);
				pstmt.setString(2, user_Pw);
				pstmt.setString(3, user_Name);
				pstmt.setString(4, user_Email);
				pstmt.setString(5, user_Tel);
				pstmt.setString(6, user_Address);
				
				result = pstmt.executeUpdate();	//등록 성공시 1 반환
				System.out.println("반환값@@@@@@: " + result);
				
			}
		} catch (SQLException e) {
			System.out.println("registerUser() 메서드 오류: " + e.getMessage());
			e.printStackTrace();
		}

        return result;
    }
	
	

}
