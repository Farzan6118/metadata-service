package com.nesstiff.reference.metadataservice.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorDetailsResponseDto {
    private String message;
    private String className;
    private String methodName;
    private String lineNumber;
}
