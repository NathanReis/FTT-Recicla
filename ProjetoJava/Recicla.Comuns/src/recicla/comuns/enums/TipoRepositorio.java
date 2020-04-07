package recicla.comuns.enums;

public enum TipoRepositorio {
    MYSQL("MySQL");
 
    private String descricao;
 
    TipoRepositorio(String descricao) {
        this.descricao = descricao;
    }
 
    public String getDescricao() {
        return descricao;
    }
}