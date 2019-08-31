//Contiene los atributos de la tabla Producto
package datos;

public class vProducto {

    private int idProd;
    private String nomProd;
    private String descProd;
    private String undmProd;
    private Double valvProd;

    public vProducto() {
    }

    public vProducto(int idProd, String nomProd, String descProd, String undmProd, Double valvProd) {
        this.idProd = idProd;
        this.nomProd = nomProd;
        this.descProd = descProd;
        this.undmProd = undmProd;
        this.valvProd = valvProd;
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public String getDescProd() {
        return descProd;
    }

    public void setDescProd(String descProd) {
        this.descProd = descProd;
    }

    public String getUndmProd() {
        return undmProd;
    }

    public void setUndmProd(String undmProd) {
        this.undmProd = undmProd;
    }

    public Double getValvProd() {
        return valvProd;
    }

    public void setValvProd(Double valvProd) {
        this.valvProd = valvProd;
    }

}
