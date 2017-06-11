package bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import model.Airline;
import model.Flight;

@ManagedBean(name="airline")
@SessionScoped
public class AirlineBean {
	
	String airlineName;

	public List<Airline> getAirlineList(){
		Client client = ClientBuilder.newClient();
		return client.target("http://localhost:8080/airlinesWebApp/rs/airline/airlines")
		.request(MediaType.APPLICATION_JSON)
		.get(new GenericType<List<Airline>>() {});	
	}
	
	public void submit(){
		System.out.println("Hello " + airlineName);
	}
	
	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}
}
