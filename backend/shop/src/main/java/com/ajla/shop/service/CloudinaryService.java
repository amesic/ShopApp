package com.ajla.shop.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService implements ICloudinaryService {

    private final Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "auctionabh",
            "api_key", "619132779487451",
            "api_secret", "I-zcYsn5xE8yNe851JBeF9Hamco"));

    @Override
    public String saveItemImage(String urlImage) throws IOException {
        Map params = ObjectUtils.asMap();
        Map uploadResult = cloudinary.uploader()
                .upload(urlImage, params);
        return (String) uploadResult.get("url");
    }
}
