//
//  MultiSet.h
//  Project for cs130A
//
//  Created by xinglunxu on 7/14/14.
//  Copyright (c) 2014 xinglunxu. All rights reserved.
//
#ifndef Project_for_cs130A_MultiSet_h
#define Project_for_cs130A_MultiSet_h
#include <iostream>

class MultiSet{
private:
    int number;
    MultiSet* ptr2previousVersion;
public:
    MultiSet(MultiSet* ms){number = 0; ptr2previousVersion = ms;}
    MultiSet(){number = 0; ptr2previousVersion = NULL;}
    virtual int WhoAmI(){return 0;};
    int getNumber(){return number;}
    virtual void insert(int){};
    void increaseNum(){number++;}
    virtual void printMember(){};
};


#endif
