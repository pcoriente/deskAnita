package controladores;

import gestionConexiones.GestionConexiones;
import gestionConexiones.UsuarioValidado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComponent;
import javax.swing.JMenuBar;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public  class ControladorConexion  implements ActionListener{
    private JTextField usuario;
    private JPasswordField pasword;
	private JComponent accesoBd;
	private JMenuBar menuSea;
   
    //public  char[] pswd;
	public ControladorConexion(JTextField pUsuario, JPasswordField pPassword,JComponent pAccesoBd,JMenuBar pMenuSea)
	{
		usuario = pUsuario;
		pasword = pPassword;
		accesoBd=pAccesoBd;
		menuSea=pMenuSea;
	}
	
	public void actionPerformed(ActionEvent e) {
		String login=usuario.getText();
		char passArray[] = pasword.getPassword();
		String pswd = new String(passArray);
		
		
		//String pass = new String(contrasenia);
		//String password=password.getPassword())
		//System.out.println("el Password es: "+pswd);
		
		Connection conexion =null;
			try {
				conexion = GestionConexiones.generarConexion(login,pswd);
				//LeeEmpresa.leeEmpresa();
				accesoBd.setVisible(false);
				menuSea.setVisible(true);
				//System.out.println("Usuario Validado");
				//System.out.println(UsuarioValidado.getUsuario(0));

			} catch (SQLException e1) {
				System.out.println("Usuario no Reconocido");
				e1.printStackTrace();
				return;
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
				return;
			}
			
//			Statement sentencia = null;
//			try {
//				sentencia = conexion.createStatement();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			String sentenciaSQL = "SELECT cod_emp,nombre FROM empresas";
//			ResultSet cursorEmpresa = null;
//			try {
//				cursorEmpresa = sentencia.executeQuery(sentenciaSQL);
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//
//			try {
//				while (cursorEmpresa.next()) {
//					System.out.println(cursorEmpresa.getString("cod_emp")+"-"+cursorEmpresa.getString("nombre"));
//
//				}
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}			
	}

	//@Override
	//public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	//}
}
