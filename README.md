# 🚀 RestAssured API Automation Framework

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![TestNG](https://img.shields.io/badge/TestNG-7.12.0-blue.svg)](https://testng.org/)
[![RestAssured](https://img.shields.io/badge/RestAssured-6.0.0-green.svg)](https://rest-assured.io/)
[![Maven](https://img.shields.io/badge/Maven-3.8+-red.svg)](https://maven.apache.org/)
[![Allure](https://img.shields.io/badge/Allure-2.33.0-purple.svg)](https://qameta.io/allure-report/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

A robust, scalable, and enterprise-grade API automation framework built with Java, RestAssured, and TestNG. This framework demonstrates industry best practices for API testing with comprehensive features including multi-environment support, data-driven testing, advanced reporting, and maintainable architecture.

---

## 📋 Table of Contents

- [Overview](#overview)
- [Key Features](#key-features)
- [Tech Stack](#tech-stack)
- [Framework Architecture](#framework-architecture)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Running Tests](#running-tests)
- [Configuration](#configuration)
- [Reporting](#reporting)
- [Design Patterns & Best Practices](#design-patterns--best-practices)
- [API Coverage](#api-coverage)
- [Future Enhancements](#future-enhancements)
- [Contributing](#contributing)

---

## 🎯 Overview

This framework is designed to automate REST API testing with a focus on maintainability, reusability, and scalability. It follows a modular architecture that separates concerns and enables easy extension for new APIs and test scenarios. The framework supports multiple environments, data-driven testing, and provides comprehensive reporting capabilities.

**Ideal for:**
- Enterprise API testing projects
- CI/CD pipeline integration
- Regression testing suites
- Performance and load testing foundation
- Contract testing

---

## ✨ Key Features

### Core Capabilities
- **Multi-Environment Support**: Seamlessly switch between DEV, QA, UAT, STAGE, and PROD environments
- **Data-Driven Testing**: Excel-based test data management using Apache POI
- **Comprehensive Logging**: Log4j2 integration for detailed execution logs
- **Advanced Reporting**: Allure TestOps integration with beautiful HTML reports
- **POJO Serialization**: Jackson-based JSON serialization/deserialization
- **JSON Path Validation**: Jayway JsonPath for complex JSON parsing and validation
- **Schema Validation**: JSON Schema validation support
- **Flexible Authentication**: Bearer token authentication with configurable headers
- **Request Specification Builder**: Reusable request specifications for DRY principle
- **Custom Exception Handling**: Framework-specific exception handling

### Testing Capabilities
- **HTTP Methods**: GET, POST, PUT, PATCH, DELETE
- **Content Types**: JSON, XML, Text, HTML, Multipart
- **Query Parameters**: Dynamic query parameter support
- **Headers Management**: Custom headers support
- **Response Validation**: Status code, body, headers, response time validation
- **Hamcrest Matchers**: Rich assertion library for response validation

---

## 🛠 Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 17 | Programming Language |
| **RestAssured** | 6.0.0 | API Testing Library |
| **TestNG** | 7.12.0 | Test Framework |
| **Maven** | 3.8+ | Build Tool |
| **Jackson Databind** | 2.22.1 | JSON Serialization |
| **Jayway JsonPath** | 3.0.0 | JSON Path Queries |
| **Lombok** | 1.18.46 | Code Generation |
| **Apache POI** | 5.4.1 | Excel Data Handling |
| **Log4j2** | 2.24.3 | Logging Framework |
| **Allure** | 2.33.0 | Test Reporting |
| **JSON Schema Validator** | 6.0.1 | Schema Validation |

---

## 🏗 Framework Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                     TestNG Test Suite                        │
│                  (testng_regression.xml)                      │
└────────────────────────┬────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────────┐
│                      BaseTest                                 │
│  - Configuration Management                                   │
│  - Allure Filter Setup                                        │
│  - Common Test Initialization                                 │
└────────────────────────┬────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────────┐
│                    Test Classes                              │
│  - CreateUserTest                                           │
│  - GetUserTest                                                │
│  - GetProductAPITest                                         │
│  - HTTPBinOrgAPITest                                         │
└────────────────────────┬────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────────┐
│                   RestClient (HTTP Client)                    │
│  - Request Specification Builder                             │
│  - HTTP Methods (GET, POST, PUT, PATCH, DELETE)             │
│  - Authentication Management                                 │
│  - Content Type Handling                                      │
└────────────────────────┬────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────────┐
│                 Supporting Components                         │
│  - ConfigurationManager (Multi-env support)                  │
│  - ExcelUtils (Data-driven testing)                           │
│  - JsonPathUtils (JSON parsing)                               │
│  - StringUtils (Test data generation)                         │
│  - POJO Classes (Request/Response models)                     │
│  - Custom Exceptions (Error handling)                        │
└─────────────────────────────────────────────────────────────┘
```

---

## 📁 Project Structure

```
RestAssuredAPIAutomation/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/qa/gorest/
│   │           ├── client/
│   │           │   └── RestClient.java              # HTTP Client with all methods
│   │           ├── configuration/
│   │           │   └── ConfigurationManager.java     # Multi-env config loader
│   │           ├── constants/
│   │           │   ├── APIConstants.java
│   │           │   └── APIHttpStatusCode.java        # HTTP status enum
│   │           ├── frameworkexception/
│   │           │   └── APIFrameworkException.java    # Custom exceptions
│   │           ├── pojo/
│   │           │   └── UserPOJO.java                 # Request/Response models
│   │           └── utils/
│   │               ├── ExcelUtils.java                # Excel data reader
│   │               ├── JsonPathUtils.java            # JSON path utilities
│   │               └── StringUtils.java               # Test data generators
│   └── test/
│       ├── java/
│       │   └── com/qa/gorest/
│       │       ├── base/
│       │       │   └── BaseTest.java                  # Test base class
│       │       └── tests/
│       │           ├── CreateUserTest.java           # User creation tests
│       │       │   ├── GetUserTest.java               # User retrieval tests
│       │       │   ├── GetProductAPITest.java        # Product API tests
│       │       │   └── HTTPBinOrgAPITest.java         # HTTP bin tests
│       └── resources/
│           ├── config/
│           │   ├── config.properties                 # Default config
│           │   ├── config_uat.properties              # UAT config
│           │   ├── config_stage.properties           # Stage config
│           │   └── config_prod.properties            # Production config
│           ├── testData/
│           │   └── UsersData.xlsx                    # Test data
│           └── testrunners/
│               └── testng_regression.xml              # Test suite
├── pom.xml                                            # Maven configuration
├── .gitignore                                         # Git ignore rules
└── README.md                                          # This file
```

---

## 📦 Prerequisites

Before setting up the project, ensure you have the following installed:

- **JDK 17** or higher
- **Maven 3.8+**
- **IDE**: IntelliJ IDEA (recommended) or Eclipse
- **Git**

---

## 🚀 Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/RestAssuredAPIAutomation.git
cd RestAssuredAPIAutomation
```

### 2. Install Dependencies

```bash
mvn clean install
```

### 3. Configure Environment

Edit the configuration files in `src/test/resources/config/`:

```properties
# config.properties (or environment-specific)
baseUri=https://gorest.co.in
tokenId=your-api-token-here
```

### 4. IDE Setup (IntelliJ IDEA)

1. Open the project in IntelliJ IDEA
2. Wait for Maven to import dependencies
3. Enable annotations processing for Lombok:
   - Settings → Build, Execution, Deployment → Compiler → Annotation Processors
   - Check "Enable annotation processing"

---

## 🏃 Running Tests

### Run All Tests

```bash
mvn clean test
```

### Run Specific Test Suite

```bash
mvn clean test -DsuiteXmlFile=src/test/resources/testrunners/testng_regression.xml
```

### Run Specific Environment

```bash
# Run tests for UAT environment
mvn clean test -Denv=uat

# Run tests for PROD environment
mvn clean test -Denv=prod

# Run tests for STAGE environment
mvn clean test -Denv=stage
```

### Run Specific Test Class

```bash
mvn clean test -Dtest=CreateUserTest
```

### Run Specific Test Method

```bash
mvn clean test -Dtest=CreateUserTest#createUserTest
```

### Generate Allure Report

```bash
# After running tests
mvn allure:serve

# Or generate static report
mvn allure:report
```

---

## ⚙️ Configuration

### Environment Configuration

The framework supports multiple environments through property files:

| Environment | Property File | Usage |
|-------------|---------------|-------|
| Default | `config.properties` | Used when no env specified |
| UAT | `config_uat.properties` | `-Denv=uat` |
| STAGE | `config_stage.properties` | `-Denv=stage` |
| PROD | `config_prod.properties` | `-Denv=prod` |

### TestNG Configuration

Test suites are defined in XML files under `src/test/resources/testrunners/`:

```xml
<suite name="API Testing Regression Suite" thread-count="1" verbose="4">
    <test name="Go Rest Get API Test">
        <parameter name="baseURI" value="https://gorest.co.in" />
        <classes>
            <class name="com.qa.gorest.tests.GetUserTest" />
        </classes>
    </test>
</suite>
```

### Logging Configuration

Log4j2 is used for logging. Logs are written to the `logs/` directory with different levels:
- INFO: General test execution information
- ERROR: Failure and exception details
- DEBUG: Detailed debugging information

---

## 📊 Reporting

### Allure Reports

The framework integrates with Allure TestOps for comprehensive test reporting:

**Features:**
- Beautiful HTML reports with test execution history
- Screenshots and attachments support
- Test execution timelines
- Environment information
- Test categorization and tagging
- Integration with CI/CD tools

**View Reports:**
```bash
mvn allure:serve
```
Reports will be available at `http://localhost:port/`

### TestNG Reports

Default TestNG reports are generated in `test-output/` directory after test execution.

---

## 🎨 Design Patterns & Best Practices

### Design Patterns Used

1. **Builder Pattern**: `RequestSpecBuilder` for building request specifications
2. **Factory Pattern**: `RestClient` as a factory for HTTP requests
3. **Singleton Pattern**: Configuration manager for single instance
4. **Data Transfer Object (DTO)**: POJO classes for request/response

### Best Practices Implemented

- **DRY Principle**: Reusable methods in `RestClient` to eliminate code duplication
- **Separation of Concerns**: Clear separation between test logic, API client, and utilities
- **Data-Driven Testing**: Excel-based test data for maintainability
- **Environment Abstraction**: Multi-environment support without code changes
- **Comprehensive Logging**: Log4j2 for debugging and audit trails
- **Exception Handling**: Custom exceptions for framework-specific errors
- **Enum for Constants**: `APIHttpStatusCode` enum for type-safe status codes
- **Lombok**: Reduces boilerplate code in POJOs
- **TestNG Features**: Priorities, data providers, parameters for flexible test execution
- **Maven Standard Directory Layout**: Follows Maven conventions
- **Git Ignore**: Proper `.gitignore` for clean version control

### Code Quality

- Clean, readable, and maintainable code
- Proper package structure
- Meaningful variable and method names
- JavaDoc comments for public methods
- Consistent code formatting

---

## 🧪 API Coverage

### APIs Currently Automated

| API | Base URL | Endpoints | Methods |
|-----|----------|-----------|---------|
| **GoRest** | https://gorest.co.in | /public/v2/users/ | GET, POST |
| **Fake Store** | https://fakestoreapi.com | /products/ | GET |
| **HTTPBin** | https://httpbin.org | /get | GET |

### Test Scenarios

- ✅ Create user with valid data
- ✅ Create user with Excel data
- ✅ Get all users
- ✅ Get single user by ID
- ✅ Get users with query parameters
- ✅ Get all products
- ✅ Filter products by rate using JSON Path
- ✅ HTTP bin endpoint testing

---

## 👥 Contributing

Contributions are welcome! Please follow these guidelines:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Coding Standards

- Follow Java naming conventions
- Add unit tests for new utilities
- Update documentation for new features
- Ensure all existing tests pass before submitting

---

## 🙏 Acknowledgments

- RestAssured team for the excellent API testing library
- TestNG community for the robust testing framework
- Allure team for the beautiful reporting solution
- Open-source community for the amazing tools and libraries

---

**⭐ If you find this framework helpful, please give it a star on GitHub!**

---

*Built with ❤️ for Quality Assurance*
