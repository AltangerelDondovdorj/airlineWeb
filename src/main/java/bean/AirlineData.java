package bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import model.Flight;

@ManagedBean(name="airlineData")
@SessionScoped
public class AirlineData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8161703447706643310L;
	private List<Flight> flights;
	private String searchAirline = "";
	private String fromDate = "";
	
	public void findAirlines(AjaxBehaviorEvent event) {
		Client client = ClientBuilder.newClient();
		if (searchAirline.isEmpty()) {
			setFlights(client.target("http://localhost:8080/airlinesWebApp/rs/flight/all")
					.request(MediaType.APPLICATION_JSON)
					.get(new GenericType<List<Flight>>() {}));
		} else {
			setFlights(client.target("http://localhost:8080/airlinesWebApp/rs/flight/findByAirline?name=" + searchAirline)
					.request(MediaType.APPLICATION_JSON)
					.get(new GenericType<List<Flight>>() {}));
		}
	}
	
	public void findFlightbyDate(){
		Client client = ClientBuilder.newClient();
		System.out.println(fromDate);
		if (fromDate.isEmpty()) {
			setFlights(client.target("http://localhost:8080/airlinesWebApp/rs/flight/all")
					.request(MediaType.APPLICATION_JSON)
					.get(new GenericType<List<Flight>>() {}));
		} else {
			setFlights(client.target("http://localhost:8080/airlinesWebApp/rs/flight/findByArrival?datetime=" + fromDate)
					.request(MediaType.APPLICATION_JSON)
					.get(new GenericType<List<Flight>>() {}));
		}
	}
	
	@PostConstruct
	public void getAllFlights(){
		Client client = ClientBuilder.newClient();
		setFlights(client.target("http://localhost:8080/airlinesWebApp/rs/flight/all")
					.request(MediaType.APPLICATION_JSON)
					.get(new GenericType<List<Flight>>() {}));
		//return flights;
	}
	
	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}
	
	public String getSearchAirline() {
		return searchAirline;
	}

	public void setSearchAirline(String searchAirline) {
		this.searchAirline = searchAirline;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	
	
}
