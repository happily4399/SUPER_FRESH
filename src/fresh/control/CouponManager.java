package fresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fresh.model.BeanCoupon;
import fresh.util.DBUtil;

public class CouponManager {
	public static void main(String[] args) throws Exception {
		CouponManager cm = new CouponManager();
		cm.add("ʮ�����Ż�ȯ", 45, 20, "2030-07-05", "2030-07-10");
	}
	
	public BeanCoupon loadByCoupon_num(int Coupon_num) throws Exception {
		if("".equals(Coupon_num)) throw new Exception("�Ż�ȯ��Ų���Ϊ��");
		BeanCoupon bc = new BeanCoupon();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM Coupon\r\n" + 
					"WHere Coupon_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Coupon_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next()) throw new Exception("�����ڴ��Ż�ȯ");
			else {
				bc.setCoupon_num(rs.getInt(1));
				bc.setCoupon_con(rs.getString(2));
				bc.setApp_amount(rs.getFloat(3));
				bc.setDed_amount(rs.getFloat(4));
				bc.setCoupon_start_date(rs.getTime(5));
				bc.setCoupon_end_date(rs.getTime(6));
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
		return bc;
	}
	
	public List<BeanCoupon> loadAll() throws Exception {
		List<BeanCoupon> result = new ArrayList<BeanCoupon>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM Coupon";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				BeanCoupon bc = new BeanCoupon();
				bc.setCoupon_num(rs.getInt(1));
				bc.setCoupon_con(rs.getString(2));
				bc.setApp_amount(rs.getFloat(3));
				bc.setDed_amount(rs.getFloat(4));
				bc.setCoupon_start_date(rs.getTime(5));
				bc.setCoupon_end_date(rs.getTime(6));
				result.add(bc);
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
	
	public void add(String Coupon_con,float app_amount,float Ded_amount,
			String Coupon_start_date,String Coupon_end_date ) throws Exception {
		if("".equals(Coupon_con)) throw new Exception("�Ż�ȯ���ݲ���Ϊ��");
		if("".equals(app_amount)) throw new Exception("���ý���Ϊ��");
		if("".equals(Ded_amount)) throw new Exception("�������Ϊ��");
		if("".equals(Coupon_start_date)) throw new Exception("��ʼ���ڲ���Ϊ��");
		if("".equals(Coupon_end_date)) throw new Exception("�������ڲ���Ϊ��");
		SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date start_date = new Date();
		java.util.Date end_date = new Date();
		
		start_date = sdf2.parse(Coupon_start_date);
		end_date = sdf2.parse(Coupon_end_date);
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT\r\n" + 
					"INTO coupon(coupon_con,app_amount,ded_amount,Coupon_start_date,coupon_end_date)\r\n" + 
					"VALUES(?,?,?,?,?)";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, Coupon_con);
			pst.setFloat(2, app_amount);
			pst.setFloat(3, Ded_amount);
			pst.setDate(4, new java.sql.Date(start_date.getTime()));
			pst.setDate(5, new java.sql.Date(end_date.getTime()));
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
	
	public void Delete(int Coupon_num) throws Exception {
		if("".equals(Coupon_num)) throw new Exception("�Ż�ȯ��Ų���Ϊ��");
		Goods_orderManager gom = new Goods_orderManager();
		Connection conn = null;
		try {
			if(!(gom.LoadbyCoupon_num(Coupon_num)==null)) throw new Exception("Goods_orderManager�����д��Ż�ȯ���ܾ�ɾ��");;
			conn = DBUtil.getConnection();
			String sql = "DELETE \r\n" + 
					"FROM coupon\r\n" + 
					"WHERE coupon_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Coupon_num);
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
	
	public void ChangeCoupon_con(int Coupon_num,String Coupon_con) throws Exception {
		if("".equals(Coupon_num)) throw new Exception("�Ż�ȯ��Ų���Ϊ��");
		Connection conn = null;
		Goods_orderManager gom = new Goods_orderManager();
		gom.LoadbyCoupon_num(Coupon_num);
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE coupon\r\n" + 
					"SET coupon_con=?\r\n" + 
					"where Coupon_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, Coupon_con);
			pst.setInt(2, Coupon_num);
			pst.execute();
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
	}
	
	public void ChangeCoupon_app_amount(int Coupon_num,float app_amount) throws Exception {
		if("".equals(Coupon_num)) throw new Exception("�Ż�ȯ��Ų���Ϊ��");
		Connection conn = null;
		Goods_orderManager gom = new Goods_orderManager();
		gom.LoadbyCoupon_num(Coupon_num);
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE coupon\r\n" + 
					"SET app_amount=?\r\n" + 
					"where Coupon_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setFloat(1, app_amount);
			pst.setInt(2, Coupon_num);
			pst.execute();
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
	}
	
	public void ChangeCoupon_Ded_amount(int Coupon_num,float Ded_amount) throws Exception {
		if("".equals(Coupon_num)) throw new Exception("�Ż�ȯ��Ų���Ϊ��");
		Connection conn = null;
		Goods_orderManager gom = new Goods_orderManager();
		gom.LoadbyCoupon_num(Coupon_num);
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE coupon\r\n" + 
					"SET Ded_amount=?\r\n" + 
					"where Coupon_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setFloat(1, Ded_amount);
			pst.setInt(2, Coupon_num);
			pst.execute();
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
	}
	
	public void ChangeCoupon_start_Date(int Coupon_num,String start_Date) throws Exception {
		if("".equals(Coupon_num)) throw new Exception("�Ż�ȯ��Ų���Ϊ��");
		Connection conn = null;
		SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date start_date = new Date();
		start_date = sdf2.parse(start_Date);
		Goods_orderManager gom = new Goods_orderManager();
		gom.LoadbyCoupon_num(Coupon_num);
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE coupon\r\n" + 
					"SET Coupon_start_date=?\r\n" + 
					"where Coupon_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setDate(1, new java.sql.Date(start_date.getTime()));
			pst.setInt(2, Coupon_num);
			pst.execute();
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
	}
	
	public void ChangeCoupon_end_Date(int Coupon_num,String end_Date) throws Exception {
		if("".equals(Coupon_num)) throw new Exception("�Ż�ȯ��Ų���Ϊ��");
		Connection conn = null;
		SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date end_date = new Date();
		end_date = sdf2.parse(end_Date);
		Goods_orderManager gom = new Goods_orderManager();
		gom.LoadbyCoupon_num(Coupon_num);
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE coupon\r\n" + 
					"SET Coupon_end_date=?\r\n" + 
					"where Coupon_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setDate(1, new java.sql.Date(end_date.getTime()));
			pst.setInt(2, Coupon_num);
			pst.execute();
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
	}
}
