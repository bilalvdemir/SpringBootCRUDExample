# Spring Boot CRUD Example MVC
Used simple pojo for showing MongDB CRUD operations and validations

## Getting Started
This project includes:
 - CREATE, Retrieve, Update, Delete Operations
 - MVC Pattern
 - Web Service
 - Logger
 - Validators and custom validator annotation
 - Exceptions
 - Regex Pattern Matchers
 - Global exception handler
 - MongoDB
 - Message formatter
 
# Used Annotations

 - @Service
 - @RestController
 - @RequestMapping
 - @ResponseBody
 - @GetMapping
 - @PostMapping
 - @PutMapping
 - @DeleteMapping
 - @ControllerAdvice
 - @ExceptionHandler
 - @Id
 - @NotNull
 - @Email
 - @Pattern
 - @Documented
 - @Constraint
 - @Target
 - @Retention
 - Custom Validator Annotation

# Start Application With Docker
 - Learn Docker version
   ```
   docker -v
   ```
   if docker dont installed: 
   * [Docker For Windows](https://docs.docker.com/docker-for-windows/install/) - Download Setup
   
 - Build docker in root directory
   ```
   docker build -f Dockerfile -t spring-boot-crud-example .
   ```
 - Show docker builded images
   ```
   docker images
   ```
 - Run docker image with exposed port 
   ```
   docker run -p 8090:8090 spring-boot-crud-example
   ```

## Contributing

Please read [CHANGELOG.md](https://github.com/bilalvdemir/SpringBootCRUDExample/blob/master/CHANGELOG.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Authors

* **Bilal Demir** - *Initial work* - [bilalvdemir](https://github.com/bilalvdemir)
