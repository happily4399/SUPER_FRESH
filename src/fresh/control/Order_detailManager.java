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
	
	public BeanOrder_detail loadbyOrder_num(int Order_num) throws Exception {
		if("".equals(String.valueOf(Order_num))) throw new Exception("������Ų���Ϊ��");
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
			if(!rs.next()) throw new Exception("�˶���������");
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
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		return bod;
	}
	
	public List<BeanOrder_detail> loadbyGoods_num(int Goods_num) throws Exception {
		List<BeanOrder_detail> result = new ArrayList<BeanOrder_detail>();
		if("".equals(String.valueOf(Goods_num))) throw new Exception("��Ʒ��Ų���Ϊ��");
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
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public List<BeanOrder_detail> loadbyDis_num(int Dis_num) throws Exception {
		List<BeanOrder_detail> result = new ArrayList<BeanOrder_detail>();
		if("".equals(String.valueOf(Dis_num))) throw new Exception("���۱�Ų���Ϊ��");
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
				// TODO �Զ����ɵ� catch ��
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
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public void reloadPrice_count(BeanOrder_detail bod) throws Exception {
		if(bod==null) throw new Exception("����������");
		float ori_price=0;
		float fin_price=0;
		float count = 0;
		
		java.util.Date today = new Date();
		
		BeanGoods bg = new BeanGoods();
		GoodsManager gm = new GoodsManager();
		bg = gm.loadbyGoodsnum(bod.getGoods_num());
		ori_price = bg.getGoods_price();
		fin_price = ori_price;
		
		BeanDiscount_infor bdi = new BeanDiscount_infor();
		DiscountManager dm = new DiscountManager();
		bdi = dm.loadbyNum(bod.getDis_num());
		
		BeanGoods_discount bgd = new BeanGoods_discount();
		Goods_discountManager bdm = new Goods_discountManager();
		bgd = bdm.LoadByGoods_Dis_num(bod.getGoods_num(), bod.getDis_num());
		
		if(bgd.getStart_Date().getTime() > today.getTime()) throw new Exception("����Ʒ�����Ż�δ����ʼʱ�䣬�޷�ʹ��");
		if(bgd.getEnd_Date().getTime() < today.getTime()) throw new Exception("����Ʒ�����Ż��ѹ��ڣ��޷�ʹ��");
 		
		if(bod.getOrder_count()>=bdi.getDis_count()) {
			fin_price = ori_price * bdi.getDicount();
			count = bdi.getDicount();
		}
		
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE order_detail\r\n" + 
					"SET order_price=?,order_dis=?\r\n" + 
					"WHERE order_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setFloat(1, fin_price);
			pst.setFloat(2, count);
			pst.setInt(3, bod.getOrder_num());
			pst.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
	
	public void Add(int Order_num,int Dis_num,int Goods_num,int order_count) throws Exception {
		if("".equals(String.valueOf(Order_num))) throw new Exception("������Ų���Ϊ��");
		if("".equals(String.valueOf(Dis_num))) throw new Exception("���۱�Ų���Ϊ��");
		if("".equals(String.valueOf(Goods_num))) throw new Exception("��Ʒ��Ų���Ϊ��");
		if("".equals(String.valueOf(order_count))) throw new Exception("��Ʒ��������Ϊ��");
		if(order_count <= 0) throw new Exception("��Ʒ��������Ϊ0��Ϊ����");
		BeanGoods bg = new BeanGoods();
		GoodsManager gm = new GoodsManager();
		bg = gm.loadbyGoodsnum(Goods_num);
		if(order_count > bg.getGoods_count()) throw new Exception("��Ʒ��治�㣬���������빺������");
		
		BeanDiscount_infor bdi = new BeanDiscount_infor();
		DiscountManager dm = new DiscountManager();
		bdi = dm.loadbyNum(Dis_num);
		
		BeanGoods_discount bgd = new BeanGoods_discount();
		Goods_discountManager bdm = new Goods_discountManager();
		bgd = bdm.LoadByGoods_Dis_num(Goods_num, Dis_num);
		
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
			odm.reloadPrice_count(bod);
			gm.SubGoods_count(Goods_num, order_count);
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
	
	public void Delete(int Order_num) throws Exception {
		if("".equals(String.valueOf(Order_num))) throw new Exception("������Ų���Ϊ��");
		Order_detailManager odm = new Order_detailManager();
		if(!(odm.loadbyOrder_num(Order_num)==null)) throw new Exception("�����ڴ˶���");
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
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
}
