import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;
class editor extends JFrame implements ActionListener {

	JTextArea t;

	JFrame f;

	editor()
	{

		f = new JFrame("editor");

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

			MetalLookAndFeel.setCurrentTheme(new OceanTheme());
		}
		catch (Exception e) {
		}

		t = new JTextArea();

		JMenuBar mb = new JMenuBar();

	
		JMenu m1 = new JMenu("Archivo");

		// Creamos los items del menu archivo
		JMenuItem mi1 = new JMenuItem("Nuevo");
		JMenuItem mi2 = new JMenuItem("Abrir");
		JMenuItem mi3 = new JMenuItem("Guardar");
		JMenuItem mi9 = new JMenuItem("Imprimir");

		mi1.addActionListener(this);
		mi2.addActionListener(this);
		mi3.addActionListener(this);
		mi9.addActionListener(this);

		m1.add(mi1);
		m1.add(mi2);
		m1.add(mi3);
		m1.add(mi9);

		
		JMenu m2 = new JMenu("Editar");

		
		JMenuItem mi4 = new JMenuItem("Cortar");
		JMenuItem mi5 = new JMenuItem("Copiar");
		JMenuItem mi6 = new JMenuItem("Pegar");

		mi4.addActionListener(this);
		mi5.addActionListener(this);
		mi6.addActionListener(this);

		m2.add(mi4);
		m2.add(mi5);
		m2.add(mi6);

		JMenuItem mc = new JMenuItem("Cerrar");

		mc.addActionListener(this);

		mb.add(m1);
		mb.add(m2);
		mb.add(mc);

		f.setJMenuBar(mb);
		f.add(t);
		f.setSize(500, 500);
		f.setVisible(true);
	}

	// Si se pulsa un botón
	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();

		if (s.equals("Cortar")) {
			t.cut();
		}
		else if (s.equals("Copiar")) {
			t.copy();
		}
		else if (s.equals("Pegar")) {
			t.paste();
		}
		else if (s.equals("Guardae")) {
			// Crear un objeto de la clase JFileChooser
			JFileChooser j = new JFileChooser("f:");

			int r = j.showSaveDialog(null);

			if (r == JFileChooser.APPROVE_OPTION) {

				// Establecer la etiqueta a la ruta del directorio seleccionado
				File fi = new File(j.getSelectedFile().getAbsolutePath());

				try {
					// Crear un escritor de archivos
					FileWriter wr = new FileWriter(fi, false);

					BufferedWriter w = new BufferedWriter(wr);
					
					w.write(t.getText());

					w.flush();
					w.close();
				}
				catch (Exception evt) {
					JOptionPane.showMessageDialog(f, evt.getMessage());
				}
			}
			// Si el usuario cancela la operacion
			else
				JOptionPane.showMessageDialog(f, "El usuario cancelo la operacion");
		}
		else if (s.equals("Imprimir")) {
			try {
				// Imprimir el archivo
				t.print();
			}
			catch (Exception evt) {
				JOptionPane.showMessageDialog(f, evt.getMessage());
			}
		}
		else if (s.equals("Abrir")) {
			// Creamos un objeto de la clase JFileChooser 
			JFileChooser j = new JFileChooser("f:");

			// Invoca la función showsOpenDialog para mostrar el diálogo de guardado
			int r = j.showOpenDialog(null);

			// Cuando el usuario seleccione un documento
			if (r == JFileChooser.APPROVE_OPTION) {
				// Establecer la etiqueta en la ruta del directorio seleccionado
				File fi = new File(j.getSelectedFile().getAbsolutePath());

				try {
					// String
					String s1 = "", sl = "";

					// Lector de archivos
					FileReader fr = new FileReader(fi);

					// Lector con búfer
					BufferedReader br = new BufferedReader(fr);

					// Inicializar sl
					sl = br.readLine();

					// Tomar la entrada del fichero
					while ((s1 = br.readLine()) != null) {
						sl = sl + "\n" + s1;
					}

					
					t.setText(sl);
				}
				catch (Exception evt) {
					JOptionPane.showMessageDialog(f, evt.getMessage());
				}
			}
			
			else
				JOptionPane.showMessageDialog(f, "El usuario cancelo la operacion");
		}
		else if (s.equals("Nuevo")) {
			t.setText("");
		}
		else if (s.equals("Cerrar")) {
			f.setVisible(false);
		}
	}
	public static void main(String args[])
	{
		editor e = new editor();
	}
}

