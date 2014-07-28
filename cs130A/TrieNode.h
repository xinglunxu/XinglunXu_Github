//
//  TrieNode.h
//  Project for cs130A
//
//  Created by xinglunxu on 7/14/14.
//  Copyright (c) 2014 xinglunxu. All rights reserved.
//

#ifndef Project_for_cs130A_TrieNode_h
#define Project_for_cs130A_TrieNode_h
#include <iostream>
#include "BasicTrieNode.h"
#define TrieMaxElem 26

class TrieNode: public BasicTrieNode{
private:
    BasicTrieNode *ptr[TrieMaxElem];
public:
    TrieNode();
    TrieNode(BasicTrieNode*);
    void setPtr(int index, BasicTrieNode* BN){ptr[index] = BN;}
    BasicTrieNode* getPtr(int index){return ptr[index];}
    int WhoAmI() {return 1;}
    bool CheckTrie(int*);
    ~TrieNode();
    bool empty();
};

#endif
