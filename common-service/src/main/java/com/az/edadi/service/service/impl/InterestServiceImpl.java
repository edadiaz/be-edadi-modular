package com.az.edadi.service.service.impl;

import com.az.edadi.dal.repository.user.InterestRepository;
import com.az.edadi.model.response.interest.InterestResponse;
import com.az.edadi.service.service.InterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterestServiceImpl implements InterestService {

    private final InterestRepository interestRepository;

    @Override
    public List<InterestResponse> getAllInterests() {
        return interestRepository.findAll().stream().map(interest -> new InterestResponse(
                interest.getId(),
                interest.getNameAz())).toList();
    }


}
