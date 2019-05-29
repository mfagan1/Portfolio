//
//  node.hpp
//  lab11_MatthewF
//
//  Created by Matthew on 4/17/18.
//  Copyright © 2018 Matthew. All rights reserved.
//

#ifndef node_hpp
#define node_hpp

#include <iostream>
#include <stdio.h>
class node
{
    private:
    int data;
    node* left;
    node* right;
    
    public:
    node(int);
    ~node();
    friend class bst;
};
#endif /* node_hpp */
