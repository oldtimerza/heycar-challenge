-- This is for demo purposes using the H2 in memory DB.

-- Create dealers
INSERT INTO DEALER(id) VALUES(1)
INSERT INTO DEALER(id) VALUES(2)

-- Create Listings
INSERT INTO LISTING(id,dealer_id,code,make, model,kilo_watts, year, color, price) VALUES(1,1,'a','renault','model',132,2014,'red',13990)
INSERT INTO LISTING(id,dealer_id,code,make, model,kilo_watts, year, color, price) VALUES(2,2,'a','renault','model',132,2014,'red',13990)
