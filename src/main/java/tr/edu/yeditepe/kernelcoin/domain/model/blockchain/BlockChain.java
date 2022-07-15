package tr.edu.yeditepe.kernelcoin.domain.model.blockchain;

import lombok.Getter;
import lombok.Setter;
import tr.edu.yeditepe.kernelcoin.domain.model.kernelblock.KernelBlock;

import java.util.ArrayList;

@Getter
@Setter
public class BlockChain {

    private ArrayList<KernelBlock> kernelBlocks;

    public BlockChain(){
        kernelBlocks = new ArrayList<>();
    }
}
