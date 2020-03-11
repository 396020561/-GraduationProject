package com.nchu.controller;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nchu.entity.BlackList;
import com.nchu.service.BlackListService;

@Controller
public class ExcelController {
	
	@Autowired
	private BlackListService blackListService;
	
	@RequestMapping("/upload/blackList/excel/")
	public String upload(@RequestParam("file") MultipartFile file) throws IOException{
        long size=file.getSize();
        if(file.getOriginalFilename()==null || file.getOriginalFilename().equals("") || size==0){
            System.out.println("文件不能为空");
            return null;
        }

        HSSFWorkbook workbook = new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
        int numberOfSheets = workbook.getNumberOfSheets();//获得有多少sheet
        HSSFSheet sheet = workbook.getSheetAt(0);//默认只有一个sheet
        int rows = sheet.getPhysicalNumberOfRows();//获得sheet有多少行
        //遍历行
        for (int j = 0; j < rows; j++) {
            if (j == 0) {
                continue;//标题行(省略)
            }
            HSSFRow row = sheet.getRow(j);
            BlackList blackList = new BlackList();
            for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {
                HSSFCell cell = row.getCell(k);
                System.out.println(cell.toString());
            }
        }
		
		return null;
	}
}
