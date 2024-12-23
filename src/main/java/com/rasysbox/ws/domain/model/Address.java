package com.rasysbox.ws.domain.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Address {
    private String postCode;
    private String city;
}
