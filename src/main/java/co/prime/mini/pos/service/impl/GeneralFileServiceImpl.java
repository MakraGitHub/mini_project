package co.prime.mini.pos.service.impl;

import co.prime.mini.pos.service.GeneralFileService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class GeneralFileServiceImpl implements GeneralFileService {
    @Override
    public String generalFile(String originalFile) {

        LocalDateTime now = LocalDateTime.now();
        String timeStamp = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        int lastIndex = originalFile.lastIndexOf(".");
         if(lastIndex == -1){
             return timeStamp;
         }
         String fileExtension = originalFile.substring(lastIndex);

        return fileExtension+timeStamp;
    }
}
