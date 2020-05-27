package recicla.dao.basis;

import java.sql.SQLException;
import java.util.ArrayList;
import recicla.comuns.crud.basis.Entidade;

public abstract class DAO <E extends Entidade> {
    protected Class<E> entityClass;

    public DAO(Class<E> entityClass){
        this.entityClass = entityClass;
    }

    public abstract ArrayList<E> listar() throws SQLException;
    public abstract E consultar(int id) throws SQLException;
    public abstract E consultar(String valorCampo) throws SQLException;
    public abstract void inserir(Entidade entidade) throws SQLException;
    public abstract void atualizar(Entidade enteidade)  throws SQLException;
    public abstract void deletar(int id) throws SQLException;
    
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