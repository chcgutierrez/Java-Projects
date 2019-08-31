package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VehiculoCRUD extends ConexionDB {

//Guardar
    public boolean GuardarVehiculo(Vehiculo nVehiculo) {

        PreparedStatement prStat = null;
        Connection nConex = conectarDB();
        try {
            String instrSQL = "INSERT INTO tb_vehiculo(placa_vehiculo,ciudad_vehiculo,marca_vehiculo,tipo_vehiculo,modelo_vehiculo,color_vehiculo,num_motor,estado_vehiculo,obs_vehiculo,cliente) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?)";
            prStat = nConex.prepareStatement(instrSQL);
            prStat.setString(1, nVehiculo.getPlaca());
            prStat.setString(2, nVehiculo.getCityVeh());
            prStat.setString(3, nVehiculo.getMarca());
            prStat.setString(4, nVehiculo.getTipoVeh());
            prStat.setString(5, nVehiculo.getModelo());
            prStat.setString(6, nVehiculo.getColor());
            prStat.setString(7, nVehiculo.getMotor());
            prStat.setString(8, "A");
            prStat.setString(9, "");
            prStat.setString(10, nVehiculo.getClienteVeh());
            prStat.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean EditarVehiculo(Vehiculo nVehiculo) {

        PreparedStatement prStat = null;
        Connection nConex = conectarDB();
        try {
            String instrSQL = "UPDATE tb_vehiculo SET placa_vehiculo=?,ciudad_vehiculo=?,marca_vehiculo=?,tipo_vehiculo=?,modelo_vehiculo=?,color_vehiculo=?,num_motor=?,estado_vehiculo=?,obs_vehiculo=?,cliente=? WHERE placa_vehiculo=?";
            prStat = nConex.prepareStatement(instrSQL);
            prStat.setString(1, nVehiculo.getPlaca());
            prStat.setString(2, nVehiculo.getCityVeh());
            prStat.setString(3, nVehiculo.getMarca());
            prStat.setString(4, nVehiculo.getTipoVeh());
            prStat.setString(5, nVehiculo.getModelo());
            prStat.setString(6, nVehiculo.getColor());
            prStat.setString(7, nVehiculo.getMotor());
            prStat.setString(8, nVehiculo.getEstVeh());
            prStat.setString(9, nVehiculo.getObsVeh());
            prStat.setString(10, nVehiculo.getClienteVeh());
            prStat.setString(11, nVehiculo.getPlaca());
            prStat.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Vehiculo consultarVehiculo(String placa) {
        PreparedStatement prStat = null;
        ResultSet rslSet = null;
        Connection nConex = conectarDB();
        Vehiculo vehiculo = null;
        try {
            vehiculo = new Vehiculo();
            String instrSQL = "select placa_vehiculo,c.nom_ciudad,m.nom_marca,t.nom_tipo,modelo_vehiculo,co.nom_color,num_motor,estado_vehiculo,obs_vehiculo,cliente from tb_vehiculo v inner join tb_ciudad c on v.ciudad_vehiculo = c.idciudad inner join tb_marca m on v.marca_vehiculo = m.idmarca inner join tb_tipo t on v.tipo_vehiculo = t.idtipo inner join tb_color co on v.color_vehiculo = co.idcolor where PLACA_VEHICULO = ?";
            prStat = nConex.prepareStatement(instrSQL);
            prStat.setString(1, placa);
            rslSet = prStat.executeQuery();

            while (rslSet.next()) {
                vehiculo.setPlaca(rslSet.getString("placa_vehiculo"));
                vehiculo.setCityVeh(rslSet.getString("nom_ciudad"));
                vehiculo.setMarca(rslSet.getString("nom_marca"));
                vehiculo.setTipoVeh(rslSet.getString("nom_tipo"));
                vehiculo.setModelo(rslSet.getString("modelo_vehiculo"));
                vehiculo.setColor(rslSet.getString("nom_color"));
                vehiculo.setMotor(rslSet.getString("num_motor"));
                vehiculo.setEstVeh(rslSet.getString("estado_vehiculo"));
                vehiculo.setObsVeh(rslSet.getString("obs_vehiculo"));
                vehiculo.setClienteVeh(rslSet.getString("cliente"));
            }
            return vehiculo;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return vehiculo;
        }
    }
    public int validarVehiculo(String placa) {

        PreparedStatement prStat = null;
        ResultSet rslSet = null;
        Connection nConex = conectarDB();
        String idMarca = null;
        int datos = 0;
        try {
            String instrSQL = "SELECT count(*) as total FROM tb_vehiculo WHERE placa_vehiculo=?";
            prStat = nConex.prepareStatement(instrSQL);
            prStat.setString(1, placa);
            rslSet = prStat.executeQuery();
            if (rslSet.next()) {
                datos = rslSet.getInt("total");
            }
            return datos;

        } catch (SQLException e) {
            e.printStackTrace();
            return datos;
        }
    }
}
