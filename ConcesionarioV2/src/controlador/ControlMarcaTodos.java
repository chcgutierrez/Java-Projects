package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import vista.frmMarca;
import vista.frmMarcaTodo;

public class ControlMarcaTodos implements ActionListener {

    private frmMarca frmMarcaControl;
    private frmMarcaTodo frmMarcaTodos;

    public ControlMarcaTodos(frmMarca frmMarcaControl, frmMarcaTodo frmMarcaTodos) {

        this.frmMarcaControl= frmMarcaControl;
        this.frmMarcaTodos = frmMarcaTodos;
        this.frmMarcaTodos.btnCerrarBusqMarca.addActionListener(this);

    }

    public void PasarFila() {
        int selFila = frmMarcaTodos.tblMarca.getSelectedRow();
        frmMarcaControl.txtCodMarca.setText(frmMarcaTodos.tblMarca.getValueAt(selFila, 0).toString());
        frmMarcaControl.txtMarca.setText(frmMarcaTodos.tblMarca.getValueAt(selFila, 1).toString());
        frmMarcaControl.txtObsMarca.setText(frmMarcaTodos.tblMarca.getValueAt(selFila, 2).toString());
        //frmMarcaControl.txtCodMarca.setText(frmMarcaTodos.tblMarca.getValueAt(selFila, 3).toString());
        frmMarcaTodos.dispose();
    }
    
    class SelectListener extends MouseAdapter{
        
        public void tablaClicked(MouseEvent met){
            PasarFila();
        }
        
        public void tablaPressed(MouseEvent met){
            PasarFila();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
         if (ae.getSource() == frmMarcaTodos.tblMarca.getMouseListeners()) {
              PasarFila();
         }

    }

}
