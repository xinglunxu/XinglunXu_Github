#ifndef TRIE_H
#define TRIE_H
#include <iostream>
#include "BasicTrieNode.h"
#include "TrieNode.h"
using namespace std;

class Trie {
private:
    BasicTrieNode *root;
    int CountNum(BasicTrieNode*);
    int useSLL, useWBLT;
public:
    void Create(string s);
    int CountNT();
    Trie(int useSLL, int useWBLT){root = new TrieNode(); this->useSLL = useSLL; this->useWBLT = useWBLT;}
    void PrintNum(string s);
    void DeBug(BasicTrieNode*, string s);
    void deBug();
    void Insert(string, int);
};

#endif
