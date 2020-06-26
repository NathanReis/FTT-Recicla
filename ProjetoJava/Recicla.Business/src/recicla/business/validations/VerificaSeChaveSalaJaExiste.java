/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.business.validations;

import com.google.gson.Gson;
import recicla.business.httpRequests.httpRequest;
import recicla.comuns.vos.Sala;

/**
 *
 * @author vitorlupinetti
 */
public class VerificaSeChaveSalaJaExiste {

    private String finalUrl = "sala/obtem-sala-por-chave/";

    public boolean execute(Sala sala) throws Exception {
        boolean salaExiste = true;
        Gson g = new Gson();
        String salaJson = httpRequest.sendGet(finalUrl + sala.getChaveAcesso());

        //se retornar algo diferente de null signfica que ja existe uma sala com essa chave
        if (salaJson.equals("null")) {
            salaExiste = false;
        }
        
        return salaExiste;

    }
}