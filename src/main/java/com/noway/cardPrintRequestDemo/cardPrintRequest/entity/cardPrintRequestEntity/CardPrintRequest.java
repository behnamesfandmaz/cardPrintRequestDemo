package com.noway.cardPrintRequestDemo.cardPrintRequest.entity.cardPrintRequestEntity;

import com.noway.cardPrintRequestDemo.cardPrintRequest.dto.CardPrintRequestDTO;
import com.noway.cardPrintRequestDemo.cardPrintRequest.entity.CardPrintRequestMapper;
import com.noway.cardPrintRequestDemo.framework.entity.user.User;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Entity(name = "t_cardPrintRequest")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CardPrintRequest extends CardPrintRequestMapper implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CardPrintRequestId id;

    @Column(name = "c_personnelcode", nullable = false)
    private String personnelCode;

    @Column(name = "c_cardpan")
    private String cardPan;

    @Column(name = "c_issuedDate")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date issuedDate;

    @Column(name = "c_PUBLISHING_DATE", updatable = false, nullable = false)
    private LocalDateTime publishingDate;

    @Version
    @Column(name = "c_version")
    private Integer version;

    @Column(name = "c_enabled", nullable = false)
    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "user_c_id")
    private User user;

    @Override
    public CardPrintRequest convertDataTransferObjectToEntity(CardPrintRequestDTO dto) {
        CardPrintRequestId cardPrintRequestId = new CardPrintRequestId();
        cardPrintRequestId.setIpAddress(dto.getIpAddress());
        cardPrintRequestId.setBranchCode(dto.getBranchCode());
        this.setId(cardPrintRequestId);
        this.setPublishingDate(dto.getPublishingDate() != null ? dto.getPublishingDate() : LocalDateTime.now());
        this.setEnabled(dto.getEnabled() != null ? dto.getEnabled() : Boolean.TRUE);
        this.setCardPan(dto.getCardPan());
        this.setIssuedDate(dto.getIssuedDate() != null ? dto.getIssuedDate() : new Date());
        this.setVersion(dto.getVersion());
        return this;
    }

    @Override
    public CardPrintRequestDTO getDataTransferObject() {
        CardPrintRequestDTO cardPrintRequestDTO = new CardPrintRequestDTO();
        cardPrintRequestDTO.setIpAddress(this.getId().getIpAddress());
        cardPrintRequestDTO.setBranchCode(this.getId().getBranchCode());
        cardPrintRequestDTO.setEnabled(this.getEnabled());
        cardPrintRequestDTO.setPublishingDate(this.getPublishingDate());
        cardPrintRequestDTO.setPersonnelCode(this.getPersonnelCode());
        cardPrintRequestDTO.setIssuedDate(this.getIssuedDate());
        cardPrintRequestDTO.setCardPan(this.getCardPan());
        cardPrintRequestDTO.setVersion(this.getVersion());
        User user = this.getUser();
        cardPrintRequestDTO.setUsername(user.getUsername());
        cardPrintRequestDTO.setUserid(user.getId());
        return cardPrintRequestDTO;
    }
}
