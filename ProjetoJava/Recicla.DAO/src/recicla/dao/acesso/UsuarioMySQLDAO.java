package recicla.dao.acesso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import recicla.comuns.crud.basis.Entidade;
import recicla.comuns.vos.Usuario;
import recicla.dao.basis.MySQLDAO;

public class UsuarioMySQLDAO <E extends Entidade> extends MySQLDAO {
    private String chaveCriptogafia = "ld83mf0";

    public UsuarioMySQLDAO() {
        super(Usuario.class);
        
        setTabela("Usuarios");
    }
    
    @Override
    protected String getComandoConsultar() {
        return "SELECT UsuarioId, Nome, Usuario, AES_DECRYPT(Senha, '" + chaveCriptogafia + "') AS Senha, SalaId, Dinheiro FROM " + getTabela() + " WHERE UsuarioId = ?;";
    }
    
    @Override
    public void inserir(Entidade entidade) throws SQLException {
        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha())) {
            String query = "INSERT INTO " + getTabela() + " (Nome, Usuario, Senha) VALUES (?, ?, AES_ENCRYPT(?, '" + chaveCriptogafia + "'));";

            try (PreparedStatement stmt = conexao.prepareStatement(query)) {
                stmt.setString(1, ((Usuario)entidade).getNome());
                stmt.setString(2, ((Usuario)entidade).getUsuario());
                stmt.setString(3, ((Usuario)entidade).getSenha());

                stmt.executeUpdate();
            }
        }
    }
    
    @Override
    protected E preencherEntidade(ResultSet rs) throws SQLException {
        Usuario entidade = new Usuario();

        entidade.setUsuarioId(rs.getInt("UsuarioId"));
        entidade.setNome(rs.getString("Nome"));
        entidade.setUsuario(rs.getString("Usuario"));
        entidade.setSenha(rs.getString("Senha"));
        entidade.setTipoUsuario(rs.getString("TipoUsuario"));
        entidade.setSalaId(rs.getInt("SalaId"));
        entidade.setDinheiro(rs.getDouble("Dinheiro"));

        return (E)entidade;
    }
    
    public Usuario buscarPorCredenciais(String usuario, String senha) throws SQLException {
        Usuario entidade = null;

        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha())) {
            String query = "SELECT UsuarioId, Nome, Usuario, AES_DECRYPT(Senha, '" + chaveCriptogafia + "') AS Senha, TipoUsuario, SalaId, Dinheiro FROM " + getTabela() + " WHERE Usuario = ? AND AES_DECRYPT(Senha, '" + chaveCriptogafia + "') = ?";

            try (PreparedStatement stmt = conexao.prepareStatement(query)) {
                stmt.setString(1, usuario);
                stmt.setString(2, senha);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.first()) {
                        entidade = (Usuario)preencherEntidade(rs);
                    }
                }
            }
        }

        return entidade;
    }
    
    @Override
    public Entidade consultar(String usuario) throws SQLException {
        Usuario entidade = null;

        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha())) {
            String query = "SELECT UsuarioId, Nome, Usuario, AES_DECRYPT(Senha, '" + chaveCriptogafia + "') AS Senha, TipoUsuario, SalaId, Dinheiro FROM " + getTabela() + " WHERE Usuario = ?;";

            try (PreparedStatement stmt = conexao.prepareStatement(query)) {
                stmt.setString(1, usuario);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.first()) {
                        entidade = (Usuario)preencherEntidade(rs);
                    }
                }
            }
        }

        return entidade;
    }
}