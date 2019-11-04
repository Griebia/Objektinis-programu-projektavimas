package tconq.server;

import java.util.ArrayList;

import org.hibernate.validator.internal.util.privilegedactions.NewInstance;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

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
            ArrayList<Long> opponentIds = ServerHandler.getOpponentIds(ServerHandler.instance.getOpponents());
            for (Long oppId : opponentIds) {
                final String uri = "http://" + ServerHandler.instance.serverip + "/Player/" + oppId.toString();
                RestTemplate  restTemplate = new RestTemplate();
                String result = restTemplate.getForObject(uri, String.class);	
                JSONObject jsonEntity = new JSONObject(result);
                
                if(jsonEntity.getBoolean("nextTurn") == false)		// checks if opponent kas ended his turn
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