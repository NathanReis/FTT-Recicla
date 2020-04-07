package recicla.dao.repositorio.basis;

import java.sql.SQLException;
import recicla.comuns.crud.basis.Entidade;
import recicla.comuns.enums.EntidadesDisponiveis;

public abstract class Repositorio {
    public abstract Entidade consultar(int id, EntidadesDisponiveis tipoEntidade) throws SQLException;
    public abstract Entidade consultar(String valorCampo, EntidadesDisponiveis tipoEntidade) throws SQLException;
    public abstract void inserir(Entidade entidade, EntidadesDisponiveis tipoEntidade) throws SQLException;
    public abstract void atualizar(Entidade entidade, EntidadesDisponiveis tipoEntidade) throws SQLException;
    public abstract void deletar(int id, EntidadesDisponiveis tipoEntidade) throws SQLException;
}