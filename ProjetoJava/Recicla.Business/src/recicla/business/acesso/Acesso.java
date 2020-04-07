package recicla.business.acesso;

import java.sql.SQLException;
import recicla.business.basis.FabricaRepositorio;
import recicla.comuns.enums.EntidadesDisponiveis;
import recicla.comuns.vos.Usuario;
import recicla.dao.repositorio.basis.Repositorio;

public class Acesso {
    private boolean validaSenha(String senhaRepositorio, String senhaDigitada){
        return (senhaRepositorio.equals(senhaDigitada)); 
    }

    public boolean validaUsuario(Usuario user) throws SQLException {
        boolean retorno = false;
        Repositorio repositorio = FabricaRepositorio.Fabrica();        
        Usuario usuario = (Usuario)repositorio.consultar(user.getUsuario(), EntidadesDisponiveis.USUARIO);

        if (usuario != null) {
            retorno = validaSenha(usuario.getSenha(), user.getSenha());
        }

        return retorno;
    }
}