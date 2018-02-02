# Heroku spring boot starter 
[![Build Status](https://travis-ci.org/createam-labs/spring-boot-starter-heroku.svg?branch=master)](https://travis-ci.org/createam-labs/spring-boot-starter-heroku)
[![maven-central](https://img.shields.io/maven-central/v/io.github.createam-labs/spring-boot-starter-heroku.svg)](http://search.maven.org/#artifactdetails%7Cio.github.createam-labs%7Cspring-boot-starter-heroku%7C0.1.1%7Cjar)
[![code coverage](https://codecov.io/gh/createam-labs/spring-boot-starter-heroku/branch/master/graph/badge.svg)](https://codecov.io/gh/createam-labs/spring-boot-starter-heroku)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

![logos](https://i.imgur.com/oJgq4p8.png)

## 🛠 Configurtion
All you have to do is to add dependency to your project and enable features in `application.properties` file 😎
#### maven
```xml
<dependency>
  <groupId>io.github.createam-labs</groupId>
  <artifactId>spring-boot-starter-heroku</artifactId>
  <version>0.1.2</version>
</dependency>
```
#### gradle
```groovy
compile('io.github.createam-labs:spring-boot-starter-heroku:0.1.2')
````
##  Features 
| name | description | property |
| ------ | ------ | ------ |
| 🔒httpsEnforcer | enforce https under the thymeleaf templates (_note that, it will work after you deploy application to heroku_) | `heroku.thymeleaf.enforceHttps=true`

## Examples
Application which works with this starter can be found [here](http://createam-labs.herokuapp.com/)  
Source code of example app can be found [here](https://github.com/createam-labs/createam-labs-test-services)

License
----
MIT
