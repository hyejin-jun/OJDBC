package exam;

import java.sql.SQLException;
import java.util.Scanner;

import dao.ArtstDAO;
import dto.MemberDTO;
import service.ArtstService;
import service.MembertShipService;

public class TestExam {
	
	MemberDTO session = null;
	
	public static void main(String[] args) throws SQLException {
		
		Scanner inputStr = new Scanner(System.in);
		MemberDTO memberDTO = new MemberDTO() ;
		
		boolean run = true ;
		
		while (run) {
			
			System.out.println("테스트: " + memberDTO.getMno() + memberDTO.getId() + memberDTO.getWriter());
			
			System.out.println("============== MBC 엔터테인먼트에 오신 것을 환영합니다 ================");
			System.out.println("1. 회원 전용 | 2. 비회원 전용 | 3. 종료");
			System.out.print("번호 입력 >>> ");
			String select = inputStr.next();
			
			switch (select) {
			
			case "1":
				System.out.println("회원 전용입니다 ^~^");
				System.out.println("회원은 멤버 정보 보기와 멤버에게 편지 전달이 가능합니다 ^~^");
				MembertShipService membertShipService1 = new MembertShipService();
				membertShipService1.MemberShip(inputStr);
				break ;
				
			case "2":
				System.out.println("비회원 전용입니다 ^~^");
				System.out.println("비회원은 멤버 정보 보기만 가능합니다");
				MembertShipService membertShipService2 = new MembertShipService();
				membertShipService2.NotMemberShip(inputStr);
				
				break;
			case "3":
				System.out.println("엔터테이먼트 사이트를 종료합니다");
				run = false ;
				break;
				
			default:
				System.out.println("번호는 1 번에서 3 번까지만 입력 가능합니다 ^~^");
				break;
			
			
			}
			
		}
		
		
		
		
		

	}

}
