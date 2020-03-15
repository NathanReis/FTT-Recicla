/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.dao.basis;

import java.sql.SQLException;
import java.util.ArrayList;
import recicla.comuns.crud.basis.Entidade;
import recicla.comuns.enums.EntidadesDisponiveis;

/**
 *
 * @author vitorlupinetti
 */
public abstract class DAO <E extends Entidade> {
    
    protected Class<E> entityClass;

    public DAO(Class<E> entityClass){
        this.entityClass = entityClass;
    }
    
        
    public abstract E seleciona(int id, EntidadesDisponiveis tipoEntidade)throws SQLException;
    public abstract E atualiza(int id, EntidadesDisponiveis tipoEntidade);
    public abstract void insere(Entidade entidade, EntidadesDisponiveis tipoEntidade);
    public abstract void deleta(int id, EntidadesDisponiveis tipoEntidade);
    
    protected E getInstanceOfE()
    {
        try
        {
            return entityClass.newInstance();
        }
        catch (IllegalAccessException | InstantiationException e)
        {
            // Oops, no default constructor
            throw new RuntimeException(e);
        }
    }
}
