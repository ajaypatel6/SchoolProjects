// TCP

//arg 1 : dotted addy of server 127.0.0.1 
// arg 2 : port # saame as the one used by TCP echo server
// arg 3 : message to send

// "./client 1010.113.1.31 23132 Hello"
//

#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <stdio.h>
#include <stdlib.h>
#include <strings.h>
#include <arpa/inet.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

#define BUF_LEN

int main (int argc, char *argv[]){

	int s, n , port_no, r;
	struct sockaddr_in server_addr;
	char *haddr, *message;
	char buf[BUF_LEN+1];

	s = socket (PF_INET, SOCK_STREAM, 0);// CREATE socket for TCP *** not sure if rite PF/AF?

	if (s < 0){
		//something
		printf("there is something wrong with the socket");
	}	
			
	bzero((char *)&server_addr, sizeof(server_addr)); // clear? 

	server_addr.sin_family = AF_INET; //IPv4 Internet fam
	server_addr.sin_addr.s_addr = inet_addr(argv[1]); // dotted addy 
	server_addr.sin_port = htons(atoi(argv[2])); // server port #

	r = connect (s, (struct sockaddr *)&server_addr, sizeof(server_addr)); // CONNECT to server

	// connecting to server doesnt work.
	if (r < 0){
		printf("connecting is fail\n"); 
		exit(1);
	}
		printf("Ajay Patel, T00651990\n");
	printf("~CONNECTED~\n");
	
	printf("arg3 has %s\n", argv[3]); 
	
	//takes input until end of line
	sscanf(argv[3], "%[^\n]s", buf); // put arg 3 into "buf"
	printf("msg has %s\n", buf);
	
	write (s, buf, 512); // write buf to socket
	
	n = read(s,buf, 512); // read buffer
	if (n < 0){
		printf("Buffer empty");
	}
	else {
		buf[n] = '\0';//terminating byte of string
		printf("%s\n", buf);
	}

	close(s);
} // END
