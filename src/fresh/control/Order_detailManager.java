package fresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fresh.model.BeanOrder_detail;
import fresh.util.DBUtil;

public class Order_detailManager {
	public BeanOrder_detail loadbyOrder_num(int Order_num) throws Exception {
		if("".equals(String.valueOf(Order_num))) throw new Exception("订单编号不可为空");
		BeanOrder_detail  bod = new BeanOrder_detail();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM order_detail\r\n" + 
					"WHERE order_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Order_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next()) throw new Exception("此订单不存在");
			else {
				bod.setOrder_num(rs.getInt(1));
				bod.setDis_num(rs.getInt(2));
				bod.setGoods_num(rs.getInt(3));
				bod.setOrder_count(rs.getInt(4));
				bod.setOrder_price(rs.getFloat(5));
				bod.setOrder_dis(rs.getFloat(6));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return bod;
	}
	
	public List<BeanOrder_detail> loadbyGoods_num(int Goods_num) throws Exception {
		List<BeanOrder_detail> result = new ArrayList<BeanOrder_detail>();
		if("".equals(String.valueOf(Goods_num))) throw new Exception("商品编号不可为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM order_detail\r\n" + 
					"WHERE goods_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Goods_num);
			java.sql.ResultSet rs = pst.executeQuery();
			while {
				BeanOrder_detail  bod = new BeanOrder_detail();
				bod.setOrder_num(rs.getInt(1));
				bod.setDis_num(rs.getInt(2));
				bod.setGoods_num(rs.getInt(3));
				bod.setOrder_count(rs.getInt(4));
				bod.setOrder_price(rs.getFloat(5));
				bod.setOrder_dis(rs.getFloat(6));
				result.add(bod);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public List<BeanOrder_detail> loadbyDis_num(int Dis_num) throws Exception {
		List<BeanOrder_detail> result = new ArrayList<BeanOrder_detail>();
		if("".equals(String.valueOf(Dis_num))) throw new Exception("满折编号不可为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM order_detail\r\n" + 
					"WHERE Dis_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Dis_num);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				BeanOrder_detail  bod = new BeanOrder_detail();
				bod.setOrder_num(rs.getInt(1));
				bod.setDis_num(rs.getInt(2));
				bod.setGoods_num(rs.getInt(3));
				bod.setOrder_count(rs.getInt(4));
				bod.setOrder_price(rs.getFloat(5));
				bod.setOrder_dis(rs.getFloat(6));
				result.add(bod);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public List<BeanOrder_detail> loadAll() throws Exception {
		List<BeanOrder_detail> result = new ArrayList<BeanOrder_detail>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM order_detail";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				BeanOrder_detail  bod = new BeanOrder_detail();
				bod.setOrder_num(rs.getInt(1));
				bod.setDis_num(rs.getInt(2));
				bod.setGoods_num(rs.getInt(3));
				bod.setOrder_count(rs.getInt(4));
				bod.setOrder_price(rs.getFloat(5));
				bod.setOrder_dis(rs.getFloat(6));
				result.add(bod);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return result;
	}
}
