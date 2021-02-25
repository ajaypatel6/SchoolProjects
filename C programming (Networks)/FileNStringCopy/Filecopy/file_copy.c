#include <stdio.h>
#include <stdlib.h>

#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>

int main (int argc, char* argv[]){
	// need two ins to reread the file after written
	int in,in2, out;
	char buf[1000];
	char buf2[1000];
	
	in = open(argv[1], O_RDONLY);
	
	if (in<0){
		printf("Cant open the file %s\n", argv[1]);
		exit(1);
	}
	
	out = open(argv[2], O_WRONLY | O_TRUNC | O_CREAT, S_IRUSR | S_IWUSR | O_RDONLY);
	
	if (out<0) {
		printf("Cant create the file %s\n", argv[2]);
		exit(1);
	}

	// read some bytes from in and write them into out
	// print bytes onto the screen while copying
	int readfile, readfile2;
	readfile = read (in, buf, 1000);
	//first file
	printf ("buf\n%s\n",buf);
	// put contents into second arg
	write(out, buf, readfile);
	// reopen 2nd file 
	in2 = open(argv[2], O_RDONLY);
	// put 2nd copied file into buffer
	readfile2 = read (in2, buf2, 1000);
	// show 2nd buffe
	printf ("buf2\n%s\n",buf2);

	// test if the two files have the same contents, print result
	// strcmp returns 0 if copied and -1 if it isnt.
	if (strcmp(buf,buf2) == 0){
		printf("Files copied\n");
	} 
	if (strcmp(buf,buf2) < 0){
		printf("Files not copied\n");
	} 
	//
	
	close(in);
	close(out);
	
	return 0;
}
