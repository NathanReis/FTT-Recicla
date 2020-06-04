package recicla.dao.sala;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import recicla.comuns.crud.basis.Entidade;
import recicla.comuns.vos.JogoRodadaXPerguntaQuiz;
import recicla.dao.basis.MySQLDAO;

public class JogoRodadaXPerguntaQuizMySQLDAO <E extends Entidade> extends MySQLDAO {
    public JogoRodadaXPerguntaQuizMySQLDAO() {
        super(JogoRodadaXPerguntaQuiz.class);
        
        setTabela("JogosRodadaXPerguntasQuiz");
    }
    
    @Override
    public void inserir(Entidade entidade) throws SQLException {
        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha())) {
            String query = getComandoInserir();

            try (PreparedStatement stmt = conexao.prepareStatement(query)) {
                stmt.setInt(1, ((JogoRodadaXPerguntaQuiz)entidade).getJogoRodadaId());
                stmt.setInt(2, ((JogoRodadaXPerguntaQuiz)entidade).getPerguntaQuizId());

                stmt.executeUpdate();
            }
        }
    }
    
    @Override
    protected String getComandoAtualizar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    protected E preencherEntidade(ResultSet rs) throws SQLException {
        JogoRodadaXPerguntaQuiz entidade = new JogoRodadaXPerguntaQuiz();

        entidade.setJogoRodadaId(rs.getInt("JogoRodadaId"));
        entidade.setPerguntaQuizId(rs.getInt("PerguntaQuizId"));

        return (E)entidade;
    }
    
    public ArrayList<JogoRodadaXPerguntaQuiz> listarPorJogoRodadaId(int jogoRodadaId) throws SQLException {
        ArrayList<JogoRodadaXPerguntaQuiz> lista = new ArrayList<JogoRodadaXPerguntaQuiz>();
        
        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha())) {
            String query = "SELECT * FROM " + getTabela() + " WHERE JogoRodadaId = ?;";
            
            try (PreparedStatement stmt = conexao.prepareStatement(query)) {
                stmt.setInt(1, jogoRodadaId);

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        lista.add((JogoRodadaXPerguntaQuiz)preencherEntidade(rs));
                    }
                }
            }
        }
        
        return lista;
    }
}
