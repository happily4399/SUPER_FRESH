package fresh.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fresh.control.Fresh_categoryManager;
import fresh.control.GoodsManager;
import fresh.control.RecipeManager;
import fresh.model.BeanFresh_category;

public class FrmRecipe_Add extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelname = new JLabel("菜谱名称：");
	private JLabel labelmater = new JLabel("菜谱用料：");
	private JLabel labelstep = new JLabel("做菜步骤：");
	private JLabel labelphoto = new JLabel("菜谱图片：");
	
	private JTextField edtname = new JTextField(20);
	private JTextField edtmater = new JTextField(20);
	private JTextField edtstep = new JTextField(20);
	private JTextField edtphoto = new JTextField(20);
	
	public FrmRecipe_Add(JDialog f, String s, boolean b) throws Exception {
		super(f,s,b);
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
		workPane.add(labelphoto);
		workPane.add(edtphoto);
		
		
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
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			String name = edtname.getText();
			String mater = edtmater.getText();
			String step = edtstep.getText();
			String photo = edtphoto.getText();
			try {
				RecipeManager rm = new RecipeManager();
				rm.add(name, mater, step, photo);
				this.setVisible(false);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}