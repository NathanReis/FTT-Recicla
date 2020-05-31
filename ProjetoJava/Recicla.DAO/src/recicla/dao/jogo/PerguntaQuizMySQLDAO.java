package recicla.dao.jogo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import recicla.comuns.crud.basis.Entidade;
import recicla.comuns.vos.PerguntaQuiz;
import recicla.dao.basis.MySQLDAO;

public class PerguntaQuizMySQLDAO <E extends Entidade> extends MySQLDAO {
    public PerguntaQuizMySQLDAO() {
        super(PerguntaQuiz.class);
        
        setTabela("PerguntasQuiz");
    }
    
    @Override
    public void inserir(Entidade entidade) throws SQLException {
        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha())) {
            String query = getComandoInserir();

            try (PreparedStatement stmt = conexao.prepareStatement(query)) {
                stmt.setString(1, ((PerguntaQuiz)entidade).getPergunta());
                stmt.setString(2, ((PerguntaQuiz)entidade).getRespostaCorreta());
                stmt.setString(3, ((PerguntaQuiz)entidade).getAlternativa1());
                stmt.setString(4, ((PerguntaQuiz)entidade).getAlternativa2());
                stmt.setInt(5, ((PerguntaQuiz)entidade).getJogoId());

                stmt.executeUpdate();
            }
        }
    }
    
    @Override
    public void atualizar(Entidade entidade) throws SQLException {
        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha())) {
            String query = getComandoAtualizar();

            try (PreparedStatement stmt = conexao.prepareStatement(query)) {
                stmt.setString(1, ((PerguntaQuiz)entidade).getPergunta());
                stmt.setString(2, ((PerguntaQuiz)entidade).getRespostaCorreta());
                stmt.setString(3, ((PerguntaQuiz)entidade).getAlternativa1());
                stmt.setString(4, ((PerguntaQuiz)entidade).getAlternativa2());
                stmt.setInt(5, ((PerguntaQuiz)entidade).getJogoId());
                stmt.setInt(6, ((PerguntaQuiz)entidade).getPerguntaQuizId());

                stmt.executeUpdate();
            }
        }
    }
    
    @Override
    protected E preencherEntidade(ResultSet rs) throws SQLException {
        PerguntaQuiz entidade = new PerguntaQuiz();

        entidade.setPerguntaQuizId(rs.getInt("PerguntaQuizId"));
        entidade.setPergunta(rs.getString("Pergunta"));
        entidade.setRespostaCorreta(rs.getString("RespostaCorreta"));
        entidade.setAlternativa1(rs.getString("Alternativa1"));
        entidade.setAlternativa2(rs.getString("Alternativa2"));
        entidade.setJogoId(rs.getInt("JogoId"));

        return (E)entidade;
    }
}
