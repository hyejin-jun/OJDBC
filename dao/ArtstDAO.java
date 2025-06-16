package dao;

import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.xml.transform.Result;

import dto.MemberDTO;

public class ArtstDAO {
	
	
	public MemberDTO memberDTO = new MemberDTO() ;
	public Connection connection = null ;
	public Statement statement = null ;
	public PreparedStatement preparedStatement = null ;
	public ResultSet resultSet = null ;
	public int result = 0 ;

	
	
	public ArtstDAO() {
			
			try { // 예외 발생용
				Class.forName("oracle.jdbc.OracleDriver");
				connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.162:1521:xe", "test12", "test12");
			} catch (ClassNotFoundException e) {
				System.out.println("드라이버 이름이나 자바 파일이 잘못 되었습니다");
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("URL, ID, PW가 잘못 되었습니다");
				e.printStackTrace();
				System.exit(0);  // 강제 종료
			}
		}
	
	
	
	
	
	
	
	

	public void artstAll() throws SQLException {		
		
		
		try {
			String sql = "select mno, writer, id, pw, mdate from member order by mdate desc";
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
	
		
		System.out.println("번호\t이름\t아이디\t비밀번호\t가입일");
		while (resultSet.next()) {
			System.out.print(resultSet.getInt("mno")+"\t");
			System.out.print(resultSet.getString("writer")+"\t");
			System.out.print(resultSet.getString("id")+"\t");
			System.out.print(resultSet.getString("pw")+"\t");
			System.out.println(resultSet.getDate("mdate")+"\t");
			
		} 
		System.out.println("=================================");

		
	} catch (Exception e) {
		System.out.println("예외 발생");
		e.printStackTrace();
	
	} finally {
		resultSet.close();
		statement.close();
	}


	}









	public void artstOne(String artName) throws SQLException {  // 아티스트 하나 보기 DAO
		
		try {
			String sql = "select mno, writer, id, pw, mdate from member where writer = ?";

			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, artName); // 서비스에서 찾고 싶은 이름이 ?로 넘어간다
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setMno(resultSet.getInt("mno"));
				memberDTO.setWriter(resultSet.getString("writer"));
				memberDTO.setId(resultSet.getString("id"));
				memberDTO.setPw(resultSet.getString("pw"));
				memberDTO.setMdate(resultSet.getDate("mdate"));  // 데이터베이스에 있던 행을 객체에 넣기
				
				System.out.println("==============================");
				System.out.println("번호: " + memberDTO.getMno());
				System.out.println("이름: " + memberDTO.getWriter());
				System.out.println("아이디: " + memberDTO.getId());
				System.out.println("비밀번호: " + memberDTO.getPw());
				System.out.println("가입일: " + memberDTO.getMdate());
			} else {
				System.out.println("해당 게시물이 존재하지 않습니다");
			}
		} catch (SQLException e) {
			System.out.println("예외 발생!!!");
			e.printStackTrace();
		} finally {
			resultSet.close();
			preparedStatement.close();
		}
	}








	

	public void artUp(Scanner inputStr, String artUp) throws SQLException {  // 아티스트 수정
		
		MemberDTO memberDTO = new MemberDTO() ;
		
		System.out.print("수정할 이름을 입력하세요 >>> ");
		memberDTO.setWriter(inputStr.next());
		System.out.print("수정할 아이디를 입력하세요 >>> ");
		memberDTO.setId(inputStr.next());
		System.out.print("수정할 비밀번호를 입력하세요 >>> ");
		memberDTO.setPw(inputStr.next());
		
		try {
			String sql = "update member set writer = ?, id = ?, pw = ?, mdate = sysdate where writer = ?";
			
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, memberDTO.getWriter());
					preparedStatement.setString(2, memberDTO.getId());
					preparedStatement.setString(3, memberDTO.getPw());
					preparedStatement.setString(4, artUp);

				result = preparedStatement.executeUpdate();
				
				if (result > 0 ) {
					System.out.println(result + " 명의 회원이 변경되었습니다");
					connection.commit(); // 저장
				} else {
					System.out.println("수정되지 않았습니다");
					connection.rollback(); // 저장 안 함
				}
				} catch (SQLException e) {
					System.out.println("예외 발생 ㅋ");
					e.printStackTrace();
				
		} finally {
			preparedStatement.close();
			
	}


	}









	public void artDel(String dele) throws SQLException {  // 삭제 DAO
		
		MemberDTO memberDTO = new MemberDTO() ;
		
		try {
			String sql = "delete member set writer = ?, id = ?, pw = ?, mdate = sysdate where writer = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberDTO.getWriter());
			preparedStatement.setString(2, memberDTO.getId());
			preparedStatement.setString(3, memberDTO.getPw());
			preparedStatement.setString(4, dele);
			
			result = preparedStatement.executeUpdate();
			
			dele = null ;
		

		
	} finally {
		preparedStatement.close();
	}
	}
}

	









