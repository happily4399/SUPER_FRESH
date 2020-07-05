package fresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fresh.model.BeanGood_buy;
import fresh.model.BeanUser;
import fresh.util.DBUtil;

public class Good_buyManager {
	public static void main(String[] args) throws Exception {
		Good_buyManager gbm = new Good_buyManager();
		GoodsManager gm = new GoodsManager();
		gbm.add(2, "31802323", 40, "���");
	}
	
	public BeanGood_buy LoadbyBuy_num(int buy_num) throws Exception {
		if("".equals(buy_num)) throw new Exception("�ɹ����Ų���Ϊ��");
		BeanGood_buy bg = new BeanGood_buy();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql="SELECT *\r\n" + 
					"FROM good_buy\r\n" + 
					"WHERE buy_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, buy_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())throw new Exception("�����ڲɹ���");
			else {
				bg.setBuy_num(rs.getInt(1));
				bg.setGoods_num(rs.getInt(2));
				bg.setAdmin_num(rs.getString(3));
				bg.setBuy_count(rs.getInt(4));
				bg.setBuy_state(rs.getString(5));
			}
			pst.close();
			rs.close();
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
	
	public List<BeanGood_buy> LoadAll() throws Exception {
		List<BeanGood_buy> result = new ArrayList<BeanGood_buy>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql="SELECT *\r\n" + 
					"FROM good_buy";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				BeanGood_buy bg = new BeanGood_buy();
				bg.setBuy_num(rs.getInt(1));
				bg.setGoods_num(rs.getInt(2));
				bg.setAdmin_num(rs.getString(3));
				bg.setBuy_count(rs.getInt(4));
				bg.setBuy_state(rs.getString(5));
				result.add(bg);
			}
			pst.close();
			rs.close();
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
	
	public void ChangeGoods_CountByState(int buy_num,int goods_num,String buy_state) throws Exception {
		Good_buyManager gbm = new Good_buyManager();
		GoodsManager gm = new GoodsManager();
		if("".equals(String.valueOf(buy_num))) throw new Exception("�ɹ���Ų���Ϊ��");
		if("".equals(String.valueOf(goods_num))) throw new Exception("��Ʒ��Ų���Ϊ��");
		if("".equals(String.valueOf(buy_state))) throw new Exception("�ɹ�״̬����Ϊ��");
		try {
			if(buy_state == "���") {
				gm.AddGoods_count(goods_num, gbm.LoadBuy_CountByBuy_num(buy_num));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int LoadBuy_CountByBuy_num(int buy_num) throws Exception {
		if("".equals(String.valueOf(buy_num))) throw new Exception("�ɹ���Ų���Ϊ��");
		int Count = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT buy_count\r\n" + 
					"FROM good_buy\r\n" + 
					"WHERE buy_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, buy_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next()) throw new Exception("�����ڴ˲ɹ���");
			else {
				Count = rs.getInt(1);
			}
			rs.close();
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
		return Count;
	}
	
	public void add(int goods_num,String admin_num,int buy_count,String buy_state) throws Exception {
		AdminManager am = new AdminManager();
		GoodsManager gm = new GoodsManager();
		Good_buyManager gb = new Good_buyManager();
		if("".equals(String.valueOf(goods_num))) throw new Exception("��Ʒ��Ų���Ϊ��");
		if("".equals(String.valueOf(admin_num))) throw new Exception("����Ա��Ų���Ϊ��");
		if("".equals(String.valueOf(buy_count))) throw new Exception("�ɹ���������Ϊ��");
		if("".equals(String.valueOf(buy_state))) throw new Exception("�ɹ�״̬����Ϊ��");
		Connection conn = null;
		try {
			if(am.loadbynum(admin_num)==null) throw new Exception("����Ա������");
			if(gm.loadbyGoodsnum(goods_num)==null) throw new Exception("�ɹ���Ʒ������");
			conn = DBUtil.getConnection();
			String sql = "INSERT \r\n" + 
					"INTO good_buy(goods_num,admin_num,buy_count,buy_state)\r\n" + 
					"VALUES(?,?,?,?)";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, goods_num);
			pst.setString(2, admin_num);
			pst.setInt(3, buy_count);
			pst.setString(4, buy_state);
			pst.execute();
			pst.close();
			if(buy_state=="���") {
				gm.AddGoods_count(goods_num, buy_count);
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
	}
	
	
	public void Delete(int buy_num) throws Exception {
		Good_buyManager gb = new Good_buyManager();
		if("".equals(String.valueOf(buy_num))) throw new Exception("�ɹ���Ų���Ϊ��");
		if(gb.LoadbyBuy_num(buy_num)==null) throw new Exception("�ɹ���������");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE \r\n" + 
					"FROM good_buy\r\n" + 
					"WHERE buy_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, buy_num);
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
