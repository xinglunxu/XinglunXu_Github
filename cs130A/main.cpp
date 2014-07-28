#include <iostream>
#include <string>
#include <stdlib.h>
#include "Trie.h"
using namespace std;

int main(void)
{
    string oper,s,t;
    int x,k;
    int useSLL, useWBLT;
    // ...
    cin >> useSLL >> useWBLT;
    Trie T(useSLL, useWBLT);
    while(1 == 1)
    { cin >> oper;
        
        if (oper == "Quit") {// delete everything then end program
        }
        
        else if(oper == "Create")
        {
            cin >> s;
            T.Create(s);
        }
        
        else if(oper == "Debug")
        {
            T.deBug();
            //Pause
        }
        
        else if(oper == "CountNT")
        {
            cout<<T.CountNT()<<"\n";
        }
        
        else if (oper == "Insert")
        { cin >> s >> x;
            T.Insert(s,x);
            //pause
        }
        
        else if (oper == "Check")
        {cin >> s;
            //...
        }
        
        else if (oper == "CheckTrie")
        {
            //...
        }
        
        
        else if (oper == "PrintKth")
        { cin >> s >> k;
            //...
        }
        
        // ...
    }
}

