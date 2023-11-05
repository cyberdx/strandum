package com.strandum.models;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductMessageDto implements Serializable {
    private String name;
    private String data;
}
