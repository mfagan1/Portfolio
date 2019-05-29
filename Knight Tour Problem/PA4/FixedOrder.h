
/*
#include <iostream>
using namespace std;
int SIZE
int moves = 8;

int xMove[8] = {2, 1, -1, -2, -2, -1, 1, 2};
int yMove[8] = {-1, -2, -2, -1, 1, 2, 2, 1};

void solve(int init_row, int init_col, int board[SIZE][SIZE]);

void dataHandler(int size, int x, int y){
    SIZE = size;
    int board[SIZE][SIZE];
    solve(x, y, board[SIZE][SIZE])
}

void print_board() {
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            if (board[i][j] < 10)
                cout << ' ';
            cout << board[i][j] << ' ';
        }
        cout << endl;
    }
    cin.get();
}

void tour(int move_no, int current_row, int current_col,int board[SIZE][SIZE]) {

    if (move_no == SIZE * SIZE)
    {
        print_board();
        return;
    }
    
    for (int move = 0; move < moves; move++) {
        int new_row = current_row + xMove[move];
        int new_col = current_col + yMove[move];
        
        if (new_row < 0 || new_row >= SIZE || new_col < 0 || new_col >= SIZE)
            continue;
        
        if (board[new_row][new_col] != 0)
            continue;
        
        board[new_row][new_col] = move_no + 1;
        tour(move_no + 1, new_row, new_col);
        board[new_row][new_col] = 0;
    }
}

void solve(int ROW, int COL, int board[SIZE][SIZE) {
    for (int row = 0; row < SIZE; row++)
        for (int col = 0; col < SIZE; col++)
            board[row][col] = 0;
    
    board[ROW][COL] = 1;
    tour(1, ROW, COL,** board);
}

