package recicla.dao.basis;

import annotation.CampoNoBanco;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import recicla.comuns.crud.basis.Entidade;

public class MySQLDAO <E extends Entidade> extends DAO {
    public MySQLDAO(Class entityclass) {
        super(entityclass);
    }
    
    final String STRING_CONEXAO = "jdbc:mysql://localhost/ReciclaDB?useTimezone=true&serverTimezone=UTC";  
    final String USUARIO = "root";  
    final String SENHA = "";
    private String tabela;
    
    protected String getStringConexao() {
        return STRING_CONEXAO;
    }
    
    protected String getUsuario() {
        return USUARIO;
    }
    
    protected String getSenha() {
        return SENHA;
    }
    
    protected void setTabela(String tabela) {
        this.tabela = tabela;
    }
    
    protected String getTabela() {
        return tabela;
    }
    
    @Override
    public ArrayList<Entidade> listar() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public String getComandoListar() {
        return "SELECT * FROM " + tabela;
    }

    @Override
    public Entidade consultar(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
//        E entidade = null;
//
//        try (Connection conexao = DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA)) {
//            String sql = getComandoConsultar();
//
//            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
//                stmt.setInt(1, id);
//
//                try (ResultSet rs = stmt.executeQuery()) {
//                    if (rs.first()) {
//                        entidade = preencherEntidade(rs);
//                    }
//                }
//            }
//        }
//
//        return entidade;
    }
    
    @Override
    public Entidade consultar(String valorCampo) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public Entidade consultar(String campo, int valor) throws SQLException {
        E entidade = null;

        try (Connection conexao = DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA)) {
            String sql = "SELECT * FROM " + tabela + " WHERE " + campo + " = ?;";

            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, valor);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.first()) {
                        entidade = preencherEntidade(rs);
                    }
                }
            }
        }

        return entidade;
    }
    
    @Override
    public Entidade consultar(String campo, String valor) throws SQLException {
        E entidade = null;

        try (Connection conexao = DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA)) {
            String sql = "SELECT * FROM " + tabela + " WHERE " + campo + " = ?;";

            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, valor);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.first()) {
                        entidade = preencherEntidade(rs);
                    }
                }
            }
        }

        return entidade;
    }
    
    protected String getComandoConsultar() {
//        String campos = "";
        String chave = "";

        for (Field campo : entityClass.getDeclaredFields()) {            
            if (campo.isAnnotationPresent(CampoNoBanco.class)) {
                CampoNoBanco anotacao = campo.getAnnotation(CampoNoBanco.class);

                if (anotacao.chave()) {
                    chave = anotacao.nome();
                    break;
                }

//                campos += anotacao.nome() + ", ";
            }
        }

//        if (campos.length() > 0) {
//            campos = campos.substring(0, campos.length()-2);
//        }

        return "SELECT * FROM " + tabela + " WHERE " + chave + " = ?;";
    }
    
    @Override
    public void inserir(Entidade entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    protected String getComandoInserir() {
        String campos = "";
        String valores = "";

        for (Field campo : entityClass.getDeclaredFields()) {            
            if (campo.isAnnotationPresent(CampoNoBanco.class)) {
                CampoNoBanco anotacao = campo.getAnnotation(CampoNoBanco.class);

                if (!anotacao.chave()) {
                    campos += anotacao.nome() + ", ";
                    valores += "?, ";
                }
            }
        }

        if (campos.length() > 0) {
            campos = campos.substring(0, campos.length()-2);
            valores = valores.substring(0, valores.length()-2);
        }

        return "INSERT INTO " + tabela + " (" + campos + ") VALUES (" + valores + ");";
    }

    @Override
    public void atualizar(Entidade entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    protected String getComandoAtualizar() {
        String campos = "";
        String chave = "";

        for (Field campo : entityClass.getDeclaredFields()) {            
            if (campo.isAnnotationPresent(CampoNoBanco.class)) {
                CampoNoBanco anotacao = campo.getAnnotation(CampoNoBanco.class);

                if (anotacao.chave()) {
                    chave = anotacao.nome();
                } else {
                    campos += anotacao.nome() + " = ?, ";
                }
            }
        }

        if (campos.length() > 0) {
            campos = campos.substring(0, campos.length()-2);
        }

        return "UPDATE " + tabela + " SET " + campos + " WHERE " + chave + " = ?";
    }

    @Override
    public void deletar(int id) throws SQLException {
        try (Connection conexao = DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA )) {
            String sql = getComandoDeletar();

            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, id);

                stmt.executeUpdate();
            }
        }
    }
    
    protected String getComandoDeletar() {
        String chave = "";

        for (Field campo : entityClass.getDeclaredFields()) {
            if (campo.isAnnotationPresent(CampoNoBanco.class)) {
                CampoNoBanco anotacao = campo.getAnnotation(CampoNoBanco.class);

                if (anotacao.chave()) {
                    chave = anotacao.nome();
                    break;
                }
            }
        }

        return "DELETE FROM " + tabela + " WHERE " + chave + " = ?;";
    }
    
    protected E preencherEntidade(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
