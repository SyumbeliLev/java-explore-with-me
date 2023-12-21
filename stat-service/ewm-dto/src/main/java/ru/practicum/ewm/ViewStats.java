package ru.practicum.ewm;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@ToString
public class ViewStats {
    private String app;
    private String uri;
    private Long hits;
}

