/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.business.crud;

import java.sql.SQLException;
import recicla.business.basis.FabricaRepositorio;
import recicla.comuns.enums.EntidadesDisponiveis;
import recicla.comuns.vos.Usuario;
import recicla.dao.acesso.UsuarioMySQLDAO;
import recicla.dao.repositorio.basis.Repositorio;

/**
 *
 * @author italo
 */
public class CadastraUsuario {
    
    public boolean InsereUsuario(Usuario user){
        boolean retorno = true;
        
        try {
            //Repositorio repositorio = FabricaRepositorio.Fabrica();
            //repositorio.inserir(user, EntidadesDisponiveis.USUARIO);
            
            UsuarioMySQLDAO dao = new UsuarioMySQLDAO();
            dao.inserir(user);
            
        } catch (SQLException ex) {
            retorno = false;
        }
        
        return retorno;
    }
}
