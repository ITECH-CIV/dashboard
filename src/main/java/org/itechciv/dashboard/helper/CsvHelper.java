package org.itechciv.dashboard.helper;

import org.springframework.web.multipart.MultipartFile;

public class CsvHelper {

    public static String TYPE = "text/csv";
    static String[] HEADERs = { "Id", "Title", "Description", "Published" };

    
    @SuppressWarnings("null")
    public static boolean hasCSVFormat(MultipartFile file) {
    if (TYPE.equals(file.getContentType())
    		|| file.getContentType().equals("application/vnd.ms-excel")) {
      return true;
    }

    return false;
  }


}
