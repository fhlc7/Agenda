package com.fhlc7.testes;

import com.fhlc7.conexoes.Conexao;

public class ConexaoTestes {

	public static void main(String[] args) {
		Conexao.conectar();
		Conexao.commit();
		Conexao.rollback();
		Conexao.desconectar();
	}
	
}
