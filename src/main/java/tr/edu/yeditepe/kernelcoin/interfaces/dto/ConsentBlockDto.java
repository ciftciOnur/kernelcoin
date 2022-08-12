package tr.edu.yeditepe.kernelcoin.interfaces.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsentBlockDto {
    private String hash;
    private String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;
    private long order;
    private UUID minerId;
    private int numberOfConsents;
    private int diffuculty;


    public String toString(){
        return "order: "+order+ "\ntimestamp: "+ timeStamp + "\npreviousHash: " + previousHash+
                "\nhash: "+hash+ "\ndata: "+data+"\nminerID: "+minerId;
    }
}
