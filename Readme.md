# Web Automation Testing with Java and Selenium

- Main Objective is to test android app using java, Slenium ,TestNg



## Getting Started
- JAVA - https://linuxize.com/post/install-java-on-ubuntu-18-04/
- Eclipse - https://linuxize.com/post/how-to-install-the-latest-eclipse-ide-on-ubuntu-18-04/
- Intelij - https://www.jetbrains.com/idea/download/#section=linux
- Maven - https://linuxize.com/post/how-to-install-apache-maven-on-ubuntu-18-04/
- TestNg - https://www.guru99.com/install-testng-in-eclipse.html
- selenium - https://www.selenium.dev/



## Running Examples in Local
- Download the zip or clone the Git repository.
- Unzip the zip file (if you downloaded one).
- Open Command Prompt and Change directory (cd) to folder containing pom.xml(enter below mvn command)
- mvn clean test
- Open Eclipse 
   - File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
   - Select the right project
- Right Click testng.xml file and Run as Java TestNG




### Remote URL

 -need to be added


### MVN Command to run in Local (Physical device needs to be connected or an Emulator)

mvn clean test -Psmoke -DMode=local
