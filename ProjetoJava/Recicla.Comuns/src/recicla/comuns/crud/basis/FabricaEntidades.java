package recicla.comuns.crud.basis;

import recicla.comuns.enums.EntidadesDisponiveis;
import recicla.comuns.vos.*;

public class FabricaEntidades {
    public static Entidade Fabrica(EntidadesDisponiveis enumEntidade) {
        Entidade retorno;

        switch (enumEntidade)
        {
            case ITEM_LOJA:
                retorno = new ItemLoja();
                break;
                
            case JOGO:
                retorno = new Jogo();
                break;
                
            case PERGUNTA_QUIZ:
                retorno = new PerguntaQuiz();
                break;
                
            case RECORDE:
                retorno = new Recorde();
                break;
                
            case RODADA:
                retorno = new Rodada();
                break;
                
            case SALA:
                retorno = new Sala();
                break;

            case USUARIO:
                retorno = new Usuario();
                break;

            default:
                retorno = new Entidade();
                break;
        }

        return retorno;
    }
}