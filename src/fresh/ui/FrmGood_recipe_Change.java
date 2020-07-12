package fresh.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fresh.control.Goods_recipeManager;

public class FrmGood_recipe_Change extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelrecipe = new JLabel("菜谱编号：");
	private JLabel labelgood = new JLabel("商品编号：");
	private JLabel labeldes = new JLabel("推荐描述：");
	
	private JTextField edtrecipe = new JTextField(20);
	private JTextField edtgood = new JTextField(20);
	private JTextField edtdes = new JTextField(20);
	private int Goods_num=0;
	private int Recipe_num=0;
	
	public FrmGood_recipe_Change(JDialog f,String s,boolean b,int Goods_num,int Recipe_num) throws Exception {
		super(f,s,b);
		this.Goods_num=Goods_num;
		this.Recipe_num=Recipe_num;
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		
		edtrecipe = new JTextField(String.valueOf(Recipe_num),20);
		edtgood = new JTextField(String.valueOf(Goods_num),20);
		Goods_recipeManager gm = new Goods_recipeManager();
		edtdes = new JTextField(gm.loadbyGood_Recipe_num(Goods_num, Recipe_num).getRecipe_des(),20);
		
		workPane.add(labelrecipe);
		workPane.add(edtrecipe);
		workPane.add(labelgood);
		workPane.add(edtgood);
		workPane.add(labeldes);
		workPane.add(edtdes);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 300;
		int height = 200;
		this.setBounds((d.width - width)/2, (d.height-height)/2, width, height);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 200);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk) {
			int recipe_num = Integer.parseInt(edtrecipe.getText());
			int Good_num = Integer.parseInt(edtgood.getText());
			String des = edtdes.getText();
			try {
				Goods_recipeManager grm = new Goods_recipeManager();
				grm.ChangeGoods_num_one(Goods_num, Recipe_num, Good_num);
				grm.ChangeRecipe_num_one(Good_num, Recipe_num, recipe_num);
				grm.Changedes(Good_num, recipe_num, des);
			}catch (Exception e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
		}
	}
}
