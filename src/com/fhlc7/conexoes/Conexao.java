package com.fhlc7.conexoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexao {

	public static Connection con;
	
	public static void conectar() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/agenda", "root", "");
			con.setAutoCommit(false);
			System.out.println("Conectado com sucesso");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao tentar conectar com banco de dados: " + e);
		}
	}

	public static void commit(){
		try {
			con.commit();
			System.out.println("Commit com sucesso");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao tentar commit: " + e);
		}
	}
	
	public static void rollback(){
		try {
			con.rollback();
			System.out.println("Rollback com sucesso");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao tentar rollback: " + e);
		}
	}
	
	public static void desconectar() {
		try {
			con.close();
			System.out.println("Desconectado com sucesso");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao tentar desconectar: " + e);
		}
	}
	
}
