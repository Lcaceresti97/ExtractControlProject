package com.sti.extractcontrolmodule.model.mapper;

import com.sti.extractcontrolmodule.dto.MainTableDto;
import com.sti.extractcontrolmodule.model.MainTableModel;
import com.sti.extractcontrolmodule.service.MainTableService;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = MainTableService.class)
public interface MainTableMapper {

    MainTableModel dtoToMainTableModel(MainTableDto mainTableDto);

    MainTableDto mainTableModelToDto(MainTableModel mainTableModel);

    List<MainTableModel> dtoToMainTableModel(List<MainTableModel> mainTableModels);

    List<MainTableDto> mainTableModelToDto(List<MainTableModel> mainTableModels);
}
