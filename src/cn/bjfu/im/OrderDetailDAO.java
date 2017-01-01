package cn.bjfu.im;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

public class OrderDetailDAO extends BaseDAO {
	public boolean addOrderDetail(ArrayList<OrderDetailVO> ODarr) {// 添加订单明细
		Connection conn = open();
		boolean f = false;
		String sql = "insert into order_detail (oid,gid,amount) values(?,?,?) ";
		try {
			for (OrderDetailVO vo : ODarr) {
				PreparedStatement ps = (PreparedStatement) conn
						.prepareStatement(sql);
				ps.setInt(1, vo.getOid());
				ps.setInt(2, vo.getGid());
				ps.setInt(3, vo.getAmount());
				int count = ps.executeUpdate();
				if (count > 0) {// 添加成功
					f = true;
				} else {
					f = false;
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
		return f;
	}
	public ArrayList<OrderDetailVO> getOrderDetail(int oid) {// 读取OrderDetail信息，根据oid返回所有相关订单信息list
		Connection conn = open();
		ArrayList<OrderDetailVO> od = new ArrayList<OrderDetailVO>();
		String sql = "select gid,amount from order_detail where oid=?";
		try {
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			ps.setInt(1, oid);
			ResultSet rs = (ResultSet) ps.executeQuery();
			while (rs.next()) {
				int gid = rs.getInt("gid");
				int amount = rs.getInt("amount");
				od.add(new OrderDetailVO(oid,gid,amount));
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
		return od;
	}
}
