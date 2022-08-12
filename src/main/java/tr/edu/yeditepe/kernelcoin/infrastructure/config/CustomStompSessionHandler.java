
package tr.edu.yeditepe.kernelcoin.infrastructure.config;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.stereotype.Component;
import tr.edu.yeditepe.kernelcoin.domain.model.StompSessions.StompSessions;
import tr.edu.yeditepe.kernelcoin.domain.model.blockchain.BlockChain;
import tr.edu.yeditepe.kernelcoin.interfaces.dto.ConsentBlockDto;
import tr.edu.yeditepe.kernelcoin.service.ConsentService;
import tr.edu.yeditepe.kernelcoin.service.MinerService;

import java.lang.reflect.Type;


@Component
public class CustomStompSessionHandler extends StompSessionHandlerAdapter {

    private Logger logger = LogManager.getLogger(CustomStompSessionHandler.class);

    private final StompSessions sessions;
    private final ConsentService consentService;

    public CustomStompSessionHandler(StompSessions session,ConsentService consent) {
        sessions = session;
        consentService=consent;
    }


    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        logger.info("New session established : " + session.getSessionId());
        session.subscribe("/topic/distrubution", this);
        session.subscribe("/topic/consents", this);
        session.subscribe("/topic/minerPayload", this);
        logger.info("Subscribed to /topic/messages");
        //session.send("/app/consent-request",blockChain.getKernelBlocks()
        //        .get(blockChain.getKernelBlocks().size()-1));
        logger.info("Message sent to websocket server");
        sessions.getSessions().add(session);
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        logger.error("Got an exception", exception);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return ConsentBlockDto.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        ConsentBlockDto consentBlockDto = (ConsentBlockDto) payload;
        if(headers.getDestination().contentEquals("/topic/distrubution")) {
            consentService.checkKernelBlock(consentBlockDto);
        }
        else if(headers.getDestination().contentEquals("/topic/consents")){
            consentService.checkIfConsensusIsOk(consentBlockDto);
        }
        else if(headers.getDestination().contentEquals("/topic/minerPayload")){
            consentService.doConsentMine(consentBlockDto);
        }
        logger.info("header"+headers.getDestination()+"Received : " + consentBlockDto.getTimeStamp() + " from : " + consentBlockDto.getMinerId() + payload.toString());
    }

}
