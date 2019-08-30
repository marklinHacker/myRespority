package com.leyou.upload.service.impl;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.upload.service.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * description: UploadServiceImpl <br>
 * date: 2019/8/28 9:54 <br>
 * author: 涛子 <br>
 * version: 1.0 <br>
 */
@Service
public class UploadServiceImpl implements UploadService {
    private static final List<String> suffixes = Arrays.asList("image/png", "image/jpeg", "image/bmp");
    @Override
    public String upload(MultipartFile file) {
        //校验文件的格式类型
        if (!suffixes.contains(file.getContentType())) {
            throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
        }
        //校验通过
        // 2)校验图片内容
        BufferedImage image = null;
        try {
            image = ImageIO.read(file.getInputStream());
        } catch (IOException e) {
            throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
        }
        if (image == null) {
            throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
        }
        // 2、保存图片
        // 2.1、生成保存目录,保存到nginx所在目录的html下，这样可以直接通过nginx来访问到
        File dir = new File("D:\\Program Files\\nginx-1.12.2\\html\\");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            // 2.2、保存图片
            file.transferTo(new File(dir, file.getOriginalFilename()));

            // 2.3、拼接图片地址
            return "http://image.leyou.com/" + file.getOriginalFilename();
        } catch (Exception e) {
            throw new LyException(ExceptionEnum.FILE_UPLOAD_ERROR);
        }
    }
}
