package recicla.presentation;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import recicla.business.config.Config;
import recicla.comuns.vos.RodadaXAluno;
import recicla.comuns.vos.Usuario;


/**
 * FXML Controller class
 */
public class TelaRankingController implements Initializable {
    
    
    private List<RodadaXAluno> Alunos_Rodada;
    @FXML
    private Label txtfraseAluno;
    @FXML
    private Label txtnomeVencedor;
    @FXML
    private Label txtpontuacaoVencedor;
    @FXML
    private Label txtpontuacaoAluno;
    @FXML
    private Label txtposicaoAluno;
    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Busca todos os jogadores da rodada elege o vencedor
        int rodada_atual = Config.getInstance().getRodadaAtualEditando();
        
        //passa rodada_atual para o método da API e obtem lista.
//        Alunos_Rodada = Retorno da API
           
        RodadaXAluno vencedor = getVencedorRodada();
        RodadaXAluno aluno_atual = getAlunoAtual(Config.getInstance().getLoggedUser().getUsuarioId());
        
        if(vencedor.getUsuarioId() == aluno_atual.getUsuarioId()){
            txtfraseAluno.setVisible(false);
            txtpontuacaoAluno.setVisible(false);
            txtposicaoAluno.setVisible(false);        
        }
         
    } 
    
    private RodadaXAluno getVencedorRodada(){
        int pontos = 0;
        RodadaXAluno retorno = new RodadaXAluno();

        for (RodadaXAluno aluno : Alunos_Rodada) {
            if (aluno.getPontos() > pontos) {
                pontos = aluno.getPontos();
                retorno = aluno;
            }
        }
        return retorno;
    
    }
    
    private RodadaXAluno getAlunoAtual(int IdAluno){
    
         RodadaXAluno aluno = Alunos_Rodada.stream().filter(x -> x.getUsuarioId() == IdAluno).findFirst().get();
         return aluno;
    }
    
    private Usuario getAluno(int IdAluno){
        return null;    
    }
}