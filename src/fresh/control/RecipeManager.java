package fresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fresh.model.BeanRecipe;
import fresh.util.DBUtil;

public class RecipeManager {
	public BeanRecipe LoadbyNum(int Recipe_num) throws Exception {
		if("".equals(String.valueOf(Recipe_num))) throw new Exception("菜谱编号不可为空");
		BeanRecipe br = new BeanRecipe();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM recipe\r\n" + 
					"WHERE Recipe_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Recipe_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next()) throw new Exception("菜谱不存在");
			else {
				br.setRecipe_num(rs.getInt(1));
				br.setRecipe_name(rs.getString(2));
				br.setRecipe_mater(rs.getString(3));
				br.setRecipe_step(rs.getString(4));
			}
			pst.close();
			rs.close();
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
		return br;
	}
	
	public List<BeanRecipe> LoadAll() throws Exception {
		List<BeanRecipe> result = new ArrayList<BeanRecipe>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM recipe";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				BeanRecipe br = new BeanRecipe();
				br.setRecipe_num(rs.getInt(1));
				br.setRecipe_name(rs.getString(2));
				br.setRecipe_mater(rs.getString(3));
				br.setRecipe_step(rs.getString(4));
				result.add(br);
			}
			pst.close();
			rs.close();
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
	
	public void add(String name,String mater,String step) throws Exception {
		if("".equals(String.valueOf(name))) throw new Exception("菜品名称不可为空");
		if("".equals(String.valueOf(mater))) throw new Exception("菜品用料不可为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM recipe\r\n" + 
					"WHERE Recipe_name=?";
			java.sql.PreparedStatement pst  = conn.prepareStatement(sql);
			pst.setString(1, name);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) throw new Exception("已存在相同名称的菜品");
			rs.close();
			pst.close();
			sql = "INSERT\r\n" + 
					"INTO recipe(recipe_name,recipe_mater,recipe_step)\r\n" + 
					"VALUES(?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, mater);
			pst.setString(3, step);
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
	
	public void Delete(int Recipe_num) throws Exception {
		Goods_recipeManager grm = new Goods_recipeManager();
		if("".equals(String.valueOf(Recipe_num))) throw new Exception("菜谱编号不可为空");
		Connection conn = null;
		try {
			if(!(grm.loadbyRecipe_num(Recipe_num).size()==0)) throw new Exception("Goods_recipeManager表中仍有此菜谱，拒绝删除");
			conn = DBUtil.getConnection();
			String sql = "DELETE \r\n" + 
					"FROM recipe\r\n" + 
					"WHERE recipe_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Recipe_num);
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
	
	public void ChangeRecipe_num(int Recipe_num,String newRecipe_name) throws Exception {
		if("".equals(String.valueOf(Recipe_num))) throw new Exception("菜谱编号不可为空");
		if("".equals(String.valueOf(newRecipe_name))) throw new Exception("菜品名称不可为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM recipe\r\n" + 
					"WHERE Recipe_name=?";
			java.sql.PreparedStatement pst  = conn.prepareStatement(sql);
			pst.setString(1, newRecipe_name);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) throw new Exception("已存在相同名称的菜品");
			rs.close();
			pst.close();
			
			sql = "UPDATE recipe\r\n" + 
					"SET Recipe_name=?\r\n" + 
					"WHERE Recipe_num=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, newRecipe_name);
			pst.setInt(2, Recipe_num);
			pst.execute();
			pst.close();
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
