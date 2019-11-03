package tconq.server;

import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.client.RestTemplate;

import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import tconq.entity.Player;

public class ServerHandler {
    //    public String serverip = "40.76.27.38:8080";
    public String serverip = "localhost:8080";
    public Long playerID = 0L;
    public static ServerHandler instance = new ServerHandler();
    public static String sessionId = "";


    WebSocketClient client;
    WebSocketStompClient stompClient;
    StompSessionHandler sessionHandler;



    public ServerHandler(){
        client = new StandardWebSocketClient();
        stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSessionHandler sessionHandler = new MyStompSessionHandler();
        stompClient.connect( "ws://" + serverip, sessionHandler);

    }

//    public  Boolean checkForNextTurns(ArrayList<Long> opponentIds){
//
//		for (Long oppId : opponentIds) {
//			final String uri = "http://" + serverip + "/Player/" + oppId.toString();
//			RestTemplate  restTemplate = new RestTemplate();
//			String result = restTemplate.getForObject(uri, String.class);
//			JSONObject jsonEntity = new JSONObject(result);
//
//			if(jsonEntity.getBoolean("nextTurn") == false)		// checks if opponent kas ended his turn
//				return false;
//			else
//				return true;
//		}
//
//		return false;
//    }

    public  String getOpponents(){
        final String uri = "http://" + serverip + "/Players/" + playerID.toString();
        RestTemplate  restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);


//		System.out.println(result);



        return result;
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

        while(sessionId.equals("")) {  }

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
        map.put("sessionId", sessionId);

        // build the request
        HttpEntity<HashMap<String, Object>> entity = new HttpEntity<>(map, headers);



        RestTemplate restTemplate = new RestTemplate();
        // send POST request
        ResponseEntity<Player> response = restTemplate.postForEntity(uri, entity, Player.class);

        //sets player id
        playerID = response.getBody().getId();
    }
}