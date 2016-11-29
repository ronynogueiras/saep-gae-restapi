package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
/**
 * Interface publica para retorno de dados da aplica��o rest SAEP
 * @author Rony Nogueira
 * @version 1.1
 * */
public interface SaepApplicationServiceLayer {
	/**
	 * M�todo para cria��o de pontua��o do RADOC 
	 * */
	void createPoints();
	/**
	 * M�todo para obter a configura��o de um RADOC
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
     * Obtem um relat�rio (RADOC) em PDF
     * @return InputStream
     * */
    InputStream getReportPDF();
    /**
     * Obtem um relat�rio (RADOC) em HTML
     * @return InputStream
     * */
    InputStream getReportHTML();
    /**
     * Obtem uma resolu��o a partir de um UUID
     * @return InputStream
     * @throws IOException 
     * */
    InputStream getResolution(String uuid) throws IOException;
    /**
     * Obtem o status de opera��o do servidor de aplica��o SAEP
     * @return int
     * */
    int status();
    /**
     * Apaga uma resolu��o que possui o UUID informado
     * @return boolean 
     * */
    boolean deleteResolution(String uuid);    
    /**
     * Obtem os identificadores das resolu��es armazenadas
     * @return String
     * */
    String getIdsResolutions();
}
