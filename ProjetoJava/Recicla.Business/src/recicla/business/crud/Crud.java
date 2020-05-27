/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.business.crud;

import java.sql.SQLException;
import recicla.business.basis.FabricaRepositorio;
import recicla.comuns.crud.basis.Entidade;
import recicla.comuns.enums.EntidadesDisponiveis;
import recicla.comuns.vos.Usuario;
import recicla.dao.acesso.UsuarioMySQLDAO;
import recicla.dao.repositorio.basis.Repositorio;

/**
 *
 * @author vitorlupinetti
 */
public class Crud {
     public boolean inserir(Entidade E, EntidadesDisponiveis tipo){
        boolean retorno = true;
        
        try {
            Repositorio repositorio = FabricaRepositorio.Fabrica();
            repositorio.inserir(E, tipo);
            //boolean isValid = userIsValid(user);          
        } catch (SQLException ex) {
            retorno = false;
        }
        
        return retorno;
    }
    
}
