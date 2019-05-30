
#include <iostream>
#include "bst.hpp"
#include <cassert>
#include <algorithm>
#include <vector>
using namespace std;
vector<int> store;

void push2vector(int);


double f3(int d, int k){
    return d * k;
}
double f2(int d){
    return d * 3;
}
double f1(int d){
    return d * 2;
}
int main() {
    
    double (*v)(int);
    v = f1;
    v = f2;
    (*v)(300);
    double(*p)(int, int);
    p = f3;
    
    bst t1;
    const int SIZE = 1000;
    
    int random_data[SIZE];
    
    for(int i = 0; i < SIZE; i++){
        random_data[i] = i;
    }
    
    random_shuffle(random_data, random_data+ SIZE);
    
    for(int i = 0; i < SIZE; i++){
        t1.insert(random_data[i]);
    }
    
    t1.inorder(push2vector);
    for(int i = 0; i< SIZE; i++){
        assert(store[i] == i);
    }
    
    bst t2;
    const int S = 10;
    int d[] = { 10,5,15,4,12,7,20,6,14,13};
    for(int i = 0; i < S; i++){
        t2.insert(d[i]);
    }
    
    int dinorder[] = { 4,5,6,7,10,12,13,14,15,20};
    int dpreorder[] = { 10,5,4,7,6,15,12,14,13,20};
    int dpostorder[] = { 4,6,7,5,13,14,12,20,15,10};
    
    store.clear();
    t2.preorder(push2vector);
    for(int i = 0; i< S; i++){
        assert(store[i] == dpreorder[i]);
    }
    store.clear();
    t2.postorder(push2vector);
    for(int i = 0; i< S; i++){
        assert(store[i] == dpostorder[i]);
    }
    
    bst t3;
    const int A = 14;
    int a[] = { 15,20,6,7,5,19,22,1,3,10,30,2,14,29};
    for(int i = 0; i < A; i++){
        t3.insert(a[i]);
    }
    
    //int ainorder[] = { 4,5,6,7,10,12,13,14,15,20};
    int apreorder[] = { 15,6,5,1,3,2,7,10,14,20,19,22,30,29};
    int apostorder[] = { 2,3,1,5,14,10,7,6,19,29,30,22,20,15};
    
    store.clear();
    t3.preorder(push2vector);
    for(int i = 0; i< A; i++){
        assert(store[i] == apreorder[i]);
    }
    store.clear();
    t3.postorder(push2vector);
    for(int i = 0; i< A; i++){
        assert(store[i] == apostorder[i]);
    }
    return 0;
}

void push2vector(int data){
    store.push_back(data);
}
