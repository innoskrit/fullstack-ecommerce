# SpringBoot Getting Started

## Create a hello world app
- Use [spring initializer](https://start.spring.io/) to create a new project
- Lets use Gradle Kotlin
    * Reasons being Gradle is faster than maven
    * Intellij has a much better support for kotlin Groovy
    * Kotlin is bit similar to Java, should be comparatively easier to understand
- Packaging
    * jar vs war - jar is general purpose packaging format whereas war is more for web applications
    * We will use jar
- Java version
    * JDKs are backward compatible(as we are using JDK 22/23)
    * Lets use Java 17 as that is still the most stable version, its ok to use Java 21 as well though

## Import project into intellij
- Make sure to select the right JDK in intellij
    * File > Project Structure > SDK

## Create hello world GET API
* Springboot annotations
    - RestController
    - Service

## Test API with postman