package com.example.bootcampodev.adapter.rest.member;

import com.example.bootcampodev.domain.member.Member;
import com.example.bootcampodev.domain.member.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/create")
    public Long create(@RequestBody MemberRequest memberRequest){
        return memberService.create(memberRequest.convertToMember());

    }
    @GetMapping("/{id}")
    public MemberResponse retrieve(@PathVariable Long id) throws InterruptedException {

        Member member = memberService.retrieve(id);
       return MemberResponse.convertToMember(member);
    }

    @GetMapping("/getAll")
    public List<MemberResponse> getAll(){
        List<Member> member = memberService.getAll();
      return   MemberResponse.convertToMemberList(member);
    }
}
