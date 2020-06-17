package recicla.dao.loja;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import recicla.comuns.crud.basis.Entidade;
import recicla.comuns.vos.ItemLojaXUsuario;
import recicla.dao.basis.MySQLDAO;

public class ItemLojaXUsuarioMySQLDAO <E extends Entidade> extends MySQLDAO {
    public ItemLojaXUsuarioMySQLDAO() {
        super(ItemLojaXUsuario.class);
        
        setTabela("ItensLojaXUsuarios");
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
        ItemLojaXUsuario entidade = new ItemLojaXUsuario();

        entidade.setItemLojaId(rs.getInt("ItemLojaId"));
        entidade.setUsuarioId(rs.getInt("UsuarioId"));
        entidade.setQuantidade(rs.getInt("Quantidade"));

        return (E)entidade;
    }
    
    public ArrayList<ItemLojaXUsuario> listarPorUsuarioId(int usuarioId) throws SQLException {
        ArrayList<ItemLojaXUsuario> lista = new ArrayList<ItemLojaXUsuario>();
        
        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha())) {
            String query = "SELECT * FROM " + getTabela() + " WHERE UsuarioId = ?;";
            
            try (PreparedStatement stmt = conexao.prepareStatement(query)) {
                stmt.setInt(1, usuarioId);

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        lista.add((ItemLojaXUsuario)preencherEntidade(rs));
                    }
                }
            }
        }
        
        return lista;
    }
    
    public void comprarItem(int itemId, int usuarioId, int quantidade) throws SQLException {
        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha())) {
            String query = 
                "UPDATE " + getTabela() + " " +
                "SET Quantidade = (Quantidade + ?) " +
                "WHERE ItemLojaId = ? AND UsuarioId = ?;";

            try (PreparedStatement stmt = conexao.prepareStatement(query)) {
                stmt.setInt(1, quantidade);
                stmt.setInt(2, itemId);
                stmt.setInt(3, usuarioId);

                stmt.executeUpdate();
            }
        }
    }
    
    public void consumirItem(int itemId, int usuarioId) throws SQLException {
        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha())) {
            String query = 
                "UPDATE " + getTabela() + " " +
                "SET Quantidade = (Quantidade - 1) " +
                "WHERE ItemId = ? AND UsuarioId = ?;";

            try (PreparedStatement stmt = conexao.prepareStatement(query)) {
                stmt.setInt(2, itemId);
                stmt.setInt(3, usuarioId);

                stmt.executeUpdate();
            }
        }
    }
}
