//
//  SLL.cpp
//  Project for cs130A
//
//  Created by xinglunxu on 7/14/14.
//  Copyright (c) 2014 xinglunxu. All rights reserved.
//
#include <iostream>
#include "SLL.h"

void SLL::printMember()
{
    SLLNode* current=first;
    while(current!=NULL)
    {
        cout<<" "<<current->getData();
        current=current->getNext();
    }
    cout<<"\n";
}

void SLL::insert(int num)
{
    int n = getNumber();
    if(n == 0)
    {
        first = new SLLNode(num);
    }
    else
    {
        SLLNode* current = first;
        for(int i=1; i<getNumber();i++)
        {
            current = current->getNext();
        }
        current->setNext(new SLLNode(num));
    }
    increaseNum();
}