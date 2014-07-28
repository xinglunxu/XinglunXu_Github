//
//  WBLTNode.cpp
//  Project for cs130A
//
//  Created by xinglunxu on 7/14/14.
//  Copyright (c) 2014 xinglunxu. All rights reserved.
//
#include <iostream>
#include "WBLTNode.h"

void WBLTNode::adjustBalance()
{
    if(rightchild->getWeight()>leftchild->getWeight())
    {
        WBLTNode* tmp = rightchild;
        rightchild = leftchild;
        leftchild = tmp;
    }
}