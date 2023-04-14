package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.Exception.InvalidResourceException;
import com.alkemy.wallet.Exception.UserNotFoundException;
import com.alkemy.wallet.model.UserEntity;
import com.alkemy.wallet.dto.requestDto.UserEntityRequestDTO;
import com.alkemy.wallet.dto.responseDto.UserEntityResponseDTO;
import com.alkemy.wallet.mapping.UserEntityMapping;
import com.alkemy.wallet.model.AccountEntity;
import com.alkemy.wallet.model.Currency;
import com.alkemy.wallet.repository.UserModelRepository;
import com.alkemy.wallet.service.service.RoleService;
import com.alkemy.wallet.service.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;



import java.util.ArrayList;
import java.util.List;

@Service
public class UserEntityServiceImpl implements UserEntityService {
    @Autowired
    private UserModelRepository userModelRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    private UserEntityMapping userEntityMapping;

    @Override
    public ResponseEntity<String> softDelete(Long userId) {
        userModelRepository.deleteById(userId);
        return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado exitosamente");
    }


    @Override
    public ResponseEntity<List<UserEntityResponseDTO>> getUserList(){
        List<UserEntity> usersEntityList = userModelRepository.findAll();
        List<UserEntityResponseDTO> userEntitiesDTOs = new ArrayList<>();
        for(UserEntity ue : usersEntityList){
            UserEntityResponseDTO uer = userEntityMapping.convertEntityToDTO(ue);
            userEntitiesDTOs.add(uer);
        }
      return ResponseEntity.status(HttpStatus.OK).body(userEntitiesDTOs);
    }

    @Override
    public Page<UserEntityResponseDTO> getUserList(Pageable pageable) throws Exception {
        try{
            Page<UserEntity> userPage = userModelRepository.findAll(pageable);
            List<UserEntityResponseDTO> usersList = UserEntityMapping.convertEntityListToDtoList(userPage.getContent());
            Page<UserEntityResponseDTO> usersPage = new PageImpl<UserEntityResponseDTO>(usersList,pageable,userPage.getTotalElements());
            return usersPage;
        }catch (Exception e){
            throw new Exception("Usuarios no encontrados");
        }
    }


    @Override
    public UserEntityResponseDTO getUserById(Long userId) {
        UserEntity newUser = userModelRepository.findById(userId).orElseThrow(()-> new InvalidResourceException("El usuario enviado en la peticion no existe"));

        return UserEntityMapping.convertEntityToDTO(newUser);
    }

    public ResponseEntity<UserEntityResponseDTO> createUser(UserEntityRequestDTO userEntityRequestDTO) {
        UserEntity newUser = (UserEntityMapping.convertDtoToEntity(userEntityRequestDTO));

        newUser.setRoleEntity(roleService.getRoleByName(userEntityRequestDTO.getRole()));

        setAccountToUser(newUser);

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        return ResponseEntity.status(HttpStatus.CREATED).body(UserEntityMapping.convertEntityToDTO(userModelRepository.save(newUser)));
    }

    @Override
    public UserEntity getUserEntityById(Long userId) {return userModelRepository.findById(userId).get();
    }

    @Override
    public UserEntityResponseDTO updateUser(Long userId, UserEntityRequestDTO dto) throws UserNotFoundException {
        UserEntity foundUser = userModelRepository.findById(userId).orElse(null);
        if(foundUser == null){
            throw new UserNotFoundException(userId);
        }
        UserEntity refreshedUser = userEntityMapping.userRefreshValues(foundUser, dto);
        UserEntity savedUser = userModelRepository.save(refreshedUser);
        UserEntityResponseDTO result = userEntityMapping.convertEntityToDTO(savedUser);
        return result;
    }

    private void setAccountToUser(UserEntity user) {
        AccountEntity USDAcount = new AccountEntity();
        AccountEntity ARSAcount = new AccountEntity();

        USDAcount.setCurrency(Currency.USD);
        ARSAcount.setCurrency(Currency.ARS);

        user.getAccountsList().add(USDAcount);
        user.getAccountsList().add(ARSAcount);

        USDAcount.setBalance(0d);
        ARSAcount.setBalance(0d);

        ARSAcount.setTransactionLimit(0d);
        USDAcount.setTransactionLimit(0d);

        ARSAcount.setUser(user);
        USDAcount.setUser(user);
    }
}
