
package datos;

public class vCliente extends vPersona {

    private String codCliente;

    public vCliente() {
    }

    public vCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

}
