/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.business.crud;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import javafx.scene.control.Alert;
import recicla.business.httpRequests.httpRequest;
import recicla.comuns.vos.ItemLoja;
import recicla.comuns.vos.ItemLojaXUsuario;
import recicla.comuns.vos.Usuario;

/**
 *
 * @author vitorlupinetti
 */
public class Loja {

    
    public static boolean comprarItem(int itemId) throws Exception{
        Usuario aluno = new Usuario(); // substituir por aluno logado
        ItemLojaXUsuario userItem = new ItemLojaXUsuario();
        String itemJson = httpRequest.sendGet("itens/obtem-item/" + itemId);
        Gson g = new Gson();

        Type itemType = new TypeToken<ItemLoja>() {}.getType();

        ItemLoja item = g.fromJson(itemJson, itemType);
        double saldoAluno = aluno.getDinheiro();
        double precoItem = item.getPreco();
        
        if(saldoAluno >= precoItem){
            aluno.setDinheiro(saldoAluno - precoItem);
            userItem.setItemLojaId(itemId);
            userItem.setUsuarioId(aluno.getUsuarioId());
            userItem.setQuantidade(1);
            
            return insertUserItem(userItem);
        }
        else{
            return false;
        }
    }
    
    private static boolean insertUserItem(ItemLojaXUsuario item) throws Exception{
        
        CrudItens crud = new CrudItens();
        boolean inserted = crud.insereItemUsuario(item);
        return inserted;
       
    }
    
}
