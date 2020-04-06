/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.business.basis;

import recicla.business.config.Config;
import recicla.comuns.enums.TipoRepositorio;
import recicla.dao.repositorio.basis.Repositorio;
import recicla.dao.repositorio.mysql.RepositorioMySQL;

/**
 *
 * @author vitorlupinetti
 */
public class FabricaRepositorio {
    
        public static Repositorio Fabrica() {
        if (Config.getInstance().getTipoRepositorio() == TipoRepositorio.MYSQL)
            return new RepositorioMySQL();
        else 
            return null;
    }
}
