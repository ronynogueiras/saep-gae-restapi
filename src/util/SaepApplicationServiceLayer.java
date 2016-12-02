package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
/**
 * Interface publica para retorno de dados da aplicação rest SAEP
 * @author Rony Nogueira
 * @version 1.1
 * */
public interface SaepApplicationServiceLayer {
	/**
	 * Método para criação de pontuação do RADOC 
	 * */
	void createPoints();
	/**
	 * Método para obter a configuração de um RADOC
	 * @return List<String>
	 * */
    List<String> getSettings();
    /**
     * Obtem um parecer em PDF
     * @return InputStream
     * */
    InputStream getDocumentPDF();
    /**
     * Obtem um parecer em HTML
     * @return InputStream
     * */
    InputStream getDocumentHTML();
    /**
     * Obtem um relatório (RADOC) em PDF
     * @return InputStream
     * */
    InputStream getReportPDF();
    /**
     * Obtem um relatório (RADOC) em HTML
     * @return InputStream
     * */
    InputStream getReportHTML();
    /**
     * Cria um relatório (RADOC) através do UUID fornecido
     * @author Rony Nogueira
     * @return boolean
     * */
    boolean createReport(String uuid, String report);
    /**
     * Apaga um relatório (RADOC) através do UUID fornecido
     * @author Rony Nogueira
     * @return boolean
     * */
    boolean deleteReport(String uuid);
    /**
     * Obtem uma resolução a partir de um UUID
     * @return InputStream
     * @throws IOException 
     * */    
    InputStream getResolution(String uuid) throws IOException;
    /**
     * Obtem o status de operação do servidor de aplicação SAEP
     * @return int
     * */
    int status();
    /**
     * Apaga uma resolução que possui o UUID informado
     * @return boolean 
     * */
    boolean deleteResolution(String uuid);    
    /**
     * Obtem os identificadores das resoluções armazenadas
     * @return String
     * */
    String getIdsResolutions();
    /**
     * Cria uma nova resolução através do uuid informado
     * @param String uuid  
     * @param String resolution
     * @return boolean
     * */
    boolean createResolution(String uuid, String resolution);
    /**
     * Obtem as alterações realizadas para um dado parecer
     * @author Rony Nogueira
     * @return String
     * */
    String getChangesDocument(String uuid);
}
