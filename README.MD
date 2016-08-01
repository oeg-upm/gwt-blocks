[![GWT User 2.6.1](https://img.shields.io/badge/GWT-2.6.1-43a047.svg)](http://www.gwtproject.org/)
[![GWT Inject 2.1.2](https://img.shields.io/badge/GWT%20Inject-2.1.2-43a047.svg)](https://code.google.com/archive/p/google-gin/)
[![GWT Dispatch 1.2.0](https://img.shields.io/badge/GWT%20Dispatch-1.2.0-43a047.svg)](https://github.com/randombits-org/gwt-dispatch)
[![GWT Presenter 1.1.1](https://img.shields.io/badge/GWT%20Presenter-1.1.1-43a047.svg)](https://code.google.com/archive/p/gwt-presenter/)

[![Guice-servlet 3.0](https://img.shields.io/badge/Guice--servlet-3.0-40c4ff.svg)](https://github.com/google/guice/wiki/Servlets)

#GWT-Blocks
This respository aims to help for the development of GWT projects.  
Add to your projects 3 new widgets: loading box, prettypopup, togglebutton.  
Add new events for togglebutton: toggleEvent and hasToggleEvent.  
Manages your URL places with PlaceManager.

# Table of Contents
1. [Pre-requisites](#pre-requisites)
2. [Compile and install it](#compile)
3. [Use as library](#useAsLibrary)
4. [Development guide](#developmentGuide)
5. [Version](#version)  


## 1) Pre-requisites <a name="pre-requisites"></a>
* Maven 3.0 or later.
* Java 1.7 or later.
* Internet connection if you use maven (for downloading dependencies).

## 2) Compile and install it<a name="compile"></a>

To compile, type in a cmd or terminal (On project folder, you need see the pom.xml):
```sh
mvn clean install
```
Maven install the library on local repository.

## 3) Use as library <a name="useAsLibrary"></a>
**Import the library**  

- If you use Maven:  
See <https://maven.apache.org/> for more information.  
When you does "mvn clean install" the library is installed locally (On your machine).  
Put the next code on your pom.xml file (of your project) (Change the version and put the latest version):
  > 	<dependency>
  >			<groupId>name.alexdeleon.lib</groupId>
  >			<artifactId>gwt-blocks</artifactId>
  >			<version>0.1.0</version>
  >		</dependency>  

If you want, change the version to another.

- If you do not use Maven:  
Import the file "target/gwt-block-VERSION.jar" into your project.  
Download and import:
  * GWT-user 
  * GWT inject
  * GWT Guice
  * GWT Presenter

**Use the library**  


## 4) Development guide <a name="developmentGuide"></a>  

For development guide visit the follow link:  
[Development Guide](https://github.com/oeg-upm/gwt-blocks/wiki/Development-guide)

## 5) Version <a name="version"></a>

0.1.0

// TODO: Update README.MD
