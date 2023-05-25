package com.mahavir.boq.services;

import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mahavir.boq.daos.ProductDao;
import com.mahavir.boq.helper.ResponseMessage;
import com.mahavir.boq.models.Product;


@Service
public class ProductService {
    
    @Autowired
    public Product product;

    @Autowired
    public ProductDao productDao;

    @Autowired
    public ResponseMessage responseMessage;

    public ResponseEntity<?> addProduct(InputStream is){
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheet("products");
            int rowNumber = 0;
            
            Iterator<Row> rowIterator = sheet.iterator();

            DataFormatter formatter = new DataFormatter();
            
            while(rowIterator.hasNext()){
                Row row = rowIterator.next();

                if(rowNumber == 0){
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellIterator = row.cellIterator();

                int cId = 0;
                boolean flag = false;

                while(cellIterator.hasNext()){
                    Cell cell = cellIterator.next();

                    switch(cId){
                        
                        case 0:
                            try {
                                product.setItemName(formatter.formatCellValue(cell));
                            } catch (Exception e) {
                                // TODO: handle exception
                                e.printStackTrace();
                                flag = true;
                            }
                            break;
                        case 1:
                            try {
                                product.setModelName(formatter.formatCellValue(cell));
                            } catch (Exception e) {
                                // TODO: handle exception
                                e.printStackTrace();
                                flag = true;
                            }
                            break;
                        case 2:
                            try {
                                String desc[] = formatter.formatCellValue(cell).split("#");
                                product.setDescription(desc);
                            } catch (Exception e) {
                                // TODO: handle exception
                                e.printStackTrace();
                                flag = true;
                            }
                            break;
                        case 3:
                            try {
                                product.setUnitRate(formatter.formatCellValue(cell));
                            } catch (Exception e) {
                                e.printStackTrace();
                                flag = true;
                            }
                            break;
                        case 4:
                            try {
                                product.setHsnCode(formatter.formatCellValue(cell));
                            } catch (Exception e) {
                                // TODO: handle exception
                                e.printStackTrace();
                                flag = true;
                            }
                            break;
                        case 5:
                            try {
                                product.setGstRate(formatter.formatCellValue(cell));
                            } catch (Exception e) {
                                // TODO: handle exception
                                e.printStackTrace();
                                flag = true;
                            }
                            break;
                        default:
                            break;
                    }

                    cId++;

                }
                
                if(!flag){
                    productDao.save(product);
                }

                rowNumber++;
            }

            return ResponseEntity.status(HttpStatus.CREATED).build();

            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    } 

}
