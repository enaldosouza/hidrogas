
package mensagem;

import java.awt.Color;
import javax.swing.UIManager;

public class Mensagem extends javax.swing.JOptionPane{
    
    public Mensagem(){
//        this.setBackground(Color.green);
        UIManager.getDefaults().put("OptionPane.background",new Color(144,238,144));
        UIManager.put ("Panel.background", new Color(144,238,144));
            
    }
}
