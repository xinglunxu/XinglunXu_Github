//
//  BasicTrieNode.h
//  Project for cs130A
//
//  Created by xinglunxu on 7/14/14.
//  Copyright (c) 2014 xinglunxu. All rights reserved.
//
#ifndef Project_for_cs130A_BasicTrieNode_h
#define Project_for_cs130A_BasicTrieNode_h
#include <iostream>
#include "SLL.h"
#include "WBLT.h"
#include "MultiSet.h"
using namespace std;

class BasicTrieNode {
private:
    MultiSet *ptr2ms;
    int numOfMS;
public:
    virtual void setPtr(int index, BasicTrieNode* BN){cout<<"Bug";}
    virtual BasicTrieNode* getPtr(int){cout<<"bug in BasicTrieNode.h::getPtr"; return NULL;}
    void setMultiSet(MultiSet *newM){ptr2ms = newM;}
    void addSLL(){ptr2ms = new SLL(ptr2ms); numOfMS++;}
    void addWBLT(){ptr2ms = new WBLT(ptr2ms);numOfMS++;}
    MultiSet* getMultiSet(){return ptr2ms;}
    BasicTrieNode(){ptr2ms = NULL; numOfMS=0;}
    BasicTrieNode(BasicTrieNode* BT){ptr2ms = BT->getMultiSet(); numOfMS=BT->getNumOfMS();}
    BasicTrieNode(MultiSet* ms){ptr2ms = ms; numOfMS = 1;}
    virtual int WhoAmI() {return(0);}
    virtual bool CheckTrie(int*);
    int getNumOfMS(){return numOfMS;}
    void printMS();
    void addMS(int i){numOfMS+=i;}
    ~BasicTrieNode();
    // Changed 7/14
};

#endif
