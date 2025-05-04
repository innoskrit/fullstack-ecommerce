# How to test?

## Step1
Run user-service

## Step2 
Create a new user
```
curl --location 'localhost:8080/auth/sign-up' \
--header 'Content-Type: application/json' \
--data-raw '{
  "email": "abhishek@google.com",
  "username": "Abhishek Sharma",
  "password": "password"
}
'
```

## Step3
Login with email and password
```
curl --location 'localhost:8080/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
  "email": "abhishek@google.com",
  "password": "password"
}
'
```

## Get User Details
**Note**: Replace the token from login response
```
curl --location 'localhost:8080/user/details' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwicm9sZXMiOlsiUk9MRV9BRE1JTiJdLCJpYXQiOjE3NDYyNjcwNjAsImV4cCI6MTc0NjI3MDY2MH0.JqEh5H_JyyF-QPnUbsgBWCIsfUeMKFvQVzUz5STBeHlBohI4CP6re4kCrhsMXv8rJlwtdZB7NVbpCBpVEyo7HA'
```

**Notice that user details are being returned for the 
logged-in user and we did not have to pass userId explicitly.** 

# How does it work?

## auth-lib
- Library that has methods to generate JWT token 
and validate the token, checkout [JwtTokenUtil](https://github.com/innoskrit/fullstack-ecommerce/blob/master/backend/authentication-demo/auth-lib/src/main/java/org/innoskrit/auth_lib/JwtTokenUtil.java)
- Notice its usage in user-service [login api](https://github.com/innoskrit/fullstack-ecommerce/blob/master/backend/authentication-demo/user-service/src/main/java/org/innoskrit/user_service/controller/AuthController.java#L29) to generate token 
by passing userId and role
- Both login and sign-up apis are whitelisted and jwt token is not validated for these APIs
checkout [permitted paths](https://github.com/innoskrit/fullstack-ecommerce/blob/master/backend/authentication-demo/user-service/src/main/java/org/innoskrit/user_service/config/SecurityConfig.java#L18) in user-service config
and its usage in [JwtRequestFilter](https://github.com/innoskrit/fullstack-ecommerce/blob/master/backend/authentication-demo/auth-lib/src/main/java/org/innoskrit/auth_lib/JwtRequestFilter.java#L35) in auth-lib

## Aspect oriented programming(Request Filter)
- Checkout [JwtRequestFilter](https://github.com/innoskrit/fullstack-ecommerce/blob/master/backend/authentication-demo/auth-lib/src/main/java/org/innoskrit/auth_lib/JwtRequestFilter.java#L17)
in auth-lib, it effectively intercepts all requests and validates the token
- It also sets [CustomUserPrincipal](https://github.com/innoskrit/fullstack-ecommerce/blob/master/backend/authentication-demo/auth-lib/src/main/java/org/innoskrit/auth_lib/JwtRequestFilter.java#L60) object by extracting 
userId and roles from JWT token which can then be used in APIs by using [AuthenticationPrincipal annotation](https://github.com/innoskrit/fullstack-ecommerce/blob/master/backend/authentication-demo/user-service/src/main/java/org/innoskrit/user_service/controller/UserController.java#L20)


# How to use auth-lib in your service
- In this example user-service import auth-lib checkout [settings.gradle.kts](backend/authentication-demo/user-service/settings.gradle.kts)
and [build.gradle.kts](https://github.com/innoskrit/fullstack-ecommerce/blob/master/backend/authentication-demo/user-service/build.gradle.kts#L30)