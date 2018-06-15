package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.ProteccionCivil;
import Modelo.Emergencia;
import Modelo.PlanProteccion;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextPane;

/**
 * 
 * @author MiguelYanes
 */
public class VentanaConfirmarEliminar extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();

	private OyenteVista oyenteVista;
	private Object obj;
	private JTextField txtdeseasEliminarEste;

	private static final String BTN_ELIMINAR = "Eliminar";
	private static final String BTN_CANCELAR = "Cancelar";
	/**
	 * Create the dialog.
	 */
	public VentanaConfirmarEliminar(OyenteVista oyenteVista, Object obj) {
		this.oyenteVista = oyenteVista;
                this.obj = obj;
		setTitle("Confirmar Eliminar");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		{
			JTextPane textPane = new JTextPane();
			contentPanel.add(textPane);
			textPane.setText(obj.toString());
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.SOUTH);
			{
				JButton btnEliminar = new JButton("Eliminar");
				btnEliminar.setActionCommand(BTN_ELIMINAR);
				btnEliminar.addActionListener(this);
				panel.add(btnEliminar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setActionCommand(BTN_CANCELAR);
				btnCancelar.addActionListener(this);
				panel.add(btnCancelar);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.NORTH);
			panel.setLayout(new BorderLayout(0, 0));
			{
				txtdeseasEliminarEste = new JTextField();
				txtdeseasEliminarEste.setFont(new Font("Tahoma", Font.BOLD, 16));
				txtdeseasEliminarEste.setEditable(false);
				txtdeseasEliminarEste.setHorizontalAlignment(SwingConstants.CENTER);
				txtdeseasEliminarEste.setText("\u00BFDeseas eliminar?");
				panel.add(txtdeseasEliminarEste);
				txtdeseasEliminarEste.setColumns(20);
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case BTN_CANCELAR:
			this.dispose();
			break;
		case BTN_ELIMINAR:
			if(obj.getClass().isAssignableFrom(Object.class)){
                            oyenteVista.notificacion(OyenteVista.Evento.ELIMINAR_PLAN,obj);
                        }else if(obj.getClass().isAssignableFrom(Emergencia.class)){
                            oyenteVista.notificacion(OyenteVista.Evento.ELIMINAR_EMERGENCIA,obj);
                        }
			this.dispose();
			break;
		}
		
	}

}
