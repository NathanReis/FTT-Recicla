package recicla.business.config;

import recicla.comuns.enums.TipoRepositorio;

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

    public TipoRepositorio getTipoRepositorio() {
        return tipoRepositorio;
    }
    
    public void setTipoRepositorio(TipoRepositorio tipoRepositorio) {
        this.tipoRepositorio = tipoRepositorio;
    }
}