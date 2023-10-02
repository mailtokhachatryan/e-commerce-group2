package com.smartcode.ecommerce.controller;

import com.smartcode.ecommerce.model.dto.UserDto;
import com.smartcode.ecommerce.model.dto.filter.UserFilterModel;
import com.smartcode.ecommerce.model.entity.UserEntity;
import com.smartcode.ecommerce.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/filter")
    @RolesAllowed("ADMIN")
    public ResponseEntity<List<UserDto>> getAll(@RequestBody UserFilterModel userFilterModel) {
        return ResponseEntity.ok(userService.getAll(userFilterModel));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<UserDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Integer id, @RequestBody UserEntity userEntity) {
        return new ResponseEntity<>(userService.update(id, userEntity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
        ResponseEntity.ok().build();
    }

    @DeleteMapping
    public void deleteAll() {
        userService.delete(null);
        ResponseEntity.ok().build();
    }

}
