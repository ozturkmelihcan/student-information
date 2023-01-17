package com.melihcanozturk.controller;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.commons.lang3.StringUtils;

import com.melihcanozturk.entity.Contact;
import com.melihcanozturk.entity.Student;
import com.melihcanozturk.service.ContactService;
import com.melihcanozturk.service.StudentService;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.awt.Color;

import java.awt.Font;

public class App {
	private JComboBox<String> comboBoxGender;
	private JFrame frmStudentInformation;
	private JTextField txtId;
	private JTextField txtEmail;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtPhone1;
	private JTextField txtPhone2;
	private JTable table;
	private JTextArea textAreaAdress2;
	private JTextArea textAreaAddress1;
	private StudentService stdService = new StudentService();
	private Student student = new Student();
	private ContactService contactService = new ContactService();
	private JButton btnSorgulaMail;
	private JLabel lblid;
	private JLabel lblEmail;
	private JLabel lblFirstName;
	private JLabel lblLastName;
	private JLabel lblCinsiyet;
	private JButton btnSorgulaFirstName;
	private JButton btnQuery;
	private JLabel lblPhone;
	private JLabel lblAdres;
	private JLabel lblContactInfo1;
	private JLabel lblContactInfo2;
	private JButton btnGetAll;
	private JButton btnSave;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JLabel lblLanguage;
	private JComboBox<?> comboBoxLanguage;
	private JLabel lblmesaj;

	private JLabel lbl_foto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frmStudentInformation.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
		Locale.setDefault(new Locale("en", "EN"));
		// i18n();

	}

	private void i18n() {
		ResourceBundle rsb = ResourceBundle.getBundle("com/melihcanozturk/config/resource_bundle");
		btnQuery.setText(rsb.getString("word9"));
		btnSave.setText(rsb.getString("word14"));
		btnUpdate.setText(rsb.getString("word15"));
		btnSorgulaFirstName.setText(rsb.getString("word8"));
		btnSorgulaMail.setText(rsb.getString("word7"));
		lblEmail.setText(rsb.getString("word2"));
		lblFirstName.setText(rsb.getString("word3"));
		lblLastName.setText(rsb.getString("word4"));
		lblid.setText(rsb.getString("word1"));
		lblCinsiyet.setText(rsb.getString("word21"));
		lblContactInfo1.setText(rsb.getString("word12"));
		lblContactInfo2.setText(rsb.getString("word12"));
		btnGetAll.setText(rsb.getString("word13"));
		btnDelete.setText(rsb.getString("word16"));
		lblPhone.setText(rsb.getString("word10"));
		lblAdres.setText(rsb.getString("word11"));
		lblLanguage.setText(rsb.getString("word17"));
		lblmesaj.setText(rsb.getString("word22"));

		comboBoxGender.removeAllItems();
		comboBoxGender.addItem(rsb.getString("word5"));
		comboBoxGender.addItem(rsb.getString("word6"));
		comboBoxLanguage.setModel(new DefaultComboBoxModel(
				new String[] { rsb.getString("word18"), rsb.getString("word19"), rsb.getString("word20") }));
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", rsb.getString("word2"), rsb.getString("word3"), rsb.getString("word4") }));
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStudentInformation = new JFrame();
		frmStudentInformation.setBackground(Color.BLACK);
		frmStudentInformation.getContentPane().setBackground(Color.DARK_GRAY);
		frmStudentInformation.setForeground(Color.WHITE);
		frmStudentInformation.getContentPane().setForeground(Color.BLACK);
		frmStudentInformation.setTitle("Student Information");
		frmStudentInformation.setBounds(100, 100, 988, 667);
		frmStudentInformation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStudentInformation.getContentPane().setLayout(null);

		lblid = new JLabel("ID");
		lblid.setBackground(Color.LIGHT_GRAY);
		lblid.setForeground(Color.WHITE);
		lblid.setHorizontalAlignment(SwingConstants.LEFT);
		lblid.setBounds(47, 30, 79, 14);
		frmStudentInformation.getContentPane().add(lblid);

		lblEmail = new JLabel("Email*");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setBounds(47, 55, 79, 14);
		frmStudentInformation.getContentPane().add(lblEmail);

		lblFirstName = new JLabel("First name *");
		lblFirstName.setForeground(Color.WHITE);
		lblFirstName.setHorizontalAlignment(SwingConstants.LEFT);
		lblFirstName.setBounds(47, 80, 79, 14);
		frmStudentInformation.getContentPane().add(lblFirstName);

		lblLastName = new JLabel("Last name *");
		lblLastName.setForeground(Color.WHITE);
		lblLastName.setHorizontalAlignment(SwingConstants.LEFT);
		lblLastName.setBounds(47, 105, 79, 14);
		frmStudentInformation.getContentPane().add(lblLastName);

		lblCinsiyet = new JLabel("Gender *");
		lblCinsiyet.setForeground(Color.WHITE);
		lblCinsiyet.setHorizontalAlignment(SwingConstants.LEFT);
		lblCinsiyet.setBounds(47, 130, 79, 14);
		frmStudentInformation.getContentPane().add(lblCinsiyet);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(136, 27, 112, 17);
		frmStudentInformation.getContentPane().add(txtId);
		txtId.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(136, 52, 112, 17);
		frmStudentInformation.getContentPane().add(txtEmail);

		txtFirstName = new JTextField();
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(136, 77, 112, 17);
		frmStudentInformation.getContentPane().add(txtFirstName);

		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(136, 102, 112, 17);
		frmStudentInformation.getContentPane().add(txtLastName);

		btnSorgulaMail = new JButton("QueryEmail");
		btnSorgulaMail.setBackground(Color.LIGHT_GRAY);
		btnSorgulaMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				queryEmail();
			}
		});

		btnSorgulaMail.setBounds(270, 51, 135, 23);
		frmStudentInformation.getContentPane().add(btnSorgulaMail);

		btnSorgulaFirstName = new JButton("QueryFirstName");
		btnSorgulaFirstName.setBackground(Color.LIGHT_GRAY);
		btnSorgulaFirstName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				queryFirstName();
			}
		});
		btnSorgulaFirstName.setBounds(270, 76, 135, 23);
		frmStudentInformation.getContentPane().add(btnSorgulaFirstName);

		btnQuery = new JButton("QueryLastName");
		btnQuery.setBackground(Color.LIGHT_GRAY);
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				queryLastName();

			}
		});
		btnQuery.setBounds(270, 101, 135, 23);
		frmStudentInformation.getContentPane().add(btnQuery);

		lblPhone = new JLabel("Phone *");
		lblPhone.setForeground(Color.WHITE);
		lblPhone.setBackground(Color.WHITE);
		lblPhone.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhone.setBounds(427, 55, 130, 14);
		frmStudentInformation.getContentPane().add(lblPhone);

		lblAdres = new JLabel("Address *");
		lblAdres.setForeground(Color.WHITE);
		lblAdres.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdres.setBounds(427, 80, 130, 14);
		frmStudentInformation.getContentPane().add(lblAdres);

		txtPhone1 = new JTextField();
		txtPhone1.setBounds(567, 52, 112, 20);
		frmStudentInformation.getContentPane().add(txtPhone1);
		txtPhone1.setColumns(10);

		txtPhone2 = new JTextField();
		txtPhone2.setColumns(10);
		txtPhone2.setBounds(689, 52, 107, 20);
		frmStudentInformation.getContentPane().add(txtPhone2);

		lblContactInfo1 = new JLabel("Contact info 1");
		lblContactInfo1.setForeground(Color.WHITE);
		lblContactInfo1.setHorizontalAlignment(SwingConstants.CENTER);
		lblContactInfo1.setBounds(567, 30, 112, 14);
		frmStudentInformation.getContentPane().add(lblContactInfo1);

		lblContactInfo2 = new JLabel("Contact info 2");
		lblContactInfo2.setForeground(Color.WHITE);
		lblContactInfo2.setHorizontalAlignment(SwingConstants.CENTER);
		lblContactInfo2.setBounds(689, 30, 107, 14);
		frmStudentInformation.getContentPane().add(lblContactInfo2);

		comboBoxGender = new JComboBox();
		comboBoxGender.setBackground(Color.LIGHT_GRAY);
		comboBoxGender.setForeground(Color.BLACK);
		comboBoxGender.setModel(new DefaultComboBoxModel(new String[] { "Man", "Woman" }));
		comboBoxGender.setBounds(136, 126, 112, 17);
		frmStudentInformation.getContentPane().add(comboBoxGender);

		btnGetAll = new JButton("Get All ");
		btnGetAll.setBackground(Color.LIGHT_GRAY);
		btnGetAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getAll();
			}
		});
		btnGetAll.setBounds(762, 249, 179, 23);
		frmStudentInformation.getContentPane().add(btnGetAll);

		btnSave = new JButton("Save");
		btnSave.setBackground(Color.LIGHT_GRAY);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				save();

			}
		});
		btnSave.setBounds(762, 296, 179, 23);
		frmStudentInformation.getContentPane().add(btnSave);

		btnUpdate = new JButton("Update");
		btnUpdate.setBackground(Color.LIGHT_GRAY);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				update();

			}

		});
		btnUpdate.setBounds(762, 343, 179, 23);
		frmStudentInformation.getContentPane().add(btnUpdate);

		btnDelete = new JButton("Delete");
		btnDelete.setBackground(Color.LIGHT_GRAY);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				delete();

			}
		});
		btnDelete.setBounds(762, 390, 179, 23);
		frmStudentInformation.getContentPane().add(btnDelete);

		lblLanguage = new JLabel("Language :");
		lblLanguage.setForeground(Color.WHITE);
		lblLanguage.setHorizontalAlignment(SwingConstants.CENTER);
		lblLanguage.setBounds(762, 443, 179, 23);
		frmStudentInformation.getContentPane().add(lblLanguage);

		comboBoxLanguage = new JComboBox();
		comboBoxLanguage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				locale();
			}
		});
		comboBoxLanguage.setModel(new DefaultComboBoxModel(new String[] { "English", "Turkish", "French" }));
		comboBoxLanguage.setBounds(762, 493, 179, 28);
		frmStudentInformation.getContentPane().add(comboBoxLanguage);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 249, 683, 272);
		frmStudentInformation.getContentPane().add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		table.setBackground(Color.LIGHT_GRAY);
		table.setForeground(Color.DARK_GRAY);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int select = table.getSelectedRow();
				TableModel model = table.getModel();

				int val = Integer.parseInt(model.getValueAt(select, 0).toString());
				selectedRow(val);
				txtId.setText(String.valueOf(val));

			}
		});
		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Email", "First Name", "Last Name" }));
		scrollPane.setViewportView(table);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(567, 81, 112, 63);
		frmStudentInformation.getContentPane().add(scrollPane_1);

		textAreaAddress1 = new JTextArea();
		textAreaAddress1.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(textAreaAddress1);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(689, 83, 107, 61);
		frmStudentInformation.getContentPane().add(scrollPane_2);

		textAreaAdress2 = new JTextArea();
		scrollPane_2.setViewportView(textAreaAdress2);

		lblmesaj = new JLabel("Zorunlu alanlar * ile işaretlenmiştir.");
		lblmesaj.setForeground(Color.WHITE);
		lblmesaj.setBounds(47, 149, 233, 14);
		frmStudentInformation.getContentPane().add(lblmesaj);

		JButton btnNewButton = new JButton("C");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emptyTextFieldAll();
			}
		});
		btnNewButton.setBounds(280, 135, 63, 23);
		frmStudentInformation.getContentPane().add(btnNewButton);

		lbl_foto = new JLabel("");
		lbl_foto.setForeground(Color.WHITE);
		lbl_foto.setBounds(811, 30, 130, 154);
		frmStudentInformation.getContentPane().add(lbl_foto);
	}

	protected void getAll() {
		List<Student> list = stdService.getAll();
		fillTable(list);
		emptyTextFieldAll();

	}

	protected void queryEmail() {
		if (!txtEmail.getText().isEmpty()) {
			List<Student> list = stdService.getByEmail(txtEmail.getText());
			if (list.isEmpty()) {
				JOptionPane.showMessageDialog(null, "email bulunamadı.");
			}
			fillTable(list);
		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "email kutucugunu doldurunuz.");
		}

	}

	protected void queryFirstName() {
		if (!txtFirstName.getText().isEmpty()) {
			List<Student> list = stdService.getByName(txtFirstName.getText());
			if (list.isEmpty()) {
				JOptionPane.showMessageDialog(null, "öğrenci bulunamadı.");
			}
			fillTable(list);
		} else if (txtFirstName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "first name kutucugunu doldurunuz.");
		}
	}

	public void selectedRow(long val) {
		Student student = stdService.findId(val);
		select(val);

		txtFirstName.setText(student.getFirstName());
		txtLastName.setText(student.getLastName());
		txtEmail.setText(student.getEmail());
		txtPhone1.setText(student.getContact().getPhoneNumber1());
		txtPhone2.setText(student.getContact().getPhoneNumber2());
		textAreaAddress1.setText(student.getContact().getAddress1());
		textAreaAdress2.setText(student.getContact().getAddress2());
		if (student.getGender().equalsIgnoreCase("MAN")) {
			comboBoxGender.setSelectedIndex(0);
		} else if (student.getGender().equalsIgnoreCase("WOMAN")) {
			comboBoxGender.setSelectedIndex(1);
		}
	}

	protected void fillTable(List<Student> list) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		Object[] columns = new Object[4];
		for (int i = 0; i < list.size(); i++) {
			columns[0] = list.get(i).getId();
			columns[1] = list.get(i).getEmail();
			columns[2] = list.get(i).getFirstName();
			columns[3] = list.get(i).getLastName();
			model.addRow(columns);
		}

	}

	private void emptyTextFieldAll() {
		txtId.setText("");
		textAreaAddress1.setText("");
		textAreaAdress2.setText("");
		txtEmail.setText("");
		txtFirstName.setText("");
		txtLastName.setText("");
		txtPhone1.setText("");
		txtPhone2.setText("");

	}

	public void save() {
		if (!txtFirstName.getText().isEmpty() && !txtLastName.getText().isEmpty() && !txtEmail.getText().isEmpty()
				&& !txtPhone1.getText().isEmpty() && !textAreaAddress1.getText().isEmpty()) {
			Student student = new Student(txtFirstName.getText(), txtLastName.getText(), txtEmail.getText(),
					String.valueOf(comboBoxGender.getSelectedItem()), new Contact(textAreaAddress1.getText(),
							textAreaAdress2.getText(), txtPhone1.getText(), txtPhone2.getText()));

			if (student.getEmail().contains("@") && !student.getEmail().startsWith("@")
					&& StringUtils.isNumeric(student.getContact().getPhoneNumber1())) {
				if (StringUtils.isNumeric(student.getContact().getPhoneNumber2())
						|| student.getContact().getPhoneNumber2().isEmpty()) {

					stdService.create(student);
				} else {
					JOptionPane.showMessageDialog(null, "phone 2 alanına karakter giriniz.");
				}

			} else {
				if (student.getEmail().startsWith("@")) {
					JOptionPane.showMessageDialog(null, "email @ ile başlayamaz.");
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "zorunlu alanları doldurunuz.");
		}

		List<Student> list = stdService.getAll();
		fillTable(list);
		emptyTextFieldAll();
	}

	public void update() {
		if (txtId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "lütfen metin alanını boş bırakmayınız.");
		} else {
			Contact contact = contactService.updateContact(Long.parseLong(txtId.getText()), textAreaAddress1.getText(),
					textAreaAdress2.getText(), txtPhone1.getText(), txtPhone2.getText());
			stdService.update(Long.parseLong(txtId.getText()), txtFirstName.getText(), txtLastName.getText(),
					txtEmail.getText(), String.valueOf(comboBoxGender.getSelectedItem()), contact);
		}
		List<Student> list = stdService.getAll();
		fillTable(list);

		emptyTextFieldAll();
	}

	public void locale() {
		if (comboBoxLanguage.getSelectedIndex() == 0) {
			Locale.setDefault(new Locale("en", "EN"));
			i18n();
			comboBoxLanguage.setSelectedIndex(0);
		} else if (comboBoxLanguage.getSelectedIndex() == 1) {
			Locale.setDefault(new Locale("tr", "TR"));
			i18n();
			comboBoxLanguage.setSelectedIndex(1);
		} else if (comboBoxLanguage.getSelectedIndex() == 2) {
			Locale.setDefault(new Locale("fr", "FR"));
			i18n();
		}
	}

	protected void delete() {
		if (!txtId.getText().isEmpty()) {
			stdService.delete(txtId.getText());
		} else {
			JOptionPane.showMessageDialog(null, "id alanı bos bırakılamaz.");
		}

		List<Student> list = stdService.getAll();
		fillTable(list);
		emptyTextFieldAll();
	}

	protected void queryLastName() {
		if (!txtLastName.getText().isEmpty()) {

			List<Student> list = stdService.getByLastName(txtLastName.getText());
			if (list.isEmpty()) {
				JOptionPane.showMessageDialog(null, "öğrenci bulunamadı.");
			}
			fillTable(list);

		} else if (txtLastName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "lastName kutucugunu doldurunuz.");
		}

	}

	private void select(long val) {
		switch ((int) val) {
		case 1:
			lbl_foto.setIcon(new ImageIcon(App.class.getResource("/com/melihcanozturk/image/man3.png")));
			break;
		case 2:
			lbl_foto.setIcon(new ImageIcon(App.class.getResource("/com/melihcanozturk/image/man2.png")));
			break;
		case 3:
			lbl_foto.setIcon(new ImageIcon(App.class.getResource("/com/melihcanozturk/image/man8.png")));
			break;
		case 4:
			lbl_foto.setIcon(new ImageIcon(App.class.getResource("/com/melihcanozturk/image/man3.png")));
			break;
		case 5:
			lbl_foto.setIcon(new ImageIcon(App.class.getResource("/com/melihcanozturk/image/man8.png")));
			break;
		case 6:
			lbl_foto.setIcon(new ImageIcon(App.class.getResource("/com/melihcanozturk/image/woman2.png")));
			break;
		case 7:
			lbl_foto.setIcon(new ImageIcon(App.class.getResource("/com/melihcanozturk/image/woman1.png")));
			break;
		case 8:
			lbl_foto.setIcon(new ImageIcon(App.class.getResource("/com/melihcanozturk/image/man7.png")));
			break;
		case 9:
			lbl_foto.setIcon(new ImageIcon(App.class.getResource("/com/melihcanozturk/image/woman3.png")));
			break;
		case 10:
			lbl_foto.setIcon(new ImageIcon(App.class.getResource("/com/melihcanozturk/image/woman4.png")));
			break;

		default:
			break;
		}

	}
}
