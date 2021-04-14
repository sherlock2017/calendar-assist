# Calendar-Assist

* The calendar assist is a spring boot, JPA, in-memory H2 DB based service that handles calendar meetings for employees
* It consists of 3 APIs - book-meeting, conflicts & available-slots
* When the app starts, it automatically creates some employees, using data.sql script like below:

`insert into employee (email_id, name) values ('rishabh.jain@gmail.com', 'Rishabh Jain');`

## Project Setup

Follow the below steps to set up and start the project
* Clone this repository using - git clone https://github.com/sherlock2017/calendar-assist.git
* Now any preferred IDE and import the project as a 'Maven Existing Project'
* Now wait for the POM to get resolved
* To run the application, navigate to com.calendar.assist package and run CalendarAssistApplication.java as Java Application

## API Documentation


1. POST - Book Meeting

**Usage:** This API can be used to book meeting for timeslots including multiple attendees employees.

**API Endpoint:** `localhost:8083/api/calendar-assist/meeting/v1/book`

**Demo ReuqestBody:**

`{
    "title": "Test",
    "startDateTime": "2018-02-05T17:00:00.000",
    "endDateTime": "2018-02-05T18:00:00.000",
    "description": "This is a test Description",
    "organizer": {
        "emailId": "rishabh.jain@gmail.com"
    },
    "atendees": [
        {
            "emailId": "priya.jain@gmail.com"
        },
        {
            "emailId": "saral.jain@gmail.com"
        }
    ]
}`


***
2. POST - Conflicts

**Usage:** This API can be used to find the conflicted attendees in a meeting.

**API Endpoint:** `localhost:8083/api/calendar-assist/meeting/v1/conflicts`

**Demo RequestBody** 

`{
    "title": "Test",
    "startDateTime": "2018-02-05T17:00:00.000",
    "endDateTime": "2018-02-05T18:00:00.000",
    "description": "This is a test Description",
    "organizer": {
        "emailId": "rishabh.jain@gmail.com"
    },
    "atendees": [
        {
            "emailId": "priya.jain@gmail.com"
        },
        {
            "emailId": "saral.jain@gmail.com"
        }
    ]
}`


***

3. GET - Available Slots

**Usage:** This API can be used to find the available slots for 2 employee calendars of a specific date and duration.

**API Endpoint:** `localhost:8083/api/calendar-assist/calendar/v1/available-slots?empId1=1&empId2=2&calendarDate=2018-02-05&durationInMins=30`

**Request Params:**

`1) empId1 - 1
2) empId2 - 2
3) calendarDate - 2018-02-05
4) durationInMins - 30`

