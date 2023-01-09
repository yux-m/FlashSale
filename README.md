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
  + [Login & Register](#Login--Register) ✅
  + [Listing & Detail Pages](#Listing--Detail-Pages) ✅
  + [Purchase](#Purchase) ✅
  + [Improvements & Load Tests](#Improvements--Load-Tests) ✅


## Structure 

# TODO: upload overall structure chart

## Sudo DevLog 

<h3 align="center"> Function Development </h3>


### Login & Register

<img width="977" alt="LoginRegister Flowchart" src="https://user-images.githubusercontent.com/109834466/211333269-54a3b7a0-d746-4502-be1e-1c7dface9af5.png">

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

    + Similar roadmap as Login/Register, with UserController and UserService. 
    + Delete cache in Redis after update password in database to ensure cache consistency.



### Listing & Detail Pages

<img width="830" alt="Listing   Detail" src="https://user-images.githubusercontent.com/109834466/211340537-e59aca37-97e9-4d4a-8546-054bae7a082a.png">

#### Some Details:

1. Listing:

    - Iterative blocks with Thymeleaf for list of products.

2. Deal Period Validation:

    - Backend: Initialize deal status and countdown variable based on current server's time when fetching sales data from database.
    - Frontend: Conditional function based on deal status and countdown function with timeout.

### Purcahse

<img width="874" alt="Purchase" src="https://user-images.githubusercontent.com/109834466/211363372-cff96b5e-39ce-46c4-9db5-13b42ddae4d0.png">

#### Stock Check & Update:

1. Check on server: 
    - Keep track of stock statuses (empty/not) on server to reduce communication with Redis; if status is empty, skip following steps.
2. Check and update on Redis: 
    - Pre-decrement stock on Redis to check if stock is enough; if not, add it back, update status on server, and skip following steps.
3. Update on database: 
    - Send message to queue and wait for new order created, where OrderService will update product stock in database.

<h3 align="center"> Improvements made during development </h3>

Improvements have already been included in structure charts in above section. In this section, I will discuss about the results of these modifications.

### Improvements & Load Tests

# TODO: upload load test results before and after improvements.
