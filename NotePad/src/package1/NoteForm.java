package package1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.JobAttributes;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NoteForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtTitle;
	
	
	ArrayList<Note> notes = new ArrayList<>();
	DefaultListModel<String> listModel ;
	
	String output ;
	private JButton btnUpdate;
	
	
	public void filltheList() {
		
		listModel.removeAllElements();
		
		for(Note n : notes) {
			listModel.addElement(n.getNoteId()+" "+n.getNoteTitle()+ " \n" );
		
 		}

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NoteForm frame = new NoteForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NoteForm() {
		
		
		setBackground(SystemColor.activeCaption);
		setFont(new Font("Segoe UI", Font.BOLD, 15));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 440);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(25, 10, 674, 30);
		contentPane.add(panel);
		
		JLabel lblpanel = new JLabel("Write Your Notes with Your NotePad");
		lblpanel.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		panel.add(lblpanel);
		
		JLabel lblId = new JLabel("Note Id :");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblId.setBounds(41, 70, 68, 13);
		contentPane.add(lblId);
		
		JLabel lblTitle = new JLabel("Note Title :");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTitle.setBounds(41, 93, 86, 13);
		contentPane.add(lblTitle);
		
		JLabel lblExp = new JLabel("Note Explanation : ");
		lblExp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblExp.setBounds(41, 130, 129, 13);
		contentPane.add(lblExp);
		
		txtId = new JTextField();
		txtId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtId.setBounds(128, 67, 96, 19);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		txtTitle = new JTextField();
		txtTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTitle.setBounds(128, 90, 96, 19);
		contentPane.add(txtTitle);
		txtTitle.setColumns(10);
		
		JTextArea txtExp = new JTextArea();
		txtExp.setBounds(36, 160, 214, 167);
		contentPane.add(txtExp);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                int noteId = Integer.parseInt(txtId.getText());
                String title = txtTitle.getText();
                String exp = txtExp.getText();
        		
                
       
        				
        		if(notes.stream()
        				.filter(n -> n.noteId == noteId)
        				.findFirst()
        				.isPresent()) {
        			JOptionPane.showMessageDialog(contentPane, "This Note Id is used");
        		}
        		else {
        			Note note = new Note(noteId , title , exp);
        			notes.add(note);
        			
        			JOptionPane.showMessageDialog(contentPane, "Note is saved");
        			
        			txtId.setText("");
        			txtTitle.setText("");
        			txtExp.setText("");
        			
        		}
				
				
				
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSave.setBounds(41, 358, 86, 21);
		contentPane.add(btnSave);
		
		JButton btnList = new JButton("List the Notes");
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filltheList();
			}
		});
		btnList.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnList.setBounds(140, 358, 129, 21);
		contentPane.add(btnList);
		
		JTextArea txtDetails = new JTextArea();
		txtDetails.setBounds(556, 93, 176, 167);
		contentPane.add(txtDetails);
		
		JLabel lblDetails = new JLabel("Details of the Notes");
		lblDetails.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDetails.setBounds(548, 58, 160, 13);
		contentPane.add(lblDetails);
		

		listModel = new DefaultListModel<>();
		
		JList list = new JList(listModel);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				output="";
				
				String selectedNote = list.getSelectedValue().toString();
				String[] selected = selectedNote.split(" ");
				int id = Integer.parseInt(selected[0]);
				
				notes.stream()
				     .filter(n -> n.noteId == id)
				     .toList()
				     .forEach(n -> {
				    	 output += n.getNoteExp();
				     });
				
			
				txtDetails.setText(output);
				
			  
				
				
			}
		});
		list.setBounds(320, 90, 200, 245);
		contentPane.add(list);
		
		JLabel lblList = new JLabel("List of the Notes");
		lblList.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblList.setBounds(320, 58, 137, 13);
		contentPane.add(lblList);
		
		
		
		JButton btnDelete = new JButton("Delete ");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				String selectedNote = list.getSelectedValue().toString();
				int id = Integer.parseInt( selectedNote.split(" ")[0]);
				
			   
				notes.removeIf(n -> n.getNoteId() == id);
				txtDetails.setText(" ");
				filltheList();
				
				
			}
		});
		
		
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDelete.setBounds(594, 301, 86, 21);
		contentPane.add(btnDelete);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
	
				
				String selectedNote = list.getSelectedValue().toString();
				int id = Integer.parseInt( selectedNote.split(" ")[0]);
				
				txtId.setText(selectedNote.split(" ")[0]);
				txtTitle.setText(selectedNote.split(" ")[1]);
				txtExp.setText(txtDetails.getText());
				
				notes.removeIf(n -> n.getNoteId() == id);
				txtDetails.setText(" ");
			
				txtId.setEnabled(false);
				
				
				
				      
				
				
				
			}
		});
		btnUpdate.setBounds(594, 340, 85, 21);
		contentPane.add(btnUpdate);
	}
}
