package com.javaweb.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UploadFileUtils {

    @Value("${dir.default}")
    private String dirDefault;

    public void writeOrUpdate(String path, byte[] bytes) {
    	  if (bytes == null || path == null) {
              // Handle invalid input, perhaps throw an IllegalArgumentException
              throw new IllegalArgumentException("Invalid input: bytes or path is null");
          }

          // Tạo đường dẫn đầy đủ
          String fullPath = dirDefault + File.separator + path;

          // Kiểm tra và tạo thư mục nếu cần
          createParentDirectories(fullPath);

          // Ghi dữ liệu vào tệp tin
          try (FileOutputStream fop = new FileOutputStream(fullPath)) {
              fop.write(bytes);
          } catch (IOException e) {
              e.printStackTrace();
          }
    }
    
    private void createParentDirectories(String filePath) {
        File file = new File(filePath);
        File parentDir = file.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            // Tạo các thư mục cha nếu chưa tồn tại
            if (!parentDir.mkdirs()) {
                // Handle the case where mkdirs fails (possibly due to permissions)
                throw new RuntimeException("Failed to create parent directories for: " + filePath);
            }
        }
    }
}
