package fresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fresh.model.BeanDiscount_infor;
import fresh.util.DBUtil;

public class DiscountManager {
	public static void main(String[] args) throws Exception {
	}
	
	public void Add(String Dis_content,int Dis_count,
			float discount,String Dis_start_date,String Dis_end_date) throws Exception {
		if("".equals(Dis_content)) throw new Exception("�������ݲ���Ϊ��");
		if("".equals(String.valueOf(Dis_count))) throw new Exception("������ʼ��������Ϊ��");
		if("".equals(String.valueOf(discount))) throw new Exception("�ۿ۲���Ϊ��");
		if("".equals(String.valueOf(Dis_start_date))) throw new Exception("���ۿ�ʼʱ�䲻��Ϊ��");
		if("".equals(String.valueOf(Dis_end_date))) throw new Exception("���۽���ʱ�䲻��Ϊ��");
		SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date start_date = new Date();
		java.util.Date end_date = new Date();
		java.util.Date today = new Date();
		if(discount >= 1 ) throw new Exception("�ۿ۲��ܴ��ڵ���1");
		start_date = sdf2.parse(Dis_start_date);
		end_date = sdf2.parse(Dis_end_date);
		if(end_date.getTime() < today.getTime()) throw new Exception("�������ڲ������ڽ���");
		if(start_date.getTime() > end_date.getTime()) throw new Exception("�������ڲ������ڿ�ʼ����");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT\r\n" + 
					"INTO discount_infor(dis_content,dis_count,discount,Dis_start_date,Dis_end_date)\r\n" + 
					"VALUES(?,?,?,?,?)";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, Dis_content);
			pst.setInt(2, Dis_count);
			pst.setFloat(3, discount);
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
	
	public BeanDiscount_infor loadbyNum(int Dis_num) throws Exception {
		if("".equals(String.valueOf(Dis_num))) throw new Exception("���۱�Ų���Ϊ��");
		BeanDiscount_infor bdi = new BeanDiscount_infor();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM discount_infor\r\n" + 
					"WHERE Dis_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Dis_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!(rs.next())) throw new Exception("���۱�Ŷ�Ӧ�����۲�����");
			else {
				bdi.setDis_num(Dis_num);
				bdi.setDis_content(rs.getString(2));
				bdi.setDis_count(rs.getInt(3));
				bdi.setDicount(rs.getFloat(4));
				bdi.setDis_start_date(rs.getTimestamp(5));
				bdi.setDis_end_date(rs.getTimestamp(6));
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
		return bdi;
	}
	
	public List<BeanDiscount_infor> loadAll() throws Exception {
		List<BeanDiscount_infor> result = new ArrayList<BeanDiscount_infor>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM discount_infor";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				BeanDiscount_infor bdi = new BeanDiscount_infor();
				bdi.setDis_num(rs.getInt(1));
				bdi.setDis_content(rs.getString(2));
				bdi.setDis_count(rs.getInt(3));
				bdi.setDicount(rs.getFloat(4));
				bdi.setDis_start_date(rs.getTimestamp(5));
				bdi.setDis_end_date(rs.getTimestamp(6));
				result.add(bdi);
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
	
	public List<BeanDiscount_infor> loadbyname(String name) throws Exception {
		List<BeanDiscount_infor> result = new ArrayList<BeanDiscount_infor>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM discount_infor\r\n" + 
					"WHERE Dis_content LIKE '%"+name+"%'";
			java.sql.Statement st = conn.createStatement();
			java.sql.ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				BeanDiscount_infor bdi = new BeanDiscount_infor();
				bdi.setDis_num(rs.getInt(1));
				bdi.setDis_content(rs.getString(2));
				bdi.setDis_count(rs.getInt(3));
				bdi.setDicount(rs.getFloat(4));
				bdi.setDis_start_date(rs.getTimestamp(5));
				bdi.setDis_end_date(rs.getTimestamp(6));
				result.add(bdi);
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
	
	public List<BeanDiscount_infor> loadbytime() throws Exception {
		List<BeanDiscount_infor> result = new ArrayList<BeanDiscount_infor>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM discount_infor\r\n" + 
					"WHERE Dis_start_date < CURRENT_TIMESTAMP and Dis_end_date > CURRENT_TIMESTAMP";
			java.sql.Statement st = conn.createStatement();
			java.sql.ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				BeanDiscount_infor bdi = new BeanDiscount_infor();
				bdi.setDis_num(rs.getInt(1));
				bdi.setDis_content(rs.getString(2));
				bdi.setDis_count(rs.getInt(3));
				bdi.setDicount(rs.getFloat(4));
				bdi.setDis_start_date(rs.getTimestamp(5));
				bdi.setDis_end_date(rs.getTimestamp(6));
				result.add(bdi);
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
	
	public List<BeanDiscount_infor> loadbynametime(String name) throws Exception {
		List<BeanDiscount_infor> result = new ArrayList<BeanDiscount_infor>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM discount_infor\r\n" + 
					"WHERE Dis_content LIKE '%"+name+"%' and Dis_start_date < CURRENT_TIMESTAMP and Dis_end_date > CURRENT_TIMESTAMP";
			java.sql.Statement st = conn.createStatement();
			java.sql.ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				BeanDiscount_infor bdi = new BeanDiscount_infor();
				bdi.setDis_num(rs.getInt(1));
				bdi.setDis_content(rs.getString(2));
				bdi.setDis_count(rs.getInt(3));
				bdi.setDicount(rs.getFloat(4));
				bdi.setDis_start_date(rs.getTimestamp(5));
				bdi.setDis_end_date(rs.getTimestamp(6));
				result.add(bdi);
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
	
	public void ChangeDis_count(int Dis_num,int Dis_count) throws Exception {
		if("".equals(String.valueOf(Dis_num))) throw new Exception("���۱�Ų���Ϊ��");
		if("".equals(String.valueOf(Dis_count))) throw new Exception("������ʼ��������Ϊ��");
		Connection conn = null;
		try {
			DiscountManager dm = new DiscountManager();
			dm.loadbyNum(Dis_num);
			conn = DBUtil.getConnection();
			String sql = "UPDATE discount_infor\r\n" + 
					"SET Dis_count = ?\r\n" + 
					"WHERE Dis_num = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Dis_count);
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
	
	public void ChangeDiscount(int Dis_num,Float Discount) throws Exception {
		if("".equals(String.valueOf(Dis_num))) throw new Exception("���۱�Ų���Ϊ��");
		if("".equals(String.valueOf(Discount))) throw new Exception("�ۿ۲���Ϊ��");
		Connection conn = null;
		try {
			DiscountManager dm = new DiscountManager();
			dm.loadbyNum(Dis_num);
			conn = DBUtil.getConnection();
			String sql = "UPDATE discount_infor\r\n" + 
					"SET Discount = ?\r\n" + 
					"WHERE Dis_num = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setFloat(1, Discount);
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
	
	public void ChangeDis_con(int Dis_num,String Dis_con) throws Exception {
		if("".equals(String.valueOf(Dis_num))) throw new Exception("���۱�Ų���Ϊ��");
		if("".equals(String.valueOf(Dis_con))) throw new Exception("�������ݲ���Ϊ��");
		Connection conn = null;
		try {
			DiscountManager dm = new DiscountManager();
			dm.loadbyNum(Dis_num);
			conn = DBUtil.getConnection();
			String sql = "UPDATE discount_infor\r\n" + 
					"SET Dis_content = ?\r\n" + 
					"WHERE Dis_num = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, Dis_con);
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
	
	public void ChangeDis_start(int Dis_num,String Dis_start_date) throws Exception {
		if("".equals(String.valueOf(Dis_num))) throw new Exception("���۱�Ų���Ϊ��");
		Connection conn = null;
		SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date start_date = new Date();
		start_date = sdf2.parse(Dis_start_date);
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE discount_infor\r\n" + 
					"SET Dis_start_date=?\r\n" + 
					"WHERE Dis_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setDate(1, new java.sql.Date(start_date.getTime()));
			pst.setInt(2, Dis_num);
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
	
	public void ChangeDis_end(int Dis_num,String Dis_end_date) throws Exception {
		if("".equals(String.valueOf(Dis_num))) throw new Exception("���۱�Ų���Ϊ��");
		Connection conn = null;
		SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date end_date = new Date();
		java.util.Date today = new Date();
		end_date = sdf2.parse(Dis_end_date);
		if(end_date.getTime() < today.getTime()) throw new Exception("�������ڲ������ڽ���");
		try {
			conn = DBUtil.getConnection();
			String sql =  "UPDATE discount_infor\r\n" + 
					"SET Dis_end_date=?\r\n" + 
					"WHERE Dis_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setDate(1, new java.sql.Date(end_date.getTime()));
			pst.setInt(2, Dis_num);
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
	
	public void Delete(int Dis_num) throws Exception {
		Connection conn = null;
		Goods_discountManager gdm = new Goods_discountManager();
		Order_detailManager odm = new Order_detailManager();
		try {
			conn = DBUtil.getConnection();
			if(gdm.LoadByDis_num(Dis_num).size()!=0) throw new Exception("Goods_discountManager�����Դ��ڸ�����");
			if(odm.loadbyDis_num(Dis_num).size()!=0) throw new Exception("Order_detail�����Դ��ڸ�����");
			String sql = "DELETE \r\n" + 
					"FROM discount_infor\r\n" + 
					"WHERE dis_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt( 1, Dis_num);
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
	
	public void Deletebytime() throws Exception {
		Connection conn = null;
		Goods_discountManager gdm = new Goods_discountManager();
		Order_detailManager odm = new Order_detailManager();
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE \r\n" + 
					"FROM discount_infor\r\n" + 
					"WHERE Dis_start_date > CURRENT_TIMESTAMP and Dis_end_date < CURRENT_TIMESTAMP";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
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
