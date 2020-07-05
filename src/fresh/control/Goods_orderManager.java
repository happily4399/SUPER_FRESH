package fresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fresh.model.BeanGoods_order;
import fresh.util.DBUtil;

public class Goods_orderManager {
	public List<BeanGoods_order> LoadbyCoupon_num(int Coupon_num) throws Exception {
		List<BeanGoods_order> result = new ArrayList<BeanGoods_order>();
		if("".equals(Coupon_num)) throw new Exception("优惠券编号不能为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM goods_order\r\n" + 
					"WHERE Coupon_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Coupon_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!(rs.next())) throw new Exception("此商品_订单记录不存在");
			else {
				BeanGoods_order bgo = new BeanGoods_order();
				bgo.setOrder_num(rs.getInt(1));
				bgo.setUser_num(rs.getInt(2));
				bgo.setShip_num(rs.getInt(3));
				bgo.setCoupon_num(rs.getInt(4));
				bgo.setOri_price(rs.getFloat(5));
				bgo.setFin_price(rs.getFloat(6));
				bgo.setService_time(rs.getDate(7));
				bgo.setOrder_state(rs.getString(8));
				result.add(bgo);
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
	
	public BeanGoods_order LoadbyOrder_num(int Order_num) throws Exception {
		BeanGoods_order bgo = new BeanGoods_order();
		if("".equals(Order_num)) throw new Exception("订单编号不能为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM goods_order\r\n" + 
					"WHERE Order_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Order_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!(rs.next())) throw new Exception("此商品_订单记录不存在");
			else {
				bgo.setOrder_num(rs.getInt(1));
				bgo.setUser_num(rs.getInt(2));
				bgo.setShip_num(rs.getInt(3));
				bgo.setCoupon_num(rs.getInt(4));
				bgo.setOri_price(rs.getFloat(5));
				bgo.setFin_price(rs.getFloat(6));
				bgo.setService_time(rs.getDate(7));
				bgo.setOrder_state(rs.getString(8));
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
		return bgo;
	}
	
	public List<BeanGoods_order> LoadbyShip_num(int Ship_num) throws Exception {
		List<BeanGoods_order> result = new ArrayList<BeanGoods_order>();
		if("".equals(Ship_num)) throw new Exception("地址编号不能为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM goods_order\r\n" + 
					"WHERE Order_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Ship_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!(rs.next())) throw new Exception("此商品_订单记录不存在");
			else {
				BeanGoods_order bgo = new BeanGoods_order();
				bgo.setOrder_num(rs.getInt(1));
				bgo.setUser_num(rs.getInt(2));
				bgo.setShip_num(rs.getInt(3));
				bgo.setCoupon_num(rs.getInt(4));
				bgo.setOri_price(rs.getFloat(5));
				bgo.setFin_price(rs.getFloat(6));
				bgo.setService_time(rs.getDate(7));
				bgo.setOrder_state(rs.getString(8));
				result.add(bgo);
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
	
	public List<BeanGoods_order> LoadbyUser_num(int User_num) throws Exception {
		List<BeanGoods_order> result = new ArrayList<BeanGoods_order>();
		if("".equals(User_num)) throw new Exception("用户编号不能为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM goods_order\r\n" + 
					"WHERE User_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, User_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!(rs.next())) throw new Exception("此商品_订单记录不存在");
			else {
				BeanGoods_order bgo = new BeanGoods_order();
				bgo.setOrder_num(rs.getInt(1));
				bgo.setUser_num(rs.getInt(2));
				bgo.setShip_num(rs.getInt(3));
				bgo.setCoupon_num(rs.getInt(4));
				bgo.setOri_price(rs.getFloat(5));
				bgo.setFin_price(rs.getFloat(6));
				bgo.setService_time(rs.getDate(7));
				bgo.setOrder_state(rs.getString(8));
				result.add(bgo);
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
