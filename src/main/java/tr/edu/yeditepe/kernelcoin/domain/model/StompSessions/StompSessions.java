package tr.edu.yeditepe.kernelcoin.domain.model.StompSessions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

import java.util.ArrayList;

@Getter
@Setter
public class StompSessions {
    private ArrayList<StompSession> sessions;
    private String nodeIp;

    public StompSessions(){
        sessions=new ArrayList<>();
        nodeIp="localhost:8084";
    }
}
