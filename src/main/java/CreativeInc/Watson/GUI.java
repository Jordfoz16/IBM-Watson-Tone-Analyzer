package CreativeInc.Watson;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GUI {

	private JFrame frmWatsonDemo;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmWatsonDemo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GUI() {
		initialize();
	}
	
	private void initialize() {
		frmWatsonDemo = new JFrame();
		frmWatsonDemo.setTitle("Watson Demo");
		frmWatsonDemo.setBounds(100, 100, 450, 550);
		frmWatsonDemo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWatsonDemo.getContentPane().setLayout(null);
		
		JButton btnAnalyse = new JButton("Analyse");
		btnAnalyse.setBounds(82, 471, 117, 29);
		frmWatsonDemo.getContentPane().add(btnAnalyse);
		
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(258, 471, 117, 29);
		frmWatsonDemo.getContentPane().add(btnClose);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(20, 20, 410, 220);
		frmWatsonDemo.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JTextPane txtInput = new JTextPane();
		panel.add(txtInput, BorderLayout.CENTER);
		txtInput.setBackground(Color.WHITE);
		txtInput.setToolTipText("Enter Text");
		
		JSeparator separator = new JSeparator();
		separator.setBounds(5, 255, 440, 12);
		frmWatsonDemo.getContentPane().add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 279, 410, 175);
		frmWatsonDemo.getContentPane().add(scrollPane);
		
		JTextPane txtResult = new JTextPane();
		scrollPane.setViewportView(txtResult);
	}
}
