package bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import model.Airline;
import model.Airplane;
import model.Airport;

@ManagedBean(name="flight")
@SessionScoped
public class FlightBean<T> {
	
	String airplaneString;
	String airlineString;
	String destAirport;
	String originAirport;
	
	public List<String> getAirlines() {
		Client client = ClientBuilder.newClient();
		List<Airline> airlines = client.target("http://localhost:8080/airlinesWebApp/rs/airline/airlines")
					.request(MediaType.APPLICATION_JSON)
					.get(new GenericType<List<Airline>>() {});
		List<String> list = new ArrayList<String>();
		for (Airline airline : airlines) {
			list.add(airline.getName());
		}
		return list;
	}
	
	public List<String> getAirports() {
		Client client = ClientBuilder.newClient();
		List<Airport> airports = client.target("http://localhost:8080/airlinesWebApp/rs/airport/airports")
					.request(MediaType.APPLICATION_JSON)
					.get(new GenericType<List<Airport>>() {});
		List<String> list = new ArrayList<String>();
		for (Airport airport : airports) {
			list.add(airport.getName());
		}
		return list;
	}
	
	public List<String> getAirplanes() {
			
		Client client = ClientBuilder.newClient();
		List<Airplane> airplanes = client.target("http://localhost:8080/airlinesWebApp/rs/airplane/airplanes")
					.request(MediaType.APPLICATION_JSON)
					.get(new GenericType<List<Airplane>>() {});
		List<String> list = new ArrayList<String>();
		for (Airplane airplane : airplanes) {
			list.add(airplane.getModel() + " " + airplane.getSerialnr());
		}
		return list;
	}
	
	public String getAirplaneString() {
		return airplaneString;
	}

	public void setAirplaneString(String airplaneString) {
		this.airplaneString = airplaneString;
	}

	public String getAirlineString() {
		return airlineString;
	}

	public void setAirlineString(String airlineString) {
		this.airlineString = airlineString;
	}

	public String getDestAirport() {
		return destAirport;
	}

	public void setDestAirport(String destAirport) {
		this.destAirport = destAirport;
	}

	public String getOriginAirport() {
		return originAirport;
	}

	public void setOriginAirport(String originAirport) {
		this.originAirport = originAirport;
	}
	
}
