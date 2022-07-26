package tr.edu.yeditepe.kernelcoin.service;

import java.util.concurrent.ExecutionException;

public interface Peer2PeerService {

    void connectToChainNode(String ip) throws ExecutionException, InterruptedException;
}
