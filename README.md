# Base64 Decoder

This project was purposed to help some members of QA-Department with Base64 and Gzip Encoding/Decoding so that 
documents information wouldn't be leaked to some unknown owners of online-services. Application was supposed to be
used with Tomcat web server so it has some .war-building Gradle scripts. 
Plus it helped me to play around with Gradle instead of Maven.  

## Prerequisites

Project requires Java 1.8 and Tomcat 8.0+

## Getting Started

To get this project just clone it with
```
git clone https://github.com/SAmser93/base64-web-tool
```

## Installing

To build .war package use "war" Gradle task

```gradle war```

## Deployment

To deploy to Tomcat set up "tomcatFolder" parameter in build.gradle file, then run

```gradle war copyWar```

## Built With

* Java 1.8 - for backend
* Gradle - for dependencies and build tasks
* SpringBoot - for Web application controllers
* HTML, CSS, JS and jQuery - for front-end 

## Authors

* **Evgenii Suharev** - *Initial work* - [SAmser93](https://github.com/SAmser93)

## License

This project is licensed under the MIT License - see the LICENSE.md file for details

## Acknowledgments

This project was inspired by [original service](http://www.txtwizard.net/compression)