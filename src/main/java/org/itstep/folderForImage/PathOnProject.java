package org.itstep.folderForImage;

import jakarta.servlet.ServletContext;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Path;

public class PathOnProject {
    public Path getUploadsDir(ServletContext context) throws URISyntaxException, MalformedURLException {
        Path pathOfContext = Path.of(context.getResource("").toURI());
        return Path.of("e:/JAVA/Projects/GitHub Homeworks/Lesson080/src/main/webapp/resources/images");
    }
}
