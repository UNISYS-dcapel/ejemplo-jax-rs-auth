package es.infointernet.rest.services;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import es.infointernet.annotation.Secured;
import es.infointernet.model.Actor;
import es.infointernet.model.Pelicula;

@Path("/pelicula")
public class PeliculaResource {
	
	@GET
	@Path("{idPelicula}")
	@Secured
    @Produces({MediaType.APPLICATION_JSON+";charset=utf-8"}) 
    public Pelicula getPelicula(@PathParam("idPelicula") Integer idPelicula) throws Exception{
		
        //Habría que llamar a un servicio que devolviera la película con el id pasado como parámetro
        return getPeliculaEjemplo(); 
    }
	
	@GET
    @Produces({MediaType.APPLICATION_JSON+";charset=utf-8"})
	@Secured
    public Pelicula findPeliculas(@Context SecurityContext secContext, @QueryParam("titulo") String titulo, @QueryParam("nombreActor") String nombreActor) throws Exception{
		
		//Podemos comprobar si el usuario tiene un rol determinado
		if(secContext.isUserInRole("ADMINISTRADOR")) {
            //Realizar alguna operación específica para el administrador
        }

		//Podemos recuperar el nombre del usuario
		String nombre = null;
        Principal p = secContext.getUserPrincipal();
        if(p != null) {
            nombre = p.getName();
        }
		        
        // Habría que llamar a un servicio de búsqueda de películas
        return getPeliculaEjemplo(); 
    }
	
	@PUT
	@Secured
	@RolesAllowed({"ADMINISTRADOR"})
    public void addPelicula() throws Exception{
		
		// Habr�a que llamar a un servicio para crear pel�culas
 
    }
	
	
	/**
	 * Servicio de ejemplo. 
	 * @return
	 */
	private Pelicula getPeliculaEjemplo(){
		List<Actor> actores = new ArrayList<Actor>();
        
        Actor a = new Actor();
        a.setNombre("Penélope Cruz");
        actores.add(a);
        
        Pelicula p = new Pelicula();
        p.setTitulo("Jamón Jamón");
        p.setActoresList(actores);
       
        return p;
	}
}
