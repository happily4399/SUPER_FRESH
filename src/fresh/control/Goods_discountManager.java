package fresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fresh.model.BeanDiscount_infor;
import fresh.model.BeanGoods_discount;
import fresh.util.DBUtil;

public class Goods_discountManager {
	
	public List<BeanGoods_discount> LoadByGoods_num(int Goods_num) throws Exception {
		List<BeanGoods_discount> result = new ArrayList<BeanGoods_discount>();
		if("".equals(String.valueOf(Goods_num))) throw new Exception("��Ʒ��Ų���Ϊ��");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM goods_discount\r\n" + 
					"WHERE goods_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Goods_num);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				BeanGoods_discount bgd = new BeanGoods_discount();
				bgd.setGoods_num(rs.getInt(1));
				bgd.setDis_num(rs.getInt(2));
				bgd.setStart_Date(rs.getTimestamp(3));
				bgd.setEnd_Date(rs.getTimestamp(4));
				result.add(bgd);
			}
			if(result.get(1)==null) throw new Exception("����Ʒ����޴��ۻ��¼");
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
	
	public List<BeanGoods_discount> LoadByDis_num(int Dis_num) throws Exception {
		List<BeanGoods_discount> result = new ArrayList<BeanGoods_discount>();
		if("".equals(String.valueOf(Dis_num))) throw new Exception("���۱�Ų���Ϊ��");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM goods_discount\r\n" + 
					"WHERE Dis_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Dis_num);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				BeanGoods_discount bgd = new BeanGoods_discount();
				bgd.setGoods_num(rs.getInt(1));
				bgd.setDis_num(rs.getInt(2));
				bgd.setStart_Date(rs.getTimestamp(3));
				bgd.setEnd_Date(rs.getTimestamp(4));
				result.add(bgd);
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
	
	public BeanGoods_discount LoadByGoods_Dis_num(int Goods_num,int Dis_num) throws Exception {
		if("".equals(String.valueOf(Goods_num))) throw new Exception("��Ʒ��Ų���Ϊ��");
		if("".equals(String.valueOf(Dis_num))) throw new Exception("���۱�Ų���Ϊ��");
		Connection conn = null;
		BeanGoods_discount bgd = new BeanGoods_discount();
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM goods_discount\r\n" + 
					"WHERE Goods_num= ? and Dis_num = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Goods_num);
			pst.setInt(2, Dis_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next()) throw new Exception("�����ڴ�������Ʒ���");
			else{
				bgd.setGoods_num(rs.getInt(1));
				bgd.setDis_num(rs.getInt(2));
				bgd.setStart_Date(rs.getTimestamp(3));
				bgd.setEnd_Date(rs.getTimestamp(4));
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
		return bgd;
	}
	
	public List<BeanGoods_discount> LoadAll() throws Exception {
		List<BeanGoods_discount> result = new ArrayList<BeanGoods_discount>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM goods_discount";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				BeanGoods_discount bgd = new BeanGoods_discount();
				bgd.setGoods_num(rs.getInt(1));
				bgd.setDis_num(rs.getInt(2));
				bgd.setStart_Date(rs.getTimestamp(3));
				bgd.setEnd_Date(rs.getTimestamp(4));
				result.add(bgd);
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
	
	public void add(int Goods_num,int Dis_num,String Dis_start_date,String Dis_end_date) throws Exception {
		if("".equals(String.valueOf(Goods_num))) throw new Exception("��Ʒ��Ų���Ϊ��");
		if("".equals(String.valueOf(Dis_num))) throw new Exception("���۱�Ų���Ϊ��");
		if("".equals(String.valueOf(Dis_start_date))) throw new Exception("���ʼ���ڲ���Ϊ��");
		if("".equals(String.valueOf(Dis_end_date))) throw new Exception("���������Ϊ��");
		SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date start_date = new Date();
		java.util.Date end_date = new Date();
		
		start_date = sdf2.parse(Dis_start_date);
		end_date = sdf2.parse(Dis_end_date);
		
		Connection conn = null;
		try {
			BeanDiscount_infor bdi = new BeanDiscount_infor();
			GoodsManager gm = new GoodsManager();
			DiscountManager dm = new DiscountManager();
			conn = DBUtil.getConnection();
			gm.loadbyGoodsnum(Goods_num);
			bdi=dm.loadbyNum(Dis_num);
			if(bdi.getDis_start_date().getTime() > start_date.getTime()) throw new Exception("���ۻ��δ��ʼ���޷����");
			if(bdi.getDis_end_date().getTime() < end_date.getTime()) throw new Exception("������Ʒ����ʱ�䲻���������ۻ");
			conn = DBUtil.getConnection();
			String sql = "INSERT\r\n" + 
					"INTO goods_discount(Goods_num,Dis_num,start_count,end_count)\r\n" + 
					"VALUES(?,?,?,?)";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Goods_num);
			pst.setInt(2, Dis_num);
			pst.setTimestamp(3, new java.sql.Timestamp(start_date.getTime()));
			pst.setTimestamp(4, new java.sql.Timestamp(end_date.getTime()));
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
	
	public void DeleteByGoods_Dis_num(int Goods_num,int Dis_num) throws Exception {
		if("".equals(String.valueOf(Goods_num))) throw new Exception("��Ʒ��Ų���Ϊ��");
		if("".equals(String.valueOf(Dis_num))) throw new Exception("���۱�Ų���Ϊ��");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE\r\n" + 
					"FROM goods_discount\r\n" + 
					"WHERE goods_num = ? and Dis_num = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Goods_num);
			pst.setInt(2, Dis_num);
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
	
	public void DeleteByGoods_num(int Goods_num) throws Exception {
		if("".equals(String.valueOf(Goods_num))) throw new Exception("��Ʒ��Ų���Ϊ��");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE\r\n" + 
					"FROM goods_discount\r\n" + 
					"WHERE goods_num = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Goods_num);
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
	
	public void DeleteByDis_num(int Dis_num) throws Exception {
		if("".equals(String.valueOf(Dis_num))) throw new Exception("���۱�Ų���Ϊ��");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE\r\n" + 
					"FROM goods_discount\r\n" + 
					"WHERE Dis_num = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Dis_num);
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
	
	public void DeleteByEndtime(String EndTime) throws Exception {
		if("".equals(String.valueOf(EndTime))) throw new Exception("����ʱ�䲻��Ϊ��");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE\r\n" + 
					"FROM goods_discount\r\n" + 
					"WHERE end_count < ?";
			java.sql.PreparedStatement pst  = conn.prepareStatement(sql);
			pst.setString(1, EndTime);
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
	
	public void DeleteByNow() throws Exception {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE\r\n" + 
					"FROM goods_discount\r\n" + 
					"WHERE end_count < CURRENT_DATE()";
			java.sql.PreparedStatement pst  = conn.prepareStatement(sql);
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
	
	public void ChangeDis(int Goods_num,int Dis_num,int newDis_num,String start_Time,String end_time) throws Exception {
		if("".equals(String.valueOf(Goods_num))) throw new Exception("��Ʒ��Ų���Ϊ��");
		if("".equals(String.valueOf(Dis_num))) throw new Exception("���۱�Ų���Ϊ��");
		if("".equals(String.valueOf(newDis_num))) throw new Exception("�����۱�Ų���Ϊ��");
		
		java.util.Date today = new Date();
		Goods_discountManager gdm = new Goods_discountManager();
		DiscountManager dm = new DiscountManager();
		GoodsManager gm = new GoodsManager();
		BeanDiscount_infor bdm = new BeanDiscount_infor();
		SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date start_date = new Date();
		java.util.Date end_date = new Date();
		
		start_date = sdf2.parse(start_Time);
		end_date = sdf2.parse(end_time);
		
		bdm=dm.loadbyNum(newDis_num);
		gdm.LoadByGoods_Dis_num(Goods_num, Dis_num);
		Connection conn = null;
		
		if(bdm.getDis_end_date().getTime() < today.getTime()) throw new Exception("�����۱�Ż�ѹ��ڣ���ѡ���µ����۱��");
		if(bdm.getDis_start_date().getTime() > start_date.getTime()) throw new Exception("�����ۿ�ʼ�ʱ�䲻���ϣ��޷����");
		if(bdm.getDis_end_date().getTime() < end_date.getTime()) throw new Exception("��������Ʒ����ʱ�䲻���������ۻ");
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE goods_discount\r\n" + 
					"SET Dis_num = ?,start_count = ?,end_count = ?\r\n" + 
					"WHERE goods_num = ? AND Dis_num = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, newDis_num);
			pst.setDate(2, new java.sql.Date(start_date.getTime()));
			pst.setDate(3, new java.sql.Date(end_date.getTime()));
			pst.setInt(4, Goods_num);
			pst.setInt(5, Dis_num);
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
	
	public void ChangGoods_num(int Goods_num,int Dis_num,int newGoods_num) throws Exception {
		if("".equals(String.valueOf(Goods_num))) throw new Exception("��Ʒ��Ų���Ϊ��");
		if("".equals(String.valueOf(Dis_num))) throw new Exception("���۱�Ų���Ϊ��");
		if("".equals(String.valueOf(newGoods_num))) throw new Exception("����Ʒ��Ų���Ϊ��");
		Goods_discountManager gdm = new Goods_discountManager();
		GoodsManager gm = new GoodsManager();
		gm.loadbyGoodsnum(newGoods_num);
		gdm.LoadByGoods_Dis_num(Goods_num, Dis_num);
		if(!(gdm.LoadByGoods_Dis_num(newGoods_num, Dis_num)==null)) throw new Exception("��Ϣ�����Ʒ�Ѵ��ڶ�Ӧ�����ۻ�������������ɾ��");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE goods_discount\r\n" + 
					"SET goods_num=?\r\n" + 
					"WHERE goods_num=? AND Dis_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, newGoods_num);
			pst.setInt(2, Goods_num);
			pst.setInt(3, Dis_num);
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
