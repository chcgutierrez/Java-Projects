//Contiene los atributos de la tabla Habitación

package datos;

public class vHabitacion {

    private int idHab;
    private String numHab;
    private String pisoHab;
    private String descHab;
    private String caracHab;
    private Double prediaHab;
    private String estHab;
    private String tipoHab;

    public vHabitacion() {
    }

    public vHabitacion(int idHab, String numHab, String pisoHab, String descHab, String caracHab, Double prediaHab, String estHab, String tipoHab) {
        this.idHab = idHab;
        this.numHab = numHab;
        this.pisoHab = pisoHab;
        this.descHab = descHab;
        this.caracHab = caracHab;
        this.prediaHab = prediaHab;
        this.estHab = estHab;
        this.tipoHab = tipoHab;
    }

    public int getIdHab() {
        return idHab;
    }

    public void setIdHab(int idHab) {
        this.idHab = idHab;
    }

    public String getNumHab() {
        return numHab;
    }

    public void setNumHab(String numHab) {
        this.numHab = numHab;
    }

    public String getPisoHab() {
        return pisoHab;
    }

    public void setPisoHab(String pisoHab) {
        this.pisoHab = pisoHab;
    }

    public String getDescHab() {
        return descHab;
    }

    public void setDescHab(String descHab) {
        this.descHab = descHab;
    }

    public String getCaracHab() {
        return caracHab;
    }

    public void setCaracHab(String caracHab) {
        this.caracHab = caracHab;
    }

    public Double getPrediaHab() {
        return prediaHab;
    }

    public void setPrediaHab(Double prediaHab) {
        this.prediaHab = prediaHab;
    }

    public String getEstHab() {
        return estHab;
    }

    public void setEstHab(String estHab) {
        this.estHab = estHab;
    }

    public String getTipoHab() {
        return tipoHab;
    }

    public void setTipoHab(String tipoHab) {
        this.tipoHab = tipoHab;
    }

}
