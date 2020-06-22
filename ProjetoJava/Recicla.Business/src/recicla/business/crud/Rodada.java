package recicla.business.crud;

import recicla.comuns.vos.Jogo;
import recicla.dao.sala.RodadaMySQLDAO;

public class Rodada {
    private RodadaMySQLDAO rodadaDAO = null;
    
    public Rodada() {
        this.rodadaDAO = new RodadaMySQLDAO();
    }
    
    public boolean validarCadastrarJogo(int rodadaId, Jogo jogo) throws Exception {
        boolean isValid = false;
        
        isValid = true;
        
        return isValid;
    }
    
    public boolean validarDeletar(int rodadaId) throws Exception {
        boolean isValid = false;
        
        isValid = true;
        
        if(isValid) {
            // Qual DAO??
        }
        
        return isValid;
    }
    
    public boolean validarDeletarJogo(int rodadaId, int jogoId) throws Exception {
        boolean isValid = false;
        
        isValid = true;
        
        return isValid;
    }
}
