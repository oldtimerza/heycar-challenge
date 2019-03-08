# heycar-challenge
My com.heycar.listings.repositories for the hey car interview challenge.

Notes along the way:

    - Relational DB is a better fit over a document based DB. This is because of the need to search on Listings independently of Dealers.
      So a hierarchical based DB structure is not a good fit.

    - Code reuse is important, so Services and Controllers are kept separate to allow Services to be reused in multiple Controllers.

    - I find Lombok helps keep our POJOs easier to read by removing boiler plate getter and setter code.

    - Spring initializr is a fantastic tool to get a project working in minimal time.

    - Postman is a great tool for testing REST endpoints quickly while developing.

    - Last line of CSV example (4,skoda/octavia,86,2018,16990) is missing the color

Problems encountered:

    - Hashcode checks need to actually use properties that are available to both listings, listings coming from endpoint dont have a dealerid.

    - cascading save not working for dealer->listings

Things I would like to add:

    - Caching to speed up search results. Using something like hazel cast.

    - Some kind of logging filtering. Using something like logback.