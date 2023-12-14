package ru.practicum.ewm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class StatsDto {

    private String app;
    private String uri;
    private Long hits;
}

