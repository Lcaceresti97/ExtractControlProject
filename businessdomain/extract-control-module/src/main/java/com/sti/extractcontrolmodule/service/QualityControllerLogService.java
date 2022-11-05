package com.sti.extractcontrolmodule.service;
import com.sti.extractcontrolmodule.dto.ExtractedLogDto;
import com.sti.extractcontrolmodule.dto.QualityControllerLogDto;
import com.sti.extractcontrolmodule.exception.QualityControllerLogException;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

/**
 * Interface for QualityControllerLog entity crud operations.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
public interface QualityControllerLogService {

    /**
     * Saves given QualityControllerDto into DB.
     * @param qualityControllerLogDto MainTable
     */
    QualityControllerLogDto saveQualityControllerLog(QualityControllerLogDto qualityControllerLogDto);

    /**
     * Find a MainTable by its ID.
     * @param qualityControllerLogId String
     * @return QualityControllerLogDto
     * @throws QualityControllerLogException
     * QualityControllerLogNotException when no QualityControllerLog is found by ID
     */
    QualityControllerLogDto findQualityControllerLogById(String qualityControllerLogId) throws QualityControllerLogException;

    /**
     * Return a page of sorted Page<QualityControllerLogDto>.
     * @param page Page number to query by.
     * @param size Page size to query by.
     * @param sort Extra sort params to sort by.
     * @return PageResponseDto.
     */
    Page<QualityControllerLogDto> findPaginatedSortedQualityControllerLog(int page, int size, String[] sort);

    /**
     *
     * @param qualityControllerLogDto
     * @return QualityControllerLogDto
     */
    QualityControllerLogDto updateQualityControllerLog(QualityControllerLogDto qualityControllerLogDto);

    /**
     * Delete QualityControllerLog by its ID.
     * @param qualityControllerLogId
     */
    void deleteQualityControllerLog(String qualityControllerLogId);

    /**
     * If exists attribute in Model
     * @param attribute
     * @return boolean
     */
    boolean existsAttribute(String attribute);

    /**
     *
     * @param userNameId String
     * @param fecha Date
     * @return List<QualityControlleLogDto>
     */
    List<QualityControllerLogDto> findQualityControllerLogByStatusAndDateQualityControllerAndUserNameId(final String userNameId, final Date fecha);
}
