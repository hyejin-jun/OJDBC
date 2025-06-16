package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.xml.transform.Result;

import dto.BoardDTO;
import dto.MemberDTO;

public class BoardDAO {
	
	
	public MemberDTO memberDTO = new MemberDTO() ;
	public PreparedStatement preparedStatement = null ;
	public Statement statement = null ;
	public ResultSet resultSet = null ;
	public Connection connection = null ;
	public  int result = 0 ;
	
	
	public BoardDAO() {
		
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


	public void letterAll() throws SQLException	 {  // 보드 전체 보기
		
		
		try {
			String sql = "select bno, writer, title, detail, bdate from letter order by bdate desc";
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
	
		
		System.out.println("번호\t이름\t아이디\t비밀번호\t가입일");
		while (resultSet.next()) {
			System.out.print(resultSet.getInt("bno")+"\t");
			System.out.print(resultSet.getString("writer")+"\t");
			System.out.print(resultSet.getString("title")+"\t");
			System.out.print(resultSet.getString("detail")+"\t");
			System.out.println(resultSet.getDate("bdate")+"\t");
			
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


	public void letterOne(String wiName) throws SQLException {  // 보드 하나 보기 DAO
		
			
			try {
				String sql = "select bno, writer, title, detail, bdate from letter where writer = ?";

				
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, wiName); // 서비스에서 찾고 싶은 이름이 ?로 넘어간다
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					BoardDTO boardDTO = new BoardDTO() ;
					boardDTO.setBno(resultSet.getInt("bno"));
					boardDTO.setWriter(resultSet.getString("writer"));
					boardDTO.setTitle(resultSet.getString("title"));
					boardDTO.setDetail(resultSet.getString("detail"));
					boardDTO.setBdate(resultSet.getDate("bdate"));  // 데이터베이스에 있던 행을 객체에 넣기
					
					System.out.println("==============================");
					System.out.println("번호: " + boardDTO.getBno());
					System.out.println("이름: " + boardDTO.getWriter());
					System.out.println("아이디: " + boardDTO.getTitle());
					System.out.println("비밀번호: " + boardDTO.getDetail());
					System.out.println("가입일: " + boardDTO.getBdate());
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


	public void letterUp(Scanner inputStr, String upLet) throws SQLException { // 수정 DAO
		
		
		BoardDTO boardDTO = new BoardDTO() ;
		
		System.out.print("수정할 제목을 입력하세요 >>> ");
		boardDTO.setTitle(inputStr.next());
		
		Scanner inputLine = new Scanner(System.in);
		System.out.print("수정할 내용를 입력하세요 >>> ");
		boardDTO.setDetail(inputLine.nextLine());
		
		try {
			String sql = "update letter set title = ?, detail = ?, bdate = sysdate where title = ?";
			
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, boardDTO.getTitle());
					preparedStatement.setString(2, boardDTO.getDetail());
					preparedStatement.setString(3, upLet);

				result = preparedStatement.executeUpdate();
				
				if (result > 0 ) {
					System.out.println(result + " 개의 편지가 변경되었습니당");
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
}

		
	

	
	
	
	
	
	

