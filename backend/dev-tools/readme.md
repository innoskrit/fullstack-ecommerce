# Developer Tools

## Download JDK
- Prefer using [homebrew](https://formulae.brew.sh/) for mac or [choco](https://community.chocolatey.org/) for windows
- We will work with JDK 23/22

### With homebrew
```
brew install openjdk@23
```
Download OpenJDK 23

### With choco
```
choco install openjdk
```
Download OpenJDK 22

## Downloading manually
- Official website  - [JDK Archives](https://jdk.java.net/archive/)
- Download zip/tar of JDK preferably version - 23.0.2
- Create directory to store all JDKs at single place
    ```
        mkdir -p ~/Library/Java/JavaVirtualMachines
    ```
- Move your JDK to above folder
- Unzip zip/tar of JDK
- update JAVA_HOME to point to latest JDK path
- echo $JAVA_HOME to print JDK path

## Integrated developement environment(IDE)
- Jetbrains - Intellij IDEA
- [Jetbrains Website](https://www.jetbrains.com/idea/)

## Database Tool
- There are plenty of tools, even command line mysql works great
- Those who prefer a UI can download [Beekeeper studio](https://www.beekeeperstudio.io/download)
- Other popular options: DBeaver, MySqlWorkbench

## Github
- Setup ssh key in order to efficiently work with github
- [documentation for ssh key generation](https://docs.github.com/en/authentication/connecting-to-github-with-ssh/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent)

## API Platform
- In order to test your APIs, you can use curl command or install an API platform
- I personally prefer [postman](https://www.postman.com/)
- Prefer creating a new workspace in postman for this course