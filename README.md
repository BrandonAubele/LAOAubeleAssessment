## Programming Exercise

The exercise we’d like to see is to use the NASA API described [here](https://api.nasa.gov) to build a project in GitHub that calls the Mars Rover API and selects a picture on a given day. We want your application to download and store each image locally.

### Acceptance Criteria
* Please send a link to the GitHub repo via <matt.hawkes@livingasone.com> when you are complete.
* Use list of dates below to pull the images were captured on that day by reading in a text ﬁle:
    * 02/27/17
    * June 2, 2018
    * Jul-13-2016
    * April 31, 2018
* Language needs to be *Java*.
* We should be able to run and build (if applicable) locally after you submit it
* Include relevant documentation (.MD, etc.) in the repo

### Bonus 
* Bonus - Unit Tests, Static Analysis, Performance tests or any other things you feel are important for Deﬁnition of Done
* Double Bonus - Have the app display the image in a web browser
* Triple Bonus – Have it run in a Docker or K8s (Preferable)

## What Was Done

For this exercise I used IntelliJ Community Edition (IDE) and Java 8 SDK.


## Setting Up
* Clone this repository
* Use Java 8 or later and Maven
* Run mvn spring-boot:run for the server
* Run mvn test to run unit tests

## What To Test
This is the base (http://localhost:8080/)

* GET /picture/<date>
   * Gets pictures for a specific date and stores locally
   * Returns submitted date and list of loaded pictures as JSON Obj
   
* GET /acceptance
   * Retrieves pictures from API using dates stored in file
   * Returns list of submitted dates and loaded pictures for each correct date as a JSON Obj


"# LAOAubeleAssessment" 
