import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VerificacaoLogin {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/biblioteca";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "gui246810";

    public static boolean checkLogin(int iduser, String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            String sql = "SELECT * FROM Usuario WHERE ID = ? AND senha = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, iduser);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return true;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return false;
    }
}