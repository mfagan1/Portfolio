#include <stdio.h>
#include <limits.h>
#include <iostream>
#include <fstream>
#include <string>
#include <cstdlib>
#include <vector>


using namespace std;

int size;

int counter = 1;
int xMove[8] = {2, 1, -1, -2, -2, -1, 1, 2};
int yMove[8] = {-1, -2, -2, -1, 1, 2, 2, 1};


void print_board(int ** board) {
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            if (board[i][j] < 10)
                cout << ' ';
            cout << board[i][j] << ' ';
        }
        cout << endl;
    }
    cin.get();
}

bool tour(int number, int ROW, int COL, int ** board) {

    if (number == size * size)
        return true;
    
    for (int move = 0; move < 8; move++) {
        int new_row = ROW + xMove[move];
        int new_col = COL + yMove[move];
        
        if (new_row < 0 || new_row >= size || new_col < 0 || new_col >= size)
            continue;
        
        if (board[new_row][new_col] != 0)
            continue;
        
        board[new_row][new_col] = number + 1;
        if (tour(number + 1, new_row, new_col, board))
            return true;
        board[new_row][new_col] = 0;
    }
    return false;
}

void solve(int init_row, int init_col,int **board) {
    for (int row = 0; row < size; row++)
        for (int col = 0; col < size; col++){
            board[row][col] = 0;}
    
    board[init_col][init_row] = 1;
    if (tour(1, init_col, init_row, board))
        print_board(board);
    else
        cout << "Failed to find a tour!\n";
}

bool limits(int x, int y)
{
    return ((x >= 0 && y >= 0) && (x < 16 && y < 16));
}

bool empty(int a[], int x, int y)
{
    return (limits(x, y)) && (a[y*size+x] < 0);
}

int getDegree(int a[], int x, int y)
{
    int count = 0;
    for (int i = 0; i < 8; ++i)
        if (empty(a, (x + xMove[i]), (y + yMove[i])))
            count++;
    
    return count;
}

bool nextMove(int a[], int *x, int *y)
{
    int holder1 = -1, c, holder2 = (size+1), nx, ny;
    int start = rand()%8;
    for (int count = 0; count < size; ++count)
    {
        int i = (start + count)%8;
        nx = *x + xMove[i];
        ny = *y + yMove[i];
        if ((empty(a, nx, ny)) &&
            (c = getDegree(a, nx, ny)) < holder2)
        {
            holder1 = i;
            holder2 = c;
        }
    }

    if (holder1 == -1)
        return false;

    nx = *x + xMove[holder1];
    ny = *y + yMove[holder1];

    a[ny*size + nx] = a[(*y)*size + (*x)]+1;

    *x = nx;
    *y = ny;
    
    return true;
}
void print(int a[])
{
    for (int i = 0; i < size; ++i)
    {
        for (int j = 0; j < size; ++j)
            cout << '\t' << a[j*size+i];
        cout << endl;
    }
}

bool adjacent(int x, int y, int xx, int yy)
{
    for (int i = 0; i < 8; ++i)
        if (((x+xMove[i]) == xx)&&((y + yMove[i]) == yy))
            return true;
    
    return false;
}

bool Warnsdorff(int j, int k)
{
    int a[size*size];
    for (int i = 0; i< size*size; ++i)
        a[i] = -1;

    int sx = j;
    int sy = k;
    
    int x = sx, y = sy;
    a[y*size+x] = 1;

    for (int i = 0; i < size*size-1; ++i)
        if (nextMove(a, &x, &y) == 0)
            return false;
    if (!adjacent(x, y, sx, sy))
        return false;
    print(a);
    return true;
}

int main(){
    string input;
    int choice;
    cout << endl;
    
    cout << "How big is the board?" << endl;
    
    getline(cin, input);
    
    size = atoi(input.c_str());
    
    cout << " Starting X" << endl;
    getline(cin, input);
    
    int x = atoi(input.c_str());
    
    cout << " Starting Y" << endl;
    getline(cin, input);
    
    int y = atoi(input.c_str());
    

    int** board = new int*[size];
    for(int i = 0; i < 16; ++i)
        board[i] = new int[16];
    cout << "Which method would you like to use? 1: Fixed Order 2: Warnsdorff’s heuristic "<<endl;
    getline(cin, input);
    choice =atoi(input.c_str());
    if (choice == 1)
    solve(x,y, board);
    if (choice == 2)
    {
        while (!Warnsdorff(x,y)){}
        
    }
    
    return 0;
    
}

