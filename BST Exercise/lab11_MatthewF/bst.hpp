//
//  bst.hpp
//  Lab 11
//
//  Created by Matthew on 4/17/18.
//  Copyright © 2018 Matthew. All rights reserved.
//

#ifndef bst_hpp
#define bst_hpp
#include "node.hpp"
#include <stdio.h>
class bst
{
private:
    node* root;
    node* rinsert(int, node*);
    void rinorder( void (*)(int),node*);
    void rpreorder( void (*)(int),node*);
    void rpostorder( void (*)(int),node*);
    
    
public:
    bst();
    ~bst();
    void insert(int);
    void inorder(void (*)(int));
    void preorder(void (*)(int));
    void postorder(void (*)(int));
    
    
};
#endif /* bst_hpp */
