//
//  WBLTNode.h
//  Project for cs130A
//
//  Created by xinglunxu on 7/14/14.
//  Copyright (c) 2014 xinglunxu. All rights reserved.
//

#ifndef __Project_for_cs130A__WBLTNode__
#define __Project_for_cs130A__WBLTNode__
#include <iostream>

class WBLTNode {
private:
    int data;
    int w;
    WBLTNode *leftchild;
    WBLTNode *rightchild;
public:
    WBLTNode(int data){this->data = data; w = 1; leftchild = NULL; rightchild = NULL;}
    void Insert( int );
    int getWeight(){return w;}
    int getData(){return data;}
    int DeleteMin( int );
    WBLTNode* getRight(){return rightchild;}
    WBLTNode* getLeft(){return leftchild;}
    void setRight(WBLTNode* n){rightchild = n;}
    void setWeight(int w){this->w = w;}
    void adjustBalance();
};


#endif /* defined(__Project_for_cs130A__WBLTNode__) */
