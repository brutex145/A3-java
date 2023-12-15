import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastrarUsuario {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/biblioteca";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "gui246810";

    public static void cadastrarUsuario(String nome, String email, String senha) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, nome);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, senha);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Usuário cadastrado com sucesso!");
                } else {
                    System.out.println("Falha ao cadastrar o usuário.");
                }
            }
        } catch (SQLException e) {
        }
    }

    public static void main(String[] args) {
        // Exemplo de uso
        cadastrarUsuario("João", "joao@email.com", "senhasegura");
    }
}
