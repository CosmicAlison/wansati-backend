package com.wansati.dto;

import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class SwipeRequest {
    private Long swiperId;
    private Long swipedId;
    private boolean liked;
}

