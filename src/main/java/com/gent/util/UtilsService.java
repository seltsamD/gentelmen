package com.gent.util;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import static com.gent.config.AppConfiguration.urlWriteImages;

/**
 * Created by daria on 25.11.2017.
 */
@Service
public class UtilsService {
    public String getSitemap() throws IOException {
        File xmlFile = new File(urlWriteImages + "/sitemap.xml");
        return FileUtils.readFileToString(xmlFile, Charset.forName("UTF-8"));
    }

    public String getRobotsTxt() throws IOException {
        File robots = new File(urlWriteImages + "/robots.txt");
        return FileUtils.readFileToString(robots, Charset.forName("UTF-8"));
    }
}
