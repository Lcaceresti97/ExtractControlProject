package com.sti.securitymodule.service.implementation;

import com.sti.securitymodule.dto.RolDto;
import com.sti.securitymodule.exception.RolNotFoundException;
import com.sti.securitymodule.model.Rol;
import com.sti.securitymodule.model.mappers.RolMapper;
import com.sti.securitymodule.model.status.ModelStatus;
import com.sti.securitymodule.repository.RolRepository;
import com.sti.securitymodule.service.RolService;
import com.sti.securitymodule.utils.SortingPagingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for Rol entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    private final RolMapper rolMapper;

    private final SortingPagingUtils sortingPagingUtils;

    @Override
    public RolDto saveRol(RolDto rolDto) {
        Rol rol = Rol.buildFromDtoRol(this.rolMapper.dtoToRol(rolDto));
        this.rolRepository.save(rol);
        return rolMapper.rolToDto(rol);
    }

    @Override
    public RolDto findRolById(String rolId) throws RolNotFoundException {
        Rol rol = rolRepository.findById(rolId).orElseThrow(() -> RolNotFoundException.buildRolNotFoundExceptionForId(rolId));
        return rolMapper.rolToDto(isActiveRol(rol,"rolId", rolId));
    }

    @Override
    public Page<RolDto> findPaginatedSortedRol(int page, int size, String[] sort) {
        List<Sort.Order> orders = sortingPagingUtils.getSortOrders(sort);
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        List<RolDto> rolDtos;
        rolDtos = rolMapper
                .rolToDto(rolRepository.findAll(pageable).toList());

        return new PageImpl<>(rolDtos);
    }

    @Override
    public RolDto updateRol(RolDto rolDto) {
        Rol rol = this.rolMapper.dtoToRol(rolDto);
        this.rolRepository.save(rol);
        return rolMapper.rolToDto(rol);
    }

    @Override
    public void deleteRol(String rolId) {
        Rol rol = rolMapper.dtoToRol(findRolById(rolId));
        rol.setRolStatus(ModelStatus.INACTIVE);
        rolRepository.save(rol);
    }

    /**
     * Return Rol if status code is ACTIVE.
     * @param rol Rol
     * @param queryField String
     * @param queryFieldValue String
     * @return Rol
     * @throws RolNotFoundException ex
     */
    private Rol isActiveRol(Rol rol, String queryField, String queryFieldValue){
        if(rol.getRolStatus().getStatusCode() == 0){
            return rol;
        }
        throw RolNotFoundException
                .buildRolNotFoundExceptionForField(queryField, queryFieldValue);
    }
}
