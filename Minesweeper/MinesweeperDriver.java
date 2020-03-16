import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class MinesweeperDriver
{

   public static void main(String[] args)throws Exception
   {
      try
      {
         String rows = JOptionPane.showInputDialog("how many rows (suggested: 16)");
         String columns = JOptionPane.showInputDialog("how many columns (suggested: 30)");
         String mines = JOptionPane.showInputDialog("how many mines (suggested 99)");
         int realrows  = Integer.parseInt(rows);
         int realcolumns = Integer.parseInt(columns);
         int realmines = Integer.parseInt(mines);
         int areaminusmines = realrows*realcolumns-realmines;
         if(areaminusmines< 9)
            throw new RuntimeException();
         JFrame frame = new JFrame("Final Project: Minesweeper");
         frame.setSize(615+ 20*(realcolumns-30), 430+ 20*(realrows-16)); 
         frame.setLocationRelativeTo(null);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setContentPane(new Minesweeper(realrows, realcolumns,realmines));
         frame.setVisible(true);
         frame.setResizable(false);
      }
      catch(NumberFormatException f)
      {
         JOptionPane.showMessageDialog(null, "Error: only enter natural numbers. Mines>1. 4<rows=<45 and columns=<80.");                    
         System.exit(0);
      }
      catch(RuntimeException b)
      {
         JOptionPane.showMessageDialog(null, "Error: not enough space for that many mines.");                    
         System.exit(0);
      }
   }
}