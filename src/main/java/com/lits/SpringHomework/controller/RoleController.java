package com.lits.SpringHomework.controller;

import com.lits.SpringHomework.dto.PermissionDto;
import com.lits.SpringHomework.dto.RoleDto;
import com.lits.SpringHomework.service.PermissionService;
import com.lits.SpringHomework.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/app/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @PostMapping("/role")
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody RoleDto roleDto) {
        return roleService.create(roleDto);
    }

    @GetMapping("/role/{id}")
    public List<RoleDto> findRolesWithIds(@PathVariable List<Long> id) {
        return roleService.findRolesWithIds(id);
    }

    @GetMapping("/role/all")
    public List<RoleDto> findAll() {
        return roleService.findAll();
    }


    @PutMapping("/role/{id}")
    public RoleDto addPermissionsToRole(@PathVariable Long id, @RequestBody List<String> permissions) {
        RoleDto roleDto = roleService.findRoleById(id);
        List<PermissionDto> permissionDto = permissions.stream().map(p-> new PermissionDto(p, id))
                .map(permissionService::update).collect(toList());
        roleDto.setPermissions(permissionDto);
        return roleService.update(roleDto);
    }

    @DeleteMapping("/role/delete/{id}")
    public void delete(@PathVariable Long id) {
        roleService.delete(id);
    }
}
