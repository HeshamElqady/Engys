package it.elqady.hesham;

import it.elqady.hesham.service.FileProcessorApp;
import it.elqady.hesham.service.FileService;

public class Main {
    public static void main(String[] args) {
        FileService fileService = new FileService();
        FileProcessorApp fileProcessorApp = new FileProcessorApp();
        fileService.printFileContent();
        fileService.printMinnie();
        fileService.printStats();
        fileProcessorApp.start();
    }
}