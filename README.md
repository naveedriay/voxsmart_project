# voxsmart_project
VoxSmart Technical Test Project


Dependencies include:
  - Java-8
  - Maven

**PROJECT SETUP**

 - you need to have java-8 installed in your machine
 - JAVA CLASSPATH, JAVA_HOME variables are set within system/env variables
 - Maven is installed in your machine
 - Unzip the VoxSmart Assignment folder
 - you will find a folders inside called *VoxSmart Project*

**TEST EXECUTION OF UI TEST PROJECT**

- For this UI Project, I have used ChromDriver_V91 and place it under the folder.

     <code>VoxSmart Project\src\test\resources\driver_binaries\chrome</code>

- If you have different version of Chrome, then you need download the right chrome driver for
 your version of Chrome browser, and put it under above folder location inside framework.

- In order to run the UI project, go to the project folder [VoxSmart Project] and Run the following command

   <code> mvn clean -U install </code>

- Now after the test build complete, you can see the output reports under
     VoxSmart Project\target\cucumber-reports\index.html
