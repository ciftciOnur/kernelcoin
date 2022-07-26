package tr.edu.yeditepe.kernelcoin.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tr.edu.yeditepe.kernelcoin.domain.model.StompSessions.StompSessions;
import tr.edu.yeditepe.kernelcoin.domain.model.blockchain.BlockChain;
import tr.edu.yeditepe.kernelcoin.interfaces.client.SocketClient;
import tr.edu.yeditepe.kernelcoin.service.Peer2PeerService;

import java.util.concurrent.ExecutionException;

@Service
@Slf4j
@RequiredArgsConstructor
public class Peer2PeerServiceImpl implements Peer2PeerService {

    private final SocketClient client;
    private final StompSessions sessions;
    private final BlockChain chain;

    @Override
    public void connectToChainNode(String ip) throws ExecutionException, InterruptedException {
        sessions.setNodeIp(ip);
        client.WebSocketClient();
        chain.setMining(true);
    }

}
