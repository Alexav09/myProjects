package br.com.trifoglio.accountControl.controllers;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.trifoglio.accountControl.services.ExcelService;

@RestController
@RequestMapping("/import")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @PostMapping("/excel")
    public ResponseEntity<String> importExcel(@RequestParam("file") MultipartFile file) {
        try {
            excelService.importExcel(file);
            return ResponseEntity.ok("Dados importados com sucesso!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao importar os dados: " + e.getMessage());
        }
    }
}
