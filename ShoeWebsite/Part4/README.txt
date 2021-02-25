******************** 
The page for
https://www.cosc.brocku.ca/~ap14jl/A4/
<!-- Ajay Patel / 5660055 / ap14jl / -->


********************

             SOME login details for testing (or you can just make an accout)
Username: Admin / Password: admin (has admin:"correct", which is admin)
Username: test / Password: test
Username: Adminfake / password : admin (has admin:yes, which wouldnt be admin, but it doesnt work anyway)


PRODUCTS are shown on products page properly with criteria, tags are able to be added and shown. 
- INDIVIDUAL PRODUCTS have all necesarry info and using AJAX

- WISHLIST is not functional

***
- you are able to SIGN UP and log in, as well as log out, (session cleared) 
the password is also hashed and unhashed to login.
- the cookies are unset, and the logout actually occurs
***
- i could not get the specific log in status, in order to show correct header information
- headers may not be CORRECT, but the actual pages will be. 


- had to have admin show up inappropriately because could not get login status to work(i tried using different html headers, but
i wasnt able to use the cookies, or signing out wouldnt work
*******we are using the page, assuming that everyone is an admin, so all headers are available*****
ADMIN page can add products, (can add images, just need a jpg from my folder somehow and add that jpg in the form.)
**To go to admin page, go to Login and it will be there**

User database schema ({_id:(mongoid), username:(givenusername), Admin:(a string for admin, was gunna use a specific key to see if they were admin), key:(the hashed key)})
Products database schema ({ _id:(mongoid), name:(nameofshoe), description:(any description), cost:(given cost), image:(using specific file from a set list), tags:(can add as many, they are stored together)})
