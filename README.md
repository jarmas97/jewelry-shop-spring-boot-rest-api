# jewelry-shop-spring-boot-rest-api

## Project Status
🚧 **Work in Progress** 🚧

## Description
This is a Spring Boot application designed to serve as the backend for a jewelry store website,
providing essential functionality through a REST API.

## How to Run
1. Clone this repository to your local machine.
2. Open the project in your preferred IDE.
3. Build and run the project.

## Language
- Java 8

## Dependencies (Maven)
- Java Persistence API
- Web
- Security
- JWT (JSON Web Token)
- H2 Database
- Lombok
- Commons IO
- JUnit

## Project Structure
- **src**: Main source directory.
    - **main**:
        - **java**: Java source code.
            - **com.github.jarmas97.jewelryshopspringbootrestapi**: Main package for the application.
                - **entities**: Package containing entity classes.
                    - **material**:
                        - **Material**: Entity class representing a material.
                        - **MaterialRepository**: Data access repository for Material.
                    - **order**:
                        - **Order**: Entity class representing an order.
                        - **OrderRepository**: Data access repository for Order.
                        - **OrderService**: Service class for handling orders.
                        - **Status**: Enumeration representing order status.
                    - **photo**:
                        - **Photo**: Entity class representing a photo.
                        - **PhotoDTO**: Data Transfer Object for Photo.
                        - **PhotoRepository**: Data access repository for Photo.
                    - **product**:
                        - **Category**: Entity class representing a product category.
                        - **Product**: Entity class representing a product.
                        - **ProductRepository**: Data access repository for Product.
                        - **ProductRequest**: Request class for handling product data.
                        - **ProductService**: Service class for handling product-related operations.
                    - **user**:
                        - **User**: Entity class representing a user.
                        - **UserRepository**: Data access repository for User.
                        - **UserService**: Service class for handling user-related operations.

                - **security**: Package containing security-related classes.
                    - **jwt**: Json Web Token classes.
                        - **JwtAuthorizationFilter**: Class for JWT authorization filter.
                        - **LoginFilter**: Class for handling login filter.
                        - **TokenProvider**: Class for JWT token provider.
                    - **CorsConfiguration**: Class for configuring Cross-Origin Resource Sharing.
                    - **SecurityConfiguration**: Class for security configuration.
                    - **UserDetailsService**: Class for handling user details service.

                - **controllers**: Package containing controller classes.
                    - **AdminController**: Controller class for administrative actions.
                    - **PublicController**: Controller class for public access actions.

                - **Application**: Main class for starting the application.

        - **resources**: Project resources.
            - **application.properties**: Application configuration file.

    - **test**: Test source directory.
        - **java**: Java source code for tests.
            - **com.github.jarmas97.jewelryshopspringbootrestapi**: Test package mirroring the source structure.

            - **resources**: Test resources.
                - **application.properties**: Test configuration file.

## Contributing
If you'd like to contribute to the project, feel free to fork the repository and submit a pull request.