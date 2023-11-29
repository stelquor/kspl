package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.coyote.Request;

import common.JdbcUtil;

public class MemberDao {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public MemberDao() {
		this.con=JdbcUtil.getConnection();
	}
	public boolean join(HashMap<String, String> hMap) {
		String sql="""
				insert into member
				values(?,?,?,?)
				""";
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, hMap.get("id"));			
			pstmt.setString(2, hMap.get("pw"));			
			pstmt.setString(3, hMap.get("name"));			
			pstmt.setString(4, hMap.get("gender"));
			result=pstmt.executeUpdate(); //update, delete, insert
			if(result>0) {
				System.out.println("DB회원가입 성공");
				return true;
			}else {
				//System.out.println("DB회원가입 실패");
				//return false;
			}
		} catch (SQLException e) {
			System.out.println("join 예외 발생");
			e.printStackTrace();
		} 
		return false;
	}
	public void close() {
		JdbcUtil.dbClose(rs,pstmt,con);
	}
	public boolean login(HashMap<String, String> hMap) {
		String sql="select * from member where id=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, hMap.get("id"));
			rs=pstmt.executeQuery(); //select만
			if(rs.next()) {
				//아이디 존재
				if(rs.getString("PW").equals(hMap.get("pw"))){
					//비번까지 일치
					return true;
				}
			}
			//else {
				//아이디 부재
				//return false;
			//}
		} catch (SQLException e) {
			System.out.println("dao login 예외발생");
			e.printStackTrace();
		}
		return false;
	}
	
	public HashMap<String, String> getMemberInfo(String id) {
		String sql ="select * from member where id=?";
		HashMap<String, String> member = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				member = new HashMap<String, String>();
				member.put("id", rs.getString("ID"));
				member.put("pw", rs.getString("PW"));
				member.put("name", rs.getString("NAME"));
				member.put("gender", rs.getString("GENDER"));
				return member;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("dao getMembeInfo 예외");
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<String> getMemberList() {
		ArrayList<String> mList=null;
		String sql="SELECT ID FROM MEMBER";
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			mList=new ArrayList<>();
			while(rs.next()) {
				mList.add(rs.getString("ID"));
			}
			return mList; //성공
		} catch (SQLException e) {
			System.out.println("dao memberList 예외");
			e.printStackTrace();
		}
		return null; //실패
	}
	public HashMap<String, String> deleteData(String id) {
		String sql = "DELETE FROM MEMBER WHERE ID = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			int result = pstmt.executeUpdate();
			if (result != 0) {
				System.out.println("delete 성공");
			} else {
				System.out.println("delete 실패");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public HashMap<String, String> getPickrate(String id) {
		String sql ="select * from member where id=?";
		HashMap<String, String> member = null;
		
		return null;
	} 
}//end Dao class 



















