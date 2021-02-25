// TCP


// argv[1] : port number

// run with "./server "portno"" , example "./server 49163"
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

int main (int argc, char *argv[]){

	int sock_server, sock_client, r, len;
	char buf[512]; // holding message? 
	struct sockaddr_in my_addr, client_addr; // not struct sockaddr type?

	sock_server = socket (PF_INET,SOCK_STREAM,0); // CREATE socket(TCP)
	
	if (sock_server < 0){
		//something
		printf("there is something wrong with the socket");
		exit(1);
	}			
	
		printf("Ajay Patel, T00651990\n");
	printf("Socket is created\n");
	
	bzero(&my_addr, sizeof(my_addr)); // clear?

	my_addr.sin_family = AF_INET; // adress fam internet
	my_addr.sin_port = htons(atoi(argv[1])); // server port # argv2
	my_addr.sin_addr.s_addr = htonl(INADDR_ANY); // accept from anywhere?

	r = bind(sock_server, (struct sockaddr *) &my_addr, sizeof(my_addr));  // bind()
	
	// if the port# given is not good
	if (r < 0) {
		printf("cant bind to port\n");
		exit(1);
	}
	printf("Binded to port\n");
	//listen()
	int listen(int sockfd, int backlog); // converts unconnected socket to passive socket

	r = listen(sock_server, 2);
	printf("Listening for client\n");
	
	if (r < 0){
		printf("CANT CONNECT\n"); 
	}
	else{ //listening for clients
		len = sizeof(client_addr);
		sock_client = accept(sock_server, (struct sockaddr*)&client_addr, &len); // accept()
		printf("Connected to client\n");
		// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		int n;
		n = read (sock_client, buf , 1000); // read message from client
		printf("Client:%s\n", buf); 
		while (n > 0){
			n = read(sock_client, buf , 1000); //returns # of bytes read
			printf("Client:%s\n", buf); 
			//write(sock_client,buf,n); // read() returns neg val for broken connection (<0)
			//connection broke
			//n = read(sock_client, buf , 512);
		}
		printf("Client has disconnected\n");
		close(sock_client);// close client socket 
	}
	printf("Server has disconnected\n");
	close(sock_server);//...; // close server socket
} // END
