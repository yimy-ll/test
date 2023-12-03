package com.example.backend.domain.dto;

import lombok.Builder;
import lombok.Getter;

//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Builder
public class MessageDTO {
    private String message;

    public MessageDTO(String message) {
        this.message = message;
    }
}
