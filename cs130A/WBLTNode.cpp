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
    if(leftchild == NULL || rightchild->getWeight()>leftchild->getWeight())
    {
        WBLTNode* tmp = rightchild;
        rightchild = leftchild;
        leftchild = tmp;
    }
}

WBLTNode::~WBLTNode()
{
    if(leftchild != NULL){delete leftchild; leftchild = NULL;}
    if(rightchild != NULL){delete rightchild; rightchild = NULL;}
}

bool WBLTNode::Check(int* pcount, int value)
{int countl, countll;
    bool resp;
    int leftw, rightw;
    countl=0;
    leftw=rightw=0;
    resp=true;
    if(leftchild != 0) {resp=leftchild->Check(&countll,data)&&resp;
        leftw=leftchild->getWeight();
        countl=countl+countll;
    }
    if(rightchild != 0) {resp=rightchild->Check(&countll,data)&&resp;
        rightw=rightchild->getWeight();
        countl=countl+countll;
    }
    if(leftw < rightw) resp=false;
    if(leftw+rightw+1 != getWeight()) resp=false;
    *pcount=countl+1;
    return resp;
}
