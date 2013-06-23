package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import dominio.Pedido;

public class ControladorGuardar implements ActionListener{
    private JButton enviar;
	public ControladorGuardar(JButton nEnviar){
		enviar = nEnviar;
		List <Pedido> aPed;
	}
	public void actionPerformed(ActionEvent e) {
		System.out.println("qui ta el boton");
		//for (int i = 0; i < aPed.size(); i++){

	}
}
