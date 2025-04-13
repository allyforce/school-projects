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
	//I dont like that everything is in initalize.
	//I dont know this library but i would assume the functions you use could exist outside of this function.
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 806, 483);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 465, 332);
		frame.getContentPane().add(scrollPane);
		//list model for what? I dont know what this is doing based on naming.
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		JList<String> list = new JList<String>(listModel);
		list.setFont(new Font("Viner Hand ITC", Font.BOLD, 18));
		list.setForeground(new Color(255, 0, 0));
		list.setBackground(new Color(0, 0, 128));
		scrollPane.setViewportView(list);

		//I would name this to make more sense. radioButtonWithdraw or withdrawRadioBtn.
		//being more explcit is better than using a letter or shortening words not usually shortened
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

		//tip: check your colors, at least on my mac, this was white text on a white background
		//stay consistent, Butt vs btn vs button makes things confusing.
		//get in the habit of this and life will be easier.
		//also note that different languages have different agreed upon standards for naming
		JButton buttDo = new JButton("Process transaction");
		buttDo.setBounds(490, 219, 269, 61);
		buttDo.setFont(new Font("Snap ITC", Font.ITALIC, 20));
		buttDo.setForeground(new Color(255, 255, 255));
		buttDo.setBackground(new Color(64, 0, 64));

		//tip: I could be wrong so this is a tip,  functions like this should probably exist outsize of this function.
		//if the function needs to access items only in this scope, then that might mean bad code design.
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
					//tip: exceptions are usually labeled as e.
					//also, in the future, dont jsut let exceptions disapear like this, it could bite you. and it may be hard to find where an exception is happening
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
				//you dont need an else if you do nothing.
			} else {
				//do nothing
			}
			/*TIP:
			when having complex code like this, reading and tracking it is difficult.
			Splitting it into functions makes everything easier to read and explains whats going on through code
			Then you can know that the function you are calling is trying to withdraw or deposit.
			 something like this would be easier:
			if (radDep.isSelected() == true) { //this part is for depositing
				//example function name, this might not be best but its better than reading through tons of code.
				TryDeposit(dataInput, dataInput)
			} else if (radWith.isSelected() == true) { //this part is if withdrawal is selected
				TryWithdraw(dataInput, dataInput)
			}

			* */
			
			}
		});
		frame.getContentPane().add(buttDo);
		
		
		//makes a button that will display your balance
		JButton btnBalance = new JButton("SEE HOW BROKE YOU ARE");
		btnBalance.setBounds(20, 354, 762, 61);
		//this doesnt do anything on my machine.
		btnBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Double.parseDouble(account.getBalance()) > 0) {
					listModel.addElement("Current balance: " + account.getBalance());

				}
			}});
		btnBalance.setFont(new Font("Chiller", Font.BOLD, 26));
		btnBalance.setForeground(new Color(128, 0, 255));
		btnBalance.setBackground(new Color(128, 0, 0));
		frame.getContentPane().add(btnBalance);

		//again, make function make sense, label new label makes no sense to me as somone reading your code.
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
		//what is mnt? you'll get a better feel for what is good and what is bad when you see other peopels code/ you need to go back to something you wrote 3 months ago.
		JMenuItem mntmNewMenuItem = new JMenuItem("Create Account");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				//checks to see if there wasnt already an "account" created
				//Try to not make large one line code.
				//its easier to read when its collapsed, if you try to hide the complxity of it then just make a function thats named corerectly and call that
				if (Double.parseDouble(account.getBalance()) == 0 && listModel.size() == 0)
				{
					listModel.add(0, "Account number: " + String.valueOf(account.getAccountNumber()));
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
					//careful not to keep functioned named like this. again, saves you issues in the future.
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
/*
* FINAL NOTES:
* when you submit code, make sure it compiles. I had to fix this not working cause you didnt have a } in 2 places.
* I know you are being funny with your text codes, but if you are giving it to a teacher or employer, you'd want to be more neutral tone. none of this, "you dum dum" stuff. I couldn't care less if its fully personal. As you kids call it; Bruh, its cringe.
* overall im impressed, it works and does what it needs to. structure aside and the broken code aside. 4/10 profession, 8.5/10 for your level
* */