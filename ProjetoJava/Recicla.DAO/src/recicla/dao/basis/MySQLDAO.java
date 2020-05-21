package recicla.dao.basis;

import annotation.CampoNoBanco;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import recicla.comuns.crud.basis.Entidade;

public class MySQLDAO <E extends Entidade> extends DAO{
    public MySQLDAO(Class entityclass){
        super(entityclass);
    }
    
    final String STRING_CONEXAO = "jdbc:mysql://localhost/ReciclaDB?useTimezone=true&serverTimezone=UTC";  
    final String USUARIO = "root";  
    final String SENHA = "1234";
    private String tabela;
    
    protected String getStringConexao(){
        return STRING_CONEXAO;
    }
    
    protected String getUsuario(){
        return USUARIO;
    }
    
    protected String getSenha(){
        return SENHA;
    }
    
    protected void setTabela(String tabela){
        this.tabela = tabela;
    }
    
    protected String getTabela(){
        return tabela;
    }

    @Override
    public Entidade consultar(int id) throws SQLException {
        E entidade = null;

        try (Connection conexao = DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA)) {
            String query = getComandoConsultar();

            try (PreparedStatement stmt = conexao.prepareStatement(query)) {
                stmt.setInt(1, id);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.first()) {
                        entidade = preencherEntidade(rs);
                    }
                }
            }
        }

        return entidade;
    }
    
    public Entidade consultar(String valorCampo) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    protected String getComandoConsultar() {
        String campos = "";
        String chave = "";

        for (Field campo : entityClass.getDeclaredFields()) {            
            if (campo.isAnnotationPresent(CampoNoBanco.class)) {
                CampoNoBanco anotacao = campo.getAnnotation(CampoNoBanco.class);

                if (anotacao.chave()) {
                    chave = anotacao.nome();
                }

                campos += anotacao.nome() + ", ";
            }
        }

        if (campos.length() > 0) {
            campos = campos.substring(0, campos.length()-2);
        }

        return "SELECT " + campos + " FROM " + tabela + " WHERE " + chave + " = ?;";
    }
    
    @Override
    public void inserir(Entidade entidade)  throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            String query = getComandoDeletar();

            try (PreparedStatement stmt = conexao.prepareStatement(query)) {
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

        return "DELETE FROM " + tabela + " WHERE " + chave + " = ?";
    }
    
    protected E preencherEntidade(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}