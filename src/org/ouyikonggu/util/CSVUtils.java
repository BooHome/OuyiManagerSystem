package org.ouyikonggu.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.ouyikonggu.moudel.Slides;

public class CSVUtils {  
    /** 
     * ����ΪCVS�ļ� 
     * @param <T>
     * @param <T>
     *  
     * @param exportData 
     *            Դ����List 
     * @param fileds 
     * @param map 
     *            csv�ļ����б�ͷmap 
     * @param outPutPath 
     *            �ļ�·�� 
     * @param fileName 
     *            �ļ����� 
     * @return 
     */  
	 public static  <T> File createCSVFile(List <T>exportData, String[] fileds,  
			 			LinkedHashMap map, String outPutPath, String fileName) {
		 File csvFile = null;  
	     BufferedWriter csvFileOutputStream = null;  
	     try {
	    	 File file = new File(outPutPath);  
	            if (!file.exists()) {  
	                file.mkdir();  
	            }  
			// �����ļ�����ʽ������  
            csvFile = File.createTempFile(fileName, ".csv", file);  
			// UTF-8ʹ��ȷ��ȡ�ָ���","
			csvFileOutputStream = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(csvFile), "UTF-8"),1024);
			 System.out.println("csvFileOutputStream��" + csvFileOutputStream);  
			 //�ֶ�����BOM,���UTF-8��������
			 csvFileOutputStream.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB,(byte) 0xBF }, "UTF-8"));  
			// д���ļ�ͷ��
			for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext();) {
				java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
				csvFileOutputStream.write((String) propertyEntry.getValue() != null
						? new String(((String) propertyEntry.getValue()).getBytes("UTF-8"), "UTF-8")
						: "");
				if (propertyIterator.hasNext()) {
					csvFileOutputStream.write(",");
				}
			}
			csvFileOutputStream.write("\r\n");
			// д���ļ�����,  
			// ============ //��һ�ָ�ʽ��Arraylist<ʵ����>���ʵ����Ļ�����Ϣ==================
			for (int j = 0; exportData != null && !exportData.isEmpty() && j < exportData.size(); j++) {
				T t = (T) exportData.get(j);
				Class clazz = t.getClass();
				String[] contents = new String[fileds.length];
				for (int i = 0; fileds != null && i < fileds.length; i++) {
					String filedName = toUpperCaseFirstOne(fileds[i]);
					Method method = clazz.getMethod(filedName);
					method.setAccessible(true); //���������û���ð�ȫ��飬�������
					Object obj = method.invoke(t);
					String str = String.valueOf(obj);
					if (str == null || str.equals("null")) {
						str = "";
					}
					contents[i] = str;

				}

				for (int n = 0; n < contents.length; n++) {
					// �����ɵĵ�Ԫ����ӵ���������
					csvFileOutputStream.write(contents[n]);
					csvFileOutputStream.write(",");

				}
				csvFileOutputStream.write("\r\n");
			}
			csvFileOutputStream.flush(); 
		} catch (Exception e) {
			 e.printStackTrace();  
		}finally {
			try {  
                csvFileOutputStream.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
		}
		 return csvFile;
	 }
	 
	/**
	 * ����һ����ĸת��Ϊ��д��ĸ����getƴ�ϳɷ���
	 * 
	 * @param origin
	 * @return
	 */
	private static String toUpperCaseFirstOne(String origin) {
		StringBuffer sb = new StringBuffer(origin);
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		sb.insert(0, "get");
		return sb.toString();
	}
	
	public static void main(String[] args) {
		 // =======�ĳ�list�ĸ�ʽ��֧�֣�Arraylist����ʵ�������ʽ��������ķ���============  
        ArrayList<Slides> bankWageList = new ArrayList<Slides>();  
        Slides pc1 = new Slides();
        pc1.setSTitle("һ��");
        pc1.setScid(1);
        pc1.setSTitleImg("sadasdsaddsa");
        
        Slides pc2= new Slides();
        pc2.setSTitle("һ��");
        pc2.setScid(1);
        pc2.setSTitleImg("sadasdsaddsa");
        
        bankWageList.add(pc1);
        bankWageList.add(pc2);
        
        LinkedHashMap map = new LinkedHashMap();  
        map.put("1", "����");  
        map.put("2", "����");  
        map.put("3", "�ƹ��ַ");  
        
        String path = "D://export//";  
        String fileName = "�ļ�����";  
        String fileds[] = new String[] { "STitle", "Scid","STitleImg"};// ������Ӣ������Ҳ����ʵ���������Ӧ��������  
        File file = CSVUtils.createCSVFile(bankWageList, fileds, map, path,  
                fileName);  
        String fileName2 = file.getName();  
        System.out.println("�ļ����ƣ�" + fileName2);  
	}
}  
