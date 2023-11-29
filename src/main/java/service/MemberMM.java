package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.Forward;
import dao.MemberDao;

public class MemberMM {
	HttpServletRequest request;
	HttpServletResponse response;

	public MemberMM(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public Forward login() {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		MemberDao mDao = new MemberDao(); // DB접속
		HttpSession session = request.getSession();
		Forward fw = new Forward();
		HashMap<String, String> hMap = new HashMap<>();
		hMap.put("id", id);
		hMap.put("pw", pw);
		if (mDao.login(hMap)) {
			session.setAttribute("id", id);
			session.setAttribute("logout", makeLogoutHtml());
			session.setAttribute("accessResult", makeResultHtml());
			fw.setPath("/main.jsp");
			fw.setRedirect(true);
		} else {
			request.setAttribute("msg", "로그인 실패");
			fw.setPath("/loginFrm.jsp");
			fw.setRedirect(false);
		}
		mDao.close();
		return fw;
	}

	private String makeResultHtml() {
		// 관리자:admin,
		StringBuilder sb = new StringBuilder();
		String id = request.getSession().getAttribute("id").toString();
		if (id.equals("admin")) {
			sb.append("<a href='/memberlist'>(관리자)회원목록보기</a>");
		} else {
			sb.append("<a href='/memberinfo?id=" + id + "'>내정보 확인</a>");
		}
		return sb.toString();
	}

	private String makeLogoutHtml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<div>");
		sb.append("<a href='logout'>로그아웃");
		sb.append("</div>");
		return sb.toString();
	}
	public Forward join() {
		// 비지니스(업무) 로직
		// Dao: Data Access Object
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		System.out.printf("%s,%s,%s,%s\n", id, pw, name, gender);
		// Member Bean or HashMap<k,v>
		HashMap<String, String> hMap = new HashMap<>();
		hMap.put("id", id);
		hMap.put("pw", pw);
		hMap.put("name", name);
		hMap.put("gender", gender);

		MemberDao mDao = new MemberDao(); // db접속
		boolean result;
		result = mDao.join(hMap); // db 로직-->-->insert작업--->
		mDao.close();
		Forward fw = new Forward();
		if (result) {
			System.out.println("회원가입 성공");
			fw.setPath("/loginFrm.jsp");
			fw.setRedirect(true);
		} else {
			// System.out.println("회원가입 실패");
			request.setAttribute("msg", "회원가입실패");
			fw.setPath("/joinFrm.jsp");
			fw.setRedirect(false);
		}
		return fw;
	}

	public Forward logout() {
		request.getSession().invalidate();
		Forward fw=new Forward();
		fw.setPath("/"); //"index.jsp"
		fw.setRedirect(true);
		return fw;
	}

	public Forward getMemberlnfo() throws JsonProcessingException{
		String id = request.getParameter("id");
		MemberDao mDao = new MemberDao();
		HashMap<String, String> mInfo = mDao.getMemberInfo(id);
		mDao.close();
		Forward fw = new Forward();
		if(mInfo!=null) {
			//1.makeHtml for
			//2.javaScript for  //java객체--->json변환 : jackson
			//3.jstl for
			String json = new ObjectMapper().writeValueAsString(mInfo);
			request.setAttribute("mb", json);
//			request.setAttribute("mb", mInfo);//jstl 제어
			fw.setPath("/memberInfo.jsp");
			fw.setRedirect(false);
		}else {
			fw.setPath("/main.jsp");
			fw.setRedirect(true);
		}
		
		return fw;
	}
	public Forward getMemberlist() throws JsonProcessingException {
		MemberDao mDao=new MemberDao();
		ArrayList<String> mList=mDao.getMemberList(); //["aaa","bbb",...]
		mDao.close();
		Forward fw=new Forward();
		if(mList!=null) {
			//1.makeHtml for
			//2.javaScript for  //java객체--->json변환 : jackson
			//3.jstl for
			ObjectMapper om = new ObjectMapper();
			////java객체--->json변환
			String json=om.writeValueAsString(mList);
			System.out.println("json:"+json);
			request.setAttribute("mList",json);
			request.setAttribute("mArrayList",mList); //ArrayList객체
			fw.setPath("/memberList.jsp");
			fw.setRedirect(false);
		}else {
			fw.setPath("/main.jsp");
			fw.setRedirect(true);
		}
		return fw;
	}
	public Forward deleteData() {
		String id = request.getParameter("id");
		MemberDao mDao = new MemberDao();
		HashMap<String, String> mDel = mDao.deleteData(id);
		mDao.close();
		Forward fw = new Forward();
		fw.setPath("/memberlist");
		return fw;
	}
}
