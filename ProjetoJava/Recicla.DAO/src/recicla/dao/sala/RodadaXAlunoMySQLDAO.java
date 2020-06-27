package recicla.dao.sala;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import recicla.comuns.crud.basis.Entidade;
import recicla.comuns.vos.JogoRodadaXPerguntaQuiz;
import recicla.comuns.vos.RodadaXAluno;
import recicla.dao.basis.MySQLDAO;

public class RodadaXAlunoMySQLDAO <E extends Entidade> extends MySQLDAO {
    
    public RodadaXAlunoMySQLDAO() {
        super(RodadaXAluno.class);
        
        setTabela("RodadasXUsuarios");
    }
    
    @Override
    protected String getComandoInserir() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public String getComandoAtualizar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void deletar(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    protected String getComandoDeletar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    protected E preencherEntidade(ResultSet rs) throws SQLException {
        RodadaXAluno entidade = new RodadaXAluno();

        entidade.setUsuarioId(rs.getInt("UsuarioId"));
        entidade.setRodadaId(rs.getInt("RodadaId"));
        entidade.setPontos(rs.getInt("Pontos"));
        return (E)entidade;
    }
    
    public ArrayList<RodadaXAluno> listarPorRodadaId(int rodadaId) throws SQLException {
        ArrayList<RodadaXAluno> lista = new ArrayList<RodadaXAluno>();
        
        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha())) {
            String query = "SELECT * FROM " + getTabela() + " WHERE RodadaId = ?;";
            
            try (PreparedStatement stmt = conexao.prepareStatement(query)) {
                stmt.setInt(1, rodadaId);

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        lista.add((RodadaXAluno)preencherEntidade(rs));
                    }
                }
            }
        }
        
        return lista;
    }
    
    public ArrayList<RodadaXAluno> listarPorUsuarioId(int usuarioId) throws SQLException {
        ArrayList<RodadaXAluno> lista = new ArrayList<RodadaXAluno>();
        
        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha())) {
            String query = "SELECT * FROM " + getTabela() + " WHERE UsuarioId = ?;";
            
            try (PreparedStatement stmt = conexao.prepareStatement(query)) {
                stmt.setInt(1, usuarioId);

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        lista.add((RodadaXAluno)preencherEntidade(rs));
                    }
                }
            }
        }
        
        return lista;
    }
    
    public void adicionarPontos(Entidade entidade) throws SQLException {
        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha())) {
            String sql = 
                    "UPDATE " + getTabela() + " " +
                    "SET Pontos = (Pontos + ?), " +
                    "RodadaId = ? " +
                    "WHERE UsuarioId = ?;";

            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, ((RodadaXAluno)entidade).getPontos());
                stmt.setInt(2, ((RodadaXAluno)entidade).getRodadaId());
                stmt.setInt(3, ((RodadaXAluno)entidade).getUsuarioId());

                stmt.executeUpdate();
            }
        }
    }
}
