package com.valtxcorresponsal.deposito_service.business.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.valtxcorresponsal.deposito_service.business.api.dtos.TransactionRequestDto;
import com.valtxcorresponsal.deposito_service.business.api.dtos.TransactionResponseDto;
import com.valtxcorresponsal.deposito_service.business.data.model.entities.TransactionEntity;

@Mapper(componentModel = "spring")
public interface TransactionMapper {


  TransactionResponseDto toDto(TransactionEntity transactionEntity);

    @Mapping(target = "id", ignore = true)
    TransactionEntity toEntity(TransactionRequestDto transactionDto);

}
