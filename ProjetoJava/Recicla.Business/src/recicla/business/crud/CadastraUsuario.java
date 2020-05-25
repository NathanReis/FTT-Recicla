package recicla.business.crud;

import java.sql.SQLException;
import recicla.business.basis.FabricaRepositorio;
import recicla.comuns.enums.EntidadesDisponiveis;
import recicla.comuns.vos.Usuario;
import recicla.dao.acesso.UsuarioMySQLDAO;
import recicla.dao.repositorio.basis.Repositorio;

public class CadastraUsuario {
    public boolean insereUsuario(Usuario user){
        boolean retorno = true;
        
        try {
            //Repositorio repositorio = FabricaRepositorio.Fabrica();
            //repositorio.inserir(user, EntidadesDisponiveis.USUARIO);
            boolean isValid = userIsValid(user);
            
            if(isValid == false){
                return false;
            }
            
            UsuarioMySQLDAO dao = new UsuarioMySQLDAO();
            dao.inserir(user);
            
        } catch (SQLException ex) {
            retorno = false;
        }
        
        return retorno;
    }
    
    public boolean userIsValid(Usuario user)
    {
        boolean isValid = true;
        
        if(user.getUsuario().trim().length() == 0)
            isValid = false;
        if(user.getNome().trim().length() == 0)
            isValid = false;
        if(user.getSenha().trim().length() == 0)
            isValid = false;
        
        return isValid;
    }
}
