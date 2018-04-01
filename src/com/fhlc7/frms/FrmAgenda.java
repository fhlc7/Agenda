package com.fhlc7.frms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.text.MaskFormatter;

import com.fhlc7.controles.ContatoControle;
import com.fhlc7.dao.ContatoDAO;
import com.fhlc7.entidades.Contato;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmAgenda extends JFrame {

	private Contato contato = new Contato();
	
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtProcurar;
	private JTable table;
	private JFormattedTextField frmtdtxtfldFone;
	private JTextArea txtrEndereco;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmAgenda frame = new FrmAgenda();
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
	public FrmAgenda() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				atualizar();
			}
		});
		setResizable(false);
		setTitle("Agenda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(6, 6, 483, 204);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblId = new JLabel("id:");
		lblId.setBounds(49, 12, 13, 16);
		panel.add(lblId);
		
		txtId = new JTextField();
		txtId.setBounds(74, 6, 122, 28);
		panel.add(txtId);
		txtId.setEditable(false);
		txtId.setText("id");
		txtId.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(25, 52, 37, 16);
		panel.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(74, 46, 400, 28);
		panel.add(txtNome);
		txtNome.setText("Nome");
		txtNome.setColumns(10);
		
		JLabel lblFone = new JLabel("Fone:");
		lblFone.setBounds(31, 92, 31, 16);
		panel.add(lblFone);
		
		try {
			MaskFormatter mascara = new MaskFormatter("(##) *####-####");
			frmtdtxtfldFone = new JFormattedTextField(mascara);
		} catch (ParseException e) {
			// TODO Bloco catch gerado automaticamente
			e.printStackTrace();
		}
		frmtdtxtfldFone.setBounds(74, 86, 107, 28);
		panel.add(frmtdtxtfldFone);
		frmtdtxtfldFone.setText("(00) 00000-0000");
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(193, 92, 39, 16);
		panel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(244, 86, 230, 28);
		panel.add(txtEmail);
		txtEmail.setText("E-mail");
		txtEmail.setColumns(10);
		
		JLabel lblEndereco = new JLabel("Endere\u00E7o:");
		lblEndereco.setBounds(6, 132, 56, 16);
		panel.add(lblEndereco);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(74, 126, 400, 70);
		panel.add(scrollPane);
		
		txtrEndereco = new JTextArea();
		scrollPane.setViewportView(txtrEndereco);
		txtrEndereco.setText("Endere\u00E7o");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(6, 222, 483, 393);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblProcurar = new JLabel("Procurar:");
		lblProcurar.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));
		lblProcurar.setBounds(7, 12, 55, 16);
		panel_1.add(lblProcurar);
		
		txtProcurar = new JTextField();
		txtProcurar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				procurar();
			}
		});
		txtProcurar.setToolTipText("");
		txtProcurar.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtProcurar.setText("Procurar");
		txtProcurar.setBounds(74, 6, 400, 28);
		panel_1.add(txtProcurar);
		txtProcurar.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(7, 46, 467, 341);
		panel_1.add(scrollPane_1);
		
		table = new JTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				editar();
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				editar();
			}
		});
		scrollPane_1.setViewportView(table);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(6, 627, 483, 39);
		contentPane.add(panel_2);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				novo();
			}
		});
		panel_2.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizar();
			}
		});
		btnAtualizar.setMnemonic('a');
		panel_2.add(btnAtualizar);
		btnNovo.setMnemonic('n');
		panel_2.add(btnNovo);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salvar();
			}
		});
		btnSalvar.setMnemonic('s');
		panel_2.add(btnSalvar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletar();
			}
		});
		btnDeletar.setMnemonic('d');
		panel_2.add(btnDeletar);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fechar();
			}
		});
		btnFechar.setMnemonic('f');
		panel_2.add(btnFechar);
	}
	
	private void limpar() {
		contato = new Contato();
		String limpo = null;
		txtProcurar.setText(limpo);
		txtId.setText("0");
		txtNome.setText(limpo);
		frmtdtxtfldFone.setText(limpo);
		txtEmail.setText(limpo);
		txtrEndereco.setText(limpo);
	}
	
	private void atualizar(){
		limpar();
		procurar();
		txtProcurar.requestFocus();
	}
	
	private void fechar(){
		System.exit(0);
	}
	
	private boolean validarCampos(){
		if(txtNome.getText().trim().isEmpty()){
			JOptionPane.showMessageDialog(null, "Digite o nome");
			txtNome.requestFocus();
			return false;
		}
		/*if(frmtdtxtfldFone.getText().trim().isEmpty()){
			JOptionPane.showMessageDialog(null, "Digite o fone");
			frmtdtxtfldFone.requestFocus();
			return false;
		}
		if(txtEmail.getText().trim().isEmpty()){
			JOptionPane.showMessageDialog(null, "Digite o e-mail");
			txtEmail.requestFocus();
			return false;
		}
		if(txtrEndereco.getText().trim().isEmpty()){
			JOptionPane.showMessageDialog(null, "Digite o endereço");
			txtrEndereco.requestFocus();
			return false;
		}*/
		return true;
	}
	
	private void criarObjetoFormulario(){
		contato.setId(Integer.valueOf(txtId.getText()));
		contato.setNome(txtNome.getText().trim());
		contato.setFone(frmtdtxtfldFone.getText());
		contato.setEmail(txtEmail.getText().trim());
		contato.setEndereco(txtrEndereco.getText());
	}
	
	private void criarObjetoTabela(){
		int linha = table.getSelectedRow();
		if (linha >= 0) {
			int id = Integer.valueOf(table.getValueAt(linha, 0).toString());
			contato = ContatoControle.get(id);
		}
	}
	
	private void preencherFormulario(){
		txtId.setText(String.valueOf(contato.getId()));
		txtNome.setText(contato.getNome());
		frmtdtxtfldFone.setText(contato.getFone().equals("(  )      -    ") ? null : contato.getFone()); //frmtdtxtfldFone.setText(null); frmtdtxtfldFone.setText(contato.getFone());
		txtEmail.setText(contato.getEmail());
		txtrEndereco.setText(contato.getEndereco());
	}
	
	private void salvar() {
		if (validarCampos()) {
			criarObjetoFormulario();
			ContatoControle.salvar(contato);
			atualizar();
		}
	}
	
	private void novo(){
		limpar();
		txtNome.requestFocus();
	}
	
	private void procurar(){
		table.setModel(ContatoControle.model(txtProcurar.getText().trim()));
	}
	
	private void editar() {
		criarObjetoTabela();
		preencherFormulario();
	}
	
	private void deletar() {
		ContatoControle.deletar(contato);
		atualizar();
	}
}
