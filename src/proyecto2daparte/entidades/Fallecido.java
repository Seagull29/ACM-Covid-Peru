/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2daparte.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "fallecido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fallecido.findAll", query = "SELECT f FROM Fallecido f"),
    @NamedQuery(name = "Fallecido.findByNrofallecido", query = "SELECT f FROM Fallecido f WHERE f.fallecidoPK.nrofallecido = :nrofallecido"),
    @NamedQuery(name = "Fallecido.findByEtapa", query = "SELECT f FROM Fallecido f WHERE f.etapa = :etapa"),
    @NamedQuery(name = "Fallecido.findBySexo", query = "SELECT f FROM Fallecido f WHERE f.sexo = :sexo"),
    @NamedQuery(name = "Fallecido.findByRegion", query = "SELECT f FROM Fallecido f WHERE f.fallecidoPK.region = :region"),
    @NamedQuery(name = "Fallecido.findByCategoria", query = "SELECT f FROM Fallecido f WHERE f.categoria = :categoria")})
public class Fallecido implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FallecidoPK fallecidoPK;
    @Basic(optional = false)
    @Column(name = "Etapa")
    private String etapa;
    @Basic(optional = false)
    @Column(name = "Sexo")
    private String sexo;
    @Basic(optional = false)
    @Column(name = "Categoria")
    private String categoria;
    @JoinColumn(name = "Region", referencedColumnName = "Nombre", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Region region1;

    public Fallecido() {
    }

    public Fallecido(FallecidoPK fallecidoPK) {
        this.fallecidoPK = fallecidoPK;
    }

    public Fallecido(FallecidoPK fallecidoPK, String etapa, String sexo, String categoria) {
        this.fallecidoPK = fallecidoPK;
        this.etapa = etapa;
        this.sexo = sexo;
        this.categoria = categoria;
    }

    public Fallecido(int nrofallecido, String region) {
        this.fallecidoPK = new FallecidoPK(nrofallecido, region);
    }

    public FallecidoPK getFallecidoPK() {
        return fallecidoPK;
    }

    public void setFallecidoPK(FallecidoPK fallecidoPK) {
        this.fallecidoPK = fallecidoPK;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Region getRegion1() {
        return region1;
    }

    public void setRegion1(Region region1) {
        this.region1 = region1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fallecidoPK != null ? fallecidoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fallecido)) {
            return false;
        }
        Fallecido other = (Fallecido) object;
        if ((this.fallecidoPK == null && other.fallecidoPK != null) || (this.fallecidoPK != null && !this.fallecidoPK.equals(other.fallecidoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyecto2daparte.entidades.Fallecido[ fallecidoPK=" + fallecidoPK + " ]";
    }
    
}
