#!/usr/bin/python
#not working
from pymongo import MongoClient
import cgi, os, uuid
username='ap14jl' 
passwd='5660055' 
client=MongoClient('mongodb://'+username+':'+passwd+'@127.0.0.1/'+username)
db=client[username]

print "Content-Type: text/html"
print

print "<html>"
print open("logged.html","r").read()
print "</html>"