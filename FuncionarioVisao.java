/*------------------------------------
Tema: Gestão de uma Barbearia
Nome: Enio Manuel
Numero: 2817
Ficheiro: FuncionarioVisao.java
Data: 10.07.2025
--------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class FuncionarioVisao extends JFrame 
{
    private PainelCentro centro;
    private PainelSul sul;
    private boolean editar;

    public FuncionarioVisao(boolean alterar, FuncionarioModelo modelo)
    {
        super("Funcionario Visao");
    
        editar = alterar;

        definirTema();
        if(!alterar)
        {
            	getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        }
        else
            getContentPane().add(centro = new PainelCentro(modelo), BorderLayout.CENTER);
         getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        setSize(400, 300);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    class PainelCentro extends JPanel
    {
        private JTextField idJTF, nomeJTF, contactoJTF;
        private JComboBox generoJCB, especialidadeJCB, provinciaJCB, municipioJCB, comunaJCB;
        private JComboBoxTabela3_Tabela3 provinciaComMunicipio;
        private String[] arrayGenero = {"Masculino", "Feminino"};
        private FuncionarioFile file;

        public PainelCentro()
        {
            setLayout(new GridLayout(8, 2));
            provinciaComMunicipio = new JComboBoxTabela3_Tabela3("Provincias.tab", "Municipios.tab", "Comunas.tab");
            file = new FuncionarioFile();

            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());
            idJTF.setText("000" + file.getProximoCodigo());
            idJTF.setFocusable(false);

            // 2º linha
            add(new JLabel("Nome"));
            add(nomeJTF = new JTextField());

            // 3º linha 
            add(new JLabel("Contacto"));
            add(contactoJTF = new JTextField());

            // 4º linha
            add(new JLabel("Genero"));
            add(generoJCB = new JComboBox(arrayGenero));

            // 5º linha
            add(new JLabel("Especialidade"));
            add(especialidadeJCB = UInterfaceBox.createJComboBoxsTabela2("Especialidades.tab"));

            // 6º linha
            add(new JLabel("Provincia"));
            add(provinciaJCB = provinciaComMunicipio.getComboBoxFather());

            // 7º linha
            add(new JLabel("Municipio"));
            add(municipioJCB = provinciaComMunicipio.getComboBoxSun());

            // 8º liha
            add(new JLabel("Comuna"));
            add(comunaJCB = provinciaComMunicipio.getComboBoxNeto());
        }

        public PainelCentro(FuncionarioModelo modelo)
        {
            setLayout(new GridLayout(8, 2));
            provinciaComMunicipio = new JComboBoxTabela3_Tabela3("Provincias.tab", "Municipios.tab", "Comunas.tab");
            file = new FuncionarioFile();

            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());
            idJTF.setText("000" + file.getProximoCodigo());
            idJTF.setText("" + modelo.getId());
            idJTF.setFocusable(false);

            // 2º linha
            add(new JLabel("Nome"));
            add(nomeJTF = new JTextField());
            nomeJTF.setText(modelo.getNome());

            // 3º linha 
            add(new JLabel("Contacto"));
            add(contactoJTF = new JTextField());
            contactoJTF.setText(modelo.getContacto());

            // 4º linha
            add(new JLabel("Genero"));
            add(generoJCB = new JComboBox(arrayGenero));
            generoJCB.setSelectedItem(modelo.getGenero());

            // 5º linha
            add(new JLabel("Especialidade"));
            add(especialidadeJCB = UInterfaceBox.createJComboBoxsTabela2("Especialidades.tab"));
            especialidadeJCB.setSelectedItem(modelo.getEspecialidade());

            // 6º linha
            add(new JLabel("Provincia"));
            add(provinciaJCB = provinciaComMunicipio.getComboBoxFather());
            provinciaJCB.setSelectedItem(modelo.getProvincia());

            // 7º linha
            add(new JLabel("Municipio"));
            add(municipioJCB = provinciaComMunicipio.getComboBoxSun());
            municipioJCB.setSelectedItem(modelo.getMunicipio());

            // 8º liha
            add(new JLabel("Comuna"));
            add(comunaJCB = provinciaComMunicipio.getComboBoxNeto());
            comunaJCB.setSelectedItem(modelo.getComuna());
        }

        // metodos getters
        public int getId()
        {
            return Integer.parseInt(idJTF.getText().trim());
        }

        public String getNome()
        {
            return nomeJTF.getText().trim();
        }

        public String getContacto()
        {
            return contactoJTF.getText().trim();
        }

        public String getGenero()
        {
            return String.valueOf(generoJCB.getSelectedItem());
        }

        public String getEspecialidade()
        {
            return String.valueOf(especialidadeJCB.getSelectedItem());
        }

        public String getProvincia()
        {
            return String.valueOf(provinciaJCB.getSelectedItem());
        }

        public String getMunicipio()
        {
            return String.valueOf(municipioJCB.getSelectedItem());
        }

        public String getComuna()
        {
            return String.valueOf(comunaJCB.getSelectedItem());
        }

        // metodos setters
        public void setId(int id)
        {
            idJTF.setText("" + id);
        }

        public void setNome(String nome)
        {
            nomeJTF.setText(nome);
        }

        public void setContacto(String contacto)
        {
            contactoJTF.setText(contacto);
        }

        public void setGenero(String genero)
        {
            generoJCB.setSelectedItem(genero);
        }

        public void setEspecialidade(String especialidade)
        {
            especialidadeJCB.setSelectedItem(especialidade);
        }

        public void setProvincia(String provincia)
        {
            provinciaJCB.setSelectedItem(provincia);
        }

        public void setMunicipio(String municipio)
        {
            municipioJCB.setSelectedItem(municipio);
        }

        public void setComuna(String comuna)
        {
            comunaJCB.setSelectedItem(comuna);
        }

        // metodo salvar
        public void salvar()
        {
            FuncionarioModelo modelo = new FuncionarioModelo(
            getId(),
            getNome(),
            getContacto(), 
            getGenero(),
            getEspecialidade(),
            getProvincia(),
            getMunicipio(),
            getComuna(),
            true);

            JOptionPane.showMessageDialog(null, modelo.toString());
            modelo.salvar();
        }

        // metodo alterar
        public void alterar()
        {
            FuncionarioModelo modelo = new FuncionarioModelo(
            getId(),
            getNome(),
            getContacto(), 
            getGenero(),
            getEspecialidade(),
            getProvincia(),
            getMunicipio(),
            getComuna(),
            true);

            JOptionPane.showMessageDialog(null, modelo.toString());
            modelo.salvarDados();
        }
    }

    class PainelSul extends JPanel implements ActionListener
    {
        private JButton salvarJBT, cancelarJBT;
        
        public PainelSul()
        {
           setLayout(new FlowLayout());

            add(salvarJBT = new JButton("Salvar", new ImageIcon("image/save24.png")));
            add(cancelarJBT = new JButton("Cancelar", new ImageIcon("image/cancel24.png")));

            salvarJBT.setBackground(Color.GREEN);
            cancelarJBT.setBackground(Color.RED);

            salvarJBT.setForeground(Color.WHITE);
            cancelarJBT.setForeground(Color.WHITE);
            
            salvarJBT.addActionListener(this);
            cancelarJBT.addActionListener(this);
        }

        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == salvarJBT)
            {
                if(editar)
                    centro.alterar();
                else
                    centro.salvar(); 
            }
            else
                dispose();
        }
    }

    public void definirTema() 
    {
        try 
        {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
                {
                    if ("Nimbus".equals(info.getName())) 
                    {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) 
        {
        }
    }

    public static void main(String[] args)
    {
        Vector_Tabelas.inic();
        new FuncionarioVisao(false, new FuncionarioModelo());
    }
}