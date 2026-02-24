package escritorio;

import controlador.Controlador;
import dto.DocumentoDTO;
import dto.PersonaDTO;
import dto.ProductoDTO;
import fabrica.FabricaDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Vista de escritorio Swing (capa de Presentación).
 * Permite al usuario elegir el dominio y la operación mediante una GUI.
 * Inyección de Dependencias: recibe el Controlador y FabricaDTO por constructor.
 */
public class VistaEscritorio extends JFrame {

    private final Controlador controlador;
    private final FabricaDTO  fabricaDTO;

    // Componentes principales
    private JTabbedPane tabbedPane;

    public VistaEscritorio(Controlador controlador, FabricaDTO fabricaDTO) {
        this.controlador = controlador;
        this.fabricaDTO  = fabricaDTO;
        construirVentana();
    }

    private void construirVentana() {
        setTitle("ComponentesConCapas — Escritorio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel de título
        JLabel titulo = new JLabel("ComponentesConCapas", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        // Pestañas por dominio
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Sistema Clientes",   panelClientes());
        tabbedPane.addTab("Gestor Documentos",  panelDocumentos());
        tabbedPane.addTab("Gestión Inventario", panelInventario());
        add(tabbedPane, BorderLayout.CENTER);
    }

    // ════════════════════ PANEL CLIENTES ════════════════════
    private JPanel panelClientes() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Formulario
        JPanel formulario = new JPanel(new GridLayout(5, 2, 5, 5));
        JTextField txtId       = new JTextField();
        JTextField txtNombres  = new JTextField();
        JTextField txtApellidos = new JTextField();
        JTextField txtEdad     = new JTextField();

        formulario.add(new JLabel("Identificación:"));  formulario.add(txtId);
        formulario.add(new JLabel("Nombres:"));         formulario.add(txtNombres);
        formulario.add(new JLabel("Apellidos:"));       formulario.add(txtApellidos);
        formulario.add(new JLabel("Edad:"));            formulario.add(txtEdad);

        // Tabla
        String[] columnas = {"Identificación", "Nombres", "Apellidos", "Edad"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);

        // Botones
        JButton btnCrear  = new JButton("Crear Persona");
        JButton btnListar = new JButton("Listar Personas");
        JPanel botones = new JPanel();
        botones.add(btnCrear);
        botones.add(btnListar);
        formulario.add(botones);

        btnCrear.addActionListener(e -> {
            try {
                PersonaDTO dto = fabricaDTO.crearPersonaDTO(
                        Double.parseDouble(txtId.getText().trim()),
                        txtNombres.getText().trim(),
                        txtApellidos.getText().trim(),
                        Integer.parseInt(txtEdad.getText().trim())
                );
                boolean ok = controlador.crearPersona(dto);
                JOptionPane.showMessageDialog(this,
                        ok ? "✔ Persona registrada correctamente." : "✘ Error al registrar.",
                        "Sistema Clientes", JOptionPane.INFORMATION_MESSAGE);
                txtId.setText(""); txtNombres.setText("");
                txtApellidos.setText(""); txtEdad.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Datos inválidos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnListar.addActionListener(e -> {
            modelo.setRowCount(0);
            List<PersonaDTO> lista = controlador.listarPersonas();
            lista.forEach(p -> modelo.addRow(new Object[]{
                    p.getIdentificacion(), p.getNombres(), p.getApellidos(), p.getEdad()
            }));
        });

        panel.add(formulario, BorderLayout.NORTH);
        panel.add(scroll,     BorderLayout.CENTER);
        return panel;
    }

    // ════════════════════ PANEL DOCUMENTOS ════════════════════
    private JPanel panelDocumentos() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel formulario = new JPanel(new GridLayout(3, 2, 5, 5));
        JComboBox<String> cmbTipo = new JComboBox<>(new String[]{"HTML", "TEXTO", "PDF"});
        JTextField txtContenido  = new JTextField();

        formulario.add(new JLabel("Tipo:")); formulario.add(cmbTipo);
        formulario.add(new JLabel("Contenido:")); formulario.add(txtContenido);

        String[] columnas = {"Tipo", "Contenido"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);

        JButton btnCrear  = new JButton("Crear Documento");
        JButton btnListar = new JButton("Listar Documentos");
        JPanel botones = new JPanel();
        botones.add(btnCrear);
        botones.add(btnListar);
        formulario.add(botones);

        btnCrear.addActionListener(e -> {
            String tipo     = (String) cmbTipo.getSelectedItem();
            String contenido = txtContenido.getText().trim();
            if (contenido.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el contenido.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            DocumentoDTO dto = fabricaDTO.crearDocumentoDTO(tipo, contenido);
            boolean ok = controlador.crearDocumento(dto);
            JOptionPane.showMessageDialog(this,
                    ok ? "✔ Documento creado correctamente." : "✘ Error al crear.",
                    "Gestor Documentos", JOptionPane.INFORMATION_MESSAGE);
            txtContenido.setText("");
        });

        btnListar.addActionListener(e -> {
            modelo.setRowCount(0);
            List<DocumentoDTO> lista = controlador.listarDocumentos();
            lista.forEach(d -> modelo.addRow(new Object[]{d.getTipo(), d.getContenido()}));
        });

        panel.add(formulario, BorderLayout.NORTH);
        panel.add(scroll,     BorderLayout.CENTER);
        return panel;
    }

    // ════════════════════ PANEL INVENTARIO ════════════════════
    private JPanel panelInventario() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel formulario = new JPanel(new GridLayout(5, 2, 5, 5));
        JTextField txtNombre   = new JTextField();
        JTextField txtPrecio   = new JTextField();
        JTextField txtCantidad = new JTextField();

        formulario.add(new JLabel("Nombre:"));   formulario.add(txtNombre);
        formulario.add(new JLabel("Precio:"));   formulario.add(txtPrecio);
        formulario.add(new JLabel("Cantidad:")); formulario.add(txtCantidad);

        String[] columnas = {"ID", "Nombre", "Precio", "Cantidad"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);

        JButton btnCrear  = new JButton("Crear Producto");
        JButton btnListar = new JButton("Listar Productos");
        JPanel botones = new JPanel();
        botones.add(btnCrear);
        botones.add(btnListar);
        formulario.add(botones);

        btnCrear.addActionListener(e -> {
            try {
                ProductoDTO dto = fabricaDTO.crearProductoDTO(
                        0,
                        txtNombre.getText().trim(),
                        Double.parseDouble(txtPrecio.getText().trim()),
                        Integer.parseInt(txtCantidad.getText().trim())
                );
                boolean ok = controlador.crearProducto(dto);
                JOptionPane.showMessageDialog(this,
                        ok ? "✔ Producto registrado correctamente." : "✘ Error al registrar.",
                        "Gestión Inventario", JOptionPane.INFORMATION_MESSAGE);
                txtNombre.setText(""); txtPrecio.setText(""); txtCantidad.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Datos inválidos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnListar.addActionListener(e -> {
            modelo.setRowCount(0);
            List<ProductoDTO> lista = controlador.listarProductos();
            lista.forEach(p -> modelo.addRow(new Object[]{
                    p.getId(), p.getNombre(), p.getPrecio(), p.getCantidad()
            }));
        });

        panel.add(formulario, BorderLayout.NORTH);
        panel.add(scroll,     BorderLayout.CENTER);
        return panel;
    }

    /** Muestra la ventana en el hilo de Swing */
    public void mostrar() {
        SwingUtilities.invokeLater(() -> setVisible(true));
    }
}
