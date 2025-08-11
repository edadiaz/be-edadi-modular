package com.az.edadi.service.adapter.interest;

import com.az.edadi.dal.entity.user.Interest;
import com.az.edadi.model.response.interest.InterestResponse;
import com.az.edadi.service.service.Translator;
import org.springframework.stereotype.Service;

@Service
public class InterestAdapter {

    public InterestResponse map(Interest interest){
        return new InterestResponse(interest.getId(),getName(interest));
    }

    private String getName(Interest interest) {
        var currentLang = Translator.getCurrentLanguage();
        return  switch (currentLang){
            case "en" -> interest.getNameEn();
            case "az" -> interest.getNameAz();
            case "ru" -> interest.getNameRu();
            default ->  interest.getNameEn();
        };
    }
}
