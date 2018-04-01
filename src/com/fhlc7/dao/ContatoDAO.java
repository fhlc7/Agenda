package com.fhlc7.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fhlc7.conexoes.Conexao;
import com.fhlc7.entidades.Contato;

public class ContatoDAO {
	
	public void inserir(Contato contato) throws SQLException {
		String sql = "INSERT INTO dados (nome, fone, email, endereco) VALUES (?, ?, ?, ?);";
		PreparedStatement ps = Conexao.con.prepareStatement(sql);
		int i = 0;
		ps.setString(++i, contato.getNome());
		ps.setString(++i, contato.getFone());
		ps.setString(++i, contato.getEmail());
		ps.setString(++i, contato.getEndereco());
		ps.execute();
		ps.close();
	}
	
	public void alterar(Contato contato) throws SQLException {
		String sql = "UPDATE dados SET nome = ?, fone = ?, email = ?, endereco = ? WHERE id = ?;";
		PreparedStatement ps = Conexao.con.prepareStatement(sql);
		int i = 0;
		ps.setString(++i, contato.getNome());
		ps.setString(++i, contato.getFone());
		ps.setString(++i, contato.getEmail());
		ps.setString(++i, contato.getEndereco());
		ps.setInt(++i, contato.getId());
		ps.execute();
		ps.close();
	}
	
	public void deletar(Contato contato) throws SQLException {
		String sql = "DELETE FROM dados WHERE id = ?;";
		PreparedStatement ps = Conexao.con.prepareStatement(sql);
		int i = 0;
		ps.setInt(++i, contato.getId());
		ps.execute();
		ps.close();
	}
	
	public Contato get(int id) throws SQLException {
		Contato contato = null;
		String sql = "SELECT * FROM dados WHERE id = ?;";
		PreparedStatement ps = Conexao.con.prepareStatement(sql);
		int i = 0;
		ps.setInt(++i, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			i = 0;
			contato = new Contato();
			contato.setId(rs.getInt(++i));
			contato.setNome(rs.getString(++i));
			contato.setFone(rs.getString(++i));
			contato.setEmail(rs.getString(++i));
			contato.setEndereco(rs.getString(++i));
		}
		rs.close();
		ps.close();
		return contato;
	}
	
	public List<Contato> procurar(String procurar) throws SQLException {
		List<Contato> list = new ArrayList<Contato>();
		String sql = "SELECT * FROM dados WHERE id LIKE ? OR nome LIKE ? OR fone LIKE ? OR email LIKE ? OR endereco LIKE ?;";
		PreparedStatement ps = Conexao.con.prepareStatement(sql);
		int i = 0;
		ps.setObject(++i, "%" + procurar + "%");
		ps.setObject(++i, "%" + procurar + "%");
		ps.setObject(++i, "%" + procurar + "%");
		ps.setObject(++i, "%" + procurar + "%");
		ps.setObject(++i, "%" + procurar + "%");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			i = 0;
			Contato contato = new Contato();
			contato.setId(rs.getInt(++i));
			contato.setNome(rs.getString(++i));
			contato.setFone(rs.getString(++i));
			contato.setEmail(rs.getString(++i));
			contato.setEndereco(rs.getString(++i));
			list.add(contato);
		}
		rs.close();
		ps.close();
		return list;
	}

}
