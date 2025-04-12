import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class GothBank extends BankAccount {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GothBank window = new GothBank();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GothBank() {
		initialize();
	}
	
	BankAccount account = new BankAccount();

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 806, 483);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 465, 332);
		frame.getContentPane().add(scrollPane);
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		JList<String> list = new JList<String>(listModel);
		list.setFont(new Font("Viner Hand ITC", Font.BOLD, 18));
		list.setForeground(new Color(255, 0, 0));
		list.setBackground(new Color(0, 0, 128));
		scrollPane.setViewportView(list);
		
		JRadioButton radWith = new JRadioButton("Withdraw\r\n");
		radWith.setBounds(481, 39, 144, 40);
		radWith.setBackground(Color.DARK_GRAY);
		radWith.setForeground(new Color(255, 0, 0));
		radWith.setFont(new Font("Viner Hand ITC", Font.BOLD, 18));
		frame.getContentPane().add(radWith);
		
		JRadioButton radDep = new JRadioButton("Deposit");
		radDep.setBounds(636, 39, 123, 40);
		radDep.setBackground(Color.DARK_GRAY);
		radDep.setForeground(Color.RED);
		radDep.setFont(new Font("Viner Hand ITC", Font.BOLD, 18));
		frame.getContentPane().add(radDep);
		
		ButtonGroup actions = new ButtonGroup();
		actions.add(radDep);
		actions.add(radWith);
		
		textField = new JTextField();
		textField.setBounds(490, 156, 269, 52);
		textField.setBackground(Color.GRAY);
		textField.setForeground(new Color(255, 128, 0));
		textField.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		//makes a button that will check to make sure theres an account made, that its a valid input
		JButton buttDo = new JButton("Process transaction");
		buttDo.setBounds(490, 219, 269, 61);
		buttDo.setFont(new Font("Snap ITC", Font.ITALIC, 20));
		buttDo.setForeground(new Color(255, 255, 255));
		buttDo.setBackground(new Color(64, 0, 64));
		buttDo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if (radDep.isSelected() == true) { //this part is for depositing
				try {
					if (listModel.getSize() == 0) { //checks if there is an account
						JOptionPane.showMessageDialog(null, "No account setup yet, go to menu to set one up");
					} else if (Double.parseDouble(textField.getText()) < 0) { 
						//checks if the deposit is a negative
						JOptionPane.showMessageDialog(null, "Bruh you cant do that");
					} else {
							//deposits into the account that was in the text box and adds to the history
							account.deposit(Double.parseDouble(textField.getText()));
							listModel.addElement("last deposit: " + textField.getText());
						
						
					}
					
				} catch (Exception z) {
					JOptionPane.showMessageDialog(null, "Bruh you cant do that");
					
				}
			} else if (radWith.isSelected() == true) { //this part is if withdrawal is selected
				try { 
					if (listModel.getSize() == 0) { //will check to make sure theres an account
						JOptionPane.showMessageDialog(null, "You literally dont even have an account with use, make sure to make one first");
					
						//ignore any problems in this try block
						
					} else if (Double.parseDouble(textField.getText()) > Double.parseDouble(account.getBalance()) || Double.parseDouble(textField.getText()) <= 0) {
						JOptionPane.showMessageDialog(null, "Bruh you cant do that");
					} else {
						if (Double.parseDouble(account.getBalance()) > 0) {
							account.withdraw(Double.parseDouble(textField.getText()));
							listModel.addElement("last withdrawal: " + textField.getText());
						} else {
							account.withdraw(Double.parseDouble(textField.getText()));
							listModel.addElement("last withdrawal: " + textField.getText());
						}
						
					}
				} catch (Exception z) {
					JOptionPane.showMessageDialog(null, "Bruh you cant do that");
				}
			} else {
				//do nothing
			}
			
			}
		});
		frame.getContentPane().add(buttDo);
		
		
		//makes a button that will display your balance
		JButton btnBalance = new JButton("SEE HOW BROKE YOU ARE");
		btnBalance.setBounds(20, 354, 762, 61);
		btnBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if (Double.parseDouble(account.getBalance()) > 0) {
				listModel.addElement("Current balance: " + account.getBalance());
			
			}
		});
		btnBalance.setFont(new Font("Chiller", Font.BOLD, 26));
		btnBalance.setForeground(new Color(128, 0, 255));
		btnBalance.setBackground(new Color(128, 0, 0));
		frame.getContentPane().add(btnBalance);
		
		JLabel lblNewLabel = new JLabel("Input Box Below");
		lblNewLabel.setBounds(485, 91, 269, 54);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 20));
		frame.getContentPane().add(lblNewLabel);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		//JDialog popup = new JDialog();
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Create Account");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				//checks to see if there wasnt already an "account" created
				if (Double.parseDouble(account.getBalance()) == 0 && listModel.size() == 0) { listModel.add(0, "Account number: " + String.valueOf(account.getAccountNumber()));
					listModel.add(1, account.getHistory()); //gets the "Hisory" of it
				} else {
					JOptionPane.showMessageDialog(null, "You already made an account dumdum");
				}
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Reset History");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			listModel.removeAllElements();
			//removes the history, but account is still there and made
			}
		});
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Create account with existing deposit");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					double test = Double.parseDouble(textField.getText());
					
//					if (textField.getText() == "") {
//						JOptionPane.showMessageDialog(null, "Please input the initial balance in the text box");
//					} else {
						account = new BankAccount (test); //sets a new acount with parameters into the original account
						
						if (listModel.getSize()!=0) {
							JOptionPane.showMessageDialog(null, "You already have an account dumdum");
							} else if (test < 0) {
							JOptionPane.showMessageDialog(null, "Sorry, broke people cant use our bank");
							} else { //shows the account number and that it was created
							listModel.add(0, "Account number: " + String.valueOf(account.getAccountNumber()));
							listModel.add(1, account.getHistory());
						}
						
//					}
					
				} catch (NumberFormatException i) {
					JOptionPane.showMessageDialog(null, "Make sure to ACTUALLY input a valid number in the text box");
				}
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Reset Account");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//resets everything to allow you to make a new account
			}
				account = null;
				listModel.removeAllElements();
				if (listModel.getSize() == 0) {
					JOptionPane.showMessageDialog(null, "Set up new account");
				}
			
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);
	
	}
}
