# Introduction

In this challenge we have handled some Login and Search scenarios on [N11.com](https://www.n11.com/) e-commerce platform.
>For more details: [Automation Challenge](Senior_QA_Engineer_-_Test_Automation_Challenge.pdf)

Framework provides test automation process for only UI validations.

>To run tests please check [Maven Commands](#Maven Commands to run the tests on Terminal) section

# Requirements:

1- Install JDK [How to install JDK](https://docs.oracle.com/en/java/javase/20/install/installation-jdk-microsoft-windows-platforms.html) 
-   Terminal command to check the version:

```
    java --version
```


2- Download Maven [How to install Maven](https://maven.apache.org/install.html)

- Run this command to check whether maven is installed: 

```
    mvn -version
```


# Usage 
### Maven Commands to run the tests on Terminal

Running all test cases:

```
    mvn clean verify
```

>Note: To generate 3rd party test report we use mvn verify instead mvn test

Running certain tags:

```
    mvn verify -Dcucumber.options="--tags @login, @search"
```

Available tags to pass by command:
- @ui
- @login
- @search
- @successful_login
- @unsuccessful_login
- @search_term
- @unsuccessful_search

Running test cases with different parameters:

```
mvn verify -Dusername=demo@demo.com -Dsearch_term=shoes -Dbrowser=firefox
```

Available parameters to change by command:
1.  browser (should be one of the following values)
    - <b>chrome</b> (default)
    - <b>firefox</b>
    - <b>edge</b>
2.  base_url
3.  username
4.  password
5.  search_term

Create 3rd party report with existing execution without running tests:

```
    mvn verify -DskipTests
```

Clearing target directory:

```
    mvn clean
```

# Reports and Results

**Warning:** When execution is started, all previous records will be deleted.

All test results and screenshots will be stored under <b>results</b> directory

Reports will be stored under <b>target</b> directory. There is a default cucumber html report under the directory. Detailed 3rd party HTML report will be under <b>target/cucumber-html-reports</b>

##Login Results

Unsuccessful login attempts will be saved into <b>results/loginerror.txt</b> file. The file will have testType, username, password, errorMessage headers.

##Search Results

Successful and unsuccessful results will be saved into  <b>results/results.txt</b> file. The file will have Search Term:<term>
Results:, Screenshot:(as path). Screenshots of search results will be saved under <b>results/screenshots</b>

### Testing Tools

- Java
- Selenium Webdriver
- Cucumber / Gherkin
- Maven

### Concepts Included

- Parallel test runs
- Page Object pattern
- Common web page interaction methods
- Singleton Design pattern
- Page Factory Design pattern
- Utilities package for common methods across the framework

### Automation Framework Details

-   The test automation framework is Maven-based and utilizes Cucumber with Selenium for Behavior Driven Development (BDD) testing.
-   Java is the chosen programming language.
-   The Page Object Model (POM) design pattern is implemented to organize web elements and functionality into separate page classes (e.g., HomePage, LoginPage) that extend a common PageBase class.
-   Page Factory Design Pattern is used to simplify WebElement initialization in page classes.
-   A singleton Driver class is employed to manage WebDriver instances.
-   The framework generates detailed HTML reports that include comprehensive information about test steps, failure screenshots, and test execution metrics (pass, fail, skip percentages). These reports are designed to be easily understood by non-technical team members.



