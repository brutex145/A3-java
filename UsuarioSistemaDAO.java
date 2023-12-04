import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioSistemaDAO {

    Connection obterConexao() {
        try {
            Connection conexao = DriverManager.getConnection(
                    // essa é a conhecida string de conexão
                    "jdbc:mysql://localhost:3306/biblioteca",
                    "root",
                    "gui246810");
            return conexao;
        } catch (SQLException e) {
            System.out.println("Exceção: " + e.getMessage());
        }
        return null;
    }

    List<AutenticacaoUsuario> listar() {
        List<AutenticacaoUsuario> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM usuario";
            PreparedStatement ps = obterConexao().prepareStatement(sql);
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {
                AutenticacaoUsuario usuario = new AutenticacaoUsuario(resultado.getString(1), resultado.getString(2),
                        resultado.getBoolean(3));
                lista.add(usuario);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println("Exceção: " + e.getMessage());
            return lista;
        }
    }

    AutenticacaoUsuario obter(String login, String senha) {
        try {
            String sql = "SELECT * FROM usuario WHERE login = ? AND senha = ?";
            PreparedStatement ps = obterConexao().prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, senha);
            ResultSet resultado = ps.executeQuery();
            if (resultado.next()) {
                return new AutenticacaoUsuario(resultado.getString(1), resultado.getString(2), resultado.getBoolean(3));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Exceção: " + e.getMessage());
            return null;
        }
    }

    boolean atualizar(AutenticacaoUsuario usuario) {
        try {
            String sql = "UPDATE usuario SET senha = ?, superusuario = ? WHERE login = ?";
            PreparedStatement ps = obterConexao().prepareStatement(sql);
            ps.setString(1, usuario.getSenha());
            ps.setBoolean(2, usuario.isSuperusuario());
            ps.setString(3, usuario.getLogin());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Exceção: " + e.getMessage());
            return false;
        }
    }

    boolean criar(AutenticacaoUsuario usuario) {
        try {
            String sql = "INSERT INTO usuario (login, senha, superusuario) VALUES (?, ?, ?)";
            PreparedStatement ps = obterConexao().prepareStatement(sql);
            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getSenha());
            ps.setBoolean(3, usuario.isSuperusuario());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Exceção: " + e.getMessage());
            return false;
        }
    }

    boolean apagar(AutenticacaoUsuario usuario) {
        try {
            String sql = "DELETE FROM usuario WHERE login = ?";
            PreparedStatement ps = obterConexao().prepareStatement(sql);
            ps.setString(1, usuario.getLogin());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Exceção: " + e.getMessage());
            return false;
        }
    }
}
