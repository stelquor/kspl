package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Forward;

public class MovingPage {
	HttpServletRequest request;
	HttpServletResponse response;
	public MovingPage(HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
	}
	public Forward showLoginFrm() {
		Forward fw=new Forward();
		//로그인 여부 판단
		if(request.getSession().getAttribute("id")!=null) {
			fw.setPath("/main.jsp");
			fw.setRedirect(true);
		}else {
			fw.setPath("/loginFrm.jsp");
			fw.setRedirect(true);
		}
		return fw;
	}
	public Forward showJoinFrm() {
		Forward fw=new Forward();
		//로그인 여부 판단
		if(request.getSession().getAttribute("id")!=null) {
			fw.setPath("/main.jsp");
			fw.setRedirect(true);
		}else {
			fw.setPath("/joinFrm.jsp");
			fw.setRedirect(true);
		}
		return fw;
	}

}
