package tr.edu.yeditepe.kernelcoin.service;

import org.springframework.scheduling.annotation.Scheduled;
import tr.edu.yeditepe.kernelcoin.domain.model.kernelblock.KernelBlock;
import tr.edu.yeditepe.kernelcoin.interfaces.dto.ConsentBlockDto;
import tr.edu.yeditepe.kernelcoin.interfaces.dto.StatusDto;

import java.util.List;

public interface MinerService {

    void doMine(ConsentBlockDto consentBlockDto);

    KernelBlock mineKernelBlock(ConsentBlockDto consentBlockDto);

    List<StatusDto> status();
}
