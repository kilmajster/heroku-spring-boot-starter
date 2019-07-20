# Heroku spring boot starter 
This starter is built to simplify https enforcing in projects hosted on [heroku](https://www.heroku.com/) cloud platform ☁️

[![Build Status](https://img.shields.io/travis/createam-labs/spring-boot-starter-heroku/master.svg?logo=travis)](https://travis-ci.org/createam-labs/spring-boot-starter-heroku)
[![Build status](https://ci.appveyor.com/api/projects/status/lr49dwaq8gou8hr8?svg=true)](https://ci.appveyor.com/project/createam-labs/spring-boot-starter-heroku)
[![Coverage Status](https://coveralls.io/repos/github/createam-labs/spring-boot-starter-heroku/badge.svg?branch=master)](https://coveralls.io/github/createam-labs/spring-boot-starter-heroku?branch=master)
![GitHub last commit](https://img.shields.io/github/last-commit/createam-labs/spring-boot-starter-heroku.svg)
[![maven-central](https://img.shields.io/maven-metadata/v/http/central.maven.org/maven2/io/github/createam-labs/spring-boot-starter-heroku/maven-metadata.xml.svg)](http://search.maven.org/#artifactdetails%7Cio.github.createam-labs%7Cspring-boot-starter-heroku%7C1.1%7Cjar)
[![StackShare](https://img.shields.io/badge/tech-stack-0690fa.svg?style=flat)](https://stackshare.io/createam-labs/spring-boot-starter-heroku)
[![MIT license](https://img.shields.io/badge/License-MIT-blue.svg)](https://github.com/createam-labs/spring-boot-starter-heroku/blob/master/LICENSE)

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
