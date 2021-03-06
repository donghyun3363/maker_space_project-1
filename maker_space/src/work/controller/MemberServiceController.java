package work.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import work.model.dto.Member;
import work.model.service.MemberService;

/**
 * Servlet implementation class MemberServiceController
 */
public class MemberServiceController extends HttpServlet {

	int unusedEmail = 0;
	private MemberService memberService;

	public MemberServiceController() {
		memberService = MemberService.getInstance();
	}

	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println(action);
		switch (action) {
		case "login":
			login(request, response);
			break;
		case "logout":
			logout(request, response);
			break;
		case "join":
			join(request, response);
			break;
		case "checkOverlap":
			checkOverlap(request, response);
			break;
		case "removeMember":
			removeMember(request, response);
			break;
		case "updateMember":
			updateMember(request, response);
			break;
		case "forgotPassword":
			forgotPassword(request, response);
			break;
		case "getMyInfo":
			getMyInfo(request, response);
			break;
		case "removeMemberByAdmin":
			removeMemberByAdmin(request, response);
			break;
		case "getInfoByAdmin":
			getInfoByAdmin(request, response);
			break;
		case "getAllInfoByAdmin":
			getAllInfoByAdmin(request, response);
			break;
		default:
			System.out.println("\n## 서비스 준비중입니다.");
			break;
		}
	}

	private void getAllInfoByAdmin(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("\n## 관리자 :: 전체 회원 조회 요청");

		ArrayList<Member> members = new ArrayList<>();
		members = memberService.getAllInfoByAdmin();
		request.setAttribute("members", members);

		// request.getRequestDispatcher("allInfo.jsp").forward(request, response);
		// 전체 정보 조회 페이지로 전송
	}

	private void getInfoByAdmin(HttpServletRequest request, HttpServletResponse response) {

	}

	private void getMyInfo(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("\n## 내 정보 조회 요청");

		HttpSession session = request.getSession(false); // false는 기존 세션을 달라.
	
		Member member = new Member();
		member = memberService.getMyInfo((String) session.getAttribute("email"));
		// System.out.println(member.getMemberId());

		request.setAttribute("email", member.getEmail());
		request.setAttribute("name", member.getName());
		request.setAttribute("password", member.getPassword());
		request.setAttribute("mobile", member.getMobile());
		request.setAttribute("company", member.getCompany());
		request.setAttribute("grade", member.getGrade());
		request.setAttribute("point", member.getPoint());

		// request.getRequestDispatcher("").forward(request, response);
		// 보낼 페이지 jsp로 전송
	}

	/**
	 * 비밀번호 변경 메서드
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void forgotPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		int result = memberService.forgotPassword(email, password);

		if (result != 0) {
			// 탈퇴 성공
			System.out.println("비밀번호 변경 성공");
			String message = "변경 되었습니다";
			// 회원전용 서비스 페이지 이동
			request.setAttribute("message", message);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {

			request.setAttribute("message", "변경에 실패했습니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	/**
	 * 회원 정보 수정 메서드 이메일, 패스워드, 전화번호, 이메일 수정 가능 ---> 이메일도 수정가능하게할것인가?
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateMember(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String mobile = request.getParameter("mobile");
		String company = request.getParameter("company");

		int result = memberService.updateMember(email, password, mobile, company);

		if (result != 0) {
			// 탈퇴 성공
			System.out.println("수정 성공");
			String message = "수정 되었습니다";
			// 회원전용 서비스 페이지 이동
			request.setAttribute("message", message);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {

			request.setAttribute("message", "수정에 실패했습니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	/**
	 * 회원 탈퇴 요청 메서드 회원이 직접 탈퇴를 원할 떄 사용되는 메서드
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void removeMember(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\n## 회원 탈퇴 요청");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		int result = memberService.removeMember(email, password);

		if (result != 0) {
			// 탈퇴 성공
			System.out.println("탈퇴성공");
			String message = "탈퇴 되었습니다";
			// 회원전용 서비스 페이지 이동
			request.setAttribute("message", message);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {

			request.setAttribute("message", "탈퇴에 실패했습니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}

	}

	/**
	 * 회원 탈퇴 요청메서드 (관리자)
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void removeMemberByAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\n## 관리자 - 회원 탈퇴 요청");

		String email = request.getParameter("email");
		int result = memberService.removeMemberByAdmin(email);

		if (result != 0) {
			// 탈퇴 성공
			System.out.println("탈퇴성공");
			String message = "탈퇴 되었습니다";
			// 회원전용 서비스 페이지 이동
			request.setAttribute("message", message);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {

			request.setAttribute("message", "탈퇴에 실패했습니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	/**
	 * 이메일 중복확인 메서드 데이터베이스에서 메일을 확인해 가입유무를 파악한다.
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * 
	 *             -- 가입요청페이지에서 가입정보 입력하고 -- 이메일중복요청 -- 가입정보 이메일 중복 검증을 해서 -- 중복이 되지
	 *             않았으면 요청시 전달받은 정보를 전체가져와서 dto 객체 담고 -- dto 객체를 응답위한 정보로 설정(응답페이지에서
	 *             검증완료된 정보로 초기화 값으로 설정) -- 응답페이지를 회원가입페이지로 응답해서 -- 응답페이지에서는
	 *             이메일중복검증이 완료여부를 확인해서 -- 완료된 응답이면 설정되어있는 dto 객체의 속성을 가져와서 --
	 *             회원가입페이지 입력양식의 해당 항목의 value="<%= dto.getEmail() %> 기본값으로 설정
	 */
	private void checkOverlap(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\n## 메일 중복확인 요청");
		Member member = new Member();

	}

	/**
	 * 로그인 요청
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n## 로그인 요청");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if (errorGenerate(request, response, email, "이메일을 입력해주세요!") != true) {
			return;
		}
		if (errorGenerate(request, response, password, "패스워드를 입력해주세요!") != true) {
			return;
		}
		String grade = memberService.login(email, password);
		System.out.println(">>>!!");
		if (grade != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("email", email);
			session.setAttribute("grade", grade);
			Member getMember = memberService.getMyInfo(email);
			session.setAttribute("name", getMember.getName());
			if(getMember != null) {
				session.setAttribute("name", getMember.getName());
				session.setAttribute("company", getMember.getCompany());
			}
			request.getRequestDispatcher("mainService.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "잘못된 정보입력입니다!");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	/**
	 * 로그아웃 요청
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("\n## 로그아웃 요청");

		HttpSession session = request.getSession(false);
		if (session != null) {
			if (session.getAttribute("grade") != null) {
				session.removeAttribute("grade");
			}
			session.invalidate();
		}
		response.sendRedirect("index.jsp");
	}

	/**
	 * 회원가입 요청 메서드
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n## 회원가입 요청");

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String company = request.getParameter("company");

		if (errorGenerate(request, response, email, "이메일를 입력해주세요!") != true) {
			return;
		}
		if (errorGenerate(request, response, password, "비밀번호를 입력해주세요!") != true) {
			return;
		}
		if (errorGenerate(request, response, name, "이름을 입력해주세요!") != true) {
			return;
		}
		if (errorGenerate(request, response, mobile, "휴대전화번호를 입력해주세요!") != true) {
			return;
		}
		if (errorGenerate(request, response, company, "회사명을 입력해주세요!") != true) {
			return;
		}
		if (!(password.trim().length() >= 6 && password.trim().length() <= 30)) {
			String message = "비밀번호를 6자리 이상 다시 입력해주세요!";
			request.setAttribute("message", message);
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}
		if (!password.equals(confirmPassword)) {
			String message = "비밀번호 입력이 일치하지 않습니다!";
			request.setAttribute("message", message);
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}
		int result = memberService.join(new Member(email, password, name, mobile, company, "G", 0));
		if (result != 0) {
			request.setAttribute("email", email);
			request.setAttribute("password", password);
			request.setAttribute("name", name);
			request.setAttribute("mobile", mobile);
			request.setAttribute("company", company);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			;
		} else {
			request.setAttribute("message", "이미 존재하는 이메일 혹은 이미 존재하는 전화번호 입니다!");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	protected boolean errorGenerate(HttpServletRequest request, HttpServletResponse response, String valuableName,
			String message) throws ServletException, IOException {
		if (valuableName == null || valuableName.trim().length() == 0) {
			// 오류메세지 응답위한 설정
			System.out.println("errorGenerate function");
			request.setAttribute("message", message);
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return false;
		}
		return true;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		process(request, response);
	}
}