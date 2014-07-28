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
    Trie* T = new Trie(useSLL, useWBLT);
    while(1 == 1)
    { cin >> oper;
        
        if (oper == "Quit")
        {// delete everything then end program
            delete T; T = NULL;
            break;
        }
        
        else if(oper == "DeleteSF")
        {
            cin>>s>>t; T->DeleteSF(s, t);
        }
        
        else if(oper == "PrintNumSF")
        {
            cin>>s>>t; T->PrintNumSF(s, t);
        }
        
        else if(oper == "CountN")
        {
            cout<<T->CountN()<<"\n";
        }
        
        else if(oper == "Delete")
        {
            cin>>s; T->Delete(s);
        }
        
        else if(oper == "DeleteKth")
        {
            cin>>s>>x; T->DeleteKth(s, x);
        }
        
        else if(oper == "PrintKth")
        {
            cin>>s>>x; T->PrintKth(s, x);
        }
        
        else if(oper == "PrintMin")
        {
            cin>>s; T->PrintMin(s);
        }
        
        else if(oper == "PrintMax")
        {
            cin>>s; T->PrintMax(s);
        }
        
        else if(oper == "DeleteMin")
        {
            cin>>s; T->DeleteMin(s);
        }
        
        else if(oper == "DeleteAll")
        {
            cin>>s; T->DeleteAll(s);
        }
        
        else if(oper == "Dist")
        {
            cin >>s>>x; T->Dist(s, x);
        }
        
        else if(oper == "Create")
        {
            cin >> s; T->Create(s);
        }
        
        else if(oper == "Merge")
        {
            cin>>s>>t;
            T->Merge(s, t);
        }
        
        else if(oper == "PrintNum")
        {
            cin>>s;
            T->PrintNum(s);
        }
        
        else if(oper == "Debug")
        {
            T->deBug();
            //Pause
        }
        
        else if(oper == "CountNT")
        {
            cout<<T->CountNT()<<"\n";
        }
        
        else if (oper == "Insert")
        { cin >> s >> x;
            T->Insert(s,x);
            //pause
        }
        
        else if (oper == "Check")
        {cin >> s;
            T->Check(s);
        }
        
        else if (oper == "CheckTrie")
        {
            int *n = new int();
            if(T->CheckTrie(n)){cout<<"True";}
            else{cout<<"False";}
            cout<<" "<<*n<<"\n";
        }
        
        
        
        // ...
    }
}

