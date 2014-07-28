//
//  WBLT.h
//  Project for cs130A
//
//  Created by xinglunxu on 7/14/14.
//  Copyright (c) 2014 xinglunxu. All rights reserved.
//

#ifndef Project_for_cs130A_WBLT_h
#define Project_for_cs130A_WBLT_h
#include <iostream>
#include "MultiSet.h"
#include "WBLTNode.h"
using namespace std;

class WBLT: public MultiSet{
private:
    WBLTNode* root;
public:
    WBLT(){ root = NULL;}
    WBLT(MultiSet* ms):MultiSet(ms){root = NULL;}
    int WhoAmI(){return 3;}
    WBLTNode* Meld(WBLTNode* a, WBLTNode* b);
    void insert(int num);
    void printMember();
    void printmember(WBLTNode*);
};


#endif
