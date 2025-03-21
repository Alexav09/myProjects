package br.com.trifoglio.accountControl.services;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.trifoglio.accountControl.model.entities.Account;
import br.com.trifoglio.accountControl.model.repositories.AccountRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExcelService {

    @Autowired
    private AccountRepository accountRepository;

    public void importExcel(MultipartFile file) throws IOException {
        List<Account> accounts = new ArrayList<>();
        try (InputStream is = file.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);

            // Ler a linha de cabeçalho
            Map<String, Integer> headerMap = new HashMap<>();
            Row headerRow = sheet.getRow(0);
            for (Cell cell : headerRow) {
                headerMap.put(cell.getStringCellValue(), cell.getColumnIndex());
            }

            // Ler as linhas subsequentes
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue; // Pular a linha de cabeçalho
                }
                Account account = new Account();

                // Usar o Map para acessar as células corretamente
                if (headerMap.containsKey("accountId")) {
                    Cell cell = row.getCell(headerMap.get("accountId"));
                    if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                        account.setAccountId((int) cell.getNumericCellValue());
                    }
                }
                if (headerMap.containsKey("accountPpmId")) {
                    Cell cell = row.getCell(headerMap.get("accountPpmId"));
                    if (cell != null && cell.getCellType() == CellType.STRING) {
                        account.setAccountPpmId(cell.getStringCellValue());
                    }
                }
                if (headerMap.containsKey("accountSapId")) {
                    Cell cell = row.getCell(headerMap.get("accountSapId"));
                    if (cell != null && cell.getCellType() == CellType.STRING) {
                        account.setAccountSapId(cell.getStringCellValue());
                    }
                }
                if (headerMap.containsKey("accountName")) {
                    Cell cell = row.getCell(headerMap.get("accountName"));
                    if (cell != null && cell.getCellType() == CellType.STRING) {
                        account.setAccountName(cell.getStringCellValue());
                    }
                }
                if (headerMap.containsKey("accountDescription")) {
                    Cell cell = row.getCell(headerMap.get("accountDescription"));
                    if (cell != null && cell.getCellType() == CellType.STRING) {
                        account.setAccountDescription(cell.getStringCellValue());
                    }
                }
                if (headerMap.containsKey("accountType")) {
                    Cell cell = row.getCell(headerMap.get("accountType"));
                    if (cell != null && cell.getCellType() == CellType.STRING) {
                        account.setAccountType(cell.getStringCellValue());
                    }
                }

                accounts.add(account);
            }
        }
        accountRepository.saveAll(accounts);
    }
}
