package org.utl.dsm.optik.controller;

import org.utl.dsm.optik.db.ConexionMySQL;
import org.utl.dsm.optik.modell.Empleado;
import org.utl.dsm.optik.modell.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;

public class ControllerEmpleado {
    
    public int insert(Empleado e) throws Exception{
        //Definimos la consulta SQL que invoca al Stored Procedure:
        String sql =    "{call insertarEmpleado(?, ?, ?, ?, ?, ?, ?, " + // Datos Personales
                        "?, ?, ?, ?, ?, ?, ?, " +
                        "?, ?, ?, " + // Datos de Seguridad
                        "?, ?, ?, ?, ?)}"; // Valores de Retorno
        //Aquí guardaremos los ID's que se generarán:
        int idPersonaGenerado = -1;
        int idEmpleadoGenerado = -1;
        int idUsuarioGenerado = -1;
        String numeroUnicoGenerado = "";
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.open();
        //Con este objeto invocaremos al StoredProcedure:
        CallableStatement cstmt = conn.prepareCall(sql);
        
        //Establecemos los parámetros de los datos personales en el orden
        //en que los pide el procedimiento almacenado, comenzando en 1:
        cstmt.setString(1, e.getNombre());
        cstmt.setString(2, e.getApellidoPaterno());
        cstmt.setString(3, e.getApellidoMaterno());
        cstmt.setString(4, e.getGenero());
        cstmt.setString(5, e.getFechaNacimiento());
        cstmt.setString(6, e.getCalle());
        cstmt.setString(7, e.getNumero());
        cstmt.setString(8, e.getColonia());
        cstmt.setString(9, e.getCp());
        cstmt.setString(10, e.getCiudad());
        cstmt.setString(11, e.getEstado());
        cstmt.setString(12, e.getTelCasa());
        cstmt.setString(13, e.getTelMovil());
        cstmt.setString(14, e.getEmail());
        
        // Registramos parámetros de datos de seguridad:
        cstmt.setString(15, e.getUsuario().getNombre());
        cstmt.setString(16, e.getUsuario().getContrasenia());
        cstmt.setString(17, e.getUsuario().getRol());
        
        //Registramos los parámetros de salida:
        cstmt.registerOutParameter(18, Types.INTEGER);
        cstmt.registerOutParameter(19, Types.INTEGER);
        cstmt.registerOutParameter(20, Types.INTEGER);
        cstmt.registerOutParameter(21, Types.VARCHAR);
        cstmt.registerOutParameter(22, Types.VARCHAR);
        
        //Ejecutamos el Stored Procedure:
          cstmt.executeUpdate();
        
        //Recuperamos los ID's generados:
        idPersonaGenerado = cstmt.getInt(18);
        idUsuarioGenerado = cstmt.getInt(19);
        idEmpleadoGenerado = cstmt.getInt(20);
        numeroUnicoGenerado = cstmt.getString(21);
        e.setIdEmpleado(idEmpleadoGenerado);
        e.setIdPersona(idPersonaGenerado);
        e.getUsuario().setIdUsuario(idUsuarioGenerado);
        e.setNumeroUnico(numeroUnicoGenerado);
        
        cstmt.close();
        connMySQL.close();
        //Devolvemos el ID de Cliente generado:
        return idEmpleadoGenerado;
    }
    
  
}
