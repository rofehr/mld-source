#include <map>
#include <string>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

using namespace std;

int main(){
  string s = "";
  string buffer = "";
  char buf[256];
  int n;
  
  while((n = read(0,buf,sizeof(buf)-1)) > 0) {
    buf[n]=0;
	s += buf;
  };

  int len = s.length();
  for (int i = 0; i < len; i++) {
      int j = i ;
      char ch = s.at(j);
      if (ch == '%'){
      char tmpstr[] = "0x0__";
          int chnum;
      tmpstr[3] = s.at(j+1);
      tmpstr[4] = s.at(j+2);
      chnum = strtol(tmpstr, NULL, 16);
      buffer += chnum;
      i += 2;
      } else {
       buffer += ch;
      }
   }
   cout << buffer << endl;
   return(0);
}
