package tr.edu.yeditepe.kernelcoin.interfaces.controller;

import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import tr.edu.yeditepe.kernelcoin.domain.model.kernelblock.KernelBlock;

@Controller
public class SocketController {

    @MessageMapping("/consent-request")
    @SendTo("/topic/distrubution")
    public KernelBlock getProof(KernelBlock consentBlock) throws Exception {
        return consentBlock;
    }


}
