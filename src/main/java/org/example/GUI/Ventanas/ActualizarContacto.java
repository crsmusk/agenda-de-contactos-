package org.example.GUI.Ventanas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.example.Controlador.AgendaControlador;
import org.example.Persistencia.Model.Entidades.Contacto;

public class ActualizarContacto extends JFrame {
    AgendaControlador controlador = new AgendaControlador();
    public ActualizarContacto(Contacto contacto){ {
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(400, 600);
        this.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        this.setVisible(true);
       

        JLabel titulo = new JLabel(" Actualizar Contacto");
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
        Camponombre.setText(contacto.getNombre());
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
        campoApellido.setText(contacto.getApellido());
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
        campoCorreo.setText(contacto.getCorreo());
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
        campoNumero.setText(contacto.getNumeroDeTelefono());
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
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.update(contacto.getId(),Camponombre.getText(),campoApellido.getText(),campoCorreo.getText(),campoNumero.getText());
                dispose();
            }
        });
    }
}
}
