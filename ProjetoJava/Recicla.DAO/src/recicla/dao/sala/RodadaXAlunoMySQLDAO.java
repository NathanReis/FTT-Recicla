/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.dao.sala;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import recicla.comuns.crud.basis.Entidade;
import recicla.comuns.vos.JogoRodadaXPerguntaQuiz;
import recicla.comuns.vos.RodadaXAluno;
import recicla.dao.basis.MySQLDAO;

/**
 *
 * @author italo
 */
public class RodadaXAlunoMySQLDAO <E extends Entidade> extends MySQLDAO {
    
    public RodadaXAlunoMySQLDAO() {
        super(RodadaXAluno.class);
        setTabela("RodadasXUsuarios");
        
    }
    
    @Override
    public void inserir(Entidade entidade) throws SQLException {
        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha())) {
            String query = "INSERT INTO RodadasXUsuarios (UsuarioId,RodadaId,Pontos) VALUES (?,?,?);";

            try (PreparedStatement stmt = conexao.prepareStatement(query)) {
                stmt.setInt(1, ((RodadaXAluno)entidade).getUsuarioId());
                stmt.setInt(2, ((RodadaXAluno)entidade).getRodadaId());
                stmt.setInt(3, ((RodadaXAluno)entidade).getPontos());

                stmt.executeUpdate();
            }
        }
    }
    
    @Override
    protected String getComandoAtualizar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    protected E preencherEntidade(ResultSet rs) throws SQLException {
        RodadaXAluno entidade = new RodadaXAluno();

        entidade.setUsuarioId(rs.getInt("UsuarioId"));
        entidade.setRodadaId(rs.getInt("RodadaId"));
        entidade.setPontos(rs.getInt("Pontos"));
        return (E)entidade;
    }
    
    public ArrayList<RodadaXAluno> listarPorRodadaId(int RodadaId) throws SQLException {
        ArrayList<RodadaXAluno> lista = new ArrayList<RodadaXAluno>();
        
        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha())) {
            String query = "SELECT * FROM " + getTabela() + " WHERE RodadaId = ?;";
            
            try (PreparedStatement stmt = conexao.prepareStatement(query)) {
                stmt.setInt(1, RodadaId);

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        lista.add((RodadaXAluno)preencherEntidade(rs));
                    }
                }
            }
        }
        
        return lista;
    }
    
      public ArrayList<RodadaXAluno> listarPorUsuarioId(int UsuarioId) throws SQLException {
        ArrayList<RodadaXAluno> lista = new ArrayList<RodadaXAluno>();
        
        try (Connection conexao = DriverManager.getConnection(getStringConexao(), getUsuario(), getSenha())) {
            String query = "SELECT * FROM " + getTabela() + " WHERE UsuarioId = ?;";
            
            try (PreparedStatement stmt = conexao.prepareStatement(query)) {
                stmt.setInt(1, UsuarioId);

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        lista.add((RodadaXAluno)preencherEntidade(rs));
                    }
                }
            }
        }
        
        return lista;
    }
    
    
    
    
}
