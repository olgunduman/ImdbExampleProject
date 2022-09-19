package com.example.bootcampodev.domain.member;

import com.example.bootcampodev.domain.movie.MovieServiceImpl;
import com.example.bootcampodev.domain.port.MemberPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {

    MemberServiceImpl memberService;
    @Mock
    private MemberPersistencePort memberPersistencePort;

    @BeforeEach
    void setUp() {
       memberService = new MemberServiceImpl(memberPersistencePort);
    }

    @Test
    void should_member_retrieve(){
        //given
        Member member = Member.builder()
                .id(1L)
                .fullName("olgun duman")
                .birthYear(1997)
                .phone("05374172301")
                .build();

        when(memberPersistencePort.retrieve(1L)).thenReturn(member);

        //when
        Member retrieveMember = memberService.retrieve(1L);

        //then
        assertThat(retrieveMember.getId()).isEqualTo(1L);

        verifyNoMoreInteractions(memberPersistencePort);
    }

    @Test
    void should_member_create(){
        //given
        Member member = Member.builder()
                .id(1L)
                .fullName("olgun duman")
                .birthYear(1997)
                .phone("05374172301")
                .build();

        when(memberPersistencePort.save(member)).thenReturn(1L);

        //when
        var id = memberService.create(member);

        //then
        assertThat(id).isEqualTo(1L);
        assertThat(member.getFullName()).isEqualTo("olgun duman");

    }

}