package cn.bjfu.im.dao;

import java.sql.Connection;
import java.sql.SQLException;

import cn.bjfu.im.vo.MemberVO;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

//import cn.bjfu.im.MemberVO;

public class MemberDAO extends BaseDAO {

	public boolean isNoneByName(String name) {// 注册前判断是否有重复登录名的方法
		Connection conn = open();
		boolean f = false;
		String sql = "select count(*) as count from member_info where name =?";
		try {
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = (ResultSet) ps.executeQuery();
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("count");
			}
			if (count == 0)
				f = true;// 没有这个名字的用户，返回true
			else
				f = false;// 有这个名字的用户，返回false
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return f;
	}

	public boolean add(MemberVO vo) {// 注册用户的方法
		Connection conn = open();
		boolean f = false;
		try {
			// 带问号的sql语句
			String sql = "insert into member_info (name,pwd) values (?,?)";
			// 3.获取PreparedStatement对象，用以执行sql语句
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			// sql的?替换为具体值, setInt, setString,序号从1开始
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getPwd());
			// 4.通过PreparedStatement对象执行sql语句
			int count = ps.executeUpdate();// 执行sql，
			// 返回：到数据库中有几条记录受到了影响
			if (count > 0) {
				f = true;// 添加成功返回true
			} else {
				f = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.close(conn);
		}
		return f;
	}

	public boolean updatePwd(MemberVO vo) {// 更新密码的方法

		Connection conn = this.open();
		boolean f = false;
		// 带问号的sql语句
		String sql = "update member_info set pwd=?  where name=?";
		// 3.获取PreparedStatement对象，用以执行sql语句
		try {
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			// sql重的?替换为具体值, setInt, setString,序号从1开始
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getPwd());
			// 4.通过PreparedStatement对象执行sql语句
			int count = ps.executeUpdate();// 执行sql，
			// 返回：到数据库中有几条记录受到了影响
			if (count > 0)
				f = true;// 更新成功返回true
			else
				f = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return f;
	}

	public int findMember(MemberVO vo) {// 登录时使用的判断方法//正确时返回mid，错误返回-1
		Connection conn = open();
		int mid = 0;
		String sql = "select count(*) as count from member_info where name =? and pwd =?";
		try {
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getPwd());
			ResultSet rs = (ResultSet) ps.executeQuery();
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("count");
			}
			if (count == 0) {
				mid = -1;// 用户名或密码不正确
			} else {// 正确
				String sql2 = "select mid from member_info where name =? and pwd =?";
				PreparedStatement ps2 = (PreparedStatement) conn
						.prepareStatement(sql2);
				ps2.setString(1, vo.getName());
				ps2.setString(2, vo.getPwd());
				ResultSet rs2 = (ResultSet) ps2.executeQuery();
				while (rs2.next()) {
					mid = rs2.getInt("mid");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mid;
	}
	 public static void main(String[] args){//test
	 
	 }

	// RegisterDAO sDAO=new RegisterDAO();
	// RegisterVO vo = sDAO.findByName("zxm");
	// System.out.println(vo.toString());
	// }

}