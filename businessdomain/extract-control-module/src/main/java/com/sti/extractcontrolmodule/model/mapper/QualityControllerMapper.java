package com.sti.extractcontrolmodule.model.mapper;
import com.sti.extractcontrolmodule.dto.QualityControllerLogDto;
import com.sti.extractcontrolmodule.model.QualityControllerLogModel;
import com.sti.extractcontrolmodule.service.QualityControllerLogService;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = QualityControllerLogService.class)
public interface QualityControllerMapper {

    QualityControllerLogModel dtoToQualityControllerLogModel(QualityControllerLogDto qualityControllerLogDto);

    QualityControllerLogDto qualityControllerLogModelToDto(QualityControllerLogModel qualityControllerLogModel);

    List<QualityControllerLogModel> dtoToQualityControllerLogModel(List<QualityControllerLogDto> qualityControllerLogDtos);

    List<QualityControllerLogDto> qualityControllerLogModelToDto(List<QualityControllerLogModel> qualityControllerLogModels);
}
