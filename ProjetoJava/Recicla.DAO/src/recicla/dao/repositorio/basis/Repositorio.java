/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.dao.repositorio.basis;
import recicla.comuns.crud.basis.Entidade;
import recicla.comuns.enums.EntidadesDisponiveis;

/**
 *
 * @author vitorlupinetti
 */
public abstract class Repositorio {
    public abstract Entidade seleciona(int id, EntidadesDisponiveis tipoEntidade);
    public abstract Entidade atualiza(int id, EntidadesDisponiveis tipoEntidade);
    public abstract void insere(Entidade E, EntidadesDisponiveis tipoEntidade);
    public abstract void deleta(int id, EntidadesDisponiveis tipoEntidade);
    public abstract Entidade localiza(String codigo, EntidadesDisponiveis tipoEntidade);
}
