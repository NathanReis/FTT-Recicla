package recicla.dao.basis;

import recicla.comuns.enums.EntidadesDisponiveis;
import recicla.comuns.enums.TipoRepositorio;
import recicla.dao.acesso.UsuarioMySQLDAO;

public class FabricaDAOS {
    public static DAO Fabrica(EntidadesDisponiveis enumEntidade, TipoRepositorio repositorio) {
        switch (repositorio)
        {
            case MYSQL:
                return montaDAOMySQL(enumEntidade);
            default:
                return null;
        }
    }
    
    private static DAO montaDAOMySQL(EntidadesDisponiveis enumEntidade) {
        DAO retorno;

        switch (enumEntidade)
        {
            case ITEM_LOJA:
                retorno = new UsuarioMySQLDAO();
                break;

            case JOGO:
                retorno = new UsuarioMySQLDAO();
                break;

            case PERGUNTA_QUIZ:
                retorno = new UsuarioMySQLDAO();
                break;

            case RECORDE:
                retorno = new UsuarioMySQLDAO();
                break;

            case RODADA:
                retorno = new UsuarioMySQLDAO();
                break;

            case SALA:
                retorno = new UsuarioMySQLDAO();
                break;

            case USUARIO:
                retorno = new UsuarioMySQLDAO();
                break;

            default:
                retorno = null;
                break;
        }

        return retorno;    
    }
}