package recicla.dao.sala;

import annotation.CampoNoBanco;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import recicla.comuns.crud.basis.Entidade;
import recicla.comuns.vos.Rodada;
import recicla.dao.basis.MySQLDAO;

public class RodadaMySQLDAO <E extends Entidade> extends MySQLDAO {
    public RodadaMySQLDAO() {
        super(Rodada.class);
        
        setTabela("Rodadas");
    }
    
    @Override
    public void inserir(Entidade entidade) throws SQLException {
        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha())) {
            String sql = getComandoInserir();

            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, ((Rodada)entidade).getSalaId());

                stmt.executeUpdate();
            }
        }
    }
     public int getIdInserido() throws SQLException {
        int idInserido = 0;
        
        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha() )) {
           //String sql = "SELECT LAST_INSERT_ID() AS id FROM " + tabela + ";";
            String sql = "SELECT RodadaId from " + getTabela() + " order by RodadaId desc LIMIT 1";


            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.first()) {
                        idInserido = rs.getInt("RodadaId");
                    }
                }
            }
        }
        
        return idInserido;
    }
    @Override
    protected String getComandoAtualizar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    protected E preencherEntidade(ResultSet rs) throws SQLException {
        Rodada entidade = new Rodada();

        entidade.setRodadaId(rs.getInt("RodadaId"));
        entidade.setSalaId(rs.getInt("SalaId"));

        return (E)entidade;
    }
    
    public ArrayList<Rodada> listarPorSalaId(int salaId) throws SQLException {
        ArrayList<Rodada> lista = new ArrayList<Rodada>();
        
        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha())) {
            String sql = "SELECT * FROM " + getTabela() + " WHERE SalaId = ?;";
            
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        lista.add((Rodada)preencherEntidade(rs));
                    }
                }
            }
        }
        
        return lista;
    }
    
     public void atualiza_rodada(Entidade entidade) throws SQLException {
        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha())) {
            String query = "UPDATE " + getTabela() +" SET StatusRodada = ? WHERE RodadaId = ?";

            try (PreparedStatement stmt = conexao.prepareStatement(query)) {
                stmt.setInt(1, ((Rodada)entidade).getStatusRodada());
                stmt.setInt(2, ((Rodada)entidade).getRodadaId());

                stmt.executeUpdate();
            }
        }
    }
}
