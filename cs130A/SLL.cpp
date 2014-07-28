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
    SLLNode* newSLLN = new SLLNode(num);
    int n = getNumber();
    if(n == 0)
    {
        first = newSLLN;
    }
    else if(num<first->getData())
    {
        newSLLN->setNext(first);
        first = newSLLN;
    }
    else
    {
        SLLNode* current = first;
        SLLNode* parent = first;
        while(current != NULL && num>=current->getData())
        {
            parent = current;
            current = current->getNext();
        }
        
        newSLLN->setNext(current);
        parent->setNext(newSLLN);
    }
    increaseNum();
}

void CreateW(WBLTNode* WN, int nums[], int total, int index)
{
    int left = index*2+1, right = index*2+2;
    if(left>total-1){return;}
    else if(left == total-1)
    {
        WN->setLeft(new WBLTNode(nums[left]));
        WN->setWeight(2);
    }
    else if(right == total -1)
    {
        WN->setLeft(new WBLTNode(nums[left]));
        WN->setRight(new WBLTNode(nums[right]));
        WN->setWeight(3);
    }
    else
    {
        WN->setLeft(new WBLTNode(nums[left]));
        WN->setRight(new WBLTNode(nums[right]));
        CreateW(WN->getLeft(), nums, total, left);
        CreateW(WN->getRight(), nums, total, right);
        WN->setWeight(WN->getLeft()->getWeight()+WN->getRight()->getWeight()+1);
    }
}


MultiSet* SLL::changeStruct()
{
    int total = getNumber();
    int *nums = new int[total];
    SLLNode* tmp = first;
    for(int i=0; i<total; i++)
    {
        nums[i] = tmp->getData();
        tmp = tmp->getNext();
    }
    WBLT* w = new WBLT(getPrevious());
    w->setRoot(new WBLTNode(nums[0]));
    CreateW(w->getRoot(), nums, total, 0);
    w->setNumber(total);
    delete nums; nums = NULL;
    return w;
}

SLLNode* MergeSLLs(SLLNode* n1, SLLNode* n2)
{
    SLLNode* newNode = n1;
    SLLNode* deletedNode = n2;
    SLLNode *current = newNode->getNext(), *parent = newNode;
    if(n1->getData()>n2->getData()){newNode = n2; deletedNode = n1;}
    while(current != NULL && deletedNode !=NULL)
    {
        if( parent->getData() <= deletedNode->getData() && deletedNode->getData()<= current->getData())
        {
            parent->setNext(deletedNode);
            deletedNode = deletedNode->getNext();
            parent->getNext()->setNext(current);
            parent = parent->getNext();
            continue;
        }
        parent = current, current = current->getNext();
    }
    if(current == NULL){parent->setNext(deletedNode);}
    return newNode;
}

void SLL::eat(SLL *s)
{
    if(first == NULL){first = s->getFirst();}
    else if(s->getFirst() == NULL){return;}
    else{first = MergeSLLs(first, s->getFirst());}
    setNumber(getNumber()+s->getNumber());
    s->setNumber(0);
    s->setFirst(NULL);    
}

void SLL::DeleteMin()
{
    SLLNode *tmp = first;
    first = first->getNext();
    tmp->setNext(NULL);
    delete tmp;
    setNumber(getNumber()-1);
}

void SLL::PrintMin()
{
    cout<<first->getData()<<"\n";
}

void SLL::PrintMax()
{
    SLLNode *current = first;
    while(current->getNext() != NULL){current = current->getNext();}
    cout<<current->getData()<<"\n";
}

int SLL::Dist(int i)
{
    int most = first->getData()+i;
    int count=0;
    SLLNode *current = first;
    while(current != NULL)
    {
        if(current->getData()<=most){count++;}
        current = current->getNext();
    }
    return count;
}

void SLL::PrintKth(int i)
{
    SLLNode *current = first;
    for(int index = 0; index<i-1; index++){current = current->getNext();}
    cout<<current->getData()<<"\n";
}

void SLL::DeleteKth(int i)
{
    if(i == 1)
    {
        SLLNode *tmp = first;
        first = first ->getNext();
        tmp->setNext(NULL);
        delete tmp;
    }
    else
    {
        SLLNode *parent = first;
        for(int index = 0; index<i-2; index++){parent = parent->getNext();}
        SLLNode *current = parent->getNext();
        parent->setNext(current->getNext());
        current->setNext(NULL);
        delete current;
    }
    setNumber(getNumber()-1);
}

bool SLL::Check(int* pcount)
{int countl;
    *pcount=getNumber();
    if(*pcount == 0 && getFirst()== 0) return true;
    if(getFirst()== 0) return false;                 //Changed 7/10 Next
    if(!getFirst()->Check(&countl,-1)) return false;
    if(getNumber() != countl) return false;
    return true;
}

SLL::~SLL(){if(first != NULL){delete first; first = NULL;}}



