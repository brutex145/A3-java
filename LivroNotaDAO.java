import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroNotaDAO {
    public List<LivroNota> obterNotas() {
        Conexao conexao = new Conexao();
        String sql = "select nome, avg(nota) from livro l join nota n on l.id = n.  id_livro group by (n.id_livro);";
        List<LivroNota> lista = new ArrayList<>();
        try {
            PreparedStatement ps = conexao.obterConexao().prepareStatement(sql);
            ResultSet medias = ps.executeQuery();
            while (medias.next()) {
                LivroNota media = new LivroNota(medias.getString(1), medias.getDouble(2));
                lista.add(media);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
