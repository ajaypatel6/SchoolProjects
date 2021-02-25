#!/usr/bin/python
#<!-- Ajay Patel / 5660055 / ap14jl / -->

#show the products
#using fieldstorage example in lab
from pymongo import MongoClient
import cgi, os, uuid 
username='ap14jl' #Change this!
passwd='5660055' #Change this!
client=MongoClient('mongodb://'+username+':'+passwd+'@127.0.0.1/'+username)
db=client[username]

print "Content-Type: text/html"
print 

print open("notLogged.html","r").read()

print "<html>"
print "<h2>Browse our shoes</h2>"
#print "<h2>apply filter</h2>"
fs=cgi.FieldStorage()
if 'name' in fs: #productid? uid?
  name=fs.getfirst('name')
  name=str(name);
  rec=db.shoes.find_one({'name':name})
  print "<h3>"+rec['name']+"</h3>"
  print "<h3>Description: "+rec['description']+"</h3>"
  print "<p>Cost: "+rec['cost']+"</p>"
  print "<p>Tags: " +rec['tags']+"</p>"
  #link to shoe folder
  print "<img src=https://www.cosc.brocku.ca/~ap14jl/A4/shoes/"+rec['image']+" style='height:200px;'>"

#print the headers of the shoes, to be able to click
r=db.shoes.find()
#links on each
for record in r:
  print "<a href='PO.cgi?name="+record['name']+"'><h3>"+record['name']+"</h3></a>"
print  "<p>There are 12 shoes with pictures, the rest wont, unless you reuse pics</p>"
print "</html>"