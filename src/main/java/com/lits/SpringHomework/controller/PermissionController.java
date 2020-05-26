package com.lits.SpringHomework.controller;

import com.lits.SpringHomework.annotation.IsAdmin;
import com.lits.SpringHomework.dto.PermissionDto;
import com.lits.SpringHomework.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/permissions")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @PostMapping("/permission")
    @IsAdmin
    @PreAuthorize("hasAuthority('CREATE_PERMISSIONS')")
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody PermissionDto permissionDto) {
        return permissionService.create(permissionDto);
    }

    @GetMapping("/permission/{id}")
    @PreAuthorize("hasAuthority('READ_PERMISSIONS')")
    public PermissionDto findPermission(@PathVariable Long id) {
        return permissionService.findPermission(id);
    }

    @GetMapping("/permission/all")
    @PreAuthorize("hasAuthority('READ_PERMISSIONS')")
    public List<PermissionDto> findAllPermissions() {
        return permissionService.findAllPermissions();
    }


    @DeleteMapping("/permission/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE_PERMISSIONS')")
    public void deletePermission(@PathVariable Long id) {
        permissionService.deletePermission(id);
    }
}
