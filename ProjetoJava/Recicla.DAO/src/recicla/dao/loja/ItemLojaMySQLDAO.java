package recicla.dao.loja;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import recicla.comuns.crud.basis.Entidade;
import recicla.comuns.vos.ItemLoja;
import recicla.dao.basis.MySQLDAO;

public class ItemLojaMySQLDAO <E extends Entidade> extends MySQLDAO {
    public ItemLojaMySQLDAO() {
        super(ItemLoja.class);
        
        setTabela("ItensLoja");
    }
    
    @Override
    public ArrayList<ItemLoja> listar() throws SQLException {
        ArrayList<ItemLoja> lista = new ArrayList<ItemLoja>();
        
        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha())) {
            String sql = getComandoListar();
            
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        lista.add((ItemLoja)preencherEntidade(rs));
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
        ItemLoja entidade = new ItemLoja();

        entidade.setItemLojaId(rs.getInt("ItemLojaId"));
        entidade.setNome(rs.getString("Nome"));
        entidade.setDescricao(rs.getString("Descricao"));
        entidade.setPreco(rs.getDouble("Preco"));

        return (E)entidade;
    }
}
