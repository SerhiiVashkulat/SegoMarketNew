package com.example.segomarketnew.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum OrderStatus {
    NEW,
    @JsonValue
    APPROVED, CANCELED,
    @JsonProperty("PAID")
    PAID, CLOSED;

}
