package com.gcu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.gcu.OrderModel;
import com.gcu.UserModel;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

/**
 * Create a new file TestController.java in the com.gcu.controller package. Add a
@RequestMapping(“/app”) annotation to the class to route all requests to the
/app URI. Add a Controller Request method named home(Model model) to handle
a GET request to the root / URI that displays a view named home. 
 * @author jjk14
 *
 */
@Controller
@RequestMapping("/app")
public class TestController {
	
	@Autowired
	EurekaClient eurekaClient;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Demo Microservices App");
		return "home";
	}
	
	/**
	 * Add a Controller Request method named getUsers(Model model) to handle a GET
request to the /getusers URI that calls the /service/users REST API running at
localhost on port 8081 using an instance of a RestTemplate class, which takes the
returned List<UserModel> and passes this to a view named users. Create the
users view that simply displays the list of Users in a HTML table.
	 * @param model
	 * @return
Part 2	Update the getUsers() method by using the eurekaClient variable to look
up the user-service (i.e., Eureka application) by calling the
getApplication() method, get the first instance of the application into local
variable instanceInfo of type InstanceInfo, and then get the hostname and
port from the instanceInfo variable by calling the getHostName() method
and getPort() method. Create the complete endpoint URL of the Users
REST API by using the hostname and port and using this URL in the Rest
Template.
	 *
	 */
	
	@GetMapping("/getusers")
	public String getUsers(Model model) {
		//Look up the Host Name and Port for the REST API endpoints
		Application application = eurekaClient.getApplication("user-service");
		
		//get the first instance of the application in into local variable instanceInfo
		InstanceInfo instanceInfo = application.getInstances().get(0);
		
		//get hostname and port from the instanceInfo variable
		String hostname = instanceInfo.getHostName();
		int port = instanceInfo.getPort();
		
		//Get Users from REST API
		String url = "http://" + hostname + ":" + port + "/service/users";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<UserModel>> restResponse = 
				restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<UserModel>>() {});
		List<UserModel> users = restResponse.getBody();
		
		//display the users
		model.addAttribute("title", "List Of Users");
		model.addAttribute("users", users);
		return "users";
	}
	
	/**
	 * Add a Controller Request method named getOrders(Model model) to handle a
GET request to the /getorders URI that calls the /service/orders REST API
running at localhost on port 8082 using an instance of a RestTemplate class,
which takes the returned List<OrderModel> and passes this to a view named
orders. Create the orders view that simply displays the list of Orders in a HTML
table.

Part 2 Update the getOrders() method by using the eurekaClient variable to look
up the order-service (i.e., Eureka application) by calling the
getApplication() method, get the first instance of the application into local
variable instanceInfo of type InstanceInfo, and then get the hostname and
port from the instanceInfo variable by calling the getHostName() method
and getPort() method. Create the complete endpoint URL of the Orders
REST API by using the hostname and port and using this URL in the Rest
Template.
	 */
	@GetMapping("/getorders")
	public String getOrders(Model model) {
		//REST API endpoints
		//Look up the Host Name and Port for the REST API endpoints
				Application application = eurekaClient.getApplication("order-service");
				
				//get the first instance of the application in into local variable instanceInfo
				InstanceInfo instanceInfo = application.getInstances().get(0);
				
				//get hostname and port from the instanceInfo variable
				String hostname = instanceInfo.getHostName();
				int port = instanceInfo.getPort();
				
				//Get Users from REST API
				String url = "http://" + hostname + ":" + port + "/service/orders";
				RestTemplate restTemplate = new RestTemplate();
				ResponseEntity<List<OrderModel>> restResponse = 
						restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderModel>>() {});
				List<OrderModel> orders = restResponse.getBody();
				
				//display the users
				model.addAttribute("title", "List Of Orders");
				model.addAttribute("orders", orders);
				return "orders";
	}
}
