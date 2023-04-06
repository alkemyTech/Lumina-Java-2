package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.model.RoleEntity;
import com.alkemy.wallet.repository.RoleRepository;
import com.alkemy.wallet.service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public RoleEntity getRoleByName(String nombreRole) {
        return roleRepository.getRoleByName(nombreRole);
    }
}
