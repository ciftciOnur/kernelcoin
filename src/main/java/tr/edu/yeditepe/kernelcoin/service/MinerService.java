package tr.edu.yeditepe.kernelcoin.service;

import org.springframework.scheduling.annotation.Scheduled;
import tr.edu.yeditepe.kernelcoin.interfaces.dto.StatusDto;

import java.util.List;

public interface MinerService {
    @Scheduled(fixedDelayString = "${jobs.fixedDelay.tenminutes}")
    void scheduledMiner();

    void mineKernelBlock(String data);

    List<StatusDto> status();
}
