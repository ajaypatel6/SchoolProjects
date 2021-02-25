#!/usr/bin/python
#<!-- Ajay Patel / 5660055 / ap14jl / -->

#just to open home page
from pymongo import MongoClient
import cgi, os, uuid, hashlib
username='ap14jl' 
passwd='5660055' 
client=MongoClient('mongodb://'+username+':'+passwd+'@127.0.0.1/'+username)
db=client[username]

print "Content-Type: text/html"
print

print "<html>"
print open("index.html","r").read()
print "</html>"