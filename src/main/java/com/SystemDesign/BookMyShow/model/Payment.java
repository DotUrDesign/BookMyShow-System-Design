package com.SystemDesign.BookMyShow.model;

import com.SystemDesign.BookMyShow.model.enums.PaymentMode;
import com.SystemDesign.BookMyShow.model.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment extends BaseModel{
    private Long amount;

    @Enumerated(EnumType.ORDINAL)
    private PaymentMode paymentMode;

    private String referenceNo;

    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus paymentStatus;

}
