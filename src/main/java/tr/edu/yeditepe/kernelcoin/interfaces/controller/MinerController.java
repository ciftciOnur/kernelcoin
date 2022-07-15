package tr.edu.yeditepe.kernelcoin.interfaces.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.edu.yeditepe.kernelcoin.interfaces.dto.StatusDto;
import tr.edu.yeditepe.kernelcoin.service.MinerService;

import java.util.List;


@RestController
@RequestMapping("/api/miner")
@RequiredArgsConstructor
public class MinerController {

    private final MinerService minerService;

    @GetMapping
    public List<StatusDto> getBlockChainStatus() {
        return minerService.status();
    }
}
