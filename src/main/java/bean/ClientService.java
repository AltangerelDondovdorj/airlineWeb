package bean;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import model.Airplane;
import model.Airline;
import model.Airport;
import model.Flight;

public class ClientService<T> {
	public List<T> getLists(String URL){
		System.out.println("hello client service");
		Client client = ClientBuilder.newClient();
		List<T> list = client.target("http://localhost:8080/airlinesWebApp/rs/" + URL)
					.request(MediaType.APPLICATION_JSON)
					.get(new GenericType<List<T>>() {});
		System.out.println("lists = " + list.size());
		return list;
	}
}
