package com.noway.cardPrintRequestDemo.cardPrintRequest.entity.cardPrintRequestEntity;


import com.noway.cardPrintRequestDemo.cardPrintRequest.dto.CardPrintRequestIdDTO;
import lombok.*;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@AttributeOverrides({
        @AttributeOverride(
                name = "ipAddress",
                column = @Column(name ="c_ipaddress",nullable = false)
        ),
        @AttributeOverride(
                name = "branchCode",
                column = @Column(name = "c_branchcode",nullable = false)
        )
})
public class CardPrintRequestId implements Serializable {

    private String ipAddress;
    private String branchCode;


    public CardPrintRequestId convertDataTransferObjectToEntity(CardPrintRequestIdDTO dto) {
        this.setIpAddress(dto.getIpAddress());
        this.setBranchCode(dto.getBranchCode());
        return this;
    }
}
