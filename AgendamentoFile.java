/*------------------------------------
Tema: Gestão de uma Barbearia
Nome: Enio Manuel
Numero: 2817
Ficheiro: AgendamentoFile.java
Data: 11.07.2025
--------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class AgendamentoFile extends ObjectsFile
{
    public AgendamentoFile()
    {
        super("AgendamentoFile.dat", new AgendamentoModelo());
    }  

    public void salvarDados(AgendamentoModelo modelo)
    {
        try
        {
            // colocar o file pointer no final do ficheiro
            stream.seek(stream.length());

            // escrever no modelo
            modelo.write(stream);

            incrementarProximoCodigo();
            JOptionPane.showMessageDialog(null,  "Dados Salvos com Sucessso");
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Falha ao Salvar os Dados");
        }
    }

    public void alterarDados(AgendamentoModelo modelo_novo)
	{
		AgendamentoModelo modelo_antigo = new AgendamentoModelo();
		
		try
		{
			stream.seek(4);
			
			for(int i = 0; i < getNregistos(); ++i)
			{
				modelo_antigo.read( stream );
				
				if (i == 0 && modelo_antigo.getId() == modelo_novo.getId())
				{
					stream.seek(4); 
					modelo_novo.write( stream );
					JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
					return;
				}	
				else
				{
					if (modelo_antigo.getId() + 1 == modelo_novo.getId())
					{
						modelo_novo.write( stream);
						return;
					}
							
				}			
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

    public void eliminarDados(AgendamentoModelo modelo_novo)
	{
		AgendamentoModelo modelo_antigo = new AgendamentoModelo();
		
		try
		{
			stream.seek(4);
			
			for(int i = 0; i < getNregistos(); ++i)
			{
				modelo_antigo.read( stream );
				
				if (i == 0 && modelo_antigo.getId() == modelo_novo.getId())
				{
					stream.seek(4); 
					modelo_novo.write( stream );
					JOptionPane.showMessageDialog(null, "Dados eliminados com sucesso!");
					return;
				}	
				else
				{
					if (modelo_antigo.getId() + 1 == modelo_novo.getId())
					{
						modelo_novo.write(stream);
						return;
					}							
				}			
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

    public static void listarAgendamentos()
    {
        AgendamentoFile file = new AgendamentoFile();
        AgendamentoModelo modelo = new AgendamentoModelo();
        
        // Cabeçalhos da tabela
        String[] colunas = {"Id", "Data de Agenda", "Horario", "Estado do Agendamento"};

        // lista para aramazenar temporariamente os dados
        java.util.List<Object[]>linhas = new java.util.ArrayList<>();

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getStatus() == true)
                {
                    Object[] linha = 
                    {
                        modelo.getId(),
                        modelo.getDataAgenda(),
                        modelo.getHorario(),
                        modelo.getEstado()
                    };
                    linhas.add(linha);
                }
            }

            // Converter lista para array de objetos para JTable
            Object[][] dados = new Object[linhas.size()][colunas.length];
            
            for(int i = 0; i < linhas.size(); i++)
            {
                dados[i] = linhas.get(i);
            }

             JTable tabela = new JTable(dados, colunas);
            JScrollPane scroll = new JScrollPane(tabela);
            tabela.setFillsViewportHeight(true);

            JOptionPane.showMessageDialog(null, scroll,
                "Gestao de Barbearia", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static int pesquisarPorId(int idProcurado)
    {
        AgendamentoFile file = new AgendamentoFile();
        AgendamentoModelo modelo = new AgendamentoModelo();

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getId() == idProcurado && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    return 0;
                }
            }
               JOptionPane.showMessageDialog(null, "Erro, id nao encontrado", 
                    "File Not Found", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return idProcurado;
    }

    public static String pesquisarPorData(String dataProcurada)
    {
        AgendamentoFile file = new AgendamentoFile();
        AgendamentoModelo modelo = new AgendamentoModelo();

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getDataAgenda().equalsIgnoreCase(dataProcurada) && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    return "";
                }
            }
               JOptionPane.showMessageDialog(null, "Erro, data de agendamento nao encontrado", 
                    "File Not Found", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return dataProcurada;
    }

    // metodos para a edicao
    public static AgendamentoModelo getPesquisaPorId(int idProcurado)
    {
        AgendamentoFile file = new AgendamentoFile();
        AgendamentoModelo modelo = new AgendamentoModelo();

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getId() == idProcurado && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    return modelo;
                }
            }
               JOptionPane.showMessageDialog(null, "Erro, id nao encontrado", 
                    "File Not Found", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return modelo;
    }

    public static AgendamentoModelo getPesquisaPorData(String dataProcurada)
    {
        AgendamentoFile file = new AgendamentoFile();
        AgendamentoModelo modelo = new AgendamentoModelo();

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getDataAgenda().equalsIgnoreCase(dataProcurada) && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    return modelo;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Erro, data de agendamento nao encontrado", 
                    "File Not Found", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return modelo;
    }

}   