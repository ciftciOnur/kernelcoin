package tr.edu.yeditepe.kernelcoin.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsentBlockDto {
    private String hash;
    private String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;
    private long order;
}
