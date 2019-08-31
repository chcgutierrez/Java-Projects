package datos;

public class vConsumo {

    private int idConsu;
    private int id_RsvaConsu;
    private int id_ProdConsu;
    private int cantConsu;
    private Double preVenConsu;
    private String estConsu;

    public vConsumo() {
    }

    public vConsumo(int idConsu, int id_RsvaConsu, int id_ProdConsu, int cantConsu, Double preVenConsu, String estConsu) {
        this.idConsu = idConsu;
        this.id_RsvaConsu = id_RsvaConsu;
        this.id_ProdConsu = id_ProdConsu;
        this.cantConsu = cantConsu;
        this.preVenConsu = preVenConsu;
        this.estConsu = estConsu;
    }

    public int getIdConsu() {
        return idConsu;
    }

    public void setIdConsu(int idConsu) {
        this.idConsu = idConsu;
    }

    public int getId_RsvaConsu() {
        return id_RsvaConsu;
    }

    public void setId_RsvaConsu(int id_RsvaConsu) {
        this.id_RsvaConsu = id_RsvaConsu;
    }

    public int getId_ProdConsu() {
        return id_ProdConsu;
    }

    public void setId_ProdConsu(int id_ProdConsu) {
        this.id_ProdConsu = id_ProdConsu;
    }

    public int getCantConsu() {
        return cantConsu;
    }

    public void setCantConsu(int cantConsu) {
        this.cantConsu = cantConsu;
    }

    public Double getPreVenConsu() {
        return preVenConsu;
    }

    public void setPreVenConsu(Double preVenConsu) {
        this.preVenConsu = preVenConsu;
    }

    public String getEstConsu() {
        return estConsu;
    }

    public void setEstConsu(String estConsu) {
        this.estConsu = estConsu;
    }

}
