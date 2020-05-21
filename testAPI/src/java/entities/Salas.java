/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nathan
 */
@Entity
@Table(name = "salas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salas.findAll", query = "SELECT s FROM Salas s"),
    @NamedQuery(name = "Salas.findBySalaId", query = "SELECT s FROM Salas s WHERE s.salaId = :salaId"),
    @NamedQuery(name = "Salas.findByDescricao", query = "SELECT s FROM Salas s WHERE s.descricao = :descricao"),
    @NamedQuery(name = "Salas.findByChaveAcesso", query = "SELECT s FROM Salas s WHERE s.chaveAcesso = :chaveAcesso"),
    @NamedQuery(name = "Salas.findByHorarioInicio", query = "SELECT s FROM Salas s WHERE s.horarioInicio = :horarioInicio")})
public class Salas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SalaId")
    private Integer salaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "ChaveAcesso")
    private String chaveAcesso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HorarioInicio")
    @Temporal(TemporalType.TIME)
    private Date horarioInicio;

    public Salas() {
    }

    public Salas(Integer salaId) {
        this.salaId = salaId;
    }

    public Salas(Integer salaId, String descricao, String chaveAcesso, Date horarioInicio) {
        this.salaId = salaId;
        this.descricao = descricao;
        this.chaveAcesso = chaveAcesso;
        this.horarioInicio = horarioInicio;
    }

    public Integer getSalaId() {
        return salaId;
    }

    public void setSalaId(Integer salaId) {
        this.salaId = salaId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getChaveAcesso() {
        return chaveAcesso;
    }

    public void setChaveAcesso(String chaveAcesso) {
        this.chaveAcesso = chaveAcesso;
    }

    public Date getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(Date horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (salaId != null ? salaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salas)) {
            return false;
        }
        Salas other = (Salas) object;
        if ((this.salaId == null && other.salaId != null) || (this.salaId != null && !this.salaId.equals(other.salaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Salas[ salaId=" + salaId + " ]";
    }
    
}
