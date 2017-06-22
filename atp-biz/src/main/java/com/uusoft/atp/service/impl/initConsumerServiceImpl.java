package com.uusoft.atp.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uusoft.atp.dao.InitConsumerMapper;
import com.uusoft.atp.model.InitConsumerInfo;
import com.uusoft.atp.service.initConsumerService;

@Service("initConsumerService")
@Transactional
public class initConsumerServiceImpl implements initConsumerService {

	@Resource
	private InitConsumerMapper cmapper;

	@Override
	public boolean insertxml(InitConsumerInfo initConsumerInfo) {
		try{
			String spath=System.getProperty("user.dir")+"\\src\\main\\resources\\spring\\";
			System.out.println(spath);
			File f = new File(spath+"dubbo-consumer.xml");
			SAXReader reader = new SAXReader();   
		    Document doc = reader.read(f);   
			Element root = doc.getRootElement();
			List<Element> booklist = root.elements();
			for(Element sonbooklist:booklist){
				List<Attribute> bookAttrs = sonbooklist.attributes();
				for (Attribute attr : bookAttrs) {
					if(initConsumerInfo.getServername().equals(attr)){
						return false;
					}
			    }
			}
			Element addelement = root.addElement("dubbo:reference");
			addelement.addAttribute("id", initConsumerInfo.getServername());
			addelement.addAttribute("interface", initConsumerInfo.getInterfacename());		
			addelement.addAttribute("version", initConsumerInfo.getVersion());
			addelement.addAttribute("generic", initConsumerInfo.getGeneric());
			addelement.addAttribute("owner", initConsumerInfo.getOwner());
			cmapper.insert(initConsumerInfo);
			try {
				FileOutputStream fos = new FileOutputStream(f);  
		            // 用于格式化输出  
		            // OutputFormat of = OutputFormat.createPrettyPrint();  
		            // 格式化输出的另一个形式，不知这两种有什么区别  
		            // 第1个参数为格式化输出缩排字符,此处为空格,第2个参数true为换行输出,false为单行输出  
		         OutputFormat of = new OutputFormat(" ", false);  
		            // 输出为GBK码解决在windows下某些系统下打开含有中文xml乱码的情况  
		         of.setEncoding("UTF-8");  
		         XMLWriter xw = new XMLWriter(fos, of);  
		         xw.write(doc);  
		         xw.close();  
		    } catch (IOException e) {  
		            e.printStackTrace();  
		    } 
		}catch (Exception e){
			e.printStackTrace();
		}
		return true;
	}
	
}
