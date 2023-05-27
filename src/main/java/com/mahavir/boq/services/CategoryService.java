package com.mahavir.boq.services;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.mahavir.boq.daos.CategoryDao;
import com.mahavir.boq.helper.ResponseMessage;
import com.mahavir.boq.models.Brand;
import com.mahavir.boq.models.Category;
import com.mahavir.boq.models.Type;

@Component
public class CategoryService {
    

    @Autowired
    public ResponseMessage responseMessage;
   

    @Autowired
    public CategoryDao categoryDao;

    public ResponseEntity<?> addCategories(InputStream is){
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheet("categories");
            int rowNumber = 0;
            
            Iterator<Row> rowIterator = sheet.iterator();

            DataFormatter formatter = new DataFormatter();
            

            Category category = null;

            ArrayList<Type> types = null;
            
            while(rowIterator.hasNext()){
                Row row = rowIterator.next();

                if(rowNumber == 0){
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellIterator = row.cellIterator();

                int cId = 0;

                String typeName = null, categoryName = null;
                
                while(cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    boolean flag = false;
                    switch(cId){
                        case 0:
                            categoryName = formatter.formatCellValue(cell);
                            category = categoryDao.findByCategoryname(categoryName);
                            if(category == null){
                                category = new Category();
                                category.setCategoryname(categoryName);
                            }
                            categoryDao.save(category);
                        break;
                        case 1:
                            types = category.getTypes();
                            if(types == null){
                                types = new ArrayList<Type>();
                            }
                            typeName = formatter.formatCellValue(cell);
                            for(int i=0;i<types.size();i++){
                                if(types.get(i).getTypename().equals(typeName)){
                                    flag = true;
                                    break;
                                }
                            }


                            if(!flag){
                                Type type = new Type();
                                type.setTypename(typeName);
                                types.add(type);
                                category.setTypes(types);
                                categoryDao.save(category);
                            }

                        break;
                        case 2:
                            String brandName = formatter.formatCellValue(cell);
                            
                            category = categoryDao.findByCategoryname(categoryName);

                            types = category.getTypes();

                            for(int i=0;i<types.size();i++){
                                if(types.get(i).getTypename().equals(typeName)){
                                    
                                    types.get(i).getBrands().add(new Brand(brandName, null));
                                    category.setTypes(types);
                                    categoryDao.save(category);
                                    break;
                                }
                            }
                            
                            
                        break;
                        default:
                        break;
                    }

                    cId++;

                }
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

}
