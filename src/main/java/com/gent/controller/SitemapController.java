package com.gent.controller;

import com.gent.model.Category;
import com.gent.model.Good;
import com.gent.service.ICategoryService;
import com.gent.service.IGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.gent.config.AppConfiguration.urlWriteImages;
import static com.gent.util.Constants.RU_LANG;

/**
 * Created by daria on 05.12.2016.
 */

@Controller
@ComponentScan("com.gent")
public class SitemapController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IGoodService goodService;

    @RequestMapping(value = "/{lang}/admin/getsitemap", method = RequestMethod.GET)
    public String getSitemap(ModelMap model) throws ParserConfigurationException, IOException, SAXException {
        File fXmlFile = new File(urlWriteImages + "/sitemap.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        dBuilder = dbFactory.newDocumentBuilder();


        Document doc = null;

        doc = dBuilder.parse(fXmlFile);

        doc.getDocumentElement().normalize();

        String info = doc.getDocumentElement().getNodeName();
        NodeList nList = doc.getElementsByTagName("url");
        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            info = info + "\nCurrent Element :" + nNode.getNodeName();

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;


                info = info + "\n\tloc : " + eElement.getElementsByTagName("loc").item(0).getTextContent();
                info = info + "\n\txhtml:link : " + eElement.getElementsByTagName("xhtml:link").item(0).getAttributes().getNamedItem("rel");


            }
        }

        Node urlSet = doc.getElementsByTagName("urlset").item(0);

        List<Good> listGood = goodService.getAllGoods();
        for (Good item : listGood) {
            Element url = doc.createElement("url");
            urlSet.appendChild(url);

            Element loc = doc.createElement("loc");
            loc.appendChild(doc.createTextNode("http://xn--d1acac0agfd5bxg.in.ua/uk/good/" + item.getCategory().getUaText() + "-" + item.getFirm().replaceAll(" ", "-") + "-" + item.getColor().getUaText() + "/" + item.getId()));
            url.appendChild(loc);

            Element xhtml = doc.createElement("xhtml:link");
            xhtml.setAttribute("rel", "alternate");
            xhtml.setAttribute("hreflang", RU_LANG);
            xhtml.setAttribute("href", "http://xn--d1acac0agfd5bxg.in.ua/uk/good/" + item.getCategory().getRuText() + "-" + item.getFirm().replaceAll(" ", "-") + "-" + item.getColor().getRuText() + "/" + item.getId());
            url.appendChild(xhtml);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(urlWriteImages + "/sitemap.xml"));
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        model.addAttribute("info", info);
        return "sitemap";
    }

    private static String w3cDateTime(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        String formated = df.format(date);
        return formated.substring(0, 22) + ":" + formated.substring(22);
    }

    @RequestMapping(value = "/admin/sitemap", method = RequestMethod.GET)
    public String newSitemap(ModelMap model) throws ParserConfigurationException, IOException, SAXException {

        //create document
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("urlset");
        rootElement.setAttribute("xmlns", "http://www.sitemaps.org/schemas/sitemap/0.9");
        rootElement.setAttribute("xmlns:xhtml", "http://www.w3.org/1999/xhtml");
        doc.appendChild(rootElement);

        List<Category> listCategory = categoryService.getSecondLevel();
        for (Category item : listCategory) {
            Element url = doc.createElement("url");
            rootElement.appendChild(url);

            Element loc = doc.createElement("loc");
            loc.appendChild(doc.createTextNode("http://xn--d1acac0agfd5bxg.in.ua/uk/" + URLEncoder.encode("каталог", "UTF-8") + "/" + URLEncoder.encode(item.getUaText().replaceAll(" ", "-"), "UTF-8") + "/" + item.getId()));
            url.appendChild(loc);

            Element priority = doc.createElement("priority");
            priority.appendChild(doc.createTextNode("0.7"));
            url.appendChild(priority);

            Element lastmod = doc.createElement("lastmod");
            lastmod.appendChild(doc.createTextNode(w3cDateTime(item.getData())));
            url.appendChild(lastmod);

            Element xhtml = doc.createElement("xhtml:link");
            xhtml.setAttribute("rel", "alternate");
            xhtml.setAttribute("hreflang", RU_LANG);
            xhtml.setAttribute("href", "http://xn--d1acac0agfd5bxg.in.ua/ru/" + URLEncoder.encode("каталог", "UTF-8") + "/" + URLEncoder.encode(item.getRuText().replaceAll(" ", "-"), "UTF-8") + "/" + item.getId());
            url.appendChild(xhtml);
        }


        List<Good> listGood = goodService.getAllGoods();
        for (Good item : listGood) {
            Element url = doc.createElement("url");
            rootElement.appendChild(url);

            Element loc = doc.createElement("loc");
            loc.appendChild(doc.createTextNode("http://xn--d1acac0agfd5bxg.in.ua/uk/good/" + URLEncoder.encode(item.getCategory().getUaText().replaceAll(" ", "-"), "UTF-8") + "-" + item.getFirm().replaceAll(" ", "-") + "-" + URLEncoder.encode(item.getColor().getUaText(), "UTF-8") + "/" + item.getId()));
            url.appendChild(loc);

            Element priority = doc.createElement("priority");
            priority.appendChild(doc.createTextNode("0.9"));
            url.appendChild(priority);

            Element lastmod = doc.createElement("lastmod");
            lastmod.appendChild(doc.createTextNode(w3cDateTime(item.getData())));
            url.appendChild(lastmod);

            Element xhtml = doc.createElement("xhtml:link");
            xhtml.setAttribute("rel", "alternate");
            xhtml.setAttribute("hreflang", RU_LANG);
            xhtml.setAttribute("href", "http://xn--d1acac0agfd5bxg.in.ua/ru/good/" + URLEncoder.encode(item.getCategory().getRuText().replaceAll(" ", "-"), "UTF-8") + "-" + item.getFirm().replaceAll(" ", "-") + "-" + URLEncoder.encode(item.getColor().getRuText(), "UTF-8") + "/" + item.getId());

            url.appendChild(xhtml);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(urlWriteImages + "/sitemap.xml"));
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        model.addAttribute("info", "ok");
        return "sitemap";


    }

    public static boolean addCategoryToSitemap(Category item) {
        File f = new File(urlWriteImages + "/sitemap.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(f);
            Element root = doc.getDocumentElement();

            Element url = doc.createElement("url");


            Element loc = doc.createElement("loc");
            loc.appendChild(doc.createTextNode("http://xn--d1acac0agfd5bxg.in.ua/uk/" + URLEncoder.encode("каталог", "UTF-8") + "/" + URLEncoder.encode(item.getUaText().replaceAll(" ", "-"), "UTF-8") + "/" + item.getId()));
            url.appendChild(loc);

            Element priority = doc.createElement("priority");
            priority.appendChild(doc.createTextNode("0.7"));
            url.appendChild(priority);

            Element lastmod = doc.createElement("lastmod");
            lastmod.appendChild(doc.createTextNode(w3cDateTime(item.getData())));
            url.appendChild(lastmod);

            Element xhtml = doc.createElement("xhtml:link");
            xhtml.setAttribute("rel", "alternate");
            xhtml.setAttribute("hreflang", RU_LANG);
            xhtml.setAttribute("href", "http://xn--d1acac0agfd5bxg.in.ua/ru/" + URLEncoder.encode("каталог", "UTF-8") + "/" + URLEncoder.encode(item.getRuText().replaceAll(" ", "-"), "UTF-8") + "/" + item.getId());
            url.appendChild(xhtml);


            root.appendChild(url);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(urlWriteImages + "/sitemap.xml"));
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }


        return true;
    }

   public static boolean addGoodToSitemap(Good item) {
        File f = new File(urlWriteImages + "/sitemap.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(f);
            Element root = doc.getDocumentElement();

            Element url = doc.createElement("url");

            Element loc = doc.createElement("loc");
            loc.appendChild(doc.createTextNode("http://xn--d1acac0agfd5bxg.in.ua/uk/good/" + URLEncoder.encode(item.getCategory().getUaText().replaceAll(" ", "-"), "UTF-8") + "-" + item.getFirm().replaceAll(" ", "-") + "-" + URLEncoder.encode(item.getColor().getUaText(), "UTF-8") + "/" + item.getId()));
            url.appendChild(loc);

            Element priority = doc.createElement("priority");
            priority.appendChild(doc.createTextNode("0.9"));
            url.appendChild(priority);

            Element lastmod = doc.createElement("lastmod");
            lastmod.appendChild(doc.createTextNode(w3cDateTime(item.getData())));
            url.appendChild(lastmod);

            Element xhtml = doc.createElement("xhtml:link");
            xhtml.setAttribute("rel", "alternate");
            xhtml.setAttribute("hreflang", RU_LANG);
            xhtml.setAttribute("href", "http://xn--d1acac0agfd5bxg.in.ua/ru/good/" + URLEncoder.encode(item.getCategory().getRuText().replaceAll(" ", "-"), "UTF-8") + "-" + item.getFirm().replaceAll(" ", "-") + "-" + URLEncoder.encode(item.getColor().getRuText(), "UTF-8") + "/" + item.getId());

            url.appendChild(xhtml);

            root.appendChild(url);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(urlWriteImages + "/sitemap.xml"));
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }


        return true;
    }


}
