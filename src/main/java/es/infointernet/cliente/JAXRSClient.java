package es.infointernet.cliente;

import es.infointernet.model.Pelicula;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.List;

public class JAXRSClient {
    public static void main(String[] args) {

        HttpAuthenticationFeature autentificacion = HttpAuthenticationFeature.basic("cecilio", "cecilio");
        Client client = ClientBuilder.newClient();
        client.register(autentificacion);
        WebTarget target = client.target("http://localhost:9090/rest/usuario/login?user=test&password=test");

        Response response=target.request().get();
        MultivaluedMap<String, Object> map = response.getHeaders();
        System.out.println(map);
        String apiToken=map.get("Authorization").get(0).toString();
        System.out.println(apiToken);
        target = client.target("http://localhost:9090/rest/pelicula/1");
        Pelicula pelicula=target.request(MediaType.APPLICATION_JSON).header("Authorization",apiToken).get(Pelicula.class);
        System.out.println(pelicula);
        /*
        GenericType<List<Pelicula>> peliculas = new GenericType<List<Pelicula>>() {
        };

        List<Pelicula> listaNoticia = target.request(MediaType.APPLICATION_JSON).get(peliculas);

        for (Pelicula n : listaNoticia) {

            System.out.println(n.getTitulo());
            System.out.println(n.getActoresList());



        }
        */
    }
}
