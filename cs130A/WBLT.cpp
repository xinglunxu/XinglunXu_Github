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
}

void WBLT::insert(int num)
{
    root = Meld(root, new WBLTNode(num));
    increaseNum();
}

void copy(WBLTNode* WN, SLL* newSLL)
{
    if(WN == NULL){return;}
    else
    {
        copy(WN->getLeft(), newSLL);
        newSLL->insert(WN->getData());
        copy(WN->getRight(), newSLL);
    }
}

MultiSet* WBLT::changeStruct()
{
    SLL* newSLL = new SLL(getPrevious());
    copy(root, newSLL);
    newSLL->setNumber(getNumber());
    return newSLL;
}

void WBLT::eat(WBLT* w)
{
    root = Meld(root, w->getRoot());
    setNumber(root->getWeight());
    w->setRoot(NULL);
    w->setNumber(0);
}

void WBLT::DeleteMin()
{
    WBLTNode *tmp = root;
    root = Meld(root->getLeft(), root->getRight());
    tmp->setLeft(NULL); tmp->setRight(NULL);
    delete tmp; tmp = NULL;
    setNumber(getNumber()-1);
}

void WBLT::PrintMin()
{
    cout<<root->getData()<<"\n";
}

int getLargest(WBLTNode *WN)
{
    if(WN->getLeft() == NULL){return WN->getData();}
    else if(WN->getRight() == NULL){return getLargest(WN->getLeft());}
    else
    {
        int l = getLargest(WN->getLeft());
        int r = getLargest(WN->getRight());
        if(l>r){return l;}
        else{return r;}
    }
}

void WBLT::PrintMax()
{
    cout<<getLargest(root)<<"\n";
}

int dist(WBLTNode *WN, int most)
{
    int count = 0;
    if(WN->getData()<=most){count++;}
    if(WN->getLeft() != NULL){count+=dist(WN->getLeft(), most);}
    if(WN->getRight() != NULL){count+=dist(WN->getRight(), most);}
    return count;
}

int WBLT::Dist(int i)
{
    int most = root->getData()+i;
    return dist(root, most);
}

void WBLT::PrintKth(int i)
{
    MultiSet *newS = this->changeStruct();
    newS->PrintKth(i);
    delete newS; newS = NULL;
}

void WBLT::DeleteKth(int i)
{
    MultiSet *newS = this->changeStruct();
    newS->DeleteKth(i);
    WBLT *newW = dynamic_cast<WBLT*>(newS->changeStruct());
    delete newS; newS = NULL;
    delete root; root = NULL;
    root = newW->getRoot();
    newW->setRoot(NULL);
    setNumber(newW->getNumber());
    delete newW; newW = NULL;
}

bool WBLT::Check(int* pcount)
{bool temp;
    int countl;
    if(root == 0 && getNumber()==0) return true;   //Changed 7/10 Next
    if(root == 0) return false;                    //Changed 7/10 Next
    temp=root->Check(&countl,-1);
    if(getNumber() != countl) temp=false;
    *pcount=countl;
    return temp;
}

WBLT::~WBLT(){if(root != NULL){delete root; root = NULL;}}



