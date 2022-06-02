package com.noway.cardPrintRequestDemo.framework.entity.activityLog;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Document(indexName = "cardprintrequestlog")
public class ActivityLogElas {

    @Id
    private String id;

    private String issuedDateAndTime;

    private String functionName;

    private String username;

    private String cardNumber;

    private String applicationType;

}
