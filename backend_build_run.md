# Building and running the backend
# Prerequisites

## 1. Setup Java JDK 

Running Gradle always requires a Java JDK version 8 or higher. 
For this project it is recommended to use version 11 of the Java JDK.
First [download](https://adoptium.net/de/temurin/releases) the installer for the Java JDK.

###Windows

Make sure you select the appropriate operating system, architecture and version 11 of the JDK.
Download and run the .msi file. Make sure the "Configure JAVA_HOME variable" option is checked. This option is disabled by default in the installer.

After the installation has finished, open CMD and check with `java -version` if the installation was successful.

## 2. Setup Gradle

Gradle is a build tool used to build the backend. Gradle version 7.1 is required for the backend. 
A list of the different versions can be found [here](https://gradle.org/releases/).
If you do not install Gradle via a package manager, there is a detailed description of 
how to install Gradle [manually](https://gradle.org/install/#manually).

After the installation has finished, open CMD and check with `gradle -v` if the installation was successful.

## 3. Get the Project

You can download the project as a ZIP file or clone it with:
```sh
   git clone https://github.com/informatik-heilbronn/transparenz-cockpit
```
If you downloaded the zip file, extract the project to a path of your choosing.

# Build
Make sure you have met the prerequisites for the backend. 
Open CMD, navigate to the project backend folder and run the following command:
```sh
   gradle backend:build
```

# Run