package com.kungfu.apkfwebapp.bootstrap;

import com.kungfu.apkfwebapp.domain.Member;
import com.kungfu.apkfwebapp.domain.Phase;
import com.kungfu.apkfwebapp.repositories.ExamRepository;
import com.kungfu.apkfwebapp.repositories.MemberRepository;
import com.kungfu.apkfwebapp.repositories.TrainingGroupRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final MemberRepository memberRepository;
    private final TrainingGroupRepository trainingGroupRepository;
    private final ExamRepository examRepository;

    public Bootstrap(MemberRepository memberRepository, TrainingGroupRepository trainingGroupRepository, ExamRepository examRepository) {
        this.memberRepository = memberRepository;
        this.trainingGroupRepository = trainingGroupRepository;
        this.examRepository = examRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadMembers();

    }

    private void loadMembers() {
        Member member1 = new Member();
        member1.setFirstName("Jake");
        member1.setLastName("Shiba");
        member1.setPhase(Phase.First);
        memberRepository.save(member1);
    }
}
