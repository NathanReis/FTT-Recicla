package recicla.dao.sala;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import recicla.comuns.crud.basis.Entidade;
import recicla.comuns.vos.Sala;
import recicla.dao.basis.MySQLDAO;

public class SalaMySQLDAO <E extends Entidade> extends MySQLDAO {
    public SalaMySQLDAO() {
        super(Sala.class);
        
        setTabela("Salas");
    }
    
    @Override
    public ArrayList<Sala> listar() throws SQLException {
        ArrayList<Sala> lista = new ArrayList<Sala>();
        
        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha())) {
            String sql = getComandoListar();
            
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        lista.add((Sala)preencherEntidade(rs));
                    }
                }
            }
        }
        
        return lista;
    }
    
    @Override
    public void inserir(Entidade entidade) throws SQLException {
        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha())) {
            String query = getComandoInserir();

            try (PreparedStatement stmt = conexao.prepareStatement(query)) {
                stmt.setString(1, ((Sala)entidade).getDescricao());
                stmt.setString(2, ((Sala)entidade).getChaveAcesso());

                stmt.executeUpdate();
            }
        }
    }
    
    @Override
    protected E preencherEntidade(ResultSet rs) throws SQLException {
        Sala entidade = new Sala();

        entidade.setSalaId(rs.getInt("SalaId"));
        entidade.setDescricao(rs.getString("Descricao"));
        entidade.setChaveAcesso(rs.getString("ChaveAcesso"));

        return (E)entidade;
    }
}
