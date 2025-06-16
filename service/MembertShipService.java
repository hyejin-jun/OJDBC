package service;

import java.sql.SQLException;
import java.util.Scanner;

import dao.ArtstDAO;
import dao.MemberDAO;
import dto.MemberDTO;


public class MembertShipService {
	
	public MemberDAO memberDAO = new MemberDAO() ;


	public void MemberShip(Scanner inputStr) throws SQLException { // 멤버 전용 메서드

		boolean run = true ;
		
		while (run) {
		System.out.println("============== 회원 전용 메뉴입니다 ================");
		System.out.println("1. 회원가입 | 2. 로그인 | 3. 아티스트 | 4. 편지 쓰기 | 5. 종료");
		System.out.print("번호 입력 >>> ");
		String select = inputStr.next();
		
		switch (select) {
		
		case "1":
			System.out.println("회원가입 메뉴입니다 ^~^");
			join (inputStr);
			break ;
			
		case "2":
			System.out.println("로그인 메뉴입니다 ^~^");
			MemberDTO session = new MemberDTO() ;
			login (inputStr, session);
			break;
			
		case "3":
			System.out.println("아티스트 메뉴입니다 ^~^");
			ArtstService artstService = new ArtstService() ;
			artstService.menu (inputStr);
			break;
			
		case "4":
			System.out.println("편지 쓰기 메뉴입니다 ^~^");
			BoardService boardService = new BoardService() ;
			boardService.Boardmenu(inputStr);
			break;
			
		case "5":
			System.out.println("멤버십 전용 사이트를 종료합니다");
			run = false ;
			break;
			
		default:
			System.out.println("번호는 1 번에서 5 번까지만 입력 가능합니다 ^~^");
			break;
		
		}  // 스위치 종료
		}  // 와일 종료


}  // 메인 종료













	private void login(Scanner inputStr, MemberDTO session) throws SQLException { // 로그인 전용 메뉴
		
			
			
			System.out.print("로그인할 아이디를 입력하세요 >>> ");
			String id = inputStr.next();
			System.out.print("로그인할 비밀번호를 입력하세요 >>> ");
			String pw = inputStr.next();
			
			session.setId(id);
			session.setPw(pw);  // 입력받은 객체 저장
			
			MemberDAO memberDAO = new MemberDAO() ;
			memberDAO.login(session);
			
			while (true)
			if (session.getMno() != 0) {
				System.out.println("로그인 성공" + session.getWriter() + session.getMno()+ " 님 환영합니다");
				break;
				
			} else {
				System.out.println("로그인 실패!! 다시 확인하세요" + session.getMno());
				break ;  // 로그인 안 되면 다시 위로 ㅋ
			}
			
			
		}



	private void join(Scanner inputStr) throws SQLException {  // 회원가입 전용 메뉴
		
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO() ;
		
		System.out.print ("가입하실 회원의 성함을 입력해 주세요 ^~^ >>> ");
		memberDTO.setWriter(inputStr.next());
		
		String id = "";  // 아이디 섹션..?이 일단은 공백
		while (true) {
		System.out.print("가입하실 회원의 아이디를 입력해 주세요 ^~^ >>> ");
		id = inputStr.next();
		
		if (memberDAO.isId(id)) {
			System.out.println("중복 아이디가 있습니다 아이디를 다시 입력해 주세요");
		} else {  
			break ;  // 중복 아니면 반복 탈출 ㅋ
		}
	}
		memberDTO.setId(id);  // 아이디 저장하기 ㅎ			
		System.out.print("가입하실 회원의 비밀번호를 입력해 주세요 ^~^ >>> ");
		memberDTO.setPw(inputStr.next());
		
		memberDAO.insertMember(memberDTO);  // 디비에 저장
		System.out.println("회원가입에 성공하였습니다 ^~^ 회원 서비스를 이용하세요 ");
		
	}













	public void NotMemberShip(Scanner inputStr) throws SQLException {  // 비회원 전용
		
		ArtstDAO artstdao = new ArtstDAO();
		
		System.out.println("1. 아티스트 전체 보기 | 2. 종료");
		System.out.print("번호 입력 >>> ");
		String aaa = inputStr.next();
		
		switch (aaa) {
		
		case "1":
			System.out.println("아티스트 전체 보기 메뉴로 진입합니다");
			artstdao.artstAll();
			break;

		case "2":
			System.out.println("종료합니다");
			break;
			
		default:
			System.out.println("1 번에서 2 번까지만 가능합니다");
		
		
		
		}
		
	}
		
		
	

} // 클래스 종료