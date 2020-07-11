package fresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fresh.model.BeanDiscount_infor;
import fresh.model.BeanGoods;
import fresh.model.BeanGoods_discount;
import fresh.model.BeanOrder_detail;
import fresh.util.DBUtil;

public class Order_detailManager {
	public static void main(String[] args) throws Exception {
	}
	
	public BeanOrder_detail loadonce(int Order_num,int Goods_num) throws Exception {
		if("".equals(String.valueOf(Order_num))) throw new Exception("订单编号不可为空");
		if("".equals(String.valueOf(Goods_num))) throw new Exception("订单编号不可为空");
		Connection conn = null;
		BeanOrder_detail  bod = new BeanOrder_detail();
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM order_detail\r\n" + 
					"WHERE order_num=? and Goods_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Order_num);
			pst.setInt(2, Goods_num);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()) {
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
	
	public List<BeanOrder_detail> loadbyOrder_num(int Order_num) throws Exception {
		List<BeanOrder_detail> result = new ArrayList<BeanOrder_detail>();
		if("".equals(String.valueOf(Order_num))) throw new Exception("订单编号不可为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM order_detail\r\n" + 
					"WHERE order_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Order_num);
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
	
	public float reloadPrice_count(BeanOrder_detail bod) throws Exception {
		if(bod==null) throw new Exception("订单不存在");
		float ori_price=0;
		float sum_price=0;
		float pro_price=0;
		float count = 0;
		int pro_count = 0;
		
		java.util.Date today = new Date();
		
		Goods_orderManager gom = new Goods_orderManager();
		BeanGoods bg = new BeanGoods();
		UserManager um = new UserManager();
		GoodsManager gm = new GoodsManager();
		bg = gm.loadbyGoodsnum(bod.getGoods_num());
		
		//传输会员价
		if(!("".equals(String.valueOf(um.loadbyUser_num(gom.LoadbyOrder_num(bod.getOrder_num()).getUser_num()).getVip_ddl()))
				)&&um.loadbyUser_num(gom.LoadbyOrder_num(bod.getOrder_num()).getUser_num()).getVip_ddl().getTime() >= today.getTime())
			ori_price = bg.getVip_price();
		else {
			ori_price = bg.getGoods_price();
		}
		
		PromotionManager pm = new PromotionManager();
		if(!(pm.LoadByGoods_num(bod.getGoods_num())==null)) {
			pro_price = pm.LoadByGoods_num(bod.getGoods_num()).getPro_price();
			pro_count = pm.LoadByGoods_num(bod.getGoods_num()).getPro_count();
		}
		sum_price = pro_price * pro_count + ori_price * (bod.getOrder_count() - pro_count);
		
		BeanDiscount_infor bdi = new BeanDiscount_infor();
		DiscountManager dm = new DiscountManager();
		bdi = dm.loadbyNum(bod.getDis_num());
		
		BeanGoods_discount bgd = new BeanGoods_discount();
		Goods_discountManager bdm = new Goods_discountManager();
		bgd = bdm.LoadByGoods_Dis_num(bod.getGoods_num(), bod.getDis_num());
		if(bgd.getStart_Date().getTime() > today.getTime()) throw new Exception("此商品满折优惠未到开始时间，无法使用");
		if(bgd.getEnd_Date().getTime() < today.getTime()) throw new Exception("此商品满折优惠已过期，无法使用");
 		
		
		
		if(bod.getOrder_count()>=bdi.getDis_count()) {
			sum_price = sum_price * bdi.getDicount();
			count = bdi.getDicount();
		}
		
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE order_detail\r\n" + 
					"SET order_price=?,order_dis=?\r\n" + 
					"WHERE order_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setFloat(1, sum_price);
			pst.setFloat(2, count);
			pst.setInt(3, bod.getOrder_num());
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
		return sum_price;
	}
	
	public void Add(int Order_num,int Dis_num,int Goods_num,int order_count) throws Exception {
		if("".equals(String.valueOf(Order_num))) throw new Exception("订单编号不可为空");
		if("".equals(String.valueOf(Goods_num))) throw new Exception("商品编号不可为空");
		if("".equals(String.valueOf(order_count))) throw new Exception("商品数量不可为空");
		if(order_count <= 0) throw new Exception("商品数量不得为0或为负数");
		BeanGoods bg = new BeanGoods();
		GoodsManager gm = new GoodsManager();
		bg = gm.loadbyGoodsnum(Goods_num);
		if(order_count > bg.getGoods_count()) throw new Exception("商品库存不足，请重新输入购买数量");
		
		if(!"".equals(String.valueOf(Dis_num))) {
			BeanDiscount_infor bdi = new BeanDiscount_infor();
			DiscountManager dm = new DiscountManager();
			bdi = dm.loadbyNum(Dis_num);
			
			BeanGoods_discount bgd = new BeanGoods_discount();
			Goods_discountManager bdm = new Goods_discountManager();
			bgd = bdm.LoadByGoods_Dis_num(Goods_num, Dis_num);
		}

		BeanOrder_detail bod = new BeanOrder_detail();
		bod.setOrder_num(Order_num);
		bod.setDis_num(Dis_num);
		bod.setGoods_num(Goods_num);
		bod.setOrder_count(order_count);
		
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT\r\n" + 
					"INTO order_detail(order_num,Dis_num,goods_num,order_count)\r\n" + 
					"VALUES(?,?,?,?)";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Order_num);
			pst.setInt(2, Dis_num);
			pst.setInt(3, Goods_num);
			pst.setInt(4, order_count);
			pst.execute();
			Order_detailManager odm = new Order_detailManager();
			System.out.println(bod.getGoods_num());
			odm.reloadPrice_count(bod);
			
			gm.SubGoods_count(Goods_num, order_count);
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
	
	public void Delete(int Order_num) throws Exception {
		if("".equals(String.valueOf(Order_num))) throw new Exception("订单编号不可为空");
		Order_detailManager odm = new Order_detailManager();
		if(odm.loadbyOrder_num(Order_num).size()==0) throw new Exception("不存在此订单");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE \r\n" + 
					"FROM order_detail\r\n" + 
					"WHERE order_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Order_num);
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
	
	public void Delete(int Order_num,int Goods_num) throws Exception {
		if("".equals(String.valueOf(Order_num))) throw new Exception("订单编号不可为空");
		Order_detailManager odm = new Order_detailManager();
		if(odm.loadbyOrder_num(Order_num).size()==0) throw new Exception("不存在此订单");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE \r\n" + 
					"FROM order_detail\r\n" + 
					"WHERE order_num=? and goods_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Order_num);
			pst.setInt(2, Goods_num);
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
	
	public void ChangeDis(int Order_num,int Goods_num,int Dis_num) throws Exception{
		if("".equals(String.valueOf(Order_num))) throw new Exception("订单编号不可为空");
		Order_detailManager odm = new Order_detailManager();
		if(odm.loadbyOrder_num(Order_num).size()==0) throw new Exception("不存在此订单");
		if((new Goods_discountManager()).LoadByGoods_Dis_num(Goods_num, Dis_num)==null) throw new Exception("不存在此满折组合");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE order_detail \r\n" + 
					"SET Dis_num=?\r\n" + 
					"WHERE order_num=? and goods_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Dis_num);
			pst.setInt(2, Order_num);
			pst.setInt(3, Goods_num);
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
	
	public void ChangeGoods(int Order_num,int Goods_num,int newGoods_num) throws Exception{
		if("".equals(String.valueOf(Order_num))) throw new Exception("订单编号不可为空");
		Order_detailManager odm = new Order_detailManager();
		if(odm.loadbyOrder_num(Order_num).size()==0) throw new Exception("不存在此订单");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE order_detail \r\n" + 
					"SET Goods_num=?\r\n" + 
					"WHERE order_num=? and goods_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, newGoods_num);
			pst.setInt(2, Order_num);
			pst.setInt(3, Goods_num);
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
	
	public void ChangeCount(int Order_num,int Goods_num,int Count) throws Exception{
		if("".equals(String.valueOf(Order_num))) throw new Exception("订单编号不可为空");
		Order_detailManager odm = new Order_detailManager();
		if(odm.loadbyOrder_num(Order_num).size()==0) throw new Exception("不存在此订单");
		GoodsManager gm = new GoodsManager();
		BeanGoods bg = gm.loadbyGoodsnum(Goods_num);
		if(Count > bg.getGoods_count()) throw new Exception("购买数量大于库存数量,请更改");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE order_detail \r\n" + 
					"SET order_count=?\r\n" + 
					"WHERE order_num=? and goods_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Count);
			pst.setInt(2, Order_num);
			pst.setInt(3, Goods_num);
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
}
