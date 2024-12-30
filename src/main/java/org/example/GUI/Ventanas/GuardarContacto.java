package org.example.GUI.Ventanas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.example.Controlador.AgendaControlador;

public class GuardarContacto extends JFrame{
    AgendaControlador controlador = new AgendaControlador();
   public GuardarContacto(){
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(400, 600);
        this.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        this.setVisible(true);
       

        JLabel titulo = new JLabel(" Guardar Contacto");
        titulo.setFont(new Font("Arial", Font.BOLD, 20)); 
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(titulo, gbc);

      
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        JTextField Camponombre = new JTextField();
        Camponombre.setPreferredSize(new Dimension(200, 35)); 
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(Camponombre, gbc);

       
        JLabel etiquetaNombre = new JLabel("Nombre");
        etiquetaNombre.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(etiquetaNombre, gbc);

       
        JLabel etiquetaApellido = new JLabel("Apellido:");  
        etiquetaApellido.setFont(new Font("Arial", Font.PLAIN, 16));
    
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(etiquetaApellido, gbc);

        JTextField campoApellido = new JTextField();
        campoApellido.setPreferredSize(new Dimension(200, 35)); 
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.add(campoApellido, gbc);

       
        JLabel etiquetaCorreo = new JLabel("Correo");
        etiquetaCorreo.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(etiquetaCorreo, gbc);

        JTextField campoCorreo = new JTextField();
        campoCorreo.setPreferredSize(new Dimension(200, 35)); 
        gbc.gridx = 1;
        gbc.gridy = 3;
        this.add(campoCorreo, gbc);

        JLabel etiquetaNumero = new JLabel("Numero");
        etiquetaNumero.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 4;
        this.add(etiquetaNumero, gbc);

        JTextField campoNumero = new JTextField();
        campoNumero.setPreferredSize(new Dimension(200, 35)); 
        gbc.gridx = 1;
        gbc.gridy = 4;
        this.add(campoNumero, gbc);

        JButton boton=new JButton("guardar");
        boton.setForeground(Color.WHITE);
        boton.setBackground(Color.DARK_GRAY);
        boton.setPreferredSize(new Dimension(85,40));
        gbc.gridx = 1;
        gbc.gridy = 5;
        this.add(boton,gbc);
        boton.addActionListener(e -> {
            String nombre = Camponombre.getText();
            String apellido = campoApellido.getText();
            String correo = campoCorreo.getText();
            String numero = campoNumero.getText();
            if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || numero.isEmpty()) {
               JOptionPane.showMessageDialog(null, "faltan datos");
            } else {
                controlador.save(nombre, apellido, correo, numero);
            }
        });
    }
   }

