package com.naidiuk.onlineshop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.naidiuk.onlineshop.util.LocalDateAdapter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@XmlRootElement(name = "currency")
@XmlAccessorType(XmlAccessType.FIELD)
public class NbuRateDto {
    @JsonProperty(value = "r030")
    @XmlElement(name = "r030")
    private int currencyNumericCode;
    @JsonProperty(value = "txt")
    @XmlElement(name = "txt")
    private String currencyLocalName;
    @JsonProperty(value = "rate")
    @XmlElement(name = "rate")
    private BigDecimal currencyRate;
    @JsonProperty(value = "cc")
    @XmlElement(name = "cc")
    private String currencyCode;
    @JsonFormat(pattern = "dd.MM.yyyy")
    @JsonProperty(value = "exchangedate")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlElement(name = "exchangedate")
    private LocalDate exchangeDate;
}
