package com.example.porfirio.codicefiscale.graphic;

/*
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import engine.CitiesCodes;
import engine.Engine;
import engine.Person;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ItemEvent;
import javax.swing.ButtonGroup;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final Action action = new SwingAction();
	private JFrame frame=this;
	private JComboBox comboBox;
	private JComboBox comboBox_3;
	private JRadioButton rdbtnNewRadioButton_1;
	*/
/**
	 * Launch the application.
	 *//*

	public static void main(String[] args) {
		new CitiesCodes();		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	*/
/**
	 * Create the frame.
	 *//*

	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNome = new JLabel("Nome");
		contentPane.add(lblNome, "2, 2, right, default");
		
		textField = new JTextField();
		contentPane.add(textField, "4, 2, fill, default");
		textField.setColumns(10);
		
		JLabel lblCognome = new JLabel("Cognome");
		contentPane.add(lblCognome, "2, 4, right, default");
		
		textField_1 = new JTextField();
		contentPane.add(textField_1, "4, 4, fill, default");
		textField_1.setColumns(10);
		
		JLabel lblSesso = new JLabel("Sesso");
		lblSesso.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblSesso, "2, 6");
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "4, 6, fill, fill");
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("M");
		rdbtnNewRadioButton.setSelected(true);
		buttonGroup.add(rdbtnNewRadioButton);
		panel.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("F");
		buttonGroup.add(rdbtnNewRadioButton_1);
		panel.add(rdbtnNewRadioButton_1);
		
		JLabel lblNewLabel = new JLabel("Data di Nascita");
		contentPane.add(lblNewLabel, "2, 8");
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, "4, 8, fill, fill");
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		comboBox = new JComboBox();
		for (int i=1; i<=31;i++)
			comboBox.addItem(i);
		panel_1.add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				System.out.println(arg0.getItem().toString());
				int giorni=31;
				int anno=1900;
				if (comboBox_2!=null)
					anno=(Integer)comboBox_2.getSelectedItem();
				Boolean bisestile=(anno%4==0 && anno%100!=0 || anno%400==0);
				System.out.println(bisestile);		
				if (arg0.getItem().toString().equals("2") && !bisestile) 
					giorni=28;
				else if (arg0.getItem().toString().equals("2") && bisestile)
					giorni=29;
				else if ((arg0.getItem().toString().equals("6"))||(arg0.getItem().toString().equals("4")||(arg0.getItem().toString().equals("9"))||(arg0.getItem().toString().equals("11"))))
					giorni=30;
				else 
					giorni=31;
				int giornoscelto=(Integer)comboBox.getSelectedItem();				
				comboBox.removeAllItems();
				for (int i=1;i<=giorni;i++)
					comboBox.addItem(i);
				if (giornoscelto<=giorni)
					comboBox.setSelectedItem(giornoscelto);
				}
		});
		for (int i=1; i<=12;i++)
			comboBox_1.addItem(i);
		panel_1.add(comboBox_1);
		
		comboBox_2 = new JComboBox();
		comboBox_2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				int giorni=31;
				int anno=1900;
				anno=(Integer) arg0.getItem();
				Boolean bisestile=(anno%4==0 && anno%100!=0 || anno%400==0);					
				if (comboBox_1.getSelectedItem().toString().equals("2") && !bisestile) 
					giorni=28;
				else if (comboBox_1.getSelectedItem().toString().equals("2") && bisestile)
					giorni=29;
				else if ((comboBox_1.getSelectedItem().toString().equals("6"))||(comboBox_1.getSelectedItem().toString().equals("4")||(comboBox_1.getSelectedItem().toString().equals("9"))||(comboBox_1.getSelectedItem().toString().equals("11"))))
					giorni=30;
				else 
					giorni=31;
				int giornoscelto=(Integer)comboBox.getSelectedItem();				
				comboBox.removeAllItems();
				for (int i=1;i<=giorni;i++)
					comboBox.addItem(i);
				if (giornoscelto<=giorni)
					comboBox.setSelectedItem(giornoscelto);
				}
		});
		for (int i=1900; i<=new GregorianCalendar().get(Calendar.YEAR);i++)
			comboBox_2.addItem(i);
		panel_1.add(comboBox_2);
		
		JLabel lblLuogoDiNascita = new JLabel("Luogo di nascita");
        contentPane.add(lblLuogoDiNascita, "2, 10, right, default");
		
		comboBox_3 = new JComboBox();
        for (int i=0;i<CitiesCodes.codes.size();i++){
		     	comboBox_3.addItem(CitiesCodes.cities.get(i).toString());
        }
		contentPane.add(comboBox_3, "4, 10, fill, default");
		
		JButton btnCalcolaCodiceFiscale = new JButton("Calcola Codice Fiscale");
		btnCalcolaCodiceFiscale.setAction(action);
		contentPane.add(btnCalcolaCodiceFiscale, "4, 12");
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Calcola Codice Fiscale");
		}
		public void actionPerformed(ActionEvent e) {
			Person person = new Person();
			
			if(textField_1.getText().compareTo("")==0)
				JOptionPane.showMessageDialog(frame,"Inserire un cognome valido!");
			
			person.setSurname(textField_1.getText());
			
			if(textField.getText().compareTo("")==0)
				JOptionPane.showMessageDialog(frame,"Inserire un nome valido!");

			person.setName(textField.getText());
			person.setDay(comboBox.getSelectedItem().toString());
			person.setMonth(comboBox_1.getSelectedItem().toString());
			person.setYear(comboBox_2.getSelectedItem().toString());
			person.setBornCity((String) comboBox_3.getSelectedItem());
			
			if(rdbtnNewRadioButton_1.isSelected())
				person.setSex("F");
			else 
				person.setSex("M");
			
			Engine engine=null;
			try {
				engine=new Engine(person);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(frame,engine.getCode());

		}
	}
}
*/
