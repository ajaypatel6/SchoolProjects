#include <stdio.h> 
#include <stdlib.h>
#include <string.h>

#define SIZE 128 // final

int tes = 5; // global, public instance

int main(int argc, char* argv[])

{
	int i;
	char name[32];
	
	printf("Welcome to C!\n");
	printf("%d arguments:\n", argc);
	
	for (i=0; i<argc; i++)
		printf("\t%s\n", argv[i]);
		
	name[0] = 'C';
	name[1] = 'o';
	name[2] = 'm';
	name[3] = 'p';
	name[4] = 'u';
	name[5] = 't';
	name[6] = 'e';
	name[7] = 'e';	
	name[8] = '\0';
	name[9] = 'r';
	
	printf("The length of %s is %lu.\n", name, strlen(name));
	}
