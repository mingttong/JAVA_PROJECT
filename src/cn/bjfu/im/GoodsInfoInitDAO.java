package cn.bjfu.im;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

public class GoodsInfoInitDAO extends BaseDAO {
	public HashMap<Integer, GoodsInfoVO> getGoodsInfo() {// 初始化读取goods信息，返回所有商品信息的map
		Connection conn = open();
		Map<Integer, GoodsInfoVO> InitMap = new HashMap<Integer, GoodsInfoVO>();
		String sql = "select gid,good,price,introduction,picture_url from goods_info ";
		try {
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			ResultSet rs = (ResultSet) ps.executeQuery();
			while (rs.next()) {
				int gid = rs.getInt("gid");
				String good = rs.getString("good");
				double price = rs.getDouble("price");
				String introduction = rs.getString("introduction");
				String pictureURL = rs.getString("picture_url");
				GoodsInfoVO vo = new GoodsInfoVO(good, price, introduction,
						pictureURL);
				InitMap.put(gid, vo);
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
		return (HashMap<Integer, GoodsInfoVO>) InitMap;
	}
}
