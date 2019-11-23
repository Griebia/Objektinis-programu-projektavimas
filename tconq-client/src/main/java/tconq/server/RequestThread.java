package tconq.server;

import java.util.ArrayList;

import org.hibernate.validator.internal.util.privilegedactions.NewInstance;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import tconq.entity.Player;

public class RequestThread extends Thread {
    private boolean nextTurn = false;
    private boolean canceled = false;
    public static RequestThread instance = new RequestThread();

    @Override
    public void run() {
        while (!canceled) {

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            ArrayList<Player> opponentList = ServerHandler.getOpponents(ServerHandler.instance.getOpponents());
            for (Player opp : opponentList) {
                final String uri = "http://" + ServerHandler.instance.serverip + "/Player/" + opp.getId().toString();
                RestTemplate  restTemplate = new RestTemplate();
                String result = restTemplate.getForObject(uri, String.class);	
                JSONObject jsonEntity = new JSONObject(result);
                
                if(jsonEntity.getBoolean("nextTurn") == false)		// checks if opponent has ended his turn
                    nextTurn = false;
                else
                    nextTurn = true;
            }
        }
    }

    public boolean GetNextTurn(){
        return nextTurn;
    }

    public void cancel(){
        this.canceled = true;
    }
}