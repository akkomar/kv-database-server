# Simple kv database server based on Finatra
## Running

    sbt run

## Usage

    http GET 'localhost:4000/get?key=somenotexistingkey'
    http PUT 'localhost:4000/set?key1=value1aaaa'
    http GET 'localhost:4000/get?key=key1'