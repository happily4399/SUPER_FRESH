package fresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fresh.model.BeanPromotion;
import fresh.util.DBUtil;

public class PromotionManager {
	public static void main(String[] args) throws Exception {
		PromotionManager pm = new PromotionManager();
		pm.ChangeGoods_num(1, 3);
	}
	public void Add(int goods_num,float Pro_price,int Pro_count,String Pro_start_date,String Pro_end_date) throws Exception{
		if("".equals(String.valueOf(goods_num))) throw new Exception("商品编号不可为空");
		if("".equals(String.valueOf(Pro_price))) throw new Exception("促销价格不可为空");
		if("".equals(String.valueOf(Pro_count))) throw new Exception("促销数量不可为空");
		if("".equals(String.valueOf(Pro_start_date))) throw new Exception("促销开始日期不可为空");
		if("".equals(String.valueOf(Pro_end_date))) throw new Exception("促销结束日期不可为空");
		Connection conn = null;
		SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date start_date = new Date();
		java.util.Date end_date = new Date();
		
		start_date = sdf2.parse(Pro_start_date);
		end_date = sdf2.parse(Pro_end_date);
		
		try {
			conn = DBUtil.getConnection();
			String sql="SELECT *\r\n" + 
					"FROM promotion\r\n" + 
					"WHERE goods_num=?";
			java.sql.PreparedStatement pst =conn.prepareStatement(sql);
			pst.setInt(1, goods_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) throw new Exception("商品已有促销信息，不可同时两个促销");
			rs.close();
			pst.close();
			
			sql="INSERT\r\n" + 
					"into promotion(goods_num,pro_price,pro_count,pro_start_date,pro_end_date)\r\n" + 
					"values(?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, goods_num);
			pst.setFloat(2, Pro_price);
			pst.setInt(3, Pro_count);
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
	
	public void DELETE(int pro_num) throws Exception {
		if("".equals(String.valueOf(pro_num))) throw new Exception("促销编号不可为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE\r\n" + 
					"FROM promotion\r\n" + 
					"WHERE pro_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, pro_num);
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
	
	public BeanPromotion LoadByPro_Num(int pro_num) throws Exception {
		if("".equals(String.valueOf(pro_num))) throw new Exception("促销编号不可为空");
		BeanPromotion bp = new BeanPromotion();
		Connection conn = null;
		try {
			conn = null;
			String sql = "Select *\r\n" + 
					"from promotion\r\n" + 
					"where Pro_num = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, pro_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next()) throw new Exception("促销商品不存在");
			else {
				bp.setPro_num(rs.getInt(1));
				bp.setGoods_num(rs.getInt(2));
				bp.setPro_price(rs.getFloat(3));
				bp.setPro_count(rs.getInt(4));
				bp.setPro_start_date(rs.getDate(5));
				bp.setPro_end_date(rs.getDate(6));
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
		return bp;
	}
	
	public BeanPromotion LoadByGoods_num(int Goods_num) throws Exception {
		if("".equals(String.valueOf(Goods_num))) throw new Exception("商品编号不可为空");
		BeanPromotion bp = new BeanPromotion();
		Connection conn = null;
		java.util.Date today = new Date();
		try {
			conn = DBUtil.getConnection();
			String sql = "Select *\r\n" + 
					"from promotion\r\n" + 
					"where Goods_num = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Goods_num);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				if(rs.getDate(5).getTime() < today.getTime() && rs.getDate(6).getTime() > today.getTime())
				{
					bp.setPro_num(rs.getInt(1));
					bp.setGoods_num(rs.getInt(2));
					bp.setPro_price(rs.getFloat(3));
					bp.setPro_count(rs.getInt(4));
					bp.setPro_start_date(rs.getDate(5));
					bp.setPro_end_date(rs.getDate(6));
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
		return bp;
	}
	
	public List<BeanPromotion> loadAll(){
		List<BeanPromotion> result = new ArrayList<BeanPromotion>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "Select *\r\n" + 
					"from promotion";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				BeanPromotion bp = new BeanPromotion();
				bp.setPro_num(rs.getInt(1));
				bp.setGoods_num(rs.getInt(2));
				bp.setPro_price(rs.getFloat(3));
				bp.setPro_count(rs.getInt(4));
				bp.setPro_start_date(rs.getDate(5));
				bp.setPro_end_date(rs.getDate(6));
				result.add(bp);
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
	
	public void ChangeGoods_num(int pro_num,int Goods_num) throws Exception {
		GoodsManager gm = new GoodsManager();
		if("".equals(String.valueOf(pro_num))) throw new Exception("促销编号不可为空");
		if("".equals(String.valueOf(Goods_num))) throw new Exception("商品编号不可为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "Select *\r\n" + 
					"from promotion\r\n" + 
					"where goods_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Goods_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) throw new Exception("已有相同商品编号的产品在促销");
			pst.close();
			rs.close();
			if(gm.loadbyGoodsnum(Goods_num) == null) throw new Exception("此商品编号不存在对应的商品");
			sql = "UPDATE promotion\r\n" + 
					"SET goods_num = ?\r\n" + 
					"WHERE Pro_num = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Goods_num);
			pst.setInt(2, pro_num);
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
