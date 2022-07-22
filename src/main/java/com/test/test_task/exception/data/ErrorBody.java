package com.test.test_task.exception.data;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorBody{
    private String error;
    private int status;
    private String message;
}