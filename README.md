# Heroku spring boot starter 
Collection of cool features which might be especially useful while developing [spring boot](https://projects.spring.io/spring-boot/) application on [heroku](https://www.heroku.com/) ☁️

[![Build Status](https://img.shields.io/travis/createam-labs/spring-boot-starter-heroku/master.svg?logo=travis)](https://travis-ci.org/createam-labs/spring-boot-starter-heroku)
[![Build status](https://ci.appveyor.com/api/projects/status/lr49dwaq8gou8hr8?svg=true)](https://ci.appveyor.com/project/createam-labs/spring-boot-starter-heroku)
[![coverage](https://img.shields.io/codecov/c/github/createam-labs/spring-boot-starter-heroku.svg)](https://codecov.io/gh/createam-labs/spring-boot-starter-heroku)
[![maven-central](https://img.shields.io/maven-metadata/v/http/central.maven.org/maven2/io/github/createam-labs/spring-boot-starter-heroku/maven-metadata.xml.svg)](http://search.maven.org/#artifactdetails%7Cio.github.createam-labs%7Cspring-boot-starter-heroku%7C1.1%7Cjar)
![GitHub last commit](https://img.shields.io/github/last-commit/createam-labs/spring-boot-starter-heroku.svg)
[![StackShare](https://img.shields.io/badge/tech-stack-0690fa.svg?style=flat)](https://stackshare.io/createam-labs/spring-boot-starter-heroku)
[![License: MIT](https://img.shields.io/packagist/l/doctrine/orm.svg)](https://opensource.org/licenses/MIT)

## 🛠 Configuration
All you have to do is to add dependency to your project and enable features in `application.properties` file 😎
#### maven
```xml
<dependency>
  <groupId>io.github.createam-labs</groupId>
  <artifactId>spring-boot-starter-heroku</artifactId>
  <version>1.1</version>
</dependency>
```
#### gradle
```groovy
compile('io.github.createam-labs:spring-boot-starter-heroku:1.1')
````
##  Features 
| name | description | property |
| ------ | ------ | ------ |
| 🔒httpsEnforcer | enforce https under the [thymeleaf](https://www.thymeleaf.org/) and [mustache](https://mustache.github.io/) templates (_note that, it will work after you deploy application to heroku_) | `heroku.enforceHttps=true`

## Examples
Application which works with this starter can be found [here](http://createam-labs.herokuapp.com/)  
Source code of example app can be found [here](https://github.com/createam-labs/createam-labs-test-services)

## Release notes

#### v1.1
- fixed broken release 🤦‍♂️

#### v1.0
- added support for mustache templates
- added tests

#### v0.1.2
- fixed redirection address after https enforcing
  
#### v0.1.1
- initial release


License
----
MIT
