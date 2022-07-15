package tr.edu.yeditepe.kernelcoin.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tr.edu.yeditepe.kernelcoin.domain.model.blockchain.BlockChain;
import tr.edu.yeditepe.kernelcoin.domain.model.kernelblock.KernelBlock;
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

    @Override
    @Scheduled(fixedDelay = 1000)
    public void scheduledMiner(){
        log.info("The miner is started");
        mineKernelBlock("test");

    }

    @Override
    public void mineKernelBlock(String data){
        if(blockChain.getKernelBlocks().size()==0){
            createGenesisBlock();
        }
        KernelBlock currentBlock = blockChain.getKernelBlocks().get(blockChain.getKernelBlocks().size()-1);
        KernelBlock nextBlock = new KernelBlock(data,currentBlock.getHash(),new Date().getTime());
        blockChain.getKernelBlocks().add(nextBlock);
    }

    @Override
    public List<StatusDto> status(){
        return blockChain.getKernelBlocks().stream().map(m -> StatusDto.builder()
                .hash(m.getHash())
                .previousHash(m.getPreviousHash())
                .data(m.getData())
                .timeStamp(m.getTimeStamp())
                .build()).collect(Collectors.toList());
    }

    private void createGenesisBlock(){
        KernelBlock genesisBlock = new KernelBlock(
                "The is a Genesis Block.",
                "The is a Genesis Block.",
                new Date().getTime());
        genesisBlock.mineBlock(4);
        blockChain.getKernelBlocks().add(genesisBlock);
    }

}
