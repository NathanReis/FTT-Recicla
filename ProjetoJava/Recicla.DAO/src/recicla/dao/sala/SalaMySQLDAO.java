package recicla.dao.sala;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import recicla.comuns.crud.basis.Entidade;
import recicla.comuns.vos.Sala;
import recicla.dao.basis.MySQLDAO;

public class SalaMySQLDAO <E extends Entidade> extends MySQLDAO {
    public SalaMySQLDAO() {
        super(Sala.class);
        
        setTabela("Salas");
    }
    
    @Override
    public void inserir(Entidade entidade) throws SQLException {
        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha())) {
            String query = getComandoInserir();

            try (PreparedStatement stmt = conexao.prepareStatement(query)) {
                stmt.setString(1, ((Sala)entidade).getDescricao());
                stmt.setString(2, ((Sala)entidade).getChaveAcesso());
                stmt.setFloat(3, ((Sala)entidade).getHorarioInicio());

                stmt.executeUpdate();
            }
        }
    }
    
    @Override
    public void atualizar(Entidade entidade) throws SQLException {
        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha())) {
            String query = getComandoAtualizar();

            try (PreparedStatement stmt = conexao.prepareStatement(query)) {
                stmt.setString(1, ((Sala)entidade).getDescricao());
                stmt.setFloat(2, ((Sala)entidade).getHorarioInicio());
                stmt.setInt(3, ((Sala)entidade).getSalaId());

                stmt.executeUpdate();
            }
        }
    }
    
    @Override
    public String getComandoAtualizar() {
        return "UPDATE " + getTabela() + " SET Descricao = ?, HorarioInicio = ? WHERE SalaId = ?;";
    }
    
    @Override
    protected E preencherEntidade(ResultSet rs) throws SQLException {
        Sala entidade = new Sala();

        entidade.setSalaId(rs.getInt("SalaId"));
        entidade.setDescricao(rs.getString("Descricao"));
        entidade.setChaveAcesso(rs.getString("ChaveAcesso"));
        entidade.setHorarioInicio(rs.getFloat("HorarioInicio"));

        return (E)entidade;
    }
}
