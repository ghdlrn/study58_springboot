package com.membertest.repository;

import com.membertest.entity.Member;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testSample() {
        log.info("memberRepository :{}", memberRepository);
    }

    @Test
    public void memberInsert() {    //추가

        for(int i=0; i<10; i++) {
            Member member = Member.builder()
                    .name("까망"+i)
                    .age(5+i)
                    .phone("000-1111-2222")
                    .address("안산시 상록구"+i)
                    .build();
            memberRepository.save(member);
        }
    }

    @Test
    public void memberGetOne() {    //단건 조회
        Long memberId = 1L;
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));
        log.info(member);
    }

    @Test
    public void memberList() {  //전체데이터 조회

        List<Member> lists = memberRepository.findAll();
        lists.forEach(System.out::println);
    }

    @Test
    public void memberPaging() {    //페이징처리
        Pageable pageable = PageRequest.of(1, 5);
        Page<Member> result = memberRepository.findAll(pageable);

        log.info("getTotalElements : " + result.getTotalElements());
        log.info("getTotalPages : " + result.getTotalPages());
        log.info("getSize : " + result.getSize());
        log.info("getContent : " + result.getContent());
        log.info("-----------------------------------------------");
        result.stream().forEach(log::info);
    }

    @Test
    public void memberDelete() { //삭제
        Long memberId = 13L;
        memberRepository.deleteById(memberId);
    }

    @Test
    public void memberUpdate() {    //수정
        Member member = Member.builder()
                .id(4L)
                .name("뽀양")
                .age(7)
                .phone("000-2222-3333")
                .address("경기도 수원시 팔달구")
                .build();
        memberRepository.save(member);
    }
}