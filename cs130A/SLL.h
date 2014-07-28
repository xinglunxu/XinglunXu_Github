//
//  SLL.h
//  Project for cs130A
//
//  Created by xinglunxu on 7/14/14.
//  Copyright (c) 2014 xinglunxu. All rights reserved.
//
#ifndef Project_for_cs130A_SLL_h
#define Project_for_cs130A_SLL_h
#include <iostream>
#include "MultiSet.h"
#include "SLLNode.h"
#include "WBLT.h"
using namespace std;

class SLL: public MultiSet{
private:
    SLLNode* first;
public:
    SLL(){ first = NULL;}
    SLL(MultiSet* ms):MultiSet(ms){first = NULL;}
    int WhoAmI(){return 0;}
    void insert(int);
    void printMember();
    MultiSet* changeStruct();
    void eat(SLL*);
    SLLNode* getFirst(){return first;}
    void setFirst(SLLNode* SN){first = SN;}
    void DeleteMin();
    void PrintMin();
    void PrintMax();
    int Dist(int);
    void PrintKth(int);
    void DeleteKth(int);
    bool Check(int*);
    ~SLL();
};


#endif
