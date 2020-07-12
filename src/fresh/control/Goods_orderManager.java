package fresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fresh.model.BeanCoupon;
import fresh.model.BeanGoods;
import fresh.model.BeanGoods_order;
import fresh.model.BeanOrder_detail;
import fresh.model.BeanPromotion;
import fresh.util.DBUtil;

public class Goods_orderManager {
	
	public static void main(String[] args) throws Exception {
		Goods_orderManager gom = new Goods_orderManager();
		System.out.println(gom.LoadPrebyUser(2));
	}
	
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
			while(rs.next()) {
				BeanGoods_order bgo = new BeanGoods_order();
				bgo.setOrder_num(rs.getInt(1));
				bgo.setUser_num(rs.getInt(2));
				bgo.setShip_num(rs.getInt(3));
				bgo.setCoupon_num(rs.getInt(4));
				bgo.setOri_price(rs.getFloat(5));
				bgo.setFin_price(rs.getFloat(6));
				bgo.setService_time(rs.getTimestamp(7));
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
				bgo.setService_time(rs.getTimestamp(7));
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
			while(rs.next()) {
				BeanGoods_order bgo = new BeanGoods_order();
				bgo.setOrder_num(rs.getInt(1));
				bgo.setUser_num(rs.getInt(2));
				bgo.setShip_num(rs.getInt(3));
				bgo.setCoupon_num(rs.getInt(4));
				bgo.setOri_price(rs.getFloat(5));
				bgo.setFin_price(rs.getFloat(6));
				bgo.setService_time(rs.getTimestamp(7));
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
			while(rs.next()){
				BeanGoods_order bgo = new BeanGoods_order();
				bgo.setOrder_num(rs.getInt(1));
				bgo.setUser_num(rs.getInt(2));
				bgo.setShip_num(rs.getInt(3));
				bgo.setCoupon_num(rs.getInt(4));
				bgo.setOri_price(rs.getFloat(5));
				bgo.setFin_price(rs.getFloat(6));
				bgo.setService_time(rs.getTimestamp(7));
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
	
	public void reload_price(int Order_num,int Coupon_num) throws Exception{
		if("".equals(String.valueOf(Order_num))) throw new Exception("订单编号不可为空");
		Order_detailManager odm = new Order_detailManager();
		List<BeanOrder_detail> result = new ArrayList<BeanOrder_detail>();
		Goods_orderManager gom = new Goods_orderManager();
		BeanCoupon bc = new BeanCoupon();
		CouponManager cm = new CouponManager();
		float Ori_price = 0;
		float fin_price = 0;
		Connection conn = null;
		try {
			result = odm.loadbyOrder_num(Order_num);
			for(int i=0;i<result.size();i++) {
				Ori_price=Ori_price+result.get(i).getOrder_price();
				fin_price=Ori_price;
			}
			if(Coupon_num!=0) {
				bc = cm.loadByCoupon_num(Coupon_num);
				if(fin_price >= bc.getApp_amount()) {
					fin_price = fin_price - bc.getDed_amount();
				}
			}
			conn = DBUtil.getConnection();
			String sql = "UPDATE goods_order\r\n" + 
					"SET Ori_price = ?,Fin_price = ?\r\n" + 
					"WHERE order_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setFloat(1, Ori_price);
			pst.setFloat(2, fin_price);
			pst.setInt(3, Order_num);
			pst.execute();
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
	}
	
	public void add(int User_num) throws Exception {
		if("".equals(String.valueOf(User_num))) throw new Exception("用户编号不可为空");
		
		java.util.Date today = new Date();
		
		UserManager um = new UserManager();
		um.loadbyUser_num(User_num);
		
//		ShippingManager sm = new ShippingManager();
//		sm.loadbyShip_num(Ship_num);
		
		BeanCoupon bc = new BeanCoupon();
//		if(!"0".equals(String.valueOf(Coupon_num))) {
//			CouponManager cm = new CouponManager();
//			bc = cm.loadByCoupon_num(Coupon_num);
//			if(bc.getCoupon_start_date().getTime() > today.getTime()) throw new Exception("优惠券未到可使用时间");
//			if(bc.getCoupon_end_date().getTime() < today.getTime()) throw new Exception("优惠券已过期");
//		}
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql= "INSERT \r\n" + 
					"into goods_order(User_num,Order_state)\r\n" + 
					"values(?,?)";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, User_num);
//			pst.setInt(2, Ship_num);
//			pst.setInt(3, Coupon_num);
			pst.setString(2, "购物车中");
			pst.execute();
			pst.close();
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
	}
	
	public void ChangeSongda(int order_num) throws Exception {
		if("".equals(String.valueOf(order_num))) throw new Exception("订单编号不可为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE goods_order\r\n" + 
					"SET Order_state = ?\r\n" + 
					"WHERE order_num = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "已送达");
			pst.setInt(2, order_num);
			pst.execute();
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
	}
	
	public void ChangeShip(int order_num,int ship_num) throws Exception {
		if("".equals(String.valueOf(order_num))) throw new Exception("订单编号不可为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE goods_order\r\n" + 
					"SET ship_num = ?\r\n" + 
					"WHERE order_num = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, ship_num);
			pst.setInt(2, order_num);
			pst.execute();
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
	}
	
	public void ChangeCoupon(int order_num,int Coupon_num) throws Exception {
		if("".equals(String.valueOf(order_num))) throw new Exception("订单编号不可为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE goods_order\r\n" + 
					"SET Coupon_num = ?\r\n" + 
					"WHERE order_num = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Coupon_num);
			pst.setInt(2, order_num);
			pst.execute();
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
	}
	
	public void ChangeTime(int order_num,String end_date) throws Exception {
		if("".equals(String.valueOf(order_num))) throw new Exception("订单编号不可为空");
		Connection conn = null;
		try {
			SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Date end = sdf2.parse(end_date);
			conn = DBUtil.getConnection();
			String sql = "UPDATE goods_order\r\n" + 
					"SET Service_time = ?\r\n" + 
					"WHERE order_num = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setTimestamp(1, new java.sql.Timestamp(end.getTime()));
			pst.setInt(2, order_num);
			pst.execute();
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
	}
	
	public void ChangeXiadan(int order_num) throws Exception {
		if("".equals(String.valueOf(order_num))) throw new Exception("订单编号不可为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE goods_order\r\n" + 
					"SET Order_state = ?\r\n" + 
					"WHERE order_num = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "下单");
			pst.setInt(2, order_num);
			pst.execute();
			Order_detailManager odm = new Order_detailManager();
			List<BeanOrder_detail> bd = odm.loadbyOrder_num(order_num);
			GoodsManager gm = new GoodsManager();
			for(int i=0;i<bd.size();i++) {
				gm.SubGoods_count(bd.get(i).getGoods_num(), bd.get(i).getOrder_count());
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
	}
	
	public void Tuihuoing(int order_num) throws Exception {
		if("".equals(String.valueOf(order_num))) throw new Exception("订单编号不可为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE goods_order\r\n" + 
					"SET Order_state = ?\r\n" + 
					"WHERE order_num = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "退货中");
			pst.setInt(2, order_num);
			pst.execute();
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
	}
	
	public void ChangeTuihuo(int order_num) throws Exception {
		if("".equals(String.valueOf(order_num))) throw new Exception("订单编号不可为空");
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE goods_order\r\n" + 
					"SET Order_state = ?\r\n" + 
					"WHERE order_num = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "退货");
			pst.setInt(2, order_num);
			pst.execute();
			pst.close();
			Order_detailManager odm = new Order_detailManager();
			List<BeanOrder_detail> bd = odm.loadbyOrder_num(order_num);
			GoodsManager gm = new GoodsManager();
			for(int i=0;i<bd.size();i++) {
				gm.AddGoods_count(bd.get(i).getGoods_num(), bd.get(i).getOrder_count());
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
	}
	
	public void Delete(int order_num) throws Exception {
		if("".equals(String.valueOf(order_num))) throw new Exception("订单编号不可为空");
		Order_detailManager odm = new Order_detailManager();
		Goods_evaluationManager gm = new Goods_evaluationManager();
		if(!(odm.loadbyOrder_num(order_num).size()==0)) throw new Exception("订单详情表中仍有此订单，拒绝删除");
		if(!(gm.loadbyOrder_num(order_num).size()==0)) throw new Exception("订单评价表中仍有此订单，拒绝删除");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE\r\n" + 
					"FROM goods_order\r\n" + 
					"WHERE order_num = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, order_num);
			pst.execute();
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
	}
	
	public int Loadorder_num(int User_num) throws Exception{
		if("".equals(String.valueOf(User_num))) throw new Exception("用户编号不可为空");
		int num=0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT COUNT(order_num)\r\n" + 
					"FROM goods_order\r\n" + 
					"WHERE User_num=? and Order_state = '已送达'\r\n" + 
					"GROUP BY User_num";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, User_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				num=rs.getInt(1);
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
		return num;
	}
	
	public float LoadPrebyUser(int User_num) throws Exception {
		if("".equals(String.valueOf(User_num))) throw new Exception("用户编号不可为空");
		float money=0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			Goods_orderManager gom = new Goods_orderManager();
			List<BeanGoods_order> bgo = gom.LoadbyUser_num(User_num);
			for(int i=0;i<bgo.size();i++) {
				float Ori_price=0;
				if("已送达".equals(bgo.get(i).getOrder_state())) {
    				Order_detailManager odm = new Order_detailManager();
    				List<BeanOrder_detail> bod = odm.loadbyOrder_num(bgo.get(i).getOrder_num());
    				for(int j=0;j<bod.size();j++) {
    					if(bod.get(j).getDis_num()!=0) {
    						UserManager um = new UserManager();
    						GoodsManager gm = new GoodsManager();
    						Goods_discountManager gdm = new Goods_discountManager();
    						BeanGoods bg = gm.loadbyGoodsnum(bod.get(j).getGoods_num());
    						Ori_price = Ori_price+bg.getGoods_price()*bod.get(j).getOrder_count();
    					}
    				}
    				money = money+Ori_price-bgo.get(i).getFin_price();
				}
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
		return money;
	}
	
	public float LoadMoneybyUser(int User_num) throws Exception {
		if("".equals(String.valueOf(User_num))) throw new Exception("用户编号不可为空");
		float money=0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			Goods_orderManager gom = new Goods_orderManager();
			List<BeanGoods_order> bgo = gom.LoadbyUser_num(User_num);
			for(int i=0;i<bgo.size();i++) {
				if("已送达".equals(bgo.get(i).getOrder_state())) {
                	money=money+bgo.get(i).getFin_price();
				}
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
		return money;
	}
}
