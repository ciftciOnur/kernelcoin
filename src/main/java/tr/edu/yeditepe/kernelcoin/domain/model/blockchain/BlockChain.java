package tr.edu.yeditepe.kernelcoin.domain.model.blockchain;

import lombok.Getter;
import lombok.Setter;
import tr.edu.yeditepe.kernelcoin.domain.model.kernelblock.KernelBlock;

import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
public class BlockChain {

    private ArrayList<KernelBlock> kernelBlocks;
    private boolean isMining;
    private UUID minerId;

    public BlockChain(){
        kernelBlocks = new ArrayList<>();
        isMining = false;
        minerId = UUID.randomUUID();
    }
}
