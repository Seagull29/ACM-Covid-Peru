/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2daparte.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Region.findAll", query = "SELECT r FROM Region r"),
    @NamedQuery(name = "Region.findByNombre", query = "SELECT r FROM Region r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Region.findByPruebasMoleculares", query = "SELECT r FROM Region r WHERE r.pruebasMoleculares = :pruebasMoleculares"),
    @NamedQuery(name = "Region.findByPruebasRapidas", query = "SELECT r FROM Region r WHERE r.pruebasRapidas = :pruebasRapidas"),
    @NamedQuery(name = "Region.findByTotalCasos", query = "SELECT r FROM Region r WHERE r.totalCasos = :totalCasos"),
    @NamedQuery(name = "Region.findByLetalidad", query = "SELECT r FROM Region r WHERE r.letalidad = :letalidad"),
    @NamedQuery(name = "Region.findByFecha", query = "SELECT r FROM Region r WHERE r.fecha = :fecha")})
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "PruebasMoleculares")
    private Integer pruebasMoleculares;
    @Column(name = "PruebasRapidas")
    private Integer pruebasRapidas;
    @Column(name = "TotalCasos")
    private Integer totalCasos;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Letalidad")
    private Double letalidad;
    @Basic(optional = false)
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "region1")
    private List<Fallecido> fallecidoList;

    public Region() {
    }

    public Region(String nombre) {
        this.nombre = nombre;
    }

    public Region(String nombre, Date fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPruebasMoleculares() {
        return pruebasMoleculares;
    }

    public void setPruebasMoleculares(Integer pruebasMoleculares) {
        this.pruebasMoleculares = pruebasMoleculares;
    }

    public Integer getPruebasRapidas() {
        return pruebasRapidas;
    }

    public void setPruebasRapidas(Integer pruebasRapidas) {
        this.pruebasRapidas = pruebasRapidas;
    }

    public Integer getTotalCasos() {
        return totalCasos;
    }

    public void setTotalCasos(Integer totalCasos) {
        this.totalCasos = totalCasos;
    }

    public Double getLetalidad() {
        return letalidad;
    }

    public void setLetalidad(Double letalidad) {
        this.letalidad = letalidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @XmlTransient
    public List<Fallecido> getFallecidoList() {
        return fallecidoList;
    }

    public void setFallecidoList(List<Fallecido> fallecidoList) {
        this.fallecidoList = fallecidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombre != null ? nombre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Region)) {
            return false;
        }
        Region other = (Region) object;
        if ((this.nombre == null && other.nombre != null) || (this.nombre != null && !this.nombre.equals(other.nombre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyecto2daparte.entidades.Region[ nombre=" + nombre + " ]";
    }
    
}
