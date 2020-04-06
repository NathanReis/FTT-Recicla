/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.dao.repositorio.mysql;

import recicla.comuns.crud.basis.Entidade;
import recicla.comuns.enums.EntidadesDisponiveis;
import recicla.dao.repositorio.basis.Repositorio;

/**
 *
 * @author vitorlupinetti
 */
public class RepositorioMySQL extends Repositorio {

    @Override
    public Entidade seleciona(int id, EntidadesDisponiveis tipoEntidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Entidade atualiza(int id, EntidadesDisponiveis tipoEntidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insere(Entidade E, EntidadesDisponiveis tipoEntidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleta(int id, EntidadesDisponiveis tipoEntidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Entidade localiza(String codigo, EntidadesDisponiveis tipoEntidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
