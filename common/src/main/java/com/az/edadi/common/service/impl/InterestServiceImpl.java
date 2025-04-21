package com.az.edadi.common.service.impl;

import com.az.edadi.common.model.InterestResponse;
import com.az.edadi.common.service.InterestService;
import com.az.edadi.dal.repository.user.InterestRepository;
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
