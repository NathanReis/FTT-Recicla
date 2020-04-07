package recicla.dao.repositorio.mysql;

import java.sql.SQLException;
import recicla.comuns.crud.basis.Entidade;
import recicla.comuns.enums.EntidadesDisponiveis;
import recicla.comuns.enums.TipoRepositorio;
import recicla.dao.basis.DAO;
import recicla.dao.basis.FabricaDAOS;
import recicla.dao.repositorio.basis.Repositorio;

public class RepositorioMySQL extends Repositorio {
    @Override
    public Entidade consultar(int id, EntidadesDisponiveis tipoEntidade) throws SQLException {
        DAO dao = FabricaDAOS.Fabrica(tipoEntidade, TipoRepositorio.MYSQL);
        Entidade entidade = dao.consultar(id);

        return entidade;
    }
    
    @Override
    public Entidade consultar(String usuario, EntidadesDisponiveis tipoEntidade) throws SQLException {
        DAO dao = FabricaDAOS.Fabrica(tipoEntidade, TipoRepositorio.MYSQL);
        Entidade entidade = dao.consultar(usuario);

        return entidade;
    }

    @Override
    public void inserir(Entidade entidade, EntidadesDisponiveis tipoEntidade) throws SQLException {
        DAO dao = FabricaDAOS.Fabrica(tipoEntidade, TipoRepositorio.MYSQL);
        dao.inserir(entidade);
    }

    @Override
    public void atualizar(Entidade entidade, EntidadesDisponiveis tipoEntidade) throws SQLException {
        DAO dao = FabricaDAOS.Fabrica(tipoEntidade, TipoRepositorio.MYSQL);
        dao.atualizar(entidade);
    }

    @Override
    public void deletar(int id, EntidadesDisponiveis tipoEntidade) throws SQLException {
        DAO dao = FabricaDAOS.Fabrica(tipoEntidade, TipoRepositorio.MYSQL);
        dao.deletar(id);
    }
}