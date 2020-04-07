package recicla.business.basis;

import recicla.business.config.Config;
import recicla.comuns.enums.TipoRepositorio;
import recicla.dao.repositorio.basis.Repositorio;
import recicla.dao.repositorio.mysql.RepositorioMySQL;

public class FabricaRepositorio {
    public static Repositorio Fabrica() {
        if (Config.getInstance().getTipoRepositorio() == TipoRepositorio.MYSQL)
            return new RepositorioMySQL();
        else 
            return null;
    }
}