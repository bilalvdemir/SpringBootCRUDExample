package com.bilald.crudsample.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String id;
    private String username;
    private String email;
    private String name;
    private String lastname;
    private Instant createdAt;
    private Instant updatedAt;

    // NO PASSWORD - Security first!
}
