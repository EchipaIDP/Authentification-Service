package com.catalogapp.securityservicejwt.controller;

import com.catalogapp.securityservicejwt.controller.request.LoginRequest;
import com.catalogapp.securityservicejwt.controller.request.SignupRequest;
import com.catalogapp.securityservicejwt.controller.response.JwtResponse;
import com.catalogapp.securityservicejwt.jwt.JwtUtils;
import com.catalogapp.securityservicejwt.service.UserService;
import com.catalogapp.securityservicejwt.services.UserDetailsImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://bookservice.idproj.me/login";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(roles);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String requestBody = String.format("{\"token\":\"%s\", " +
                "\"type\": \"Bearer\", " +
                "\"id\": \"%s\", " +
                "\"username\": \"%s\"," +
                "\"email\": \"%s\"," +
                "\"roles\": %s }", jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), jsonString);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        System.out.println(response.getBody());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/register")
    public ResponseEntity<?> createBackOfficeUser(@RequestBody SignupRequest userDto) {
        var response = userService.saveUser(userDto);
        return ResponseEntity.ok(response);
    }

}
