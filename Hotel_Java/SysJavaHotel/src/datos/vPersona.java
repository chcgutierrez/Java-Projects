//Contiene los atributos de la tabla Producto
package datos;

public class vPersona {

    private int idPers;
    private String nomPers;
    private String prapePers;
    private String segapePers;
    private String tipdocPers;
    private String numdocPers;
    private String direPers;
    private String telPers;
    private String emailPers;

    public vPersona() {
    }

    public vPersona(int idPers, String nomPers, String prapePers, String segapePers, String tipdocPers, String numdocPers, String direPers, String telPers, String emailPers) {
        this.idPers = idPers;
        this.nomPers = nomPers;
        this.prapePers = prapePers;
        this.segapePers = segapePers;
        this.tipdocPers = tipdocPers;
        this.numdocPers = numdocPers;
        this.direPers = direPers;
        this.telPers = telPers;
        this.emailPers = emailPers;
    }

    public int getIdPers() {
        return idPers;
    }

    public void setIdPers(int idPers) {
        this.idPers = idPers;
    }

    public String getNomPers() {
        return nomPers;
    }

    public void setNomPers(String nomPers) {
        this.nomPers = nomPers;
    }

    public String getPrapePers() {
        return prapePers;
    }

    public void setPrapePers(String prapePers) {
        this.prapePers = prapePers;
    }

    public String getSegapePers() {
        return segapePers;
    }

    public void setSegapePers(String segapePers) {
        this.segapePers = segapePers;
    }

    public String getTipdocPers() {
        return tipdocPers;
    }

    public void setTipdocPers(String tipdocPers) {
        this.tipdocPers = tipdocPers;
    }

    public String getNumdocPers() {
        return numdocPers;
    }

    public void setNumdocPers(String numdocPers) {
        this.numdocPers = numdocPers;
    }

    public String getDirePers() {
        return direPers;
    }

    public void setDirePers(String direPers) {
        this.direPers = direPers;
    }

    public String getTelPers() {
        return telPers;
    }

    public void setTelPers(String telPers) {
        this.telPers = telPers;
    }

    public String getEmailPers() {
        return emailPers;
    }

    public void setEmailPers(String emailPers) {
        this.emailPers = emailPers;
    }

}
