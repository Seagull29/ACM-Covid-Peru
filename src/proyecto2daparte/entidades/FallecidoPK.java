/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2daparte.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author USER
 */
@Embeddable
public class FallecidoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "Nrofallecido")
    private int nrofallecido;
    @Basic(optional = false)
    @Column(name = "Region")
    private String region;

    public FallecidoPK() {
    }

    public FallecidoPK(int nrofallecido, String region) {
        this.nrofallecido = nrofallecido;
        this.region = region;
    }

    public int getNrofallecido() {
        return nrofallecido;
    }

    public void setNrofallecido(int nrofallecido) {
        this.nrofallecido = nrofallecido;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) nrofallecido;
        hash += (region != null ? region.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FallecidoPK)) {
            return false;
        }
        FallecidoPK other = (FallecidoPK) object;
        if (this.nrofallecido != other.nrofallecido) {
            return false;
        }
        if ((this.region == null && other.region != null) || (this.region != null && !this.region.equals(other.region))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyecto2daparte.entidades.FallecidoPK[ nrofallecido=" + nrofallecido + ", region=" + region + " ]";
    }
    
}
