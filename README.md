# Board-No-More-Rest

## CLIENT

Initial webpage is a list of products including name, price, players, playing time, age, and image This
information is all gathered by requesting a resource from the server which gets all of the board games
in a SQL database.

At the top of the page is a navigation bar that users can click to navigate from the
"Home" page to the "About" page. The "About" page is a general description of the website and the team that created it.


Clicking on either an image or the name of a board game in the "Home" page will take you to a more detailed
listing of that particular board game. Within that page is a slideshow of a few images of the board game,
a table for the product's details, a description, and a form to purchase the game.

When filling out the form, the inputs will be:

<pre>
Board Game: select one of the listed board games
Quantity: a positive integer
First Name: nonempty string
Last Name: nonempty string
Phone Number: 9 digit integer in the format: 123-456-7890
Address: nonempty string
Zip: 5 digit integer
City: nonempty string
State/Province/Region: nonempty string
Delivery Option: select one of the listed shipping options
Credit Card Number: 16 digit integer in the format: 1234-5678-9012-3456
</pre>

*NOTE: There are no options to select a country because we are currently
 shipping within the United States only.

After filling out the form with the valid data and selecting purchase, you will be navigated
to a confirmation page. Your order is also added into a SQL database.

## SQL


board_no_more (database)

  games (table)
  
    gid: int(11) PRIMARY KEY
    name: varchar(255)
    price: float
    minP: int(11)
    maxP: int(11)
    play_time: int(11)
    age: int(11)
    description: text
  
  images (table)
  
    imgId: int(11) PRIMARY KEY
    gid: int(11) FOREIGN KEY
    path: varchar(255)
  
  orders (table)
  
    oid: int(11) PRIMARY KEY
    gid: int(11) FOREIGN KEY
    quantity: int(11)
    fname: varchar(255)
    lname: varchar(255)
    phone_num: char(12)
    address: varchar(255)
    city: varchar(255)
    state: varchar(255)
    zip: int(5)
    delivery: varchar(9)
    credit: char(19)

