# heycar-challenge
My com.heycar.listings.repositories for the hey car interview challenge.

Notes along the way:

    - Relational DB is a better fit over a document based DB. This is because of the need to search on Listings independently of Dealers.
      So a hierarchical based DB structure is not a good fit.

    - Code reuse is important, so Services and Controllers are kept separate to allow Services to be reused in multiple Controllers.

    - I find Lombok helps keep our POJOs easier to read by removing boiler plate getter and setter code.

    - Spring initializr is a fantastic tool to get a project working in minimal time.

Things I would like to add:

    - Caching to speed up search results.
