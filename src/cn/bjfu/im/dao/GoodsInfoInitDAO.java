package cn.bjfu.im.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import cn.bjfu.im.vo.GoodsInfoVO;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

public class GoodsInfoInitDAO extends BaseDAO {
	public HashMap<Integer, GoodsInfoVO> getAllGoodsInfo() {// 初始化读取goods信息，返回所有商品信息的map
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
	
		public GoodsInfoVO getGoodsInfo(int gidd) {// 初始化读取goods信息，返回所有商品信息的map
			Connection conn = open();
			GoodsInfoVO vo = null;
			String sql = "select gid,good,price,introduction,picture_url from goods_info where gid = ?";
			try {
				PreparedStatement ps = (PreparedStatement) conn
						.prepareStatement(sql);
				ps.setInt(1, gidd);
				ResultSet rs = (ResultSet) ps.executeQuery();
				while (rs.next()) {
					String good = rs.getString("good");
					double price = rs.getDouble("price");
					String introduction = rs.getString("introduction");
					String pictureURL = rs.getString("picture_url");
					vo = new GoodsInfoVO(good, price, introduction,
							pictureURL);
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
			return vo;
		}
}
