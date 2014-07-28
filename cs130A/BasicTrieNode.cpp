//
//  BasicTrieNode.cpp
//  Project for cs130A
//
//  Created by xinglunxu on 7/14/14.
//  Copyright (c) 2014 xinglunxu. All rights reserved.
//
#include <iostream>
#include "BasicTrieNode.h"

void BasicTrieNode::printMS()
{
    if(ptr2ms == NULL){cout<<"No multisets\n";}
    else{ptr2ms->printMember();}
    
}

BasicTrieNode::~BasicTrieNode(){if(ptr2ms!=NULL){delete ptr2ms; ptr2ms = NULL;}}

bool BasicTrieNode::CheckTrie(int* pcount)
{if(ptr2ms == 0) {return false;}
    *pcount=*pcount+1;                             // Changed 7/10
    return true;
}