package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



import dto.MemberDTO;

public class MemberDAO {
   
	public MemberDTO memberDTO = new MemberDTO();
	public PreparedStatement preparedStatement = null ;
	public Connection connection = null ;
	public Statement statement = null ;
	public ResultSet resultSet = null ;
	public int result = 0 ;
	

	
	public MemberDAO () {
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.162:1521:xe", "test12", "test12");
		} catch (ClassNotFoundException e) {
			System.out.println("예외 발생 ㅋ 확인해 주세요");
			e.printStackTrace();
	} catch (SQLException e) {
		System.out.println("URL, 아이디, 비밀번호 잘못됐습니다 확인 ㄱㄱ");
		e.printStackTrace();
		System.exit(0);  // 강제 종료
	}
	 

	}



	public boolean isId(String id) throws SQLException {
		
		try {
			String sql = "select count(*) from member where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				int count = resultSet.getInt(1);  // 카운트 결과를 가지고 옴
				return count > 0 ;  // 0보다 크면 이미 존재하는 아이디
			}
			
		} catch (SQLException e) {
			System.out.println("예외 발생 ㅋ");
			e.printStackTrace();
		} finally {
			if (resultSet != null) resultSet.close();
			if (preparedStatement != null) preparedStatement.close();
		}

		return false;
	}



	public void insertMember(MemberDTO memberDTO) throws SQLException {
		
		try {
			String sql = "insert into member (mno, writer, id, pw, mdate)" + "values (member_seq.nextval, ?, ?, ?, sysdate)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberDTO.getWriter());
			preparedStatement.setString(2, memberDTO.getId());
			preparedStatement.setString(3, memberDTO.getPw());
		
		
		result = preparedStatement.executeUpdate();
		
		if (result > 0) {
			System.out.println(result + " 명의 회원이 등록되었습니당");
			connection.commit();  // 저장 완
		}  else {
			System.out.println("예외 밣생 ㅋ");
			connection.rollback();  // 저장 안 함 ㅋ
		} 
		} catch (SQLException e) {
			System.out.println("예외 발생 ㅋ");
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			
		}

		
	}



	public void login(MemberDTO session) throws SQLException {  // 로그인 DAO
		
		try {
			String sql = "select * from member where id = ? and pw = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, session.getId());
			preparedStatement.setString(2, session.getPw());
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				session.setMno(resultSet.getInt("mno"));
			    session.setId(resultSet.getString("id"));
			    session.setPw(resultSet.getString("pw"));
			    session.setWriter(resultSet.getString("writer"));  // 이렇게 넣어야 널이 안 뜸
				
			} else {
				session.setMno(0);
			}
		} catch (SQLException e) {
			System.out.println("예외 발생 ㅋ");
			e.printStackTrace();
			session.setMno(0);
		} finally {
			if (resultSet != null) resultSet.close();
			if (preparedStatement != null) preparedStatement.close();
		}
			return;
	}




		
	

}












