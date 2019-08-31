
package datos;

public class vEmpleado extends vPersona {

    //privete int id_Persona;
    Double sueldoEmp;
    String accesoEmp;
    String loginEmp;
    String claveEmp;
    String estEmp;

    public vEmpleado() {
    }

    public vEmpleado(Double sueldoEmp, String accesoEmp, String loginEmp, String claveEmp, String estEmp) {
        this.sueldoEmp = sueldoEmp;
        this.accesoEmp = accesoEmp;
        this.loginEmp = loginEmp;
        this.claveEmp = claveEmp;
        this.estEmp = estEmp;
    }

    public Double getSueldoEmp() {
        return sueldoEmp;
    }

    public void setSueldoEmp(Double sueldoEmp) {
        this.sueldoEmp = sueldoEmp;
    }

    public String getAccesoEmp() {
        return accesoEmp;
    }

    public void setAccesoEmp(String accesoEmp) {
        this.accesoEmp = accesoEmp;
    }

    public String getLoginEmp() {
        return loginEmp;
    }

    public void setLoginEmp(String loginEmp) {
        this.loginEmp = loginEmp;
    }

    public String getClaveEmp() {
        return claveEmp;
    }

    public void setClaveEmp(String claveEmp) {
        this.claveEmp = claveEmp;
    }

    public String getEstEmp() {
        return estEmp;
    }

    public void setEstEmp(String estEmp) {
        this.estEmp = estEmp;
    }

}
