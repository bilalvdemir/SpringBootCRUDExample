package com.bilald.crudsample.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "User Management API",
                version = "1.0.0",
                description = "RESTful API for user CRUD operations with caching, monitoring, and resilience",
                contact = @Contact(
                        name = "Bilal Demir",
                        email = "bilal@example.com"
                ),
                license = @License(
                        name = "MIT License",
                        url = "https://opensource.org/licenses/MIT"
                )
        ),
        servers = {
                @Server(
                        description = "Local Development Environment",
                        url = "http://localhost:8090"
                ),
                @Server(
                        description = "Production Environment",
                        url = "https://api.production.com"
                )
        }
)
public class OpenApiConfig {
}
