package tr.edu.yeditepe.kernelcoin.interfaces.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.edu.yeditepe.kernelcoin.interfaces.dto.StatusDto;
import tr.edu.yeditepe.kernelcoin.service.MinerService;
import tr.edu.yeditepe.kernelcoin.service.Peer2PeerService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/api/miner")
@RequiredArgsConstructor
public class MinerController {

    private final MinerService minerService;

    private final Peer2PeerService peer2PeerService;

    @GetMapping
    public List<StatusDto> getBlockChainStatus() {
        return minerService.status();
    }

    @PostMapping
    public void setBlockChainNodeIp(String nodeIp) throws ExecutionException, InterruptedException {
        peer2PeerService.connectToChainNode(nodeIp);
    }
}
