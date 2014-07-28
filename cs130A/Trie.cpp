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
            TrieNode *newTN = new TrieNode(tmp);
            tmp->setMultiSet(NULL); delete tmp; tmp =NULL;
            current->setPtr(index, newTN);
        }
        current = current->getPtr(index);
    }
    
    int i =s.at(s.length()-1)-'a';
    tmp = current->getPtr(i);
    if(tmp == NULL)
    {
        if(useSLL==0)
        {current->setPtr(i, new BasicTrieNode(new WBLT()));}
        else
        {current->setPtr(i, new BasicTrieNode(new SLL()));}
    }
    else
    {
        if(useSLL==0){tmp->addWBLT();}
        else{tmp->addSLL();}
    }
}





int CountNum2(BasicTrieNode* BN)
{
    int count = 0;
    if(BN->WhoAmI() == 1)
    {
        if(BN->getMultiSet() != NULL){count++;}
        for(int i=0; i<TrieMaxElem; i++)
        {
            if(BN->getPtr(i) != NULL)
            {count+=CountNum2(BN->getPtr(i));}
        }
        return count;
    }
    else{return 1;}
}

int Trie::CountN()
{
    return CountNum2(root);
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
            {count+=CountNum(BN->getPtr(i));}
        }
        return count;
    }
    else{return count+=BN->getNumOfMS();}
}

int Trie::CountNT()
{
    return CountNum(root);
}

void Trie::PrintNum(string s)
{
    if(getNode(s)==NULL||getNode(s)->getMultiSet()==NULL){return;}
    BasicTrieNode* current = root;
    for(int i = 0; i<s.length(); i++)
    {
        int index = s.at(i) - 'a';
        current = current->getPtr(index);
    }
    if(current->getMultiSet() == NULL){return;}
    cout<<current->getMultiSet()->getNumber()<<"\n";
}

void Trie::Insert(string s, int num)
{
    BasicTrieNode* current = getNode(s);
    if(current==NULL){return;}
    MultiSet* ms = current->getMultiSet();
    if(ms==NULL){return;}
    if(useSLL != 0 && ms->getNumber() == useWBLT-1 && ms->WhoAmI() == 0)
    {
        current->setMultiSet(ms->changeStruct());
        delete ms; ms = NULL;
    }
    current->getMultiSet()->insert(num);
}

BasicTrieNode* Trie::getNode(string s)
{
    if(s.length() == 0){return root;}
    BasicTrieNode* current = root;
    for(int i = 0;i<s.length(); i++)
    {
        int index = s.at(i) - 'a';
        if(current->WhoAmI() == 1 && current->getPtr(index) != NULL)
        {current = current->getPtr(index);}
        else{current = NULL; break;}
    }
    return current;
}

void Trie::DeleteNode(string s)
{
    BasicTrieNode *parent = getNode(s.substr(0, s.length()-1));
    getNode(s)->setMultiSet(NULL);
    delete getNode(s);
    parent->setPtr(s.at(s.length()-1)-'a', NULL);
}

void MergeWBLTs(MultiSet* a, MultiSet* b)
{
    WBLT* w = dynamic_cast<WBLT*>(a), *y = dynamic_cast<WBLT*>(b);
    w->eat(y);
}

void MergeWBLTSLL(MultiSet* a, MultiSet* b)
{
    WBLT *w = dynamic_cast<WBLT*>(a);
    WBLT *y = dynamic_cast<WBLT*>(b);
    w->eat(y);
}

void MergeSLLs(MultiSet* a, MultiSet* b)
{
    SLL *w = dynamic_cast<SLL*>(a);
    SLL *y = dynamic_cast<SLL*>(b);
    w->eat(y);
}

void Trie::Merge(string s, string t)
{
    BasicTrieNode* n1 = getNode(s), *n2 = getNode(t);
    if(n1==NULL||n2==NULL){return;}
    MultiSet *ms1 = n1->getMultiSet();
    MultiSet *ms2 = n2->getMultiSet();
    if(ms1==NULL||ms2==NULL){return;}
    if(ms1->WhoAmI() == 1 && ms2->WhoAmI() == 1)
    {
        MergeWBLTs(ms1, ms2);
    }
    else if(ms1->WhoAmI() ==1 && ms2->WhoAmI() == 0)
    {
        n2->setMultiSet(ms2->changeStruct());
        delete ms2; ms2 = NULL;
        ms2 = n2->getMultiSet();
        MergeWBLTSLL(ms1,ms2);
    }
    else if(ms1->WhoAmI() == 0 && ms2->WhoAmI() == 1)
    {
        n1->setMultiSet(ms1->changeStruct());
        delete ms1; ms1 = NULL;
        ms1 = n1->getMultiSet();
        MergeWBLTSLL(ms1, ms2);
    }
    else if(ms1->WhoAmI() == 0 &&ms2->WhoAmI() == 0)
    {
        if(ms1->getNumber()+ms2->getNumber()>=useWBLT)
        {
            n1->setMultiSet(ms1->changeStruct());
            delete ms1; ms1 = NULL;
            ms1 = n1->getMultiSet();
            n2->setMultiSet(ms2->changeStruct());
            delete ms2; ms2 = NULL;
            ms2 = n2->getMultiSet();
            MergeWBLTs(ms1, ms2);
        }
        else{MergeSLLs(ms1, ms2);}
    }
}

//void RecursiveDelete(BasicTrieNode* BN)
//{
//    if(BN->WhoAmI() == 1)
//    {
//        BasicTrieNode* tmp = NULL;
//        for(int i = 0; i<TrieMaxElem; i++)
//        {
//            tmp = BN->getPtr(i);
//            if(tmp!=NULL){RecursiveDelete(tmp);};
//        }
//        delete BN;
//    }
//    else{delete BN;}
//}

Trie::~Trie(){delete root; root = NULL;}

void Trie::Delete(string s)
{
    BasicTrieNode *tmp = getNode(s);
    if(tmp==NULL){return;}
    MultiSet *ms = tmp->getMultiSet();
    if(ms==NULL){return;}
    tmp->setMultiSet(ms->getPrevious());
    tmp->addMS(-1);
    delete ms; ms = NULL;
    if(tmp->WhoAmI() == 0 && tmp->getNumOfMS() == 0)
    {
        DeleteNode(s);
        adjustTrie(s.substr(0, s.length()-1));
    }
    
}

void Trie::DeleteAll(string s)
{
    BasicTrieNode *tmp = getNode(s);
    if(tmp==NULL){return;}
    MultiSet *ms = tmp->getMultiSet();
    if(ms==NULL){return;}
    MultiSet *pre = NULL;
    while(ms!=NULL)
    {
        pre = ms->getPrevious();
        delete ms; ms = NULL; ms = pre;
    }
    tmp->addMS(-(tmp->getNumOfMS()));
    tmp->setMultiSet(NULL);
    if(tmp->WhoAmI() == 0)
    {DeleteNode(s);adjustTrie(s.substr(0, s.length()-1));}
    
}

void Trie::adjustTrie(string s)
{
    if(s.length() == 0){return;}
    int l = s.length();
    TrieNode *parent = NULL;
    TrieNode *current = NULL;
    string newS1 = "";
    string newS2 = "";
    BasicTrieNode *newNode = NULL;
    while(l != 0)
    {
        newS1 = s.substr(0, l);
        newS2 = s.substr(0, l-1);
        current = dynamic_cast<TrieNode*>(getNode(newS1));
        parent = dynamic_cast<TrieNode*>(getNode(newS2));
        if(current->empty() && current->getNumOfMS()==0)
        {DeleteNode(newS1);}
        else if(current->empty())
        {
            newNode = new BasicTrieNode(getNode(newS1));
            DeleteNode(newS1);
            parent->setPtr(newS1.at(l-1)-'a', newNode);
        }
        else{break;}
        l--;
    }
}

void Trie::DeleteMin(string s)
{
    BasicTrieNode *BN = getNode(s);
    if(BN==NULL){return;}
    MultiSet *ms = BN->getMultiSet();
    if(ms==NULL||ms->getNumber()==0){return;}
    ms->DeleteMin();
    if(ms->WhoAmI() == 1 && ms->getNumber() == useSLL)
    {
        BN->setMultiSet(ms->changeStruct());
        delete ms; ms = NULL;
    }
}

void Trie::PrintMin(string s)
{
    if(getNode(s)==NULL||getNode(s)->getMultiSet()==NULL||getNode(s)->getMultiSet()->getNumber()==0){return;}
    getNode(s)->getMultiSet()->PrintMin();
}

void Trie::PrintMax(string s)
{
    if(getNode(s)==NULL||getNode(s)->getMultiSet()==NULL||getNode(s)->getMultiSet()->getNumber()==0){return;}
    getNode(s)->getMultiSet()->PrintMax();
}

void Trie::Dist(string s, int i)
{
    if(getNode(s)==NULL||getNode(s)->getMultiSet()==NULL){return;}
    cout<<getNode(s)->getMultiSet()->Dist(i)<<"\n";
}

void Trie::PrintKth(string s, int i)
{
    if(getNode(s)==NULL||getNode(s)->getMultiSet()==NULL){return;}
    if(getNode(s)->getMultiSet()->getNumber()<i){return;}
    getNode(s)->getMultiSet()->PrintKth(i);
}

void Trie::DeleteKth(string s, int i)
{
    if(getNode(s)==NULL||getNode(s)->getMultiSet()==NULL){return;}
    if(getNode(s)->getMultiSet()->getNumber()<i){return;}
    getNode(s)->getMultiSet()->DeleteKth(i);
}

int RecurPrintNumSF(string s, string t, int i, BasicTrieNode *BN)
{
    int l, r, count=0;
    count += BN->getNumOfMS();
    if(BN->WhoAmI() == 0){return count;}
    if(i >= t.length()){l=0; r=TrieMaxElem-1;}
    else if(i >= s.length()){l = 0; r = t.at(i) - 'a';}
    else if(s.at(i)>t.at(i)){l=0; r=TrieMaxElem-1;}
    else{l = s.at(i) - 'a'; r = t.at(i) - 'a';}
    for(int index = l; index<=r; index++)
    {
        if(BN->getPtr(index) != NULL)
        {count += RecurPrintNumSF(s, t, i+1, BN->getPtr(index));}
    }
    return count;
}

void Trie::PrintNumSF(string s, string t)
{
    int num = RecurPrintNumSF(s, t, 0, root);
    if(getNode(s)!=NULL && getNode(s)->getMultiSet()!=NULL){num-=getNode(s)->getNumOfMS();}
    if(getNode(t)!=NULL && getNode(t)->getMultiSet()!=NULL){num-=getNode(t)->getNumOfMS();}
    cout<<num<<"\n";
}

bool compareNodes(BasicTrieNode *a, BasicTrieNode *b, BasicTrieNode* BN)
{
    return BN != a && BN != b;
}

bool Trie::RecurDeleteSF(string s, string t, int i, BasicTrieNode *BN)
{
    int l, r;
    if(BN->getMultiSet()!=NULL && compareNodes(getNode(s), getNode(t), BN))
    {
        BN->setMultiSet(BN->getMultiSet()->getPrevious());
        BN->addMS(-1);
    }
    if(BN->WhoAmI() == 0 && BN->getNumOfMS()==0){return true;}
    if(BN->WhoAmI() == 0){return false;}
    if(i >= t.length()){l=0; r=TrieMaxElem-1;}
    else if(i >= s.length()){l = 0; r = t.at(i) - 'a';}
    else if(s.at(i)>t.at(i)){l=0; r=TrieMaxElem-1;}
    else{l = s.at(i) - 'a'; r = t.at(i) - 'a';}
    for(int index = l; index<=r; index++)
    {
        if(BN->getPtr(index) != NULL)
        {
            if(RecurDeleteSF(s, t, i+1, BN->getPtr(index)))
            {delete BN->getPtr(index); BN->setPtr(index, NULL);}
        }
    }
    TrieNode *TN = dynamic_cast<TrieNode*>(BN);
    if(TN->empty() && BN->getNumOfMS()==0 && compareNodes(getNode(s), getNode(t),BN)){return true;}
    return false;
}

void Trie::DeleteSF(string s, string t)
{
    RecurDeleteSF(s, t, 0, root);
}

void Trie::Check(string s)
{
    int *n = new int();
    MultiSet *ms = getNode(s)->getMultiSet();
    if(ms->Check(n)){cout<<"True"<<" ";}
    else{cout<<"False"<<" ";}
    cout<<*n<<" ";
    if(ms->WhoAmI() == 0){cout<<1<<"\n";}
    else{cout<<2<<"\n";}
}

bool Trie::CheckTrie(int* count)
{
    int countl;
    bool result;
    countl= 0;                               
    if (root == 0) { *count=0; return true;}
    result = root->CheckTrie(&countl);
    *count=countl;
    return result;
}

