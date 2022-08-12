package tr.edu.yeditepe.kernelcoin.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tr.edu.yeditepe.kernelcoin.domain.model.StompSessions.StompSessions;
import tr.edu.yeditepe.kernelcoin.domain.model.blockchain.BlockChain;
import tr.edu.yeditepe.kernelcoin.domain.model.kernelblock.KernelBlock;
import tr.edu.yeditepe.kernelcoin.interfaces.dto.ConsentBlockDto;
import tr.edu.yeditepe.kernelcoin.interfaces.dto.StatusDto;
import tr.edu.yeditepe.kernelcoin.service.MinerService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MinerServiceImpl implements MinerService {

    private final BlockChain blockChain;
    private final StompSessions sessions;

    @Override
    public void doMine(ConsentBlockDto consentBlockDto){
        blockChain.setMining(true);
        if(checkMinerStatus()) {
            log.info("The miner is started");
            KernelBlock consentBlock = mineKernelBlock(consentBlockDto);
            ConsentBlockDto consentDto = ConsentBlockDto.builder()
                    .minerId(blockChain.getMinerId())
                    .hash(consentBlock.getHash())
                    .data(consentBlock.getData())
                    .nonce(consentBlock.getNonce())
                    .order(consentBlock.getOrder())
                    .previousHash(consentBlock.getPreviousHash())
                    .timeStamp(consentBlock.getTimeStamp())
                    .build();
            blockChain.setMining(false);
            sessions.getSessions().get(0).send("/app/consent-request", consentDto);
        }
        else
            log.info("No node available");
    }

    @Override
    public KernelBlock mineKernelBlock(ConsentBlockDto consentBlockDto)  {
        if(blockChain.getKernelBlocks().size()==0){
            return createGenesisBlock(consentBlockDto);
        }
        KernelBlock currentBlock = blockChain.getKernelBlocks().get(blockChain.getKernelBlocks().size()-1);
        KernelBlock nextBlock = new KernelBlock(consentBlockDto.getData(),currentBlock.getHash(),
                new Date().getTime(),currentBlock.getOrder()+1);
        return nextBlock;
    }

    @Override
    public List<StatusDto> status(){
        return blockChain.getKernelBlocks().stream().map(m -> StatusDto.builder()
                .order(m.getOrder())
                .hash(m.getHash())
                .previousHash(m.getPreviousHash())
                .data(m.getData())
                .timeStamp(m.getTimeStamp())
                .build()).collect(Collectors.toList());
    }

    private KernelBlock createGenesisBlock(ConsentBlockDto consentBlockDto){
        KernelBlock genesisBlock = new KernelBlock(
                "The is a Genesis Block.",
                "The is a Genesis Block.",
                new Date().getTime(),0);
        genesisBlock.mineBlock(consentBlockDto.getDiffuculty());
        return genesisBlock;
    }

    private boolean checkMinerStatus(){
        return blockChain.isMining();
    }

}
