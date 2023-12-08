package ru.practicum.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class ViewStatsDto {

    private String app;
    private String uri;
    private Long hits;
}

