package com.alex.dbms.utils;

import com.alex.dbms.constants.CommonConstants;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Namespace;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: Dom4j解析框架相关操作方法
 * @Author:     alex
 * @CreateDate: 2019/12/3 13:37
 * @Version:    1.0
 *
*/
public class Dom4jUtils {

    public static String nameSpacePrefix = null;

    private static String nameSpace = null;

    private Dom4jUtils() {}

    /**
     * @Description: 根据文件获取对应的document对象
     * @Author:      alex
     * @CreateDate:  2019/12/3 13:39
     * @param file
     * @return
    */
    public static Document getDocument(File file) {
        return getDocument(file.getAbsolutePath());
    }

    /**
     * @Description: 根据文件路径获取对应的document对象
     * @Author:      alex
     * @CreateDate:  2019/12/3 13:40
     * @param filePath
     * @return
    */
    public static Document getDocument(String filePath) {
        if (!new File(filePath).exists()){
            System.out.println(filePath + " is not exist!");
            return null;
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return getDocument(fis);
    }

    /**
     * @Description: 根据输入流读取对应的document对象
     * @Author:      alex
     * @CreateDate:  2019/12/3 13:42
     * @param input
     * @return
    */
    public static Document getDocument(InputStream input) {
        Document document = null;
        if (input != null) {
            try {
                SAXReader saxReader = new SAXReader();
                saxReader.setEncoding(CommonConstants.ENCODING_UTF8);
                DocumentFactory df = new DocumentFactory();
                Map<String, String> map = new HashMap<>();
                df.setXPathNamespaceURIs(map);
                saxReader.setDocumentFactory(df);
                document = saxReader.read(input);
                if (document.getRootElement().getNamespace() != Namespace.NO_NAMESPACE) {
                    nameSpace = "nameSpace";
                    nameSpacePrefix = nameSpace + ":";
                    map.put(nameSpace, document.getRootElement().getNamespace().getURI());
                }
            } catch (DocumentException e) {
                System.out.println(e.getMessage());
            } finally {
                if (input != null)
                    try {
                        input.close();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
            }
        }
        return document;
    }

    /**
     * @Description: 将文档内容持久化到文件
     * @Author:      alex
     * @CreateDate:  2019/12/3 14:02
     * @param document
     * @param file
     * @return
    */
    public static void writeDocument(Document document, File file) {
        try {
            OutputFormat of = OutputFormat.createPrettyPrint();
            of.setEncoding(CommonConstants.ENCODING_UTF8);
            FileOutputStream fos = new FileOutputStream(file);
            XMLWriter xmlWriter = new XMLWriter(fos, of);
            xmlWriter.write(document);
            xmlWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description:
     * @Author:      alex
     * @CreateDate:  2019/12/3 14:12
     * @param file
     * @param xpath
     * @return
    */
    public static List selectNodes(String file, String xpath) {
        return selectNodes(new File(file), xpath);
    }

    /**
     * @Description: 根据xpath获取查询结果列表集合
     * @Author:      alex
     * @CreateDate:  2019/12/3 14:13
     * @param file
     * @param xpath
     * @return
    */
    public static List selectNodes(File file, String xpath) {
        List nodeList = new ArrayList();
        if (file.exists()) {
            Document document = getDocument(file);
            xpath = translateXPath(xpath);
            if (document != null)
                nodeList = document.selectNodes(xpath);
        }
        return nodeList;
    }

    public static String translateXPath(String xpath) {
        String newXPath = "";
        if (!StringUtils.isEmpty(nameSpace)) {
            int i = xpath.lastIndexOf("/");
            while(xpath.length() > 1) {
                newXPath = "/" + nameSpace + ":" + xpath.substring(i + 1, xpath.length()) + newXPath;
                xpath = xpath.substring(0, i);
                i = xpath.lastIndexOf("/");
            }
        } else
            newXPath = xpath;
        return newXPath;
    }
}
