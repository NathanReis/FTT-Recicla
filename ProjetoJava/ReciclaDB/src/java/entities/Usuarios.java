/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vitorlupinetti
 */
@Entity
@Table(name = "Usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u where u.usuario = :usuario")
    , @NamedQuery(name = "Usuarios.findByUsuarioId", query = "SELECT u FROM Usuarios u WHERE u.usuarioId = :usuarioId")
    , @NamedQuery(name = "Usuarios.findByNome", query = "SELECT u FROM Usuarios u WHERE u.nome = :nome")
    , @NamedQuery(name = "Usuarios.findByUsuario", query = "SELECT u FROM Usuarios u WHERE u.usuario = :usuario")
    , @NamedQuery(name = "Usuarios.findByTipoUsuario", query = "SELECT u FROM Usuarios u WHERE u.tipoUsuario = :tipoUsuario")
    , @NamedQuery(name = "Usuarios.findByDinheiro", query = "SELECT u FROM Usuarios u WHERE u.dinheiro = :dinheiro")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "UsuarioId")
    private Integer usuarioId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "Usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "Senha")
    private byte[] senha;
    @Column(name = "TipoUsuario")
    private Character tipoUsuario;
    @Column(name = "Dinheiro")
    private Long dinheiro;
    @JoinColumn(name = "SalaId", referencedColumnName = "SalaId")
    @ManyToOne
    private Salas salaId;

    public Usuarios() {
    }

    public Usuarios(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Usuarios(Integer usuarioId, String nome, String usuario, byte[] senha) {
        this.usuarioId = usuarioId;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public byte[] getSenha() {
        return senha;
    }

    public void setSenha(byte[] senha) {
        this.senha = senha;
    }

    public Character getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Character tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Long getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(Long dinheiro) {
        this.dinheiro = dinheiro;
    }

    public Salas getSalaId() {
        return salaId;
    }

    public void setSalaId(Salas salaId) {
        this.salaId = salaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioId != null ? usuarioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.usuarioId == null && other.usuarioId != null) || (this.usuarioId != null && !this.usuarioId.equals(other.usuarioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Usuarios[ usuarioId=" + usuarioId + " ]";
    }
    
}
