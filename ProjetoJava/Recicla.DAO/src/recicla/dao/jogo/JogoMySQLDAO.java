package recicla.dao.jogo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import recicla.comuns.crud.basis.Entidade;
import recicla.comuns.vos.Jogo;
import recicla.dao.basis.MySQLDAO;

public class JogoMySQLDAO <E extends Entidade> extends MySQLDAO {
    public JogoMySQLDAO() {
        super(Jogo.class);
        
        setTabela("Jogos");
    }
    
    @Override
    public ArrayList<Jogo> listar() throws SQLException {
        ArrayList<Jogo> lista = new ArrayList<Jogo>();
        
        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha())) {
            String sql = getComandoListar();
            
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        lista.add((Jogo)preencherEntidade(rs));
                    }
                }
            }
        }
        
        return lista;
    }
    
    @Override
    protected String getComandoInserir() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    protected String getComandoAtualizar() {
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
        Jogo entidade = new Jogo();

        entidade.setJogoId(rs.getInt("JogoId"));
        entidade.setDescricao(rs.getString("Descricao"));

        return (E)entidade;
    }
}
