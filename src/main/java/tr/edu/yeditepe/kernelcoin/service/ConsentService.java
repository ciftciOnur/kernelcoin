package tr.edu.yeditepe.kernelcoin.service;

import tr.edu.yeditepe.kernelcoin.domain.model.kernelblock.KernelBlock;
import tr.edu.yeditepe.kernelcoin.interfaces.dto.ConsentBlockDto;

public interface ConsentService {
    void checkKernelBlock(ConsentBlockDto consentBlock);

    void checkIfConsensusIsOk(ConsentBlockDto consentBlock);

    void doConsentMine(ConsentBlockDto consentBlockDto);
}
