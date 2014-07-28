//
//  SLLNode.cpp
//  Project for cs130A
//
//  Created by xinglunxu on 7/14/14.
//  Copyright (c) 2014 xinglunxu. All rights reserved.
//
#include <iostream>
#include "SLLNode.h"
SLLNode::~SLLNode(){if(next != NULL){delete next; next = NULL;}}

bool SLLNode::Check(int* pcount, int val)
{ int countl;
    if(val > getData()) return false;
    if(getNext() == 0) {*pcount=1; return true;}
    if(!getNext()->Check(&countl,getData())) return false;
    *pcount=countl+1;
    return true;
}