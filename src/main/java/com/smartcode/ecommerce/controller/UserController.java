package com.smartcode.ecommerce.controller;

import com.smartcode.ecommerce.model.dto.UserCreateRequest;
import com.smartcode.ecommerce.model.dto.UserDto;
import com.smartcode.ecommerce.model.dto.filter.UserFilterModel;
import com.smartcode.ecommerce.model.entity.UserEntity;
import com.smartcode.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserCreateRequest request) {
        return ResponseEntity.ok(userService.create(request));
    }

    @PostMapping("/filter")
    public ResponseEntity<List<UserDto>> getAll(@RequestBody UserFilterModel userFilterModel) {
        return ResponseEntity.ok(userService.getAll(userFilterModel));
    }

    @GetMapping("/{id}")
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


}
