public interface ApplicationServiceLayerSaep {
    void criarPonuacao();
    List<String> obterConfiguracoes();
    InputStream obterParecerPDF();
    InputStream obterParecerHTML();
    InputStream obterRelatorioPDF();
    InputStream obterRelatorioHTML();
}