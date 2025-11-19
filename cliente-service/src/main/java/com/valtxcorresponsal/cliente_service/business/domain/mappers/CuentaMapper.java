package com.valtxcorresponsal.cliente_service.business.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.valtxcorresponsal.cliente_service.business.api.dtos.CuentaResponseDto;
import com.valtxcorresponsal.cliente_service.business.data.model.entities.AccountEntity;

@Mapper(componentModel = "spring")
public interface CuentaMapper {

  @Mapping(source = "accountNumber", target = "accountNumber")
  CuentaResponseDto toDto(AccountEntity accountEntity);





}
