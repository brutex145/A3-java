/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 823160923
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static Connection conexao = null;

    public static Connection obterConexao() {
        if (conexao == null) {
            try {
                conexao = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/biblioteca",
                        "root",
                        "anima123");
            } catch (SQLException e) {
                System.err.println("Erro ao obter conexão com o banco de dados:");
                e.printStackTrace();
                // Considere lançar uma exceção personalizada ou realizar outra ação apropriada.
            }
        }
        return conexao;
    }

    public static void fecharConexao() {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão com o banco de dados:");
                e.printStackTrace();
            } finally {
                conexao = null; // Certifique-se de que a referência seja nula após fechar a conexão
            }
        }
    }
}

