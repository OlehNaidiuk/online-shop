package com.naidiuk.onlineshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@XmlRootElement(name = "exchange")
@XmlAccessorType(XmlAccessType.FIELD)
public class NbuExchangeDto {
    @XmlElement(name = "currency")
    private List<NbuRateDto> currencies;
}
