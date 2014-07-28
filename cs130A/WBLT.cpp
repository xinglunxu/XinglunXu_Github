//
//  WBLT.cpp
//  Project for cs130A
//
//  Created by xinglunxu on 7/14/14.
//  Copyright (c) 2014 xinglunxu. All rights reserved.
//
#include <iostream>
#include "WBLT.h"

void WBLT::printMember()
{
    printmember(root);
    cout<<"\n";
}

void WBLT::printmember(WBLTNode* WN)
{
    if(WN == NULL){return;}
    else
    {
        printmember(WN->getLeft());
        cout<<" "<<WN->getData();
        printmember(WN->getRight());
    }
}

WBLTNode* WBLT::Meld(WBLTNode *a, WBLTNode *b)
{
    if(a==NULL){return b;}
    
    int aData = a->getData(), bData = b->getData(),
    aWeight = a->getWeight(), bWeight = b->getWeight();
    
    if(aData<bData)
    {
        a->setRight(Meld(a->getRight(), b));
        a->setWeight(aWeight+bWeight);
        a->adjustBalance();
        return a;
    }
    else
    {
        b->setRight(Meld(b->getRight(),a));
        b->setWeight(bWeight+aWeight);
        b->adjustBalance();
        return b;
    }
    increaseNum();
}

void WBLT::insert(int num)
{
    root = Meld(root, new WBLTNode(num));
}