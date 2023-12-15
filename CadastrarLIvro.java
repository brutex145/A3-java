import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CadastrarLIvro {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/biblioteca";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "gui246810";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Digite o ID do usuário que está cadastrando o livro:");
            int idUsuario = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner
            
            System.out.println("Digite o título do livro:");
            String tituloLivro = scanner.nextLine();
            
            System.out.println("Digite a nota média do livro:");
            double notaMedia = scanner.nextDouble();
            
            cadastrarLivro(idUsuario, tituloLivro, notaMedia);
        }
    }

    public static void cadastrarLivro(int idUsuario, String tituloLivro, double notaMedia) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            // Inserir o livro na tabela livros
            String sqlLivro = "INSERT INTO livro (id_usuario, titulo, nota_media) VALUES (?, ?, ?)";
            try (PreparedStatement psLivro = connection.prepareStatement(sqlLivro)) {
                psLivro.setInt(1, idUsuario);
                psLivro.setString(2, tituloLivro);
                psLivro.setDouble(3, notaMedia);
                psLivro.executeUpdate();
            }

            // Obter o ID do livro recém-cadastrado
            int idLivro = 0;
            String sqlIdLivro = "SELECT LAST_INSERT_ID()";
            try (PreparedStatement psIdLivro = connection.prepareStatement(sqlIdLivro);
                 ResultSet resultSet = psIdLivro.executeQuery()) {
                if (resultSet.next()) {
                    idLivro = resultSet.getInt(1);
                }
            }

            System.out.println("Livro cadastrado com sucesso! ID do livro: " + idLivro);

        } catch (SQLException e) {
        }
    }
}