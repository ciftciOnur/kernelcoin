/*
 * Copyright (c) 2012-
 * Vodafone Teknoloji Hizmetleri A.S., Istanbul, Turkey
 *
 * All rights reserved. This Software or any portion of it can not be translated,
 * distributed, sold, adapted, arranged, used, copied, modified, de-compiled,
 * reverse assembled or otherwise reverse engineered, disassembled, replaced or made
 * additions to and to be reproduced in whole or in part, in any way, manner or form.
 */
package tr.edu.yeditepe.kernelcoin.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import tr.edu.yeditepe.kernelcoin.domain.model.StompSessions.StompSessions;
import tr.edu.yeditepe.kernelcoin.domain.model.blockchain.BlockChain;
import tr.edu.yeditepe.kernelcoin.domain.model.kernelblock.KernelBlock;
import tr.edu.yeditepe.kernelcoin.infrastructure.config.CustomStompSessionHandler;
import tr.edu.yeditepe.kernelcoin.interfaces.dto.ConsentBlockDto;
import tr.edu.yeditepe.kernelcoin.service.ConsentService;
import tr.edu.yeditepe.kernelcoin.service.MinerService;

@Service
@RequiredArgsConstructor
public class ConsentServiceImpl implements ConsentService {


    private Logger logger = LogManager.getLogger(CustomStompSessionHandler.class);


    private final BlockChain blockChain;
    private final StompSessions sessions;
    private final MinerService miner;

    @Override
    public void checkKernelBlock(ConsentBlockDto consentBlock){
        KernelBlock lastBlock=null;
        if(blockChain.getKernelBlocks().size()!=0)
            lastBlock = blockChain.getKernelBlocks()
                .get(blockChain.getKernelBlocks().size()-1);
        if(blockChain.getKernelBlocks().size()==0 || consentBlock.getOrder()>lastBlock.getOrder())
            sessions.getSessions().get(0).send("/app/consent-response", consentBlock);
        logger.info("Consent is sent");
    }

    @Override
    public void checkIfConsensusIsOk(ConsentBlockDto consentBlock){
        KernelBlock lastBlock=null;
        if(blockChain.getKernelBlocks().size()!=0) {
            lastBlock = blockChain.getKernelBlocks()
                    .get(blockChain.getKernelBlocks().size() - 1);
            if(lastBlock.getOrder()+1==consentBlock.getOrder()&&consentBlock.getNumberOfConsents()>0){
                lastBlock = new KernelBlock(consentBlock);
                blockChain.getKernelBlocks().add(lastBlock);
            }
        }
        if(blockChain.getKernelBlocks().size()==0&&consentBlock.getNumberOfConsents()>0){
            lastBlock = new KernelBlock(consentBlock);
            blockChain.getKernelBlocks().add(lastBlock);
        }


    }

    @Override
    public void doConsentMine(ConsentBlockDto consentBlockDto){
        miner.doMine(consentBlockDto);
    }
}
