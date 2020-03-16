import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
public class Minesweeper extends JPanel
{
   private JButton[][] board;
   private JLabel label;
   private JButton reset;
   private Numbersnmines fred;
   private int clicks, flags;
   private ImageIcon icon, icon1, icon2, icon3, icon4, icon5, icon6, icon7, icon8;
   private ImageIcon iconflagsquare, iconmine, iconsquare, iconredmine, iconwrongmine;
   private int NUMBEROFROWS, NUMBEROFCOLUMNS, NUMBEROFMINES;
   private PrintStream outfile;
   public Minesweeper(int numrows, int numcols, int nummines) throws Exception
   {
      outfile = new PrintStream(new FileOutputStream("windata.txt"));
      outfile.println("Winstatus, Rows, Columns, Mines, Actual Mines Remaining, Mines you thought you had remaining");   
      NUMBEROFROWS = numrows;
      NUMBEROFCOLUMNS = numcols;
      NUMBEROFMINES = nummines;
      clicks = 0;
      flags = NUMBEROFMINES;
      int decidedval = 20;   
      
      fred = new Numbersnmines(NUMBEROFCOLUMNS,NUMBEROFROWS);
      
      setLayout(new BorderLayout());
      
      JPanel north = new JPanel();
      north.setLayout(new FlowLayout());
      add(north, BorderLayout.NORTH);
      if(NUMBEROFCOLUMNS>=15)
      {
         label = new JLabel("Welcome to Minesweeper -- You have "+ NUMBEROFMINES+" mine(s) left.");
      }
      else
      {
         label = new JLabel(NUMBEROFMINES+ " left");
      }
      north.add(label);
      
      JPanel center = new JPanel();
      center.setLayout(new GridLayout(NUMBEROFROWS,NUMBEROFCOLUMNS));
      add(center, BorderLayout.CENTER);
           
      reset = new JButton("Reset");
      reset.addActionListener(new Handler2());
      add(reset, BorderLayout.SOUTH);
      
      icon = new ImageIcon("zero.png"); 
      Image image = icon.getImage(); 
      Image image0 = image.getScaledInstance(decidedval, decidedval, Image.SCALE_SMOOTH);  
      icon = new ImageIcon(image0);
      
      icon1 = new ImageIcon("one.png"); 
      Image image1 = icon1.getImage(); 
      Image imageo1 = image1.getScaledInstance(decidedval, decidedval, Image.SCALE_SMOOTH);  
      icon1 = new ImageIcon(imageo1);
      
      icon2 = new ImageIcon("two.png"); 
      Image image2 = icon2.getImage(); 
      Image imageo2 = image2.getScaledInstance(decidedval, decidedval, Image.SCALE_SMOOTH);  
      icon2 = new ImageIcon(imageo2);
   
      icon3 = new ImageIcon("three.png"); 
      Image image3 = icon3.getImage(); 
      Image imageo3 = image3.getScaledInstance(decidedval, decidedval, Image.SCALE_SMOOTH);  
      icon3 = new ImageIcon(imageo3);
   
      icon4 = new ImageIcon("four.png"); 
      Image image4 = icon4.getImage(); 
      Image imageo4 = image4.getScaledInstance(decidedval, decidedval, Image.SCALE_SMOOTH);  
      icon4 = new ImageIcon(imageo4);
   
      icon5 = new ImageIcon("five.png"); 
      Image image5 = icon5.getImage(); 
      Image imageo5 = image5.getScaledInstance(decidedval, decidedval, Image.SCALE_SMOOTH);  
      icon5 = new ImageIcon(imageo5);
   
      icon6 = new ImageIcon("six.png"); 
      Image image6 = icon6.getImage(); 
      Image imageo6 = image6.getScaledInstance(decidedval, decidedval, Image.SCALE_SMOOTH);  
      icon6 = new ImageIcon(imageo6);
   
      icon7 = new ImageIcon("seven.png"); 
      Image image7 = icon7.getImage(); 
      Image imageo7 = image7.getScaledInstance(decidedval, decidedval, Image.SCALE_SMOOTH);  
      icon7 = new ImageIcon(imageo7);
   
      icon8 = new ImageIcon("eight.png"); 
      Image image8 = icon8.getImage(); 
      Image imageo8 = image8.getScaledInstance(decidedval, decidedval, Image.SCALE_SMOOTH);  
      icon8 = new ImageIcon(imageo8);
   
      iconflagsquare = new ImageIcon("flagsquare.png"); 
      Image imageflagsquare = iconflagsquare.getImage(); 
      Image imageoflagsquare = imageflagsquare.getScaledInstance(decidedval, decidedval, Image.SCALE_SMOOTH);  
      iconflagsquare = new ImageIcon(imageoflagsquare);
   
      iconsquare = new ImageIcon("square.png"); 
      Image imagesquare = iconsquare.getImage(); 
      Image imageosquare = imagesquare.getScaledInstance(decidedval, decidedval, Image.SCALE_SMOOTH);  
      iconsquare = new ImageIcon(imageosquare);
   
      board = new JButton[NUMBEROFROWS][NUMBEROFCOLUMNS];
      for(int r = 0; r<NUMBEROFROWS; r++)
         for(int c = 0; c<NUMBEROFCOLUMNS; c++)
         {
            board[r][c] = new JButton(iconsquare);
            board[r][c].setDisabledIcon(iconsquare);
            board[r][c].setBackground(Color.gray);
            board[r][c].addActionListener(new Handler1(r,c));
            center.add(board[r][c]);
         }
   }
   
 
   public void reveal(int column, int row)
   {
      int temp = 0;
      temp = fred.valueOf(column, row);
      if(temp == 0)
      {
         board[row][column].setIcon(icon);
         board[row][column].setDisabledIcon(icon);
         board[row][column].setEnabled(false);
         if(fred.squareExists(column-1,row-1)&& board[row-1][column-1].isEnabled())
            reveal(column-1,row-1);
         if(fred.squareExists(column-1,row)&& board[row][column-1].isEnabled())
            reveal(column-1,row);
         if(fred.squareExists(column-1,row+1)&& board[row+1][column-1].isEnabled())
            reveal(column-1,row+1);
         if(fred.squareExists(column+1,row-1)&& board[row-1][column+1].isEnabled())
            reveal(column+1,row-1);
         if(fred.squareExists(column+1,row)&& board[row][column+1].isEnabled())
            reveal(column+1,row);
         if(fred.squareExists(column+1,row+1)&& board[row+1][column+1].isEnabled())
            reveal(column+1,row+1);
         if(fred.squareExists(column,row-1)&& board[row-1][column].isEnabled())
            reveal(column,row-1);
         if(fred.squareExists(column,row+1)&& board[row+1][column].isEnabled())
            reveal(column,row+1);
         
      }
      else if(temp == 1)
      {
         board[row][column].setIcon(icon1);
         board[row][column].setDisabledIcon(icon1);
         board[row][column].setEnabled(false);
         
      }
      else if(temp == 2)
      {
         board[row][column].setIcon(icon2);
         board[row][column].setDisabledIcon(icon2);
         board[row][column].setEnabled(false);
      }
      else if(temp == 3)
      {
         board[row][column].setIcon(icon3);
         board[row][column].setDisabledIcon(icon3);
         board[row][column].setEnabled(false);
      }
      else if(temp == 4)
      {
         board[row][column].setIcon(icon4);
         board[row][column].setDisabledIcon(icon4);
         board[row][column].setEnabled(false);
      }
      else if(temp == 5)
      {
         board[row][column].setIcon(icon5);
         board[row][column].setDisabledIcon(icon5);
         board[row][column].setEnabled(false);
      }
      else if(temp == 6)
      {
         board[row][column].setIcon(icon6);
         board[row][column].setDisabledIcon(icon6);
         board[row][column].setEnabled(false);
      }
      else if(temp == 7)
      {
         board[row][column].setIcon(icon7);
         board[row][column].setDisabledIcon(icon7);
         board[row][column].setEnabled(false);
      }
      else if(temp == 8)
      {
         board[row][column].setIcon(icon8);
         board[row][column].setDisabledIcon(icon8);
         board[row][column].setEnabled(false);
      }
      else if(temp == 9)
      {
         label.setText("You lose");
         int fredsthoughts = fred.actualMinesRemaining();
         outfile.println("L "+NUMBEROFROWS+" "+NUMBEROFCOLUMNS+" "+NUMBEROFMINES+" "+fredsthoughts+" "+flags+"");
         endgame();
      }
      else
         clicks++;
   }
   
   public void endgame()
   {
      for(int r = 0; r<NUMBEROFROWS; r++)
         for(int c = 0; c<NUMBEROFCOLUMNS; c++)
         {
            board[r][c].setVisible(false);
         }     
   }
   
   private class Handler1 implements ActionListener
   {
   
      private int myRow, myCol;
      public Handler1(int r, int c)
      {
         myRow = r;
         myCol = c;
      }
      
      public void actionPerformed(ActionEvent e)
      {
         if(clicks>=1)
         {
            if ((e.getModifiers() & InputEvent.SHIFT_MASK) != 0)
            {
               if(!fred.hasFlag(myCol, myRow))
               {
                  if(flags == 0)
                  {
                     JOptionPane.showMessageDialog(null, "Remove Mine(s) First");
                  }
                  else
                  {
                     fred.addFlag(myCol, myRow);
                     board[myRow][myCol].setIcon(iconflagsquare);
                     flags = flags -1;
                     if(NUMBEROFCOLUMNS>=10)
                     {
                        label.setText("You have "+ flags+ " mine(s) left");
                     }
                     else
                     {
                        label.setText(flags+" left");
                     }
                     if(flags == 0 && fred.actualMinesRemaining() == 0)
                     {
                        label.setText("You win");
                        outfile.println("W "+NUMBEROFROWS+" "+NUMBEROFCOLUMNS+" "+NUMBEROFMINES+"");
                        endgame();
                     }
                  }
               }
               else if(fred.hasFlag(myCol, myRow))
               {
                  fred.removeFlag(myCol, myRow);
                  board[myRow][myCol].setIcon(iconsquare);
                  flags++;
                  if(NUMBEROFCOLUMNS>=10)
                  {
                     label.setText("You have "+ flags+ " mine(s) left");
                  }
                  else
                  {
                     label.setText(""+flags+" left");
                  }
               }
               
            }
            else
            {
               reveal(myCol, myRow);
            }
         }
         if(clicks == 0)
         {
            fred.randomize(myCol, myRow, NUMBEROFMINES);
            clicks++;
            reveal(myCol, myRow);           
         }
      }
   }

   
   private class Handler2 implements ActionListener
   {

      public void actionPerformed(ActionEvent e)
      {
         clicks = 0;
         flags = NUMBEROFMINES;
         if(NUMBEROFCOLUMNS>=15)
         {
            label.setText("Welcome to Minesweeper -- You have "+ NUMBEROFMINES+" mine{s) left.");
         }
         else
         {
            label.setText(""+NUMBEROFMINES+ " left");
         }
         for(int r = 0; r<NUMBEROFROWS; r++)
            for(int c = 0; c<NUMBEROFCOLUMNS; c++)
            {
               board[r][c].setIcon(iconsquare);
               board[r][c].setBackground(Color.gray);
               board[r][c].setVisible(true);
               board[r][c].setEnabled(true);
            }
         fred.reset();
      }
   }
}