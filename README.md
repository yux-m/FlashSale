<h1 align="center"> FlashSale </h1>

<p align="center">
  An ecommerce platform as web application project.
</p>
<p align="center">
  <img src="https://img.shields.io/badge/yuxin-open to work :P-ff69b4" />
</p>


## Main Tech Stack 

|            |                              |
|:----------:|:---------------------------- |
| Frontend   | jQuery, Thymeleaf, Bootstrap |
| Backend    | SpringBoot, MyBatis          |
| Middleware | Redis, RabbitMQ              |
| Database   | MySQL                        |

</p>

## Table of Contents
* [Structure](#Structure)
* [Sudo DevLog](#Sudo-DevLog)
  + [Login & Register](#Login--Register)
  + [Listing & Detail Pages](#Listing--Detail-Pages)
  + [Purchase](#Purchase)
  + [Improvements & Load Tests](#Improvements--Load-Tests)


## Structure 

# TODO

## Sudo DevLog 

<h3 align="center"> Function Development </h3>


### Login & Register

<img width="1123" alt="LoginRegister Flowchart" src="https://user-images.githubusercontent.com/109834466/211227235-59339dde-2758-46e9-826c-f75b4670ec4a.png">

#### Distributed Session: 
To support distribution, single session is no enough:

<img width="335" alt="Distributed Session" src="https://user-images.githubusercontent.com/109834466/211229903-e31572b0-6fa7-4ecb-9ed0-486b3a601818.png">

With Nginx's load balancing, requests are distributed to different Tomcats according to time order. 

> Suppose a user signs in at Tomcat1, the information will be stored in Session on Tomcat1. 
Later another request from the same user is distributed to Tomcat2, where no info of this user has been stored in Session, 
then the user will have to sign in again, which produces a bad experience.

* Why Redis: security and easy scaling out.

#### Other Details:
1. Exception Handler: 

    + SpringMVC allows entralized handle of Exceptions. 
In this project, I used @ControllerAdvice and @ExceptionHandler annotations to catch different types of exceptions and prompt messages.

2. Password Encryption: Double-layer salted hash
    + Frontend encryption: to avoid password being sent as clear text.
    + Backend encryption:  hash password again before storing into database, in case of data leakage during transmission.
  
3. Password Update: 

    + Similar roadmap as Login/Register. Delete cache in Redis after update password in database to ensure cache consistency.



### Listing & Detail Pages

# TODO

### Purcahse

# TODO

<h3 align="center"> Improvements made during development </h3>

Improvements have already been included in structure charts in above section. In this section, I will discuss about the results of these modifications.

### Improvements & Load Tests

# TODO
