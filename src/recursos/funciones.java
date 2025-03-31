/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recursos;

import javax.swing.JPanel;

/**
 *
 * @author alex5
 */
public class funciones {
    
    public void remover_fondo_panel(JPanel... panels) {
        for (JPanel panel : panels) {
            panel.setOpaque(false);
        }
    }
    
    
}
