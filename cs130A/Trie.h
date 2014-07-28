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
    void adjustTrie(string);
    void DeleteNode(string);
    bool RecurDeleteSF(string,string,int,BasicTrieNode*);
public:
    void Create(string s);
    int CountN();
    int CountNT();
    Trie(int useSLL, int useWBLT){root = new TrieNode(); this->useSLL = useSLL; this->useWBLT = useWBLT;}
    void PrintNum(string s);
    void DeBug(BasicTrieNode*, string s);
    void deBug();
    void Insert(string, int);
    WBLT* SLLtoWBLT(MultiSet*);
    SLL* WBLTtoSLL(MultiSet*);
    void Merge(string, string);
    BasicTrieNode* getNode(string);
    void Delete(string);
    void DeleteAll(string);
    ~Trie();
    void DeleteMin(string);
    void PrintMin(string);
    void PrintMax(string);
    void Dist(string, int);
    void PrintKth(string,int);
    void DeleteKth(string,int);
    void PrintNumSF(string,string);
    void DeleteSF(string,string);
    void Check(string);
    bool CheckTrie(int*);
};

#endif
