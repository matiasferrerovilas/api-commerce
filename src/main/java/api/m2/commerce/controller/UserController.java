package api.m2.commerce.controller;

import api.m2.commerce.records.users.UserRecord;
import api.m2.commerce.services.user.UserService;
import api.m2.commerce.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/me")
    public ResponseEntity<UserRecord> getCurrentUser() {
        var user = userService.getCurrentUser();
        if (user == null) {
            return ResponseEntity.ok(new UserRecord(null, null, null, null, null));
        }
        return ResponseEntity.ok(userMapper.toRecord(user));
    }
}
