package tconq.server;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import tconq.entity.Player;

public class ServerHandler {
    public String serverip = "40.76.27.38:8080";
    public Long playerID = 0L;
    public static ServerHandler instance = new ServerHandler();

    public ServerHandler(){
        
    }

    public  Boolean checkForNextTurns(){
		
		return RequestThread.instance.GetNextTurn();
    }
    
    public  String getOpponents(){
		final String uri = "http://" + serverip + "/Players/" + playerID.toString();
		RestTemplate  restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);


//		System.out.println(result);



		return result;
	}
	
	public static ArrayList<Long> getOpponentIds(String opponents){
		JSONArray jsonArray = new JSONArray(opponents); // changes string to jsonArray
		ArrayList<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonEntity = jsonArray.getJSONObject(i); // gets one json onject form array
			ids.add(jsonEntity.getLong("id"));
		}
		return ids;
	}
    
    public  String getEntities(Long playerId){
		final String uri = "http://" + serverip + "/SEntities/" + playerId.toString();
		RestTemplate  restTemplate = new RestTemplate();
		return restTemplate.getForObject(uri, String.class);
		//System.out.println(result);
	}

	private  void addEntities() {
		final String uri = "http://" + serverip + "/SEntities";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// create a map for post parameters
		HashMap<String, Object> map = new HashMap<>();
		map.put("x",5);
		map.put("type","WEAK");
		map.put("y",1);
		map.put("player",2);
		HashMap<String, Object> map2 = new HashMap<>();
		map2.put("x",8);
		map2.put("type","STRONG");
		map2.put("y",4);
		map2.put("player",2);

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		list.add(map);
		list.add(map2);

		// build the request
		HttpEntity<ArrayList<HashMap<String, Object>>> entity = new HttpEntity<>(list, headers);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(uri, entity, String.class);
	}

	private  void addEntity() {
		final String uri = "http://" + serverip + "/SEntity";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// create a map for post parameters
		HashMap<String, Object> map = new HashMap<>();
		map.put("x",12);
		map.put("type","CASTLE");
		map.put("y",10);
		map.put("player",2);

		// build the request
		HttpEntity<HashMap<String, Object>> entity = new HttpEntity<>(map, headers);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(uri, entity, String.class);
	}

	public void addPlayer(String name){
		final String uri = "http://" + serverip + "/Player";

		
		// create headers
		HttpHeaders headers = new HttpHeaders();
		// set `content-type` header
		headers.setContentType(MediaType.APPLICATION_JSON);

		// create a map for post parameters
		HashMap<String, Object> map = new HashMap<>();
		map.put("points",1);
		map.put("name",name);
		map.put("gold",2);
		map.put("playing","true");
		map.put("nextTurn","false");

		// build the request
		HttpEntity<HashMap<String, Object>> entity = new HttpEntity<>(map, headers);



		RestTemplate restTemplate = new RestTemplate();
		// send POST request
		ResponseEntity<Player> response = restTemplate.postForEntity(uri, entity, Player.class);

		//sets player id
		playerID = response.getBody().getId();


	}
}