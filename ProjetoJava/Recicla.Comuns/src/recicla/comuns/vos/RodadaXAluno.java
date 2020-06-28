package recicla.comuns.vos;

import annotation.CampoNoBanco;
import recicla.comuns.crud.basis.Entidade;

public class RodadaXAluno extends Entidade implements Comparable<RodadaXAluno> {

    @CampoNoBanco(nome = "UsuarioId", chave = true)
    private int UsuarioId;
    @CampoNoBanco(nome = "RodadaId", chave = false)
    private int RodadaId;
    @CampoNoBanco(nome = "Pontos", chave = false)
    private int pontos;

    /**
     * @return the UsuarioId
     */
    public int getUsuarioId() {
        return UsuarioId;
    }

    /**
     * @param UsuarioId the UsuarioId to set
     */
    public void setUsuarioId(int UsuarioId) {
        this.UsuarioId = UsuarioId;
    }

    /**
     * @return the RodadaId
     */
    public int getRodadaId() {
        return RodadaId;
    }

    /**
     * @param RodadaId the RodadaId to set
     */
    public void setRodadaId(int RodadaId) {
        this.RodadaId = RodadaId;
    }

    /**
     * @return the pontos
     */
    public int getPontos() {
        return pontos;
    }

    /**
     * @param pontos the pontos to set
     */
    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    @Override
    public int compareTo(RodadaXAluno aluno) {
        if (this.pontos > aluno.getPontos()) {
            return -1;
        }
        if (this.pontos < aluno.getPontos()) {
            return 1;
        }
        return 0;
    }
}

