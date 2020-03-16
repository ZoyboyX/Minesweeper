public class Numbersnmines
{

   private int[][] board;
   private int rows;
   private int columns;
   private int miness;
      
   public Numbersnmines(int x, int y)
   {    
      rows = y;
      columns = x;
      board = new int[y][x];
      for(int r = 0; r < y; r++)
         for(int c = 0; c < x; c++)
         {
            board[r][c] = 0;
         }
   }
   public void randomize(int x, int y, int m)
   {      
      miness = m;
      int hx, hy, count;
      count = 0;
      while(count< m)
      {
         hx = (int)(Math.random()*columns);
         hy = (int) (Math.random()*rows);
         if(!(board[hy][hx] == 9) && notA3x3Square(x,y,hx,hy))
         {
            board[hy][hx] = 9;
            count ++;
         }
         else
         {
            hx = (int)(Math.random()*columns);
            hy = (int)(Math.random()*rows);
         }
      }
      for(int r = 0; r < rows; r++)
      {
         for(int c = 0; c < columns; c++)
         {
            board[r][c] = numberSurroundingMines(c,r);
         }
      }
   }
   
      
   public void reset()
   {
      for(int r = 0; r < rows; r++)
         for(int c = 0; c < columns; c++)
         {
            board[r][c] = 0;
         }
   }
   
  
   public boolean notA3x3Square(int centerx, int centery, int inputx, int inputy)
   {
      if(squareExists(centerx-1,centery+1))
      {
         if((inputy == centery+1)&& (inputx == centerx-1))
            return false;
      }
      if(squareExists(centerx-1,centery))
      {
         if((inputy==centery)&&(inputx == centerx-1))
            return false;
      }
      if(squareExists(centerx-1,centery-1))
      {
         if((inputy == centery-1) && (inputx == centerx-1))
            return false;
      }
      if(squareExists(centerx,centery+1))
      {
         if((inputy == centery+1)&& (inputx == centerx))
            return false;
      }
      if(squareExists(centerx,centery-1))
      {
         if((inputy==centery-1)&&(inputx == centerx))
            return false;
      }
      if(squareExists(centerx+1,centery-1))
      {
         if((inputy == centery-1)&&(inputx == centerx+1))
            return false;
      }
      if(squareExists(centerx+1,centery))
      {
         if((inputy == centery)&&(inputx == centerx+1))
            return false;
      }
      if(squareExists(centerx+1,centery+1))
      {
         if((inputy == centery+1)&&(inputx == centerx+1))
            return false;
      }
      if(squareExists(centerx,centery))
      {
         if((inputy == centery)&&(inputx == centerx))
            return false;
      }
      return true;
      
   }
   

   public int numberSurroundingMines(int x, int y)
   {     
      int nummines = 0;
      if(hasMine(x,y))
         nummines = 9;
      if(hasMine(x,y) == false)
      {
         if(squareExists(x-1, y+1))
            if(hasMine(x-1, y+1))
               nummines++;
         if(squareExists(x-1, y))
            if(hasMine(x-1, y))
               nummines++;
         if(squareExists(x-1, y-1))
            if(hasMine(x-1, y-1))
               nummines++;
         if(squareExists(x, y+1))
            if(hasMine(x, y+1))
               nummines++;
         if(squareExists(x, y-1))
            if(hasMine(x, y-1))
               nummines++;
         if(squareExists(x+1, y+1))
            if(hasMine(x+1, y+1))
               nummines++;
         if(squareExists(x+1, y))
            if(hasMine(x+1, y))
               nummines++;
         if(squareExists(x+1, y-1))
            if(hasMine(x+1, y-1))
               nummines++;
      }
      return nummines;      
   }

      
   public boolean squareExists(int x, int y)
   {
      try
      {        
         int b = board[y][x];
         return true;
      }
      catch(ArrayIndexOutOfBoundsException e)
      {
         return false;
      }
   }
   

   public boolean hasMine(int x, int y)
   {
      if(board[y][x]==9)
         return true;
      else
         return false;
   }
   
      
   public int valueOf(int x, int y)
   {
      return board[y][x];
   }
   
      
   public void addFlag(int x, int y)
   {
      int orig = board[y][x];
      if(orig == 9)
         orig = 94;
      if(orig == 8)
         orig = 84;
      if(orig == 7)
         orig = 74;
      if(orig == 6)
         orig = 64;
      if(orig == 5)
         orig = 54;
      if(orig == 4)
         orig = 44;
      if(orig == 3)
         orig = 34;
      if(orig == 2)
         orig = 24;
      if(orig == 1)
         orig = 14;
      if(orig == 0)
         orig = 104;
      board[y][x] = orig;
   }
   
      
   public void removeFlag(int x, int y)
   { 
      int orig = board[y][x];
      if(orig == 94)
         orig = 9;
      if(orig == 84)
         orig = 8;
      if(orig == 74)
         orig = 7;
      if(orig == 64)
         orig = 6;
      if(orig == 54)
         orig = 5;
      if(orig == 44)
         orig = 4;
      if(orig == 34)
         orig = 3;
      if(orig == 24)
         orig = 2;
      if(orig == 14)
         orig = 1;
      if(orig == 104)
         orig = 0;
      board[y][x] = orig;
   }
   
      
   public boolean hasFlag(int x, int y)
   { 
      if(board[y][x]>10)
         return true;
      return false;
   }
   
   public int actualMinesRemaining()
   {
      int count = 0;
      for(int r = 0; r < rows; r++)
         for(int c = 0; c < columns; c++)
         {
            if(board[r][c] == 9)
               count++;
         }
      return count;
   }
}