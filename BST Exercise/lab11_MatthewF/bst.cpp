//
//  bst.cpp
//  Lab 11
//
//  Created by Matthew on 4/17/18.
//  Copyright © 2018 Matthew. All rights reserved.
//

#include "bst.hpp"
#include <iostream>
using namespace std;

bst::bst() :root(NULL)
{
    
}

bst::~bst(){
    
}

void bst::insert(int data){
    root = rinsert(data,root);
}

node* bst::rinsert(int data, node* r){
    if(r == NULL){
        r = new node(data);
    }
    else{
        if(data< r ->data){
            r -> left= rinsert(data, r ->left);
        }
        else if(data> r ->data){
            r -> right = rinsert(data, r->right);
        }
        else{
            
        }
    }
    return r;
}

void bst::inorder(void(*visit)(int)){
    rinorder(visit,root);
}

void bst::rinorder(void(*visit)(int), node * r){
    if(r == NULL) return;
    else{
        rinorder(visit, r -> left);
        (*visit)(r ->data);
        rinorder(visit, r -> right);
    }
}

void bst::preorder(void(*visit)(int)){
    rpreorder(visit,root);
}

void bst::rpreorder(void(*visit)(int), node * r){
    if(r == NULL) return;
    else{
        (*visit)(r ->data);
        rpreorder(visit, r -> left);
        rpreorder(visit, r -> right);
    }
}

void bst::postorder(void(*visit)(int)){
    rpostorder(visit,root);
}

void bst::rpostorder(void(*visit)(int), node * r){
    if(r == NULL) return;
    else{
        rpostorder(visit, r -> left);
        rpostorder(visit, r -> right);
        (*visit)(r ->data);
    }
}










