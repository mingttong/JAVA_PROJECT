package cn.bjfu.im;

public class MemberVO { //用户的VO类，包括用户名各密码
	private String name;
	private String pwd;
	public MemberVO(String t_name,String t_pwd	) {
		// 构造方法
		this.name=t_name;
		this.pwd=t_pwd;
	}
	public String getName() {//get,set方法
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {//toString方法
		return "MemberVO [name=" + name + ", pwd=" + pwd + "]";
	}
	
	
		
}
