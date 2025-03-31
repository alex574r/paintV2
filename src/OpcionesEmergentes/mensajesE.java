/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OpcionesEmergentes;

import paint.CustomDrawPanel;

/**
 *
 * @author alex5
 */
public class mensajesE {

    public void RellenoAlert(CustomDrawPanel drawPanel) {
        OpcionesRelleno mensaje = new OpcionesRelleno(drawPanel);
        mensaje.setVisible(true);
    }

    
    public void ContornoAlert(CustomDrawPanel drawPanel) {
        OpcionesTrazo mensaje = new OpcionesTrazo(drawPanel);
        mensaje.setVisible(true);
    }

}
