//
//  TrieNode.cpp
//  Project for cs130A
//
//  Created by xinglunxu on 7/14/14.
//  Copyright (c) 2014 xinglunxu. All rights reserved.
//
#include <iostream>
#include "TrieNode.h"
TrieNode::TrieNode(BasicTrieNode* BT):BasicTrieNode(BT)
{
    for(int i = 0; i<TrieMaxElem;i++)
    {
        ptr[i] = NULL;
    }
}

TrieNode::TrieNode()
{
    for(int i = 0; i<TrieMaxElem;i++)
    {
        ptr[i] = NULL;
    }
}

TrieNode::~TrieNode()
{
    for(int i = 0; i<TrieMaxElem; i++)
    {if(ptr[i]!=NULL){delete ptr[i]; ptr[i] = NULL;}}
}

bool TrieNode::empty()
{
    for(int i; i<TrieMaxElem; i++){if(ptr[i] != NULL){return false;}}
    return true;
}

bool TrieNode::CheckTrie(int* pcount)
{
    bool           result;
    int            i,countl,countll;
    countl= *pcount;
    result=true;
    if (getMultiSet() != 0) *pcount= *pcount+1;
    for(i=0; i<TrieMaxElem; i++)
    {if(getPtr(i)!=0)
        if(!getPtr(i)->CheckTrie(pcount)) {return false;}
    }
    if(countl == *pcount || (countl+1 == *pcount && getMultiSet() != 0)) return false; // Changed 7/10
    return true;
}