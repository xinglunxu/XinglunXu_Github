//
//  SLLNode.h
//  Project for cs130A
//
//  Created by xinglunxu on 7/14/14.
//  Copyright (c) 2014 xinglunxu. All rights reserved.
//
#ifndef Project_for_cs130A_SLLNode_h
#define Project_for_cs130A_SLLNode_h
#include <iostream>

class SLLNode {
private:
    int data;
    SLLNode *next;
public:
    SLLNode(int data){ this->data = data; next = NULL;}
    SLLNode* getNext(){return next;}
    void setNext(SLLNode* SN){next = SN;}
    void Insert( int );
    int DeleteMin( int );
    int getData(){return data;}
};


#endif
