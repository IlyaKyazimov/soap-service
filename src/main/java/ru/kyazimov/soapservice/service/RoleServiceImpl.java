package ru.kyazimov.soapservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kyazimov.soapservice.entity.Role;
import ru.kyazimov.soapservice.repository.RoleRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleRepository repository;

    public RoleServiceImpl() {

    }

    @Autowired
    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role getRoleById(int id) { return repository.getReferenceById(id); }
}
