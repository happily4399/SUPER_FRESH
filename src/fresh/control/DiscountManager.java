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
		DiscountManager dm = new DiscountManager();
		dm.Add("十周年大折扣", 20, (float) 0.5, "2030-07-01", "2030-07-05");
	}
	
	public void Add(String Dis_content,int Dis_count,
			float discount,String Dis_start_date,String Dis_end_date) throws Exception {
		if("".equals(Dis_content)) throw new Exception("满折内容不能为空");
		if("".equals(String.valueOf(Dis_count))) throw new Exception("满折起始数量不能为空");
		if("".equals(String.valueOf(discount))) throw new Exception("折扣不能为空");
		if("".equals(String.valueOf(Dis_start_date))) throw new Exception("满折开始时间不能为空");
		if("".equals(String.valueOf(Dis_end_date))) throw new Exception("满折结束时间不能为空");
		SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date start_date = new Date();
		java.util.Date end_date = new Date();
		
		start_date = sdf2.parse(Dis_start_date);
		end_date = sdf2.parse(Dis_end_date);
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	
	public BeanDiscount_infor loadbyNum(int Dis_num) throws Exception {
		if("".equals(String.valueOf(Dis_num))) throw new Exception("满折编号不能为空");
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
			if(!(rs.next())) throw new Exception("满折编号对应的满折不存在");
			else {
				bdi.setDis_num(Dis_num);
				bdi.setDis_content(rs.getString(2));
				bdi.setDis_count(rs.getInt(3));
				bdi.setDicount(rs.getFloat(4));
				bdi.setDis_start_date(rs.getDate(5));
				bdi.setDis_end_date(rs.getDate(6));
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
				bdi.setDis_start_date(rs.getTime(5));
				bdi.setDis_end_date(rs.getTime(6));
				result.add(bdi);
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
	
	public void ChangeDis_count(int Dis_num,int Dis_count) throws Exception {
		if("".equals(String.valueOf(Dis_num))) throw new Exception("满折编号不能为空");
		if("".equals(String.valueOf(Dis_count))) throw new Exception("满折起始数量不能为空");
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	
	public void ChangeDiscount(int Dis_num,Float Discount) throws Exception {
		if("".equals(String.valueOf(Dis_num))) throw new Exception("满折编号不能为空");
		if("".equals(String.valueOf(Discount))) throw new Exception("折扣不能为空");
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	
	public void ChangeDis_con(int Dis_num,String Dis_con) throws Exception {
		if("".equals(String.valueOf(Dis_num))) throw new Exception("满折编号不能为空");
		if("".equals(String.valueOf(Dis_con))) throw new Exception("满折内容不能为空");
		Connection conn = null;
		try {
			DiscountManager dm = new DiscountManager();
			dm.loadbyNum(Dis_num);
			conn = DBUtil.getConnection();
			String sql = "UPDATE discount_infor\r\n" + 
					"SET Dis_con = ?\r\n" + 
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
}
