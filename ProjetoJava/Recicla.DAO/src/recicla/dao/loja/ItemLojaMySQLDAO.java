package recicla.dao.loja;

import java.sql.ResultSet;
import java.sql.SQLException;
import recicla.comuns.crud.basis.Entidade;
import recicla.comuns.vos.ItemLoja;
import recicla.dao.basis.MySQLDAO;

public class ItemLojaMySQLDAO <E extends Entidade> extends MySQLDAO {
    public ItemLojaMySQLDAO() {
        super(ItemLoja.class);
        
        setTabela("ItensLoja");
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
        ItemLoja entidade = new ItemLoja();

        entidade.setItemLojaId(rs.getInt("ItemLojaId"));
        entidade.setNome(rs.getString("Nome"));
        entidade.setDescricao(rs.getString("Descricao"));
        entidade.setPreco(rs.getDouble("Preco"));

        return (E)entidade;
    }
}
