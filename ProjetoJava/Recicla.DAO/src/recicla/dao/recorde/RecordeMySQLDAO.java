package recicla.dao.recorde;

import java.sql.ResultSet;
import java.sql.SQLException;
import recicla.comuns.crud.basis.Entidade;
import recicla.comuns.vos.Recorde;
import recicla.dao.basis.MySQLDAO;

public class RecordeMySQLDAO <E extends Entidade> extends MySQLDAO {
    public RecordeMySQLDAO() {
        super(Recorde.class);
        
        setTabela("Recordes");
    }
    
    /**
     * Decidir como fazer:
     * listar()
     * inserir()
     * atualizar()
     */
    
    @Override
    public void deletar(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    protected String getComandoDeletar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    protected E preencherEntidade(ResultSet rs) throws SQLException {
        Recorde entidade = new Recorde();

        entidade.setRecordeId(rs.getInt("RecordeId"));
        entidade.setJogoId(rs.getInt("JogoId"));
        entidade.setUsuarioId(rs.getInt("UsuarioId"));
        entidade.setQtdPartidas(rs.getInt("QtdPartidas"));
        entidade.setQtdVitorias(rs.getInt("QtdVitorias"));
        entidade.setMelhorTempo(rs.getFloat("MelhorTempo"));
        entidade.setPontos(rs.getInt("Pontos"));

        return (E)entidade;
    }
}
