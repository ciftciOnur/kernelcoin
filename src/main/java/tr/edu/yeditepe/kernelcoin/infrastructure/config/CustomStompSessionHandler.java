
package tr.edu.yeditepe.kernelcoin.infrastructure.config;


import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.stereotype.Component;
import tr.edu.yeditepe.kernelcoin.domain.model.StompSessions.StompSessions;
import tr.edu.yeditepe.kernelcoin.domain.model.blockchain.BlockChain;
import tr.edu.yeditepe.kernelcoin.domain.model.kernelblock.KernelBlock;
import tr.edu.yeditepe.kernelcoin.interfaces.dto.ConsentBlockDto;

import java.lang.reflect.Type;


@Component
public class CustomStompSessionHandler extends StompSessionHandlerAdapter {

    private Logger logger = LogManager.getLogger(CustomStompSessionHandler.class);

    private final StompSessions sessions;

    public CustomStompSessionHandler(StompSessions session) {
        sessions = session;
    }


    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        logger.info("New session established : " + session.getSessionId());
        session.subscribe("/topic/distrubution", this);
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
        ConsentBlockDto msg = (ConsentBlockDto) payload;
        logger.info("Received : " + msg.getTimeStamp() + " from : " + msg.getOrder());
    }

}
