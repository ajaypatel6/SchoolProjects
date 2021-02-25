#!/usr/bin/python
#<!-- Ajay Patel / 5660055 / ap14jl / -->

#code from class, manipulated
from pymongo import MongoClient
import cgi, os, uuid 
username='ap14jl' 
passwd='5660055' 
client=MongoClient('mongodb://'+username+':'+passwd+'@127.0.0.1/'+username)
db=client[username]

def productbox():
  fs=cgi.FieldStorage()
  pname=fs.getfirst('pname')
  d=fs.getfirst('description')
  c=fs.getfirst('cost')
  i=fs.getfirst('image')
  t=fs.getfirst('tags')
  if pname!=None and d!=None: 
    pname=str(pname); d=str(d); c=str(c);i=str(i); t=str(t)
    rec=db.shoes.find_one({'name':pname})
    if rec==None: 
	#	pw = hashlib.sha512(pw).hexdigest()
		
		db.shoes.insert({'name':pname,"description":d,"cost":c,"image":i,"tags":t})
		print "Location: ./PO.cgi" 
		return ""
    else: 
      errornope() 
	  
  box="""<form action='#' method='post'>
  Name: <input type='text' name='pname'/><br/>
  Description: <input type='text' name='description'/><br/>
  Cost: <input type='text' name='cost'/><br>
  Image: <input type='text' name='image'/></br>
  Tags: <input type='text' name='tags'/>
  <br/><input type='submit' value='Create product'/>
  </form>""" 
  return box
  
pb=productbox() 

print "Content-Type: text/html"
print
print """<html><head>
<style>
#user{float:right; border:1px solid black;padding:10px;text-align:right;}
</style></head><body>"""
print open("aLogged.html","r").read()

print "<div id='user'>"+pb+"</div>"
print "<h1>Welcome!</h1>"
print "<p>As an admin, you can put new products</p>"
print "</body></html>"