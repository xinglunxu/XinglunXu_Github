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