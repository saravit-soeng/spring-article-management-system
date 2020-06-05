# Article Management System Tutorial
Spring Framework - AMS API and Application 

## Prerequisite
Before getting into the tutorial, please make sure you have the following requirements on your computer:
- Installing Java Version 8 or later
- IDE: IntelliJ or Spring Tool Suite (This tutorial using IntelliJ)
- Database: PostgreSQL on local or Server (Backup the database file that is located in folder _src/main/resources/database_)

## Create Sring Boot Project
- Open IntelliJ IDE and you will see screen as below, after that choose __Create New Project__

<p align="center">
  <img align="center" src="https://firebasestorage.googleapis.com/v0/b/fir-demo-b5359.appspot.com/o/pictures%2Fintellij-start-screen.jpg?alt=media&token=6918381b-90d4-4b15-a116-e34db21bb9b8" width="600" height="400" />
</p>

- Select __Spring Initializr__ for Spring Boot Project -> Next

<p align="center">
<img align="center" src="https://firebasestorage.googleapis.com/v0/b/fir-demo-b5359.appspot.com/o/pictures%2Fintellij-start-screen.-1jpg.JPG?alt=media&token=fa043697-40f7-4c96-8878-853597ce6055" width="600" height="400" />
</p>

- Enter and modifty __Spring Initializr Project Settings__ (Sample in screen below) -> Next

<p align="center">
<img align="center" src="https://firebasestorage.googleapis.com/v0/b/fir-demo-b5359.appspot.com/o/pictures%2Fintellij-start-screen.-2jpg.JPG?alt=media&token=31e9b1de-0fe0-42d0-a1f9-e9808be3cb2e" width="600" height="400" />
</p>

- Next Screen (Keep Default) -> Next
- New Project
  - Project name: enter your project name
  - Project location: choose the location that you want to store your project
- Finish
- Your project is created as screen below

<p align="center">
<img align="center" src="https://firebasestorage.googleapis.com/v0/b/fir-demo-b5359.appspot.com/o/pictures%2Fintellij-start-screen.-3jpg.JPG?alt=media&token=0a5cd952-0875-4392-9f6d-3aa0fb89d5ca" width="600" height="400" />
</p>

## Project Structure

Suppose that you have the package _com.example.ams_ under _src/main_ folder in your project structure, then create sub-packages under the package _com.example.ams_ :
- configuration
- model
- repository
- service
- controller


