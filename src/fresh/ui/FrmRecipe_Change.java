package fresh.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fresh.control.GoodsManager;
import fresh.control.RecipeManager;

public class FrmRecipe_Change extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelname = new JLabel("菜谱名称：");
	private JLabel labelmater = new JLabel("菜谱用料：");
	private JLabel labelstep = new JLabel("做菜步骤：");
	
	private JTextField edtname = new JTextField(20);
	private JTextField edtmater = new JTextField(20);
	private JTextField edtstep = new JTextField(20);
	
	private int Recipe_num;
	
	public FrmRecipe_Change(JDialog f, String s, boolean b,int Recipe_num) throws Exception {
		super(f,s,b);
		this.Recipe_num=Recipe_num;
		RecipeManager rm = new RecipeManager();
		edtname = new JTextField(rm.LoadbyNum(Recipe_num).getRecipe_name(),20);
		edtmater = new JTextField(rm.LoadbyNum(Recipe_num).getRecipe_mater(),20);
		edtstep = new JTextField(rm.LoadbyNum(Recipe_num).getRecipe_step(),20);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelname);
		workPane.add(edtname);
		workPane.add(labelmater);
		workPane.add(edtmater);
		workPane.add(labelstep);
		workPane.add(edtstep);
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 250);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			String name = edtname.getText();
			String mater = edtmater.getText();
			String step = edtstep.getText();
			try {
				RecipeManager rm = new RecipeManager();
				rm.ChangeRecipe_name(Recipe_num, name);
				rm.ChangeRecipe_mater(Recipe_num, mater);
				rm.ChangeRecipe_step(Recipe_num, step);
				this.setVisible(false);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}