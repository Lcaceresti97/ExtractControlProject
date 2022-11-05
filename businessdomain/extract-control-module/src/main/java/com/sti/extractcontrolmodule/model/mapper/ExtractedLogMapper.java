package com.sti.extractcontrolmodule.model.mapper;

import com.sti.extractcontrolmodule.dto.ExtractedLogDto;
import com.sti.extractcontrolmodule.model.ExtractedLogModel;
import com.sti.extractcontrolmodule.service.ExtractedLogService;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ExtractedLogService.class)
public interface ExtractedLogMapper {

    ExtractedLogModel dtoToExtractedLogModel(ExtractedLogDto extractedLogDto);

    ExtractedLogDto extractedLogModelToDto(ExtractedLogModel extractedLogModel);

    List<ExtractedLogModel> dtoToExtractedLogModel(List<ExtractedLogDto> extractedLogDto);

    List<ExtractedLogDto> extractedLogModelToDto(List<ExtractedLogModel> extractedLogModel);
}
