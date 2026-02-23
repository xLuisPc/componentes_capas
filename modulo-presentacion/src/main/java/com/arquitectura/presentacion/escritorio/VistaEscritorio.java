package com.arquitectura.presentacion.escritorio;

import com.arquitectura.negocio.controlador.Controlador;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Componente VistaEscritorio.
 * Interfaz grafica de usuario de escritorio (Swing).
 * Accede a los servicios de las capas a traves del Controlador.
 */
public class VistaEscritorio extends JFrame {

    private final Controlador controlador;

    // --- Campos SistemaClientes ---
    private JTextField txtId, txtNombres, txtApellidos, txtEmail;
    private JTextArea areaResultados;

    // --- Campos GestorDocumentos ---
    private JComboBox<String> comboTipo;
    private JTextField txtContenido;

    public VistaEscritorio(Controlador controlador) {
        this.controlador = controlador;
        configurarVentana();
        crearComponentes();
    }

    private void configurarVentana() {
        setTitle("Monolito con Capas - Vista Escritorio");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
    }

    private void crearComponentes() {
        // --- Panel superior: pestanas ---
        JTabbedPane pestanas = new JTabbedPane();
        pestanas.addTab("Sistema Clientes (MySQL)", crearPanelClientes());
        pestanas.addTab("Gestor Documentos (H2)", crearPanelDocumentos());
        add(pestanas, BorderLayout.CENTER);

        // --- Panel inferior: area de resultados ---
        areaResultados = new JTextArea(8, 50);
        areaResultados.setEditable(false);
        areaResultados.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(areaResultados);
        scroll.setBorder(BorderFactory.createTitledBorder("Resultados"));
        add(scroll, BorderLayout.SOUTH);
    }

    private JPanel crearPanelClientes() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ID
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("ID:"), gbc);
        txtId = new JTextField(15);
        gbc.gridx = 1;
        panel.add(txtId, gbc);

        // Nombres
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Nombres:"), gbc);
        txtNombres = new JTextField(15);
        gbc.gridx = 1;
        panel.add(txtNombres, gbc);

        // Apellidos
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Apellidos:"), gbc);
        txtApellidos = new JTextField(15);
        gbc.gridx = 1;
        panel.add(txtApellidos, gbc);

        // Email
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Email:"), gbc);
        txtEmail = new JTextField(15);
        gbc.gridx = 1;
        panel.add(txtEmail, gbc);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnRegistrar = new JButton("Registrar Cliente");
        JButton btnListar = new JButton("Listar Clientes");

        btnRegistrar.addActionListener(e -> accionRegistrarCliente());
        btnListar.addActionListener(e -> accionListarClientes());

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnListar);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        panel.add(panelBotones, gbc);

        return panel;
    }

    private JPanel crearPanelDocumentos() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tipo
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Tipo:"), gbc);
        comboTipo = new JComboBox<>(new String[]{"HTML", "TEXTO", "PDF"});
        gbc.gridx = 1;
        panel.add(comboTipo, gbc);

        // Contenido
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Contenido:"), gbc);
        txtContenido = new JTextField(20);
        gbc.gridx = 1;
        panel.add(txtContenido, gbc);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnCrear = new JButton("Crear Documento");
        JButton btnListar = new JButton("Listar Documentos");
        JButton btnVisualizar = new JButton("Visualizar");

        btnCrear.addActionListener(e -> accionCrearDocumento());
        btnListar.addActionListener(e -> accionListarDocumentos());
        btnVisualizar.addActionListener(e -> accionVisualizarDocumento());

        panelBotones.add(btnCrear);
        panelBotones.add(btnListar);
        panelBotones.add(btnVisualizar);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panel.add(panelBotones, gbc);

        return panel;
    }

    // --- Acciones SistemaClientes ---

    private void accionRegistrarCliente() {
        try {
            double id = Double.parseDouble(txtId.getText().trim());
            String nombres = txtNombres.getText().trim();
            String apellidos = txtApellidos.getText().trim();
            String email = txtEmail.getText().trim();
            String mensaje = "Bienvenido a ***miempresa***";

            controlador.registrarCliente(id, nombres, apellidos, email, mensaje);

            String info = controlador.obtenerInfoCliente(id, nombres, apellidos, email, mensaje);
            areaResultados.setText("Cliente registrado exitosamente.\n" + info);

            // Limpiar campos
            txtId.setText("");
            txtNombres.setText("");
            txtApellidos.setText("");
            txtEmail.setText("");
        } catch (NumberFormatException ex) {
            areaResultados.setText("Error: El ID debe ser un numero.");
        }
    }

    private void accionListarClientes() {
        List<String> clientes = controlador.listarClientes();
        if (clientes.isEmpty()) {
            areaResultados.setText("No hay clientes registrados.");
        } else {
            areaResultados.setText("--- Clientes Registrados ---\n");
            clientes.forEach(c -> areaResultados.append(c + "\n"));
        }
    }

    // --- Acciones GestorDocumentos ---

    private void accionCrearDocumento() {
        String tipo = (String) comboTipo.getSelectedItem();
        String contenido = txtContenido.getText().trim();

        controlador.crearDocumento(tipo, contenido);
        areaResultados.setText("Documento " + tipo + " creado exitosamente.");
        txtContenido.setText("");
    }

    private void accionListarDocumentos() {
        List<String> documentos = controlador.listarDocumentos();
        if (documentos.isEmpty()) {
            areaResultados.setText("No hay documentos guardados.");
        } else {
            areaResultados.setText("--- Documentos Guardados ---\n");
            documentos.forEach(d -> areaResultados.append(d + "\n"));
        }
    }

    private void accionVisualizarDocumento() {
        String tipo = (String) comboTipo.getSelectedItem();
        String contenido = txtContenido.getText().trim();

        controlador.visualizarDocumento(tipo, contenido);
        areaResultados.setText("Documento " + tipo + " visualizado (ver consola para detalles).");
    }

    /**
     * Mostrar la ventana.
     */
    public void iniciar() {
        SwingUtilities.invokeLater(() -> setVisible(true));
    }
}
