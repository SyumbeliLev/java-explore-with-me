package ru.practicum.ewm.service;


import ru.practicum.ewm.dto.compilation.CompilationDto;
import ru.practicum.ewm.dto.compilation.NewCompilationDto;
import ru.practicum.ewm.dto.compilation.UpdateCompilationDto;

import java.util.List;

public interface CompilationService {
    CompilationDto addCompilation(NewCompilationDto compilationDto);

    CompilationDto updateCompilation(long compId, UpdateCompilationDto update);

    void deleteCompilation(long compId);

    List<CompilationDto> getCompilations(Boolean pinned, int from, int size);

    CompilationDto findByIdCompilation(long compId);
}