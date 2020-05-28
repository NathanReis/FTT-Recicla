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
import recicla.comuns.vos.Sala;
import recicla.dao.basis.MySQLDAO;

public class RodadaMySQLDAO <E extends Entidade> extends MySQLDAO {
    public RodadaMySQLDAO() {
        super(Rodada.class);
        
        setTabela("Rodadas");
    }
    
    @Override
    public ArrayList<Entidade> listar() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ArrayList<Rodada> listarPorSalaId(int salaId) throws SQLException {
        ArrayList<Rodada> lista = new ArrayList<Rodada>();
        
        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha())) {
            String query = "SELECT * FROM " + getTabela() + " WHERE SalaId = ?;";
            
            try (PreparedStatement stmt = conexao.prepareStatement(query)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        lista.add((Rodada)preencherEntidade(rs));
                    }
                }
            }
        }
        
        return lista;
    }
    
    @Override
    public Entidade consultar(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected String getComandoConsultar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void inserir(Entidade entidade) throws SQLException {
        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha())) {
            String query = getComandoInserir();

            try (PreparedStatement stmt = conexao.prepareStatement(query)) {
                stmt.setInt(1, ((Rodada)entidade).getSalaId());

                stmt.executeUpdate();
            }
        }
    }
    
    @Override
    protected String getComandoAtualizar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected E preencherEntidade(ResultSet rs) throws SQLException {
        Rodada entidade = new Rodada();

        entidade.setRodadaId(rs.getInt("RodadaId"));
        entidade.setSalaId(rs.getInt("SalaId"));

        return (E)entidade;
    }
}
