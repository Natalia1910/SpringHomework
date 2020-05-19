package com.lits.SpringHomework.service;

import com.lits.SpringHomework.dto.PermissionDto;

import java.util.List;

public interface PermissionService {
    Long create(PermissionDto permissionDto);

    PermissionDto findPermission(Long permissionId);

    List<PermissionDto> findAllPermissions();

    PermissionDto update(PermissionDto permissionDto);

    void deletePermission(Long permissionId);
}
