package com.naidiuk.onlineshop.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public LocalDate unmarshal(String localDate) throws Exception {
        return LocalDate.from(formatter.parse(localDate));
    }

    @Override
    public String marshal(LocalDate localDate) throws Exception {
        return formatter.format(localDate);
    }
}
