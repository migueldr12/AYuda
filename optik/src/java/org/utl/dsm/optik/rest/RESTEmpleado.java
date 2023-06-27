package org.utl.dsm.optik.rest;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.utl.dsm.optik.controller.ControllerEmpleado;
import org.utl.dsm.optik.modell.Empleado;

@Path("empleado")
public class RESTEmpleado {
    @Path("save")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response save(@FormParam("datosEmpleado") @DefaultValue("") String datosEmpleado) throws Exception{
        String out = null;
        Empleado empleado = null;
        ControllerEmpleado ce = null;
        Gson gson = new Gson();
        
        try{
            // Convertimos los datos JSON del empleado en un objeto Java:
            empleado = gson.fromJson(datosEmpleado, Empleado.class);
            // Creamos una instancia del controlador de empleados:
            ce = new ControllerEmpleado();
            //Revisamos si haremos una insercion o actualizacion:
            //if (empleado.getIdEmpleado() < 1)
            ce.insert(empleado);
            //else
            //ce.update(empleado);
            //Convertimos el objeto empleado a una cadena JSON:
            out = gson.toJson(empleado); 
        } catch(JsonParseException e){
            out = "{ \"response\": \" Error interno del servidor. Intente mas tarde. \"}";
            //jpe.printStackTrace();
        }
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
