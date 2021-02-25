#include <stdio.h>
#include <stdlib.h>

	//copy string1 to string2
	void string_copy (char *destination, char *source){
		int i;
		// run until source is at end of its contents, and put into location at destination
		for (int i=0; source[i]!='\0'; i++){
			destination[i]=source[i];
		}
	
		destination[i] = '\0';
	}
	
	//find lenght of string
	int string_length (char *s){
		int count = 0;
		
		for (int i=0; s[count]!='\0'; i++){
			count++;
		}
	
		return count;
	}
	
	//add strings
	void string_cat (char *destination, char *source){
		int i;
		int j = 0;
		
		// length function
		int count = 0;
		
		for (int i=0; source[count]!='\0'; i++){
			count++;
		}
		// 
	
		for (i=0; source[i]!='\0'; i++){
			destination[i]=source[i];
		}

		while (count!=0){
			destination[i] = source[j];
			count--;
			j++;
			i++;
		}

		destination[i] = '\0';
	} 