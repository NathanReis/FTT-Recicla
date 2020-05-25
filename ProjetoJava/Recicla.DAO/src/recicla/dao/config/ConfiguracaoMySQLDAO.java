package recicla.dao.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import recicla.comuns.crud.basis.Entidade;
import recicla.comuns.vos.Configuracao;
import recicla.dao.basis.MySQLDAO;

public class ConfiguracaoMySQLDAO <E extends Entidade> extends MySQLDAO {
    public ConfiguracaoMySQLDAO() {
        super(Configuracao.class);
        
        setTabela("Configuracoes");
    }
    
    @Override
    protected String getComandoInserir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void atualizar(Entidade entidade) throws SQLException {
        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha())) {
            try (PreparedStatement stmt = conexao.prepareStatement(getComandoAtualizar())) {
                stmt.setString(1, ((Configuracao)entidade).getMusica());
                stmt.setString(2, ((Configuracao)entidade).getSom());

                stmt.executeUpdate();
            }
        }
    }
    
    @Override
    public void deletar(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected String getComandoDeletar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected E preencherEntidade(ResultSet rs) throws SQLException {
        Configuracao entidade = new Configuracao();

        entidade.setConfiguracaoId(rs.getInt("ConfiguracaoId"));
        entidade.setMusica(rs.getString("Musica"));
        entidade.setSom(rs.getString("Som"));

        return (E)entidade;
    }
}
