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
		if("".equals(String.valueOf(Goods_num))) throw new Exception("商品编号不能为空");
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
			if(result.get(1)==null) throw new Exception("此商品编号无打折活动记录");
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
	
	public List<BeanGoods_discount> LoadByDis_num(int Dis_num) throws Exception {
		List<BeanGoods_discount> result = new ArrayList<BeanGoods_discount>();
		if("".equals(String.valueOf(Dis_num))) throw new Exception("满折编号不能为空");
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public BeanGoods_discount LoadByGoods_Dis_num(int Goods_num,int Dis_num) throws Exception {
		if("".equals(String.valueOf(Goods_num))) throw new Exception("商品编号不能为空");
		if("".equals(String.valueOf(Dis_num))) throw new Exception("满折编号不能为空");
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
			if(!rs.next()) throw new Exception("不存在此满折商品组合");
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
				// TODO 自动生成的 catch 块
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public void add(int Goods_num,int Dis_num,String Dis_start_date,String Dis_end_date) throws Exception {
		if("".equals(String.valueOf(Goods_num))) throw new Exception("商品编号不能为空");
		if("".equals(String.valueOf(Dis_num))) throw new Exception("满折编号不能为空");
		if("".equals(String.valueOf(Dis_start_date))) throw new Exception("活动开始日期不能为空");
		if("".equals(String.valueOf(Dis_end_date))) throw new Exception("活动结束不能为空");
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
			if(bdi.getDis_start_date().getTime() > start_date.getTime()) throw new Exception("满折活动尚未开始，无法添加");
			if(bdi.getDis_end_date().getTime() < end_date.getTime()) throw new Exception("满折商品结束时间不能晚于满折活动");
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	
	public void DeleteByGoods_Dis_num(int Goods_num,int Dis_num) throws Exception {
		if("".equals(String.valueOf(Goods_num))) throw new Exception("商品编号不能为空");
		if("".equals(String.valueOf(Dis_num))) throw new Exception("满折编号不能为空");
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	
	public void DeleteByGoods_num(int Goods_num) throws Exception {
		if("".equals(String.valueOf(Goods_num))) throw new Exception("商品编号不能为空");
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	
	public void DeleteByDis_num(int Dis_num) throws Exception {
		if("".equals(String.valueOf(Dis_num))) throw new Exception("满折编号不能为空");
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	
	public void DeleteByEndtime(String EndTime) throws Exception {
		if("".equals(String.valueOf(EndTime))) throw new Exception("结束时间不能为空");
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
				// TODO 自动生成的 catch 块
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	
	public void ChangeDis(int Goods_num,int Dis_num,int newDis_num,String start_Time,String end_time) throws Exception {
		if("".equals(String.valueOf(Goods_num))) throw new Exception("商品编号不能为空");
		if("".equals(String.valueOf(Dis_num))) throw new Exception("满折编号不能为空");
		if("".equals(String.valueOf(newDis_num))) throw new Exception("新满折编号不能为空");
		
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
		
		if(bdm.getDis_end_date().getTime() < today.getTime()) throw new Exception("新满折编号活动已过期，请选择新的满折编号");
		if(bdm.getDis_start_date().getTime() > start_date.getTime()) throw new Exception("新满折开始活动时间不符合，无法添加");
		if(bdm.getDis_end_date().getTime() < end_date.getTime()) throw new Exception("新满折商品结束时间不能晚于满折活动");
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	
	public void ChangGoods_num(int Goods_num,int Dis_num,int newGoods_num) throws Exception {
		if("".equals(String.valueOf(Goods_num))) throw new Exception("商品编号不能为空");
		if("".equals(String.valueOf(Dis_num))) throw new Exception("满折编号不能为空");
		if("".equals(String.valueOf(newGoods_num))) throw new Exception("新商品编号不能为空");
		Goods_discountManager gdm = new Goods_discountManager();
		GoodsManager gm = new GoodsManager();
		gm.loadbyGoodsnum(newGoods_num);
		gdm.LoadByGoods_Dis_num(Goods_num, Dis_num);
		if(!(gdm.LoadByGoods_Dis_num(newGoods_num, Dis_num)==null)) throw new Exception("信息表此商品已存在对应的满折活动，若想更改请先删除");
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
}
