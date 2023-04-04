package com.alkemy.wallet.mapping;

import com.alkemy.wallet.dto.requestDto.UserModelRequestDTO;
import com.alkemy.wallet.dto.responseDto.UserModelResponseDTO;
import com.alkemy.wallet.model.UserModel;

import java.util.List;
import java.util.stream.Collectors;

public abstract class UserModelMapping {
    public static UserModelResponseDTO convertEntityToDTO(UserModel userModel) {
        return UserModelResponseDTO.builder()
                .userId(userModel.getUserId())
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .email(userModel.getEmail())
                .password(userModel.getPassword())
                .role(userModel.getRole().getName().toString())
                .creationDate(userModel.getCreationDate())
                .updateDate(userModel.getUpdateDate())
                .softDelete(userModel.getSoftDelete())
                .accountsList(null)
                .build();
    }

    public static UserModel convertDtoToEntity(UserModelRequestDTO userModelRequestDTO) {
        return UserModel.builder()
                .firstName(userModelRequestDTO.getFirstName())
                .lastName(userModelRequestDTO.getLastName())
                .email(userModelRequestDTO.getEmail())
                .password(userModelRequestDTO.getPassword())
                .creationDate(userModelRequestDTO.getCreationDate())
                .updateDate(userModelRequestDTO.getUpdateDate())
                .build();
    }

    public static List<UserModelResponseDTO> convertEntityListToDtoList(List<UserModel> userModelList) {
        return userModelList.stream().map(u -> convertEntityToDTO(u)).collect(Collectors.toList());

    }

    public static List<UserModel> convertDTOListToEntityList(List<UserModelRequestDTO> userModelList) {
        return userModelList.stream().map(u -> convertDtoToEntity(u)).collect(Collectors.toList());

    }

}
