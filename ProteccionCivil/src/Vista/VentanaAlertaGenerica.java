package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * @author MiguelYanes
 */
public class VentanaAlertaGenerica extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private static final String BTN_ACEPTAR = "Aceptar";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaAlertaGenerica dialog = new VentanaAlertaGenerica("alerta prueba");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaAlertaGenerica(String alerta) {
		setType(Type.POPUP);
		setTitle("¡Atencion!");
		setBounds(50, 50, 250, 150);
		getContentPane().setLayout(new BorderLayout(0, 0));
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		{
			JTextPane textPane = new JTextPane();
			textPane.setFont(new Font("Tahoma", Font.BOLD, 11));
			textPane.setEditable(false);
			contentPanel.add(textPane);
			textPane.setText(alerta);
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.SOUTH);
			{
				JButton btnAceptar = new JButton("Aceptar");
				panel.add(btnAceptar);
				btnAceptar.setActionCommand(BTN_ACEPTAR);
				btnAceptar.addActionListener(this);
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getActionCommand()) {
		case BTN_ACEPTAR:
			this.dispose();
			break;
		}
			
	}

}
