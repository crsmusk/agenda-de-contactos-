package org.example.GUI.Ventanas;

import com.formdev.flatlaf.FlatLightLaf;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import org.example.Persistencia.Model.Entidades.Contacto;

import java.awt.*;
import java.util.List;

public class verContactos extends JFrame {
    private ModeloTabla modeloTabla;
    private JTable tabla;
    private List<Contacto> contactos;
    private static final Color PRIMARY_COLOR = new Color(63, 81, 181);
    
    public verContactos(List<Contacto> contactos) {
        this.contactos = contactos;
        setupLookAndFeel();
        initializeComponents();
    }
    
    private void setupLookAndFeel() {
        try {
            FlatLightLaf.setup();
            UIManager.put("Button.arc", 10);
            UIManager.put("Component.arc", 10);
            UIManager.put("TextComponent.arc", 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void initializeComponents() {
        // Configurar ventana
        setTitle("Lista de Contactos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(800, 500));
        setLocationRelativeTo(null);
        
        // Usar MigLayout para mejor organización
        setLayout(new MigLayout("fill, insets 20", "[grow]", "[]10[grow]10[]"));
        
        // Añadir título
        JLabel titleLabel = new JLabel("Lista de Contactos");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(PRIMARY_COLOR);
        add(titleLabel, "center, wrap");
        
        // Configurar tabla
        setupTable();
        
        // Añadir panel de estadísticas
        setupStatsPanel();
    }
    
    private void setupTable() {
        modeloTabla = new ModeloTabla(contactos);
        tabla = new JTable(modeloTabla);
        
        // Estilo de tabla
        tabla.setRowHeight(35);
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
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        
        // Renderizador para centrar el contenido
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                return c;
            }
        };
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        // Scroll Pane
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.WHITE);
        
        add(scrollPane, "grow, wrap");
    }
    
    private void setupStatsPanel() {
        JPanel statsPanel = new JPanel(new MigLayout("insets 0", "[]20[]20[]"));
        statsPanel.setBackground(Color.WHITE);
        
        // Crear etiquetas de estadísticas
        addStatLabel(statsPanel, "Total de Contactos:", String.valueOf(contactos.size()));
        
        // Contar contactos con correo
        long contactosConCorreo = contactos.stream()
                .filter(c -> c.getCorreo() != null && !c.getCorreo().isEmpty())
                .count();
        addStatLabel(statsPanel, "Con Correo:", contactosConCorreo + " (" + 
                String.format("%.1f%%", (contactosConCorreo * 100.0 / contactos.size())) + ")");
        
        // Contar contactos con teléfono
        long contactosConTelefono = contactos.stream()
                .filter(c -> c.getNumeroDeTelefono() != null && !c.getNumeroDeTelefono().isEmpty())
                .count();
        addStatLabel(statsPanel, "Con Teléfono:", contactosConTelefono + " (" + 
                String.format("%.1f%%", (contactosConTelefono * 100.0 / contactos.size())) + ")");
        
        add(statsPanel, "center");
    }
    
    private void addStatLabel(JPanel panel, String title, String value) {
        JPanel statPanel = new JPanel(new MigLayout("insets 0"));
        statPanel.setBackground(new Color(245, 245, 245));
        statPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        titleLabel.setForeground(PRIMARY_COLOR);
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        statPanel.add(titleLabel);
        statPanel.add(valueLabel);
        panel.add(statPanel);
    }
    
    class ModeloTabla extends AbstractTableModel {
        private final String[] columnas = {"Nombre", "Apellido", "Correo", "Teléfono"};
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
    }
    
    // Método para actualizar los datos
    public void actualizarDatos(List<Contacto> nuevosContactos) {
        this.contactos = nuevosContactos;
        modeloTabla = new ModeloTabla(contactos);
        tabla.setModel(modeloTabla);
        setupStatsPanel(); // Actualizar estadísticas
    }
}