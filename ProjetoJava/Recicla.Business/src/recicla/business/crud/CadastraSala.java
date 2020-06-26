/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.business.crud;

import java.sql.SQLException;
import java.util.Random;
import recicla.business.validations.SalaValidation;
import recicla.business.validations.VerificaSeChaveSalaJaExiste;
import recicla.comuns.vos.Sala;
import recicla.dao.sala.SalaMySQLDAO;

/**
 *
 * @author italo
 */
public class CadastraSala {

    SalaMySQLDAO dao = new SalaMySQLDAO();

    public boolean InsereSala(Sala sala) throws Exception {
        boolean salaValida = true;
        boolean salaExiste;
        try {

            SalaValidation valida_sala = new SalaValidation();
            VerificaSeChaveSalaJaExiste verifica = new VerificaSeChaveSalaJaExiste();
            
            do {
                sala.setChaveAcesso(codigo_de_acesso());
                salaExiste = verifica.execute(sala);
            } while (salaExiste);
            
            salaValida = valida_sala.validate(sala);
            if (salaValida == false) {
                return false;
            }

            dao.inserir(sala);

        } catch (SQLException ex) {
            salaValida = false;
        }
        return salaValida;
    }

    public String codigo_de_acesso() {
        String codigo = "";

        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            codigo += String.valueOf(rand.nextInt(6));
        }
        return codigo;
    }
    

}
