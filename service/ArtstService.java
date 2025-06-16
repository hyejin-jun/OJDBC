package service;

import java.sql.SQLException;
import java.util.Scanner;

import dao.ArtstDAO;
import dao.MemberDAO;
import dto.MemberDTO;

public class ArtstService {

	
	public MemberDAO memberDAO = new MemberDAO() ;
	public ArtstDAO artstDAO = new ArtstDAO();



	Scanner inputStr = new Scanner(System.in);

	public void menu(Scanner inputStr) throws SQLException {

			boolean run = true ;
			
			while (run) {
			System.out.println("============== 멤버 게시판 메뉴입니다 ================");
			System.out.println("1. 아티스트 전체 보기 | 2. 아티스트 한 명 보기 | 3. 아티스트 수정 | 4. 아티스트 삭제 | 5. 종료");
			System.out.print("번호 입력 >>> ");
			String select = inputStr.next();
			
			switch (select) {
			
			case "1":
				System.out.println("아티스트 전체 보기 메뉴입니다 ^~^");
				artstAll (artstDAO);
				break ;
				
			case "2":
				System.out.println("아티스트 한 명 보기 메뉴입니다 ^~^");
				artstOne (inputStr);
				break;
				
			case "3":
				System.out.println("아티스트 수정 메뉴입니다 ^~^");
				artUp(inputStr);
				break;
			
			case "4":
				System.out.println("아티스트 삭제 메뉴입니다 ^~^");
				artDel (inputStr);
				break;
				
			case "5":
				System.out.println("아티스트 프로그램을 종료합니다");
				run = false ;
				break;
				
			default:
				System.out.println("번호는 1 번에서 5 번까지만 입력 가능합니다 ^~^");
				break;
			
			}  // 스위치 종료
			}  // 와일 종료


	}  // 메인 종료





		private void artDel(Scanner inputStr) throws SQLException {
		System.out.print("삭제할 아티스트를 입력하세요 >>> ");
		String dele = inputStr.next();
		
		ArtstDAO artstDAO = new ArtstDAO();
		artstDAO.artDel (dele) ;
		System.out.println();
		
		
	}





		private void artUp(Scanner inputStr) throws SQLException {  // 아티스트 수정 
			
			System.out.print("수정하고 싶은 아티스트를 입력하세요 >>> ");
			String upArt = inputStr.next() ;
			artstDAO.artUp (inputStr, upArt);
			System.out.println("==============================");
		
	}





		private void artstOne(Scanner inputStr) throws SQLException {
			
			System.out.print("보고 싶은 아티스트명을 입력하세요 >>> ");
			String artName = inputStr.next();
			artstDAO.artstOne(artName);
			System.out.println("============================");
		
	}





		private void artstAll(ArtstDAO artstDAO) throws SQLException  {
			
			System.out.println("================ 멤버 모두 보기 ================");
			artstDAO.artstAll();
			System.out.println("=============== 끝 ================");
			
		}
}
