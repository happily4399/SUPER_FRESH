package fresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fresh.model.BeanGoods;
import fresh.util.DBUtil;

public class GoodsManager {
	public void Add(int category_num,String goods_name,
			float goods_price,int goods_count,String goods_spe,String goods_det) throws Exception {
		
		if("".equals(String.valueOf(category_num))) throw new Exception("����Ų���Ϊ��");
		if(goods_name==null) throw new Exception("��Ʒ������Ϊ��");
		if("".equals(String.valueOf(goods_price))) throw new Exception("��Ʒ�۸񲻿�Ϊ��");
		if("".equals(String.valueOf(goods_count))) throw new Exception("��Ʒ��������Ϊ��");
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="INSERT\r\n" + 
					"INTO goods(category_number,goods_name,goods_price,goods_count,goods_spe,goods_det)\r\n" + 
					"VALUES(?,?,?,?,?,?)";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, category_num);
			pst.setString(2, goods_name);
			pst.setFloat(3, goods_price);
			pst.setInt(4, goods_count);
			pst.setString(5, goods_spe);
			pst.setString(6, goods_det);
			pst.execute();
			conn.commit();
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
	
	public void DELETE(int goods_num) throws Exception {
		if("".equals(String.valueOf(goods_num))) throw new Exception("��Ʒ��Ų���Ϊ��");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="SELECT *\r\n" + 
					"FROM good_buy\r\n" + 
					"WHERE goods_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, goods_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) throw new Exception("Goods_buy�����Դ��ڴ���Ʒ���ܾ�ɾ��");
			rs.close();
			pst.close();
			
			sql="SELECT *\r\n" + 
					"FROM goods_recipe\r\n" + 
					"WHERE goods_num=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, goods_num);
			rs=pst.executeQuery();
			if(rs.next()) throw new Exception("Goods_recipe�����Դ��ڴ���Ʒ���ܾ�ɾ��");
			rs.close();
			pst.close();
			
			sql="SELECT *\r\n" + 
					"FROM goods_discount\r\n" + 
					"WHERE goods_num=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, goods_num);
			rs=pst.executeQuery();
			if(rs.next()) throw new Exception("Goods_discount�����Դ��ڴ���Ʒ���ܾ�ɾ��");
			rs.close();
			pst.close();
			
			sql="SELECT *\r\n" + 
					"FROM order_detail\r\n" + 
					"WHERE goods_num=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, goods_num);
			rs=pst.executeQuery();
			if(rs.next()) throw new Exception("Order_detail�����Դ��ڴ���Ʒ���ܾ�ɾ��");
			rs.close();
			pst.close();
			
			sql="SELECT *\r\n" + 
					"FROM promotion\r\n" + 
					"WHERE goods_num=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, goods_num);
			rs=pst.executeQuery();
			if(rs.next()) throw new Exception("Promotion�����Դ��ڴ���Ʒ���ܾ�ɾ��");
			rs.close();
			pst.close();
			
			sql="SELECT *\r\n" + 
					"FROM goods_evaluation\r\n" + 
					"WHERE goods_num=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, goods_num);
			rs=pst.executeQuery();
			if(rs.next()) throw new Exception("Goods_evaluation�����Դ��ڴ���Ʒ���ܾ�ɾ��");
			rs.close();
			pst.close();
			
			sql="DELETE\r\n" + 
					"FROM goods\r\n" + 
					"WHERE goods_num=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, goods_num);
			pst.execute();
			pst.close();
			conn.commit();
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
	
	public BeanGoods loadbyGoodsnum (int goods_num) throws Exception {
		if("".equals(String.valueOf(goods_num))) throw new Exception("��Ʒ��Ų���Ϊ��");
		BeanGoods bg = new BeanGoods();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM goods\r\n" + 
					"WHERE goods_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, goods_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next()) throw new Exception("��Ʒ��Ŷ�Ӧ����Ʒ������");
			else {
				bg.setGoods_num(rs.getInt(1));
				bg.setCate_gory_number(rs.getInt(2));
				bg.setPro_num(rs.getInt(3));
				bg.setGoods_name(rs.getString(4));
				bg.setGoods_price(rs.getFloat(5));
				bg.setVip_price(rs.getFloat(6));
				bg.setGoods_count(rs.getInt(7));
				bg.setGoods_spe(rs.getString(8));
				bg.setGoods_det(rs.getString(9));
			}
			pst.close();
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
		return bg;
	}
	
	public List<BeanGoods> Loadall () throws Exception {
		List<BeanGoods> result = new ArrayList<BeanGoods>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM goods";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				BeanGoods bg = new BeanGoods();
				bg.setGoods_num(rs.getInt(1));
				bg.setCate_gory_number(rs.getInt(2));
				bg.setPro_num(rs.getInt(3));
				bg.setGoods_name(rs.getString(4));
				bg.setGoods_price(rs.getFloat(5));
				bg.setVip_price(rs.getFloat(6));
				bg.setGoods_count(rs.getInt(7));
				bg.setGoods_spe(rs.getString(8));
				bg.setGoods_det(rs.getString(9));
				result.add(bg);
			}
			pst.close();
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
	
	public void ChangeGoods_name(int goods_num,String goods_name) throws Exception {
		if("".equals(String.valueOf(goods_num))) throw new Exception("��Ʒ��Ų���Ϊ��");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE goods\r\n" + 
					"SET goods_name=?\r\n" + 
					"WHERE goods_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, goods_name);
			pst.setInt(2, goods_num);
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