/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.dao.basis;
import java.sql.SQLException;
import recicla.comuns.crud.basis.Entidade;
import recicla.comuns.enums.EntidadesDisponiveis;
/**
 *
 * @author vitorlupinetti
 */
public class MySQLDAO <E extends Entidade> extends DAO{
    
    public MySQLDAO(Class entityclass){
        super(entityclass);
    }
    
    final String STRING_CONEXAO = "jdbc:mysql://localhost/recicladb";  
    final String USUARIO = "root";  
    final String SENHA = "";
    private String tabela;
    
    
    protected void setTabela(String value){
        tabela = value;
    }

    @Override
    public Entidade seleciona(int id, EntidadesDisponiveis tipoEntidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Entidade atualiza(int id, EntidadesDisponiveis tipoEntidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insere(Entidade entidade, EntidadesDisponiveis tipoEntidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleta(int id, EntidadesDisponiveis tipoEntidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
