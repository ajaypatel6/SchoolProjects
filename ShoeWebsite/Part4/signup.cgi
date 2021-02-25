#!/usr/bin/python
#<!-- Ajay Patel / 5660055 / ap14jl / -->

# using the login code from mongo lab, changed it for a sign in
#https://stackoverflow.com/questions/1183161/to-sha512-hash-a-password-in-mysql-database-by-python
#
from pymongo import MongoClient
import cgi, os, uuid, hashlib
username='ap14jl' 
passwd='5660055' 
client=MongoClient('mongodb://'+username+':'+passwd+'@127.0.0.1/'+username)
db=client[username]


#This is what we show when the user did something wrong
#It has an early termination, for simplicity's sake
def errornope():
  print "Content-Type: text/html"
  print
  print "<html><head><title>Bad!</title></head><body>"
  print "Username taken"
  print "</body></html>"
  exit()
  
def userbox():
  fs=cgi.FieldStorage()
  uname=fs.getfirst('uname')
  pw=fs.getfirst('key')
  a=fs.getfirst('admin')
  if uname!=None and pw!=None: 
    uname=str(uname); pw=str(pw); a=str(a)
    rec=db.users.find_one({'username':uname})
    if rec==None: 
		pw = hashlib.sha512(pw).hexdigest()
		
		db.users.insert({'username':uname,"key":pw,"admin":a})
		print "Location: ./PO.cgi" 
		return ""
    else: 
      errornope() 
	  
  box="""<form action='#' method='post'>
  Username: <input type='text' name='uname'/><br/>
  Password: <input type='password' name='key'/><br/>
  Admin: <input type='admin' name='admin'/>
  <br/><input type='submit' value='Sign up'/>
  </form>""" 
  return box
  
ub=userbox() 
print "Content-Type: text/html"
print
print """<html><head>
<style>
#user{float:right; border:1px solid black;padding:10px;text-align:right;}
</style></head><body>"""
print open("logged.html","r").read()

print "<div id='user'>"+ub+"</div>"
print "<h1>Sign up!</h1>"
print "<p> only those who enter the admin portion correctly will be given admin rights to add products<p>"
print "</body></html>"