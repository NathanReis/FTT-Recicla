package recicla.presentation;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import recicla.business.config.Config;
import recicla.business.httpRequests.httpRequest;
import recicla.comuns.vos.PerguntaQuiz;
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

        try {
            Gson g = new Gson();
            //Busca todos os jogadores da rodada elege o vencedor
            int rodada_atual = Config.getInstance().getRodadaAtualEditando();

            //passa rodada_atual para o método da API e obtem lista.
            String chamadaWs = "rodada/listar-rodadaAluno-por-rodadaId/";
            String response = httpRequest.sendGet(chamadaWs + rodada_atual);
            Type type = new TypeToken<ArrayList<RodadaXAluno>>() {
            }.getType();
            Alunos_Rodada = g.fromJson(response, type);

            RodadaXAluno vencedor = getVencedorRodada();
            RodadaXAluno aluno_atual = getAlunoAtual(Config.getInstance().getLoggedUser().getUsuarioId());
            preencheLabels(vencedor, aluno_atual);
            /*if (vencedor.getUsuarioId() == aluno_atual.getUsuarioId()) {
                txtfraseAluno.setVisible(false);
                txtpontuacaoAluno.setVisible(false);
                txtposicaoAluno.setVisible(false);
            }*/
        } catch (Exception ex) {
            Logger.getLogger(TelaRankingController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private RodadaXAluno getVencedorRodada() {
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

    private RodadaXAluno getAlunoAtual(int IdAluno) {

        RodadaXAluno aluno = Alunos_Rodada.stream().filter(x -> x.getUsuarioId() == IdAluno).findFirst().get();
        return aluno;
    }

    private Usuario getAluno(int IdAluno) {
        return null;
    }
    
    private void preencheLabels(RodadaXAluno vencedor, RodadaXAluno alunoLogado) throws Exception{
        Usuario campeao = obtemAlunoPorId(vencedor.getUsuarioId());
        txtnomeVencedor.setText(campeao.getNome());
        txtpontuacaoVencedor.setText(Integer.toString(vencedor.getPontos()));
        txtpontuacaoAluno.setText(Integer.toString(alunoLogado.getPontos()));
    }
    
    private Usuario obtemAlunoPorId(int alunoId) throws Exception{
        Gson g = new Gson();
        String chamadaWs = "user/obtem-usuario-por-id/";
        String response = httpRequest.sendGet(chamadaWs + alunoId);
        Usuario aluno = g.fromJson(response, Usuario.class);
        
        return aluno;
    }
}
