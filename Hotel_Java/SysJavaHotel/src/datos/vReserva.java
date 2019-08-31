//Contiene los atributos de la tabla Reserva
package datos;

import java.sql.Date;

public class vReserva {

    private int idReserva;
    private int codigoHab;
    private int codigoCli;
    private int codigoEmp;
    private String tipoRsva;
    private Date fechaRsva;
    private Date fecIniRsva;
    private Date fecFinRsva;
    private Double costoRsva;
    private String estRsva;

    public vReserva() {
    }

    public vReserva(int idReserva, int codigoHab, int codigoCli, int codigoEmp, String tipoRsva, Date fechaRsva, Date fecIniRsva, Date fecFinRsva, Double costoRsva, String estRsva) {
        this.idReserva = idReserva;
        this.codigoHab = codigoHab;
        this.codigoCli = codigoCli;
        this.codigoEmp = codigoEmp;
        this.tipoRsva = tipoRsva;
        this.fechaRsva = fechaRsva;
        this.fecIniRsva = fecIniRsva;
        this.fecFinRsva = fecFinRsva;
        this.costoRsva = costoRsva;
        this.estRsva = estRsva;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getCodigoHab() {
        return codigoHab;
    }

    public void setCodigoHab(int codigoHab) {
        this.codigoHab = codigoHab;
    }

    public int getCodigoCli() {
        return codigoCli;
    }

    public void setCodigoCli(int codigoCli) {
        this.codigoCli = codigoCli;
    }

    public int getCodigoEmp() {
        return codigoEmp;
    }

    public void setCodigoEmp(int codigoEmp) {
        this.codigoEmp = codigoEmp;
    }

    public String getTipoRsva() {
        return tipoRsva;
    }

    public void setTipoRsva(String tipoRsva) {
        this.tipoRsva = tipoRsva;
    }

    public Date getFechaRsva() {
        return fechaRsva;
    }

    public void setFechaRsva(Date fechaRsva) {
        this.fechaRsva = fechaRsva;
    }

    public Date getFecIniRsva() {
        return fecIniRsva;
    }

    public void setFecIniRsva(Date fecIniRsva) {
        this.fecIniRsva = fecIniRsva;
    }

    public Date getFecFinRsva() {
        return fecFinRsva;
    }

    public void setFecFinRsva(Date fecFinRsva) {
        this.fecFinRsva = fecFinRsva;
    }

    public Double getCostoRsva() {
        return costoRsva;
    }

    public void setCostoRsva(Double costoRsva) {
        this.costoRsva = costoRsva;
    }

    public String getEstRsva() {
        return estRsva;
    }

    public void setEstRsva(String estRsva) {
        this.estRsva = estRsva;
    }

}
