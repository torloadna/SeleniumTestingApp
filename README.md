# SeleniumTestingApp

Test cases:

userRegistrationProcessTest:
![image](https://user-images.githubusercontent.com/105055319/194230633-90f648c7-2769-4463-b572-accf307a0c34.png)

womanButtonClickTest:
![image](https://user-images.githubusercontent.com/105055319/194231553-4c4c3033-95a4-48b3-8b3a-126747960bed.png)

endToEndBuyProduct:
![image](https://user-images.githubusercontent.com/105055319/194231978-da38015a-5bad-4c3a-8bdb-0f1535789443.png)


# How to run tests

1. Go into SeleniumTestingApp folder
2. Run mvn clean test from the command line

Note that you may need a java SDK installed on your PC. If you have trouble running the tests it could be because of the java version,
so you can adjust the version in pom.xml file, at the bottom you can see:

 <properties>
        <maven.compiler.source>19</maven.compiler.source>
        <maven.compiler.target>19</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
 </properties>
 
 Just change the 19 version to the one you have/installed


