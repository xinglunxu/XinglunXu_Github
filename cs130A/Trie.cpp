//
//  Trie.cpp
//  Project for cs130A
//
//  Created by xinglunxu on 7/14/14.
//  Copyright (c) 2014 xinglunxu. All rights reserved.
//
#include <iostream>
#include "Trie.h"

void Trie::deBug()
{
    DeBug(root, "");
}

void Trie::DeBug(BasicTrieNode* BN, string s)
{
    if(BN == NULL){return;}
    if(BN->WhoAmI() == 0)
    {
        cout<<s<<" ";
        BN->printMS();
        return;
    }
    if(BN->WhoAmI() == 1)
    {
        if(BN != root)
        {
            cout<<s<<" ";
            BN->printMS();
        }
        for(int i = 0; i<TrieMaxElem; i++)
        {
            char c = 'a' + i;
            DeBug(BN->getPtr(i),s+c);
        }
        return;
    }
}




void Trie::Create(string s)
{
    BasicTrieNode *current = root;
    BasicTrieNode *tmp = NULL;
    for(int i = 0; i<s.length()-1;i++)
    {
        int index = s.at(i) - 'a';
        tmp = current->getPtr(index);
        if(tmp == NULL)
        {
            TrieNode* newTN = new TrieNode();
            current->setPtr(index, newTN);
        }
        else if(!tmp->WhoAmI())
        {
            TrieNode *newTN = new TrieNode();
            newTN->setMultiSet(tmp->getMultiSet());
            delete tmp;
            current->setPtr(index, newTN);
        }
        current = current->getPtr(index);
    }
    
    int i =s.at(s.length()-1)-'a';
    tmp = current->getPtr(i);
    if(tmp == NULL)
    {
        if(useSLL==0)
        {
            current->setPtr(i, new BasicTrieNode(new WBLT()));
        }
        else
            {
            current->setPtr(i, new BasicTrieNode(new SLL()));
            }
    }
    else
    {
        if(useSLL==0){tmp->addWBLT();}
        else{tmp->addSLL();}
    }
}

int Trie::CountNT()
{
    return CountNum(root);
}

int Trie::CountNum(BasicTrieNode* BN)
{
    int count = 0;
    if(BN->WhoAmI() == 1)
    {
        count+=BN->getNumOfMS();
        for(int i=0; i<TrieMaxElem; i++)
        {
            if(BN->getPtr(i) != NULL)
            {
                count+=CountNum(BN->getPtr(i));
            }
        }
        return count;
    }
    else{return count+=BN->getNumOfMS();}
}

void Trie::PrintNum(string s)
{
    BasicTrieNode* current = root;
    for(char c:s)
    {
        int index = c - 'a';
        current->getPtr(index);
    }
    cout<<current->getMultiSet()->getNumber()<<"\n";
}

void Trie::Insert(string s, int num)
{
    BasicTrieNode* current = root;
    for(char c:s)
    {
        int index = c - 'a';
        current = current->getPtr(index);
    }
    current->getMultiSet()->insert(num);
}


