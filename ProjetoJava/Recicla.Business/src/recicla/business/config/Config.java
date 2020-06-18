package recicla.business.config;

import recicla.comuns.enums.TipoRepositorio;
import recicla.comuns.vos.Usuario;

public class Config {
    private static Config uniqueInstance;
 
    private Config() {
    }
 
    public static synchronized Config getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new Config();
 
        return uniqueInstance;
    }
    
    private TipoRepositorio tipoRepositorio; 
    private Usuario loggedUser;

    public TipoRepositorio getTipoRepositorio() {
        return tipoRepositorio;
    }
    
    public void setTipoRepositorio(TipoRepositorio tipoRepositorio) {
        this.tipoRepositorio = tipoRepositorio;
    }
    
    public void setLoggedUser (Usuario usuario){
        this.loggedUser = usuario;
    }
    
    public Usuario getLoggedUser (){
        return this.loggedUser;
    }
}