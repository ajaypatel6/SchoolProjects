#!/usr/bin/python
#<!-- Ajay Patel / 5660055 / ap14jl / -->
#https://stackoverflow.com/questions/1183161/to-sha512-hash-a-password-in-mysql-database-by-python
#for the hashing and using lab code
from pymongo import MongoClient
import cgi, os, uuid, hashlib
username='ap14jl' 
passwd='5660055' 
client=MongoClient('mongodb://'+username+':'+passwd+'@127.0.0.1/'+username)
db=client[username]

def errornope():
  print "Content-Type: text/html"
  print
  print "<html><head><title>Bad!</title></head><body>"
  print "Could you not?"
  print "</body></html>"
  exit()

def create_session(username):
  sid=uuid.uuid1().hex
  db.users.update_one({'username':username},{'$set':{'usid':sid}})
  print "Set-Cookie: user="+username
  print "Set-Cookie: usid="+sid
  print "Location: ./PO.cgi" 
  print
  exit()

aUser = open("aLogged.html","r").read()
    
def check_logged_in():
  if os.environ.has_key('HTTP_COOKIE'):
    user=None 
    usid=None 
    cookies=os.environ['HTTP_COOKIE'].split(';')
    for cookie in cookies:
      if cookie.split('=')[0].strip()=='user':
        user=cookie[cookie.find('=')+1:] 
      elif cookie.split('=')[0].strip()=='usid':
        usid=cookie[cookie.find('=')+1:]
    if user and usid: 
      rec=db.users.find_one({'username':user,'usid':usid})
      if rec!=None: 
        return user 
  return None

def actually_sign_out(username):
  db.users.update_one({'username':username},{'$unset':{'usid':''}})
  print "Location: ./login.cgi"

def signoutbox():
  return "<a href='login.cgi?logout=yes'>Sign out</a>"
  
def userbox():
  fs=cgi.FieldStorage()
  uname=fs.getfirst('uname')
  pw=fs.getfirst('key')
  if uname!=None and pw!=None: 
    uname=str(uname); pw=str(pw)
    rec=db.users.find_one({'username':uname})
    if rec==None: 
      errornope() 
    else: 
	  rec=db.users.find_one({'username':uname})
	  stored=rec['key']
	  current = hashlib.sha512(pw).hexdigest()
	  if stored == current:
		create_session(uname)
	  else:
		errornope()
  user=check_logged_in() 
  if user: 
    if fs.getfirst('logout')!=None: 
      actually_sign_out(user)
    else:
      return signoutbox()
  box="""<form action='#' method='post'>
  Username: <input type='text' name='uname'/><br/>
  Password: <input type='password' name='key'/>
  <br/><input type='submit' value='Sign in'/>
  </form>""" 
  return box
ub=userbox() 
  
userStatus = aUser

print "Content-Type: text/html"
print

print userStatus
print """<html><head>
<style>
#user{float:right; border:1px solid black;padding:10px;text-align:right;}
</style></head><body>"""

print "<div id='user'>"+ub+"</div>"
print "<h1>Welcome!</h1>"
print "<p>Log in with your account you made (or logout)</p>"
print "<p>Admins have an admin page here if you are an admin</p>"
print "</body></html>"