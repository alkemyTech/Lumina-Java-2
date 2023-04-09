package com.alkemy.wallet.mapping;

import com.alkemy.wallet.dto.requestDto.UserEntityRequestDTO;
import com.alkemy.wallet.dto.responseDto.UserEntityResponseDTO;
import com.alkemy.wallet.model.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public abstract class UserEntityMapping {
    public static UserEntityResponseDTO convertEntityToDTO(UserEntity userEntity) {
        return UserEntityResponseDTO.builder()
                .userId(userEntity.getUserId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .role(userEntity.getRoleEntity().getName().name())
                .creationDate(userEntity.getCreationDate())
                .updateDate(userEntity.getUpdateDate())
                .softDelete(userEntity.getSoftDelete())
                .accountsList(null)
                .build();
    }

    public static UserEntity convertDtoToEntity(UserEntityRequestDTO userEntityRequestDTO) {
        return UserEntity.builder()
                .firstName(userEntityRequestDTO.getFirstName())
                .lastName(userEntityRequestDTO.getLastName())
                .email(userEntityRequestDTO.getEmail())
                .password(userEntityRequestDTO.getPassword())
                .creationDate(userEntityRequestDTO.getCreationDate())
                .updateDate(userEntityRequestDTO.getUpdateDate())
                .build();
    }

    public static List<UserEntityResponseDTO> convertEntityListToDtoList(List<UserEntity> userEntityList) {
        return userEntityList.stream().map(u -> convertEntityToDTO(u)).collect(Collectors.toList());

    }

    public static List<UserEntity> convertDTOListToEntityList(List<UserEntityRequestDTO> userModelList) {
        return userModelList.stream().map(u -> convertDtoToEntity(u)).collect(Collectors.toList());

    }

}
