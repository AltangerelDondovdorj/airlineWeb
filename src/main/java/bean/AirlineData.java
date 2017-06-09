package bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import model.Flight;

@ManagedBean(name="data", eager=true)
@SessionScoped
public class AirlineData {
	
	public List<Flight> flights;
	
	public List<Flight> callFlights(){
		Client client = ClientBuilder.newClient();
		flights = client.target("http://localhost:8080/airlinesWebApp/rs/flight/all")
					.request(MediaType.APPLICATION_JSON)
					.get(new GenericType<List<Flight>>() {});
		return flights;
	}
	
}
