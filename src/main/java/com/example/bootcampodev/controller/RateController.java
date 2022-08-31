package com.example.bootcampodev.controller;

import com.example.bootcampodev.dto.request.rate.RateRequest;
import com.example.bootcampodev.dto.response.rate.RateResponse;
import com.example.bootcampodev.service.rate.Rate;
import com.example.bootcampodev.service.rate.RateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rates")
public class RateController {

    private final RateService rateService;

    public RateController(RateService rateService) {
        this.rateService = rateService;
    }


    @PostMapping("/create")
    public void create(@RequestBody RateRequest rateRequest){

        Rate rate = rateRequest.convertToRate();
        rateService.create(rate);
    }

    @GetMapping("/movie/{movieId}")
    public List<RateResponse> retrieveByMovie(@PathVariable Long movieId){

        List<Rate> rateList = rateService.retrieveByMovieId(movieId);
        return RateResponse.convertFromRate(rateList);

    }

    @GetMapping("/member/{memberId}")
    public List<RateResponse> retrieveByMember(@PathVariable Long memberId){
        List<Rate> rateList = rateService.retrieveByMemberId(memberId);
        return RateResponse.convertFromRate(rateList);
    }
}
