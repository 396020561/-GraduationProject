package com.nchu.service.impl;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nchu.dao.BlackListDao;
import com.nchu.entity.BlackList;
import com.nchu.exception.MyException;
import com.nchu.service.BlackListService;

@Service
public class BlackListServiceImpl implements BlackListService {

	@Autowired
	private BlackListDao blackListDao;
	
	@Override
	public Page<BlackList> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return blackListDao.findAll(pageable);
	}

	@Override
	public BlackList findById(Integer id) {
		// TODO Auto-generated method stub
		return blackListDao.queryById(id);
	}

	@Override
	public void save(BlackList blackList) {
		// TODO Auto-generated method stub
		Date date = new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		blackList.setCreateTime(sdf.format(date).toString());
		blackListDao.save(blackList);
	}

	@Override
	public void delById(Integer id) {
		// TODO Auto-generated method stub
		blackListDao.deleteById(id);
	}
	
	@Transactional(readOnly = false,rollbackFor = Exception.class)
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {
		// TODO Auto-generated method stub
		  boolean notNull = false;
	      List<BlackList> userList = new ArrayList<>();
	      if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
	         throw new MyException("上传文件格式不正确");
	      }
	      boolean isExcel2003 = true;
	      if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
	         isExcel2003 = false;
	      }
	      InputStream is = file.getInputStream();
	      Workbook wb = null;
	      if (isExcel2003) {
	         wb = new HSSFWorkbook(is);
	      } else {
	         wb = new XSSFWorkbook(is);
	      }
	      Sheet sheet = wb.getSheetAt(0);
	      if(sheet!=null){
	         notNull = true;
	      }
	      BlackList blackList;
	      for (int r = 2; r <= sheet.getLastRowNum(); r++) {//r = 2 表示从第三行开始循环 如果你的第三行开始是数据
	         Row row = sheet.getRow(r);//通过sheet表单对象得到 行对象
	         if (row == null){
	            continue;
	         }
	         //sheet.getLastRowNum() 的值是 10，所以Excel表中的数据至少是10条；不然报错 NullPointerException
	         blackList=new BlackList();
	         if( row.getCell(0).getCellType() !=1){//循环时，得到每一行的单元格进行判断
	            throw new MyException("导入失败(第"+(r+1)+"行,用户名请设为文本格式)");
	         }
	         row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第1个单元格的值
	         String name = row.getCell(0).getStringCellValue();
	         if(name == null || name.isEmpty()){//判断是否为空
	            throw new MyException("导入失败(第"+(r+1)+"行,用户名未填写)");
	         }
	         row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
	         String faceid = row.getCell(1).getStringCellValue();
	         if(faceid==null || faceid.isEmpty()){
	            throw new MyException("导入失败(第"+(r+1)+"行,人脸id未填写)");
	         }
	         row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
	         String sex = row.getCell(2).getStringCellValue();
	         if(sex==null || sex.isEmpty()){
	            throw new MyException("导入失败(第"+(r+1)+"行,密码未填写)");
	         }
	         //完整的循环一次 就组成了一个对象
	        blackList.setName(name);
	        blackList.setFaceid(faceid);
	        blackList.setSex(sex);
	         userList.add(blackList);
	      }
	      for (BlackList user : userList) {
	         blackListDao.save(user);
	      }
	      return notNull;
	   }
	}
