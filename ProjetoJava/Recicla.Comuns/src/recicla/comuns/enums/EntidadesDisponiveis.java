package recicla.comuns.enums;

public enum EntidadesDisponiveis {
    ITEM_LOJA("item_loja"),
    
    JOGO("jogo"),
    
    PERGUNTA_QUIZ("pergunta_quiz"),
    
    RECORDE("recorde"),
    
    RODADA("rodada"),
    
    SALA("sala"),
    
    USUARIO("usuario");     
 
    private String descricao;
 
    EntidadesDisponiveis(String descricao) {
        this.descricao = descricao;
    }
 
    public String getDescricao() {
        return descricao;
    }
}