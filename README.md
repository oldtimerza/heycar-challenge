# heycar-challenge
My application for the hey car interview challenge.

Up and running:

    - mvnw clean install, this will build the project, run the tests and create a docker image.
    
    - docker run -p 8080:8080 --name heycar oldtimerza/heycar-challenge

Testing and reports:

    - mvnw test

    - mvnw jacoco:report , this will create a readable reports html file at target/site/jacoco/index.html
    
Extras:

    - Simple logging using logsl4j with markers. Should switch to logback to handle logging formatting and remove log bulder.
    
    - Swagger 2 api documentation.
    
    - dockerfile-maven and Dockerfile to create docker image for project.

Notes along the way:

    - Relational DB is a better fit over a document based DB. This is because of the need to search on Listings independently of Dealers.
      So a hierarchical based DB structure is not a good fit.

    - Code reuse is important, so Services and Controllers are kept separate to allow Services to be reused in multiple Controllers.

    - I find Lombok helps keep our POJOs easier to read by removing boiler plate getter and setter code.

    - Spring initializr is a fantastic tool to get a project working in minimal time.

    - Postman is a great tool for testing REST endpoints quickly while developing.

    - Last line of CSV example (4,skoda/octavia,86,2018,16990) is missing the color

    - Make it work first and foremost, then make the code pretty, then optimise it to make it fast.

Problems encountered:

    - Non standard CSV format required a bit of extra mapping work done.

    - Not splitting the Listing model initially into domain and dto made it more difficult to handle two different input formats.

Things I would like to add:

    - Caching to speed up search results. Using something like hazel cast.

    - Hypermedia as the Engine of Application State. Create some kind of URI appender for the responses to make the API self discoverable.

    - Docker image deployment to docker hub for mvnw deploy.
