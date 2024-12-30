package org.example.GUI.Ventanas;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;
import net.miginfocom.swing.MigLayout;
import org.example.Controlador.AgendaControlador;
import org.example.Persistencia.Model.Entidades.Contacto;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaPrincipal extends JFrame {
    private final AgendaControlador controlador = new AgendaControlador();
    private ModeloTabla modeloTabla;
    private JTable tabla;
    private static final Color PRIMARY_COLOR = new Color(63, 81, 181);
    private static final Color ACCENT_COLOR = new Color(92, 107, 192);
    
    public VentanaPrincipal() {
        // Configurar tema moderno
        setupLookAndFeel();
        
        // Configurar ventana principal
        setTitle("Agenda de Contactos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1000, 600));
        setLocationRelativeTo(null);
        
        // Usar MigLayout para mejor organización
        setLayout(new MigLayout("fill, insets 20", "[grow]", "[grow][]"));
        
        // Configurar tabla
        setupTable();
        
        // Configurar panel de botones
        setupButtonPanel();
        
        // Agregar barra de búsqueda
        setupSearchBar();
    }
    
    private void setupLookAndFeel() {
        try {
            FlatMaterialLighterIJTheme.setup();
            UIManager.put("Button.arc", 10);
            UIManager.put("Component.arc", 10);
            UIManager.put("TextComponent.arc", 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void setupTable() {
        modeloTabla = new ModeloTabla(controlador.getAll());
        tabla = new JTable(modeloTabla);
        
        // Estilo de tabla
        tabla.setRowHeight(40);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.setSelectionBackground(new Color(232, 234, 246));
        tabla.setSelectionForeground(Color.BLACK);
        tabla.setIntercellSpacing(new Dimension(10, 10));
        tabla.setShowGrid(false);
        
        // Estilo del encabezado
        JTableHeader header = tabla.getTableHeader();
        header.setBackground(PRIMARY_COLOR);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setPreferredSize(new Dimension(header.getWidth(), 45));
        
        // Renderizador personalizado para las celdas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                return c;
            }
        };
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        // Panel de desplazamiento personalizado
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.WHITE);
        
        add(scrollPane, "grow, wrap");
    }
    
    private void setupButtonPanel() {
        JPanel panelBotones = new JPanel(new MigLayout("insets 0", "[]10[]10[]10[]"));
        panelBotones.setBackground(Color.WHITE);
        
        // Estilo común para botones
        JButton[] botones = {
            createStyledButton("Borrar", new Color(244, 67, 54)),
            createStyledButton("Actualizar", new Color(33, 150, 243)),
            createStyledButton("Nuevo Contacto", new Color(76, 175, 80)),
            createStyledButton("Refrescar Datos", new Color(156, 39, 176))
        };
        
        // Agregar acciones a los botones
        botones[0].addActionListener(e -> {
            int selectedRow = tabla.getSelectedRow();
            if (selectedRow != -1) {
                if (JOptionPane.showConfirmDialog(this, 
                    "¿Está seguro de que desea eliminar este contacto?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                    Contacto contacto = modeloTabla.getContactoAt(selectedRow);
                    controlador.delete(contacto.getId());
                    modeloTabla.actualizarDatos(controlador.getAll());
                }
            } else {
                mostrarMensaje("Seleccione un contacto para borrar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        botones[1].addActionListener(e -> {
            int selectedRow = tabla.getSelectedRow();
            if (selectedRow != -1) {
                Contacto contacto = modeloTabla.getContactoAt(selectedRow);
                new ActualizarContacto(contacto).setVisible(true);
                modeloTabla.actualizarDatos(controlador.getAll());
            } else {
                mostrarMensaje("Seleccione un contacto para actualizar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        botones[2].addActionListener(e -> {
            new GuardarContacto().setVisible(true);
            modeloTabla.actualizarDatos(controlador.getAll());
        });
        
        botones[3].addActionListener(e -> {
            modeloTabla.actualizarDatos(controlador.getAll());
            mostrarMensaje("Datos actualizados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        });
        
        // Agregar botones al panel
        for (JButton boton : botones) {
            panelBotones.add(boton);
        }
        
        add(panelBotones, "center, wrap");
    }
    
    private void setupSearchBar() {
        JPanel searchPanel = new JPanel(new MigLayout("insets 0 0 10 0", "[]10[grow]10[]"));
        searchPanel.setBackground(Color.WHITE);
        
        JLabel searchLabel = new JLabel("Buscar:");
        searchLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        JTextField searchField = new JTextField(20);
        searchField.putClientProperty("JTextField.placeholderText", "Ingrese el nombre o apellido del contacto...");
        
        JButton searchButton = createStyledButton("Buscar", ACCENT_COLOR);
        
        searchPanel.add(searchLabel);
        searchPanel.add(searchField, "grow");
        searchPanel.add(searchButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verContactos ver=new   verContactos(controlador.getByNameOrLastName(searchField.getText()));
               ver.setVisible(true);
            }
        });
        
        add(searchPanel, "dock north");
    }
    
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(150, 40));
        
        // Efectos hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        
        return button;
    }
    
    private void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }
    
    // La clase ModeloTabla se mantiene igual
    class ModeloTabla extends AbstractTableModel {
        private final String[] columnas = {"Nombre", "Apellido", "Correo", "Numero"};
        private List<Contacto> datos;

        public ModeloTabla(List<Contacto> datos) {
            this.datos = datos;
        }

        @Override
        public int getRowCount() {
            return datos.size();
        }

        @Override
        public int getColumnCount() {
            return columnas.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Contacto contacto = datos.get(rowIndex);
            return switch (columnIndex) {
                case 0 -> contacto.getNombre();
                case 1 -> contacto.getApellido();
                case 2 -> contacto.getCorreo();
                case 3 -> contacto.getNumeroDeTelefono();
                default -> null;
            };
        }

        @Override
        public String getColumnName(int column) {
            return columnas[column];
        }

        public void actualizarDatos(List<Contacto> nuevosDatos) {
            this.datos = nuevosDatos;
            fireTableDataChanged();
        }

        public Contacto getContactoAt(int row) {
            return datos.get(row);
        }
    }

    
}