//
//  TrieNode.cpp
//  Project for cs130A
//
//  Created by xinglunxu on 7/14/14.
//  Copyright (c) 2014 xinglunxu. All rights reserved.
//
#include <iostream>
#include "TrieNode.h"
TrieNode::TrieNode()
{
    for(int i = 0; i<TrieMaxElem;i++)
    {
        ptr[i] = NULL;
    }
}