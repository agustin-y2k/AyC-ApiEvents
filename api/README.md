![madewith](https://img.shields.io/badge/made%20with-SpringBoot-green?logo=spring&style=for-the-badge)

# Connect to Data Base

**Set up application.properties**

Spring DATASOURCE (DataSourceAutoConfiguration & DataSourceProperties)

    spring.datasource.url
    
    spring.datasource.username
    
    spring.datasource.password

The SQL dialect makes Hibernate generate better SQL for the chosen database

    spring.jpa.properties.hibernate.dialect
    
    logging.level.org.hibernate.SQL

Hibernate ddl auto (create, create-drop, validate, update)

    spring.jpa.hibernate.ddl-auto

# Connect Mail Service

    spring.mail.host=smtp.gmail.com
    spring.mail.port=587
    spring.mail.username=<Your Email>
    spring.mail.password=<Your Password>
    spring.mail.properties.mail.smtp.auth=true
    spring.mail.properties.mail.smtp.connectiontimeout=5000
    spring.mail.properties.mail.smtp.timeout=5000
    spring.mail.properties.mail.smtp.writetimeout=5000
    spring.mail.properties.mail.smtp.starttls.enable=true

# How to use

### **Organize**

    {
    "Title": "MovieTitle",
    "Genre": "HORROR",
    "Release Date": "2021-06-05",
    "Duration": "01:30:00",
    "Trailer": "url",
    "Language": "ENGLISH",
    "Subtitle": "SPANISH",
    "Cast": [
        "actor1"
        ],
    "Director": "director1"
}

![GET](https://img.shields.io/badge/method-GET-%3CCOLOR%3E.svg)

    http://localhost:8080/attendees

![GET](https://img.shields.io/badge/method-GET-%3CCOLOR%3E.svg)

    http://localhost:8080/attendee/{dni}


![GET](https://img.shields.io/badge/method-POST-yellow.svg)

    http://localhost:8080/attendee/verify/{dni}


![GET](https://img.shields.io/badge/method-DELETE-red.svg)

    http://localhost:8080/attendee/{dni}

### **Attendee**

![GET](https://img.shields.io/badge/method-GET-%3CCOLOR%3E.svg)

**Check if the user is approved**

    http://localhost:8080/attendee/checkStatus/{dni}

![GET](https://img.shields.io/badge/method-POST-yellow.svg)

**Register using the browser**

Ingress into the index:

    http://localhost:8080

Or go direct to the registration page:

    http://localhost:8080/attendee/register

# Unit Test

>Attendee Service

>Mail Service

>Controller



