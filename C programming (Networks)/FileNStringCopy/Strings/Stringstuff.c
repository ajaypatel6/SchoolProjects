#include <stdio.h>
#include <stdlib.h>
#include "Strings.c"

int main (int argc, char* argv[]){
	
	char source[512];
	char sid[10] = {'T','0','0','6','5','1','9','9','0','\0'}; 
	char destination[512];
	
	printf("Type something (a string without spaces): ");
	scanf("%s",source);
	
	// prints for two variables and length
	printf("you entered:%s (length:%d) and your student ID is:%s (length:%d)\n",source,string_length(source),sid,string_length(sid));
	
	// prints to show uncopied - copied string
	printf("string1:%s not copied string2:%s \n",source,destination);
	string_copy(destination, source);
	printf("string1:%s copied string2:%s \n",source,destination);
	
	//prints to show added string (concatenation)
	printf("string 1:%s + string 2:%s \n", destination, source);
	string_cat (destination, source);
	printf("=%s\n", destination);
	
	return 0;
}
