package service;

import java.sql.SQLException;
import java.util.Scanner;

import dao.ArtstDAO;
import dao.BoardDAO;
import dao.MemberDAO;

public class BoardService {
	

	public MemberDAO memberDAO = new MemberDAO() ;
	public ArtstDAO artstDAO = new ArtstDAO();
	public BoardDAO boardDAO = new BoardDAO();


	Scanner inputStr = new Scanner(System.in);

	public void Boardmenu(Scanner inputStr) throws SQLException {

			boolean run = true ;
			
			while (run) {
			System.out.println("============== 편지 게시판 메뉴입니다 ================");
			System.out.println("1. 편지 목록 전체 보기 | 2. 내가 쓴 편지 보기 | 3. 편지 수정 | 4. 편지 삭제 | 5. 종료");
			System.out.print("번호 입력 >>> ");
			String select = inputStr.next();
			
			switch (select) {
			
			case "1":
				System.out.println("편지 목록 전체 보기 메뉴입니다 ^~^");
				letterAll (boardDAO) ;
				break ;
				
			case "2":
				System.out.println("내가 쓴 편지 보기 메뉴입니다 ^~^");
				letterOne (inputStr);
				break;
				
			case "3":
				System.out.println("편지 수정 메뉴입니다 ^~^");
				letterUp (inputStr);
				break;
			
			case "4":
				System.out.println("편지 삭제 메뉴입니다 ^~^");
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

	private void letterUp(Scanner inputStr) throws SQLException {
		
		System.out.print("수정하고 싶은 제목을 입력하세요 >>> ");
		String upLet = inputStr.next() ;
		boardDAO.letterUp (inputStr, upLet);
		System.out.println("==============================");
		
	}

	private void letterOne(Scanner inputStr) throws SQLException {  // 내가 쓴 편지 보기 (보드 하나 보기)
		
		System.out.print("작성자의 이름을 입력하세요 >>> ");
		String wiName = inputStr.next();
		boardDAO.letterOne (wiName);
		System.out.println("============================");
	
		
	}

	private void letterAll(BoardDAO boardDAO) throws SQLException {  // 편지 전체 보기
		
		System.out.println("================ 멤버 모두 보기 ================");
		boardDAO.letterAll();
		System.out.println("=============== 끝 ================");
		
	}
	
	

}
