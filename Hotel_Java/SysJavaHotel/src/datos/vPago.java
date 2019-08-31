package datos;

import java.sql.Date;

public class vPago {

    private int idPago;
    private int idRsvaPago;
    private String tipComprPago;
    private String numComprPago;
    private Double ivaPago;
    private Double totalPago;
    private Date fecGenCompr;
    private Date fecPago;

    public vPago() {
    }

    public vPago(int idPago, int idRsvaPago, String tipComprPago, String numComprPago, Double ivaPago, Double totalPago, Date fecGenCompr, Date fecPago) {
        this.idPago = idPago;
        this.idRsvaPago = idRsvaPago;
        this.tipComprPago = tipComprPago;
        this.numComprPago = numComprPago;
        this.ivaPago = ivaPago;
        this.totalPago = totalPago;
        this.fecGenCompr = fecGenCompr;
        this.fecPago = fecPago;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getIdRsvaPago() {
        return idRsvaPago;
    }

    public void setIdRsvaPago(int idRsvaPago) {
        this.idRsvaPago = idRsvaPago;
    }

    public String getTipComprPago() {
        return tipComprPago;
    }

    public void setTipComprPago(String tipComprPago) {
        this.tipComprPago = tipComprPago;
    }

    public String getNumComprPago() {
        return numComprPago;
    }

    public void setNumComprPago(String numComprPago) {
        this.numComprPago = numComprPago;
    }

    public Double getIvaPago() {
        return ivaPago;
    }

    public void setIvaPago(Double ivaPago) {
        this.ivaPago = ivaPago;
    }

    public Double getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(Double totalPago) {
        this.totalPago = totalPago;
    }

    public Date getFecGenCompr() {
        return fecGenCompr;
    }

    public void setFecGenCompr(Date fecGenCompr) {
        this.fecGenCompr = fecGenCompr;
    }

    public Date getFecPago() {
        return fecPago;
    }

    public void setFecPago(Date fecPago) {
        this.fecPago = fecPago;
    }

}
