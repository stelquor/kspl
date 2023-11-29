package controller;

import service.MemberMM;
import service.MovingPage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Forward;

@WebServlet({"/memberdelete", "/memberlist", "/memberinfo", "/login", "/loginfrm", "/joinfrm", "/join", "/logout", "/main" })
public class HomeContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getServletPath();
		System.out.println("cmd=" + cmd);
		// 회원관리 서비스클래스
		MemberMM mm = new MemberMM(request, response);
		// 게시판관리 서비스클래스
		MovingPage mp = new MovingPage(request, response);

		Forward fw = null;
		if (cmd.equals("/loginfrm")) {
			fw = mp.showLoginFrm();
		} else if (cmd.equals("/login")) {
			fw = mm.login();
		} else if (cmd.equals("/joinfrm")) {
			fw = mp.showJoinFrm();
		} else if (cmd.equals("/join")) {
			fw = mm.join();
		} else if (cmd.equals("/logout")) {
			fw = mm.logout();
		} else if (cmd.equals("/memberlist")) {
			fw = mm.getMemberlist();
		} else if (cmd.equals("/memberinfo")) {  //id
			fw = mm.getMemberlnfo();
		}else if (cmd.equals("/memberdelete")) {
			fw = mm.deleteData();
		}
		if (fw != null) {
			if (fw.isRedirect()) { // true: redirect
				response.sendRedirect(fw.getPath()); // 새로운 req, res객체,주소창 새url
			} else {
				request.getRequestDispatcher(fw.getPath()).forward(request, response); // 기존 req, res객체사용,주소창 갱신안됨
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
