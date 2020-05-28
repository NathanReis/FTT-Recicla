package recicla.dao.jogo;

import java.sql.ResultSet;
import java.sql.SQLException;
import recicla.comuns.crud.basis.Entidade;
import recicla.comuns.vos.Jogo;
import recicla.dao.basis.MySQLDAO;

public class JogoMySQLDAO <E extends Entidade> extends MySQLDAO {
    public JogoMySQLDAO() {
        super(Jogo.class);
        
        setTabela("Jogos");
    }
    
    @Override
    protected String getComandoInserir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected String getComandoAtualizar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        Jogo entidade = new Jogo();

        entidade.setJogoId(rs.getInt("JogoId"));
        entidade.setDescricao(rs.getString("Descricao"));

        return (E)entidade;
    }
}
