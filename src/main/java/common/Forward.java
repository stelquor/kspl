package common;
//포워딩 정보
public class Forward {
	//true: redirect포워딩
	//false: (dispatcher)포워딩
	private boolean isRedirect; //false
	private String path; //포워딩할 뷰 페이지 or url
	
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
}
