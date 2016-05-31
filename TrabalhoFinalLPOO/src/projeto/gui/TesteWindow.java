package projeto.gui;
import java.awt.*;
import java.awt.event.HierarchyBoundsAdapter;
import java.awt.event.HierarchyEvent;
import javax.swing.*;

public class TesteWindow {

   JPanel panel;
   double proportion = 0.44;

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {

         @Override
         public void run() {
            new TesteWindow().makeUI();
         }
      });
   }

   public void makeUI() {
      panel = new JPanel();
      panel.setBackground(Color.YELLOW);
      panel.setBorder(BorderFactory.createLineBorder(Color.RED));
      panel.addHierarchyBoundsListener(new HierarchyBoundsAdapter() {

         @Override
         public void ancestorResized(HierarchyEvent e) {
            JComponent parent = (JComponent) panel.getParent();
            Insets insets = parent.getInsets();
            int width = parent.getWidth() - insets.left - insets.right;
            int height = parent.getHeight() - insets.top - insets.bottom;
            width = (int) Math.min(width, height / proportion);
            height = (int) Math.min(width * proportion, height);
            panel.setPreferredSize(new Dimension(width, height));
         }
      });
      JFrame frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(400, 400);
      frame.setLayout(new GridBagLayout());
      frame.add(panel);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }
}
