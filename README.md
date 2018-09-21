# Spring Boot CRUD Example MVC
Sample user service, includes:
 - CREATE, Retrieve, Update, Delete
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
   docker -v
   if docker dont installed, Setup file link : https://docs.docker.com/docker-for-windows/install/
 - Build docker in root directory
   docker build -f Dockerfile -t spring-boot-crud-example .
 - Show docker builded images
   docker images
 - Run docker image with exposed port 
   docker run -p 8090:8090 spring-boot-crud-example
