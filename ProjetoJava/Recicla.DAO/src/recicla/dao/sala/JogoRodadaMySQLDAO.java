package recicla.dao.sala;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import recicla.comuns.crud.basis.Entidade;
import recicla.comuns.vos.JogoRodada;
import recicla.dao.basis.MySQLDAO;

public class JogoRodadaMySQLDAO <E extends Entidade> extends MySQLDAO {
    public JogoRodadaMySQLDAO() {
        super(JogoRodada.class);
        
        setTabela("JogosRodada");
    }
    
    @Override
    public void inserir(Entidade entidade) throws SQLException {
        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha())) {
            String query = getComandoInserir();

            try (PreparedStatement stmt = conexao.prepareStatement(query)) {
                stmt.setInt(1, ((JogoRodada)entidade).getJogoId());
                stmt.setInt(2, ((JogoRodada)entidade).getRodadaId());

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
        JogoRodada entidade = new JogoRodada();

        entidade.setJogoRodadaId(rs.getInt("JogoRodadaId"));
        entidade.setJogoId(rs.getInt("JogoId"));
        entidade.setRodadaId(rs.getInt("RodadaId"));

        return (E)entidade;
    }
    
    public ArrayList<JogoRodada> listarPorRodadaId(int rodadaId) throws SQLException {
        ArrayList<JogoRodada> lista = new ArrayList<JogoRodada>();
        
        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha())) {
            String query = "SELECT * FROM " + getTabela() + " WHERE RodadaId = ?;";
            
            try (PreparedStatement stmt = conexao.prepareStatement(query)) {
                stmt.setInt(1, rodadaId);

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        lista.add((JogoRodada)preencherEntidade(rs));
                    }
                }
            }
        }
        
        return lista;
    }
}
