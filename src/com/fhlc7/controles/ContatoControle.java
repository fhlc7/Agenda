package com.fhlc7.controles;

import java.sql.Connection;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.fhlc7.conexoes.Conexao;
import com.fhlc7.dao.ContatoDAO;
import com.fhlc7.entidades.Contato;

public class ContatoControle {

	private static ContatoDAO dao = new ContatoDAO();
	
	public static void salvar(Contato contato){
		Conexao.conectar();
		try {
			if (contato.getId() == 0) {
				dao.inserir(contato);
			} else {
				dao.alterar(contato);
			}
			Conexao.commit();
			JOptionPane.showMessageDialog(null, "Salvo com sucesso");
		} catch (Exception e) {
			Conexao.rollback();
			JOptionPane.showMessageDialog(null, "Erro ao tentar salvar: " + e);
		}
		Conexao.desconectar();
	}
	
	public static void deletar(Contato contato){
		Conexao.conectar();
		try {
			if (contato.getId() == 0) {
				JOptionPane.showMessageDialog(null, "Selecione um contato para deletar");
			} else {
				if (JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja deletar este contato?", "Sistema",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					dao.deletar(contato);
					Conexao.commit();
					JOptionPane.showMessageDialog(null, "Deletado com sucesso");
				}
			}
		} catch (Exception e) {
			Conexao.rollback();
			JOptionPane.showMessageDialog(null, "Erro ao tentar deletar: " + e);
		}
		Conexao.desconectar();
	}
	
	public static DefaultTableModel model(String procurar) {
		DefaultTableModel model = new DefaultTableModel(null, new String[]{"id", "Nome", "Fone", "E-mail", "Endereço"}){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		Conexao.conectar();
		try {
			for (Contato contato : dao.procurar(procurar)) {
				model.addRow(new Object[] {
						contato.getId(),
						contato.getNome(),
						contato.getFone(),
						contato.getEmail(),
						contato.getEndereco()
				});
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao tentar procurar: " + e);
		}
		Conexao.desconectar();
		return model;
	}
	
	public static Contato get(int id){
		Contato contato = null;
		Conexao.conectar();
		try {
			contato = dao.get(id);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao tentar selecionar: " + e);
		}
		Conexao.desconectar();
		return contato;
	}
	
}
