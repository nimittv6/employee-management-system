The application is developed for voting purpose. I have used Swagger for API documentation and hitting the APIs from web. In- memory DB : I have used H2 Data base as in memory DB which stores tables in each run and once the server is stopped, the tables will be vanished.

Swagger link:http://localhost:8080/swagger-ui/index.html#/voting-controller

DB link: http://localhost:8080/h2-console/login.jsp user id - sa password - password

There are 5 endpoints as below:

1> Register Candidate : This will take Candidate ID, Candidate Name and will initialize the vote count to 0. URI-> http://localhost:8080/votingSystem/entercandidate?candidateName=Nimit&id=1' Output: { "candidateId": 1, "candidateName": "Nimit", "voteCount": 0 }

2>updating vote casted for each candidates: This will work as casting vote for each individual candidate. URI-> http://localhost:8080/votingSystem/castvote?id=1' Response-> { "candidateId": 1, "candidateName": "Nimit", "voteCount": 1 }

3>Counting vote casted for individual candidate : This will count vote for each and every candidate. URI-> http://localhost:8080/votingSystem/countvote?id=1 Response->{ "candidateId": 1, "candidateName": "Nimit", "voteCount": 1 }

4>Fetching details of all candidatesFetching details of all candidates URI->http://localhost:8080/votingSystem/listvote Response->[ { "candidateId": 1, "candidateName": "Nimit", "voteCount": 1 }, { "candidateId": 2, "candidateName": "Monty", "voteCount": 5 }, { "candidateId": 3, "candidateName": "Nimit verma", "voteCount": 0 } ]

5>Fetching details of the winner candidate URI->http://localhost:8080/votingSystem/getwinner Response->{ "candidateId": 2, "candidateName": "Monty", "voteCount": 5 }
