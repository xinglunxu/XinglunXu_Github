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
using namespace std;

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
    virtual MultiSet* changeStruct(){cout<<"bug in multiset.h";return NULL;};
    MultiSet* getPrevious(){return ptr2previousVersion;}
    void setPrevious(MultiSet* ms){ptr2previousVersion = ms;}
    void setNumber(int num){number = num;}
    virtual void DeleteMin(){};
    virtual void PrintMin(){};
    virtual void PrintMax(){};
    virtual int Dist(int i){return 1000;};
    virtual void PrintKth(int i){};
    virtual void DeleteKth(int i){};
    virtual bool Check(int*){return false;};
};


#endif
