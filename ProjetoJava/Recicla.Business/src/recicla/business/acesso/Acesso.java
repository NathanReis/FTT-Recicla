/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.business.acesso;

import recicla.business.basis.FabricaRepositorio;
import recicla.comuns.enums.EntidadesDisponiveis;
import recicla.comuns.vos.Usuario;
import recicla.dao.repositorio.basis.Repositorio;

/**
 *
 * @author vitorlupinetti
 */
public class Acesso {
     private boolean validaSenha(String senhaRepositorio, String senhaDigitada){
        return (senhaRepositorio.equals(senhaDigitada)); 
    }
    
    public boolean validaUsuario(Usuario user) {
        boolean retorno = false;
        Repositorio repositorio = FabricaRepositorio.Fabrica();        
        Usuario usuario = (Usuario)repositorio.localiza(user.getUsuario(), EntidadesDisponiveis.USUARIO);
        if (usuario != null)
        {
            retorno = validaSenha(usuario.getSenha(), user.getSenha());
        }
        return retorno;
    }
}
