package bean;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
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
	
	String oldName;
	String newName;

	
	public String updateAirline(){
		
		Client client = ClientBuilder.newClient();
		 client.target("http://localhost:8080/airlinesWebApp/rs/airline/update?old=" + oldName + "&new="+newName)
		.request(MediaType.APPLICATION_JSON)
		.get();	
		
		return "updated";
	}
	public String deleteAirline(){
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
		        .getRequest();
		   String id = request.getParameter("id");
		
		
		Client client = ClientBuilder.newClient();
		 client.target("http://localhost:8080/airlinesWebApp/rs/airline/delete?id=" + id)
		.request(MediaType.APPLICATION_JSON)
		.get();	
		 return "deleted";
	}
	
	public List<Airline> getAirlineList(){
		Client client = ClientBuilder.newClient();
		return client.target("http://localhost:8080/airlinesWebApp/rs/airline/airlines")
		.request(MediaType.APPLICATION_JSON)
		.get(new GenericType<List<Airline>>() {});	
	}
	
	public String submit(){
		Client client = ClientBuilder.newClient();
		try{
		 client.target("http://localhost:8080/airlinesWebApp/rs/airline/create?name=" + airlineName)
		.request(MediaType.APPLICATION_JSON)
		.get();		
		}
		catch(Exception ex){
			//throw new Exception();
			System.out.println("error");
			return "error";
		}
		System.out.println("success");
		return "success";
	}
	
	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}
	
	
}
